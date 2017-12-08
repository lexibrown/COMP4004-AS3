package network;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import com.lexi.comp4004.common.game.util.Config.Endpoint;
import com.lexi.comp4004.common.game.util.Config.Key;
import com.lexi.comp4004.server.util.JsonUtil;
import com.lexi.comp4004.server.util.Variables;

public class TestNetworkFramework {

	protected String token;

	protected Client client;
	protected WebTarget target;

	@Test
	public void reset() {
		if (token == null || token.isEmpty()) {
			return;
		}
		Map<String, Object> params = null;
		Map<String, Object> response = null;
		try {

			// get dev token
			reloadUri();
			target = target.path(Endpoint.Dev.DEV);
			params = new HashMap<String, Object>();
			params.put(Key.TOKEN, token);
			params.put(Key.ADMIN, Variables.ADMIN);
			params.put(Key.PASSWORD, Variables.ADMIN_PASSWORD);
			response = postRequest(JsonUtil.stringify(params));
			assertTrue(response.containsKey(Key.ADMIN_TOKEN));
			String adminToken = response.get(Key.ADMIN_TOKEN).toString();

			// reset
			reloadUri();
			target = target.path(Endpoint.Dev.RESET);
			params = new HashMap<String, Object>();
			params.put(Key.TOKEN, token);
			params.put(Key.ADMIN_TOKEN, adminToken);
			response = postRequest(JsonUtil.stringify(params));
			assertTrue(response.containsKey(Key.MESSAGE));
			assertFalse(response.containsKey(Key.ERROR));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void reloadUri() {
		target = null;
		target = client.target(Variables.baseUri);
	}

	@SuppressWarnings("unchecked")
	protected Map<String, Object> connectRequest(String user) {
		Response response = target.queryParam(Key.USER, user).request(MediaType.APPLICATION_JSON).get();
		Map<String, Object> payload = response.readEntity(Map.class);
		return payload;
	}

	@SuppressWarnings("unchecked")
	protected Map<String, Object> postRequest(String input) {
		Response response = target.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(input, MediaType.APPLICATION_JSON), Response.class);
		return response.readEntity(Map.class);
	}

}
