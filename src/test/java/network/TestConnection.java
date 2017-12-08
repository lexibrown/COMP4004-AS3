package network;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.ClientBuilder;

import org.junit.Before;
import org.junit.Test;

import com.lexi.comp4004.common.game.util.Config.Endpoint;
import com.lexi.comp4004.common.game.util.Config.Key;
import com.lexi.comp4004.server.util.JsonUtil;
import com.lexi.comp4004.server.util.Variables;

public class TestConnection extends TestNetworkFramework {

	private String token1;
	private String token2;

	@Before
	public void setUp() {
		client = ClientBuilder.newClient();
		target = client.target(Variables.baseUri);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testConnect() {
		Map<String, Object> response = null;
		Map<String, Object> params = null;
		String name1 = "testConnect1";
		String name2 = "testConnect2";

		try {
			// connect
			reloadUri();
			target = target.path(Endpoint.Connect.CONNECT);
			response = connectRequest(name1);
			assertTrue(response.containsKey(Key.TOKEN));
			token1 = response.get(Key.TOKEN).toString();

			// connect
			reloadUri();
			target = target.path(Endpoint.Connect.CONNECT);
			response = connectRequest(name2);
			assertTrue(response.containsKey(Key.TOKEN));
			token2 = response.get(Key.TOKEN).toString();

			// lobby
			reloadUri();
			target = target.path(Endpoint.Lobby.USERS);
			params = new HashMap<String, Object>();
			params.put(Key.TOKEN, token1);
			response = postRequest(JsonUtil.stringify(params));
			assertTrue(response.containsKey(Key.LOBBY));
			List<String> users = JsonUtil.parse(response.get(Key.LOBBY).toString(), List.class);
			assertTrue(users.contains(name1));
			assertTrue(users.contains(name2));

			// lobby status
			reloadUri();
			target = target.path(Endpoint.Lobby.STATUS);
			params = new HashMap<String, Object>();
			params.put(Key.TOKEN, token2);
			response = postRequest(JsonUtil.stringify(params));
			assertTrue(response.containsKey(Key.STATUS));
			assertFalse((Boolean) response.get(Key.STATUS));

			// disconnect
			reloadUri();
			target = target.path(Endpoint.Disconnect.DISCONNECT);
			params = new HashMap<String, Object>();
			params.put(Key.TOKEN, token2);
			response = postRequest(JsonUtil.stringify(params));
			assertTrue(response.containsKey(Key.MESSAGE));
			assertFalse(response.containsKey(Key.ERROR));
			
			// lobby
			reloadUri();
			target = target.path(Endpoint.Lobby.USERS);
			params = new HashMap<String, Object>();
			params.put(Key.TOKEN, token1);
			response = postRequest(JsonUtil.stringify(params));
			users = JsonUtil.parse(response.get(Key.LOBBY).toString(), List.class);
			assertTrue(users.contains(name1));
			assertFalse(users.contains(name2));

			// reset
			this.token = token1;
			reset();
			
			// disconnect
			reloadUri();
			target = target.path(Endpoint.Disconnect.DISCONNECT);
			params = new HashMap<String, Object>();
			params.put(Key.TOKEN, token1);
			response = postRequest(JsonUtil.stringify(params));
			assertTrue(response.containsKey(Key.MESSAGE));
			assertFalse(response.containsKey(Key.ERROR));			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
