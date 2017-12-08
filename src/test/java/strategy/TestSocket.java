package strategy;

import java.io.IOException;
import java.net.URI;
import java.util.Stack;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import com.lexi.comp4004.common.game.util.Config;

@ClientEndpoint(configurator = AuthorizationConfigurator.class)
public class TestSocket {

	private WebSocketContainer container = ContainerProvider.getWebSocketContainer();
	private Session session = null;

	private Stack<String> messages = new Stack<String>();

	public Session getSession() {
		return session;
	}

	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
	}

	@OnClose
	public void onClose(Session session) {
	}

	@OnMessage
	public void onMessage(String message) {
		messages.add(message);
	}

	public Stack<String> getMessages() {
		return messages;
	}

	public void connectToWebSocket() {
		try {
			URI uri = URI.create("ws://" + Config.DEFAULT_HOST + Config.BASE_PATH + Config.WEB_SOCKET_PATH);
			session = container.connectToServer(this, uri);
		} catch (DeploymentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void disconnectFromWebSocket() {
		try {
			if (session != null) {
				session.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}