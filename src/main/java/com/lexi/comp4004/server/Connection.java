package com.lexi.comp4004.server;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.lexi.comp4004.common.game.data.ClientPoker;
import com.lexi.comp4004.common.game.data.Result;
import com.lexi.comp4004.common.game.util.Config.Key;
import com.lexi.comp4004.server.util.JsonUtil;
import com.lexi.comp4004.server.util.TokenUtil;

@ServerEndpoint(value = "/ws")
public class Connection {

	private static Map<String, Session> userSessions = Collections.synchronizedMap(new HashMap<String, Session>());

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) throws Exception {
		
		Map<String, Object> params = config.getUserProperties();
		if (!params.containsKey(Key.TOKEN)) {
			session.getBasicRemote().sendText(JsonUtil.errorJson("Invalid token."));
			return;
		}
		String token = params.get(Key.TOKEN).toString();
		
		System.out.println("onOpen");
		if (Lobby.getInstance().verifyUser(token)) {
			userSessions.put(TokenUtil.pullUsername(token), session);
			session.getBasicRemote().sendText(JsonUtil.makeMessage("Successfully connected to websocket."));
		} else {
			session.getBasicRemote().sendText(JsonUtil.errorJson("Invalid. Must connect to server first."));
		}
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

	public static boolean isConnected(String user) {
		return userSessions.containsKey(user);
	}

	private static void sendMessage(String user, String message) {
		try {
			userSessions.get(user).getBasicRemote().sendText(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void broadcastLobby(List<String> users) {
		try {
			String message = JsonUtil.makeJson(Key.LOBBY, users);
			for (String user : userSessions.keySet()) {
				sendMessage(user, message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void broadcastStartGame(List<String> users) {
		try {
			String message = JsonUtil.makeJson(Key.GAMESTARTED, true);
			for (String user : userSessions.keySet()) {
				sendMessage(user, message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void broadcastGame(String user, ClientPoker game) {
		try {
			sendMessage(user, JsonUtil.makeJson(Key.GAME, game));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void broadcastEndGame(String user, List<Result> results) {
		try {
			sendMessage(user, JsonUtil.makeJson(Key.RESULTS, results));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
