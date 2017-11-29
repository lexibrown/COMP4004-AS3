package com.lexi.comp4004.server;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.lexi.comp3601.data.Message;
import com.lexi.comp3601.data.Notification;
import com.lexi.comp3601.utils.JsonUtil;
import com.lexi.comp3601.utils.NotificationDatabaseUtil;
import com.lexi.comp3601.utils.TokenUtil;
import com.lexi.comp3601.utils.Variables.Response;

@ServerEndpoint("/notification")
public class Connection {
	
	private static String SERVICE = "MSG";
	
	private static final String TOKEN = "token";
	private static final String CONNECT = "connect";
	private static final String DISCONNECT = "disconnect";
	private static final String MESSAGE = "message";
	private static final String NOTIFY = "notify";
	
	private static Map<String, Session> userSessions = Collections.synchronizedMap(new HashMap<String, Session>());

	@SuppressWarnings("unchecked")
	@OnMessage
	public void onMessage(String message, Session session) throws IOException, InterruptedException {
		System.out.println("User input: " + message);
		
		Map<String, Object> request = null;
		try {
			request = JsonUtil.parse(message, HashMap.class);
		} catch (Exception e) {
			invalidRequest(session);
			return;
		}
		
		if (!request.containsKey(TOKEN)) {
			invalidRequest(session);
			return;
		}
		
		String token = request.get(TOKEN).toString();
		Response res = TokenUtil.verify(token);
		if (!res.equals(Response.OK)) {
			try {
				session.getBasicRemote().sendText(JsonUtil.handleError(res, SERVICE));
			} catch (Exception e) {
				invalidRequest(session);
			}
			return;
		}
		
		if (request.containsKey(CONNECT)) { // {"connect":"user1", "token":"user1"}
			System.out.println("Add client");
			String user = TokenUtil.pullUsername(token);
			userSessions.put(user, session);
			if (NotificationDatabaseUtil.hasNotifications(user)) {
				List<Notification> notifications = NotificationDatabaseUtil.getNotifications(user);
				String jsonMessage = null;
				try {
					jsonMessage = JsonUtil.makeJson(NOTIFICATIONS, JsonUtil.stringify(notifications));
				} catch (Exception e) {
					invalidRequest(session);
					return;
				}
				sendMessage(user, jsonMessage);
			}
		} else if (request.containsKey(DISCONNECT)) { // {"disconnect":"user1"}
			System.out.println("Remove client");
			userSessions.remove(request.get(DISCONNECT).toString());
			
		} else if (request.containsKey(MESSAGE)) {  // {"message":{"from":"user2","message":"hello from user2","to":"user1"}}
			System.out.println("Chat message");
			Message chat = JsonUtil.parse(request.get(MESSAGE).toString(), Message.class);
			if (chat == null) {
				invalidRequest(session);
				return;
			}
			
			if (isConnected(chat.getTo())) {
				String jsonMessage = null;
				try {
					jsonMessage = JsonUtil.makeJson(MESSAGE, JsonUtil.stringify(chat));
				} catch (Exception e) {
					invalidRequest(session);
					return;
				}
				// TODO returns false when sending failed
				sendMessage(chat.getTo(), jsonMessage);
			} else {
				NotificationDatabaseUtil.addMessage(chat);
			}
		}
	}

	private boolean sendMessage(String user, String message) {
		try {
			userSessions.get(user).getBasicRemote().sendText(message);
		} catch (IOException e) {
			return false;
		}
		return true;
	}
		
	private void invalidRequest(Session session) throws IOException, InterruptedException {
		try {
			session.getBasicRemote().sendText(JsonUtil.errorJson("Invalid request."));
		} catch (Exception e1) {
			session.getBasicRemote().sendText("Invalid request.");
		}
	}
	
	public static boolean isConnected(String user) {
		return userSessions.containsKey(user);
	}

	public static boolean sendNotification(Notification notification) {
		String jsonMessage = null;
		try {
			jsonMessage = JsonUtil.makeJson(NOTIFICATION, JsonUtil.stringify(notification));
			userSessions.get(notification.getUserid()).getBasicRemote().sendText(jsonMessage);
		} catch (IOException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@OnOpen
	public void onOpen(Session session) {
		// do nothing
		System.out.println("onOpen");
	}

	@OnClose
	public void onClose(Session session) {
		System.out.println("onClose");
		for (String user : userSessions.keySet()) {
			if (userSessions.get(user).equals(session)) {
				userSessions.remove(user);
				break;
			}
		}
	}
}
