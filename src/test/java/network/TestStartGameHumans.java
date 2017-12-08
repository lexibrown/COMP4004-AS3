package network;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.ClientBuilder;

import org.junit.Before;
import org.junit.Test;

import com.lexi.comp4004.common.game.util.Config.Endpoint;
import com.lexi.comp4004.common.game.util.Config.Key;
import com.lexi.comp4004.common.template.ClientSetUp;
import com.lexi.comp4004.common.template.SetUp;
import com.lexi.comp4004.server.util.JsonUtil;
import com.lexi.comp4004.server.util.Variables;

public class TestStartGameHumans extends TestNetworkFramework {

	private String token1;
	private String token2;

	@Before
	public void setUp() {
		client = ClientBuilder.newClient();
		target = client.target(Variables.baseUri);
	}

	@Test
	public void testStartGameOnlyHumans() {
		Map<String, Object> response = null;
		Map<String, Object> params = null;
		String name1 = "testStartGameOnlyHumans1";
		String name2 = "testStartGameOnlyHumans2";

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

			// set up game
			SetUp setUp = new ClientSetUp();
			setUp.setNumPlayers(2);
			
			reloadUri();
			target = target.path(Endpoint.Lobby.SETUPGAME);
			params = new HashMap<String, Object>();
			params.put(Key.TOKEN, token1);
			params.put(Key.SETUP, JsonUtil.stringify(setUp));
			response = postRequest(JsonUtil.stringify(params));
			assertTrue(response.containsKey(Key.MESSAGE));
			assertFalse(response.containsKey(Key.ERROR));
			
			// join game
			reloadUri();
			target = target.path(Endpoint.Lobby.JOINGAME);
			params = new HashMap<String, Object>();
			params.put(Key.TOKEN, token2);
			response = postRequest(JsonUtil.stringify(params));
			assertTrue(response.containsKey(Key.MESSAGE));
			assertFalse(response.containsKey(Key.ERROR));
			
			// start game
			reloadUri();
			target = target.path(Endpoint.Lobby.STARTGAME);
			params = new HashMap<String, Object>();
			params.put(Key.TOKEN, token1);
			response = postRequest(JsonUtil.stringify(params));
			assertTrue(response.containsKey(Key.MESSAGE));
			assertFalse(response.containsKey(Key.ERROR));

			// disconnect
			reloadUri();
			target = target.path(Endpoint.Disconnect.DISCONNECT);
			params = new HashMap<String, Object>();
			params.put(Key.TOKEN, token2);
			response = postRequest(JsonUtil.stringify(params));
			assertTrue(response.containsKey(Key.MESSAGE));
			assertFalse(response.containsKey(Key.ERROR));

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
