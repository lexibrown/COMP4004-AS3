package com.lexi.comp4004.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lexi.comp4004.common.game.data.Card;
import com.lexi.comp4004.server.util.JsonUtil;
import com.lexi.comp4004.server.util.TokenUtil;
import com.lexi.comp4004.server.util.Variables;

public class AIConnection {

	private static Client client = ClientBuilder.newClient();
	private static WebTarget target = client.target(Variables.baseUri);

	private static void reloadUri() {
		target = null;
		target = client.target(Variables.baseUri);
	}

	public static void sendKeep(String user) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					//Thread.sleep(Variables.MED_DELAY);
					
					reloadUri();
					Map<String, Object> params = new HashMap<String, Object>();
					params.put(Variables.COMP, user);
					params.put(Variables.COMP_TOKEN, constructToken());

					target = target.path("/ai/keephand");
					sendRequest(JsonUtil.stringify(params));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public static void sendSwap(String user, List<Card> cards) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					//Thread.sleep(Variables.MED_DELAY);
					
					reloadUri();
					Map<String, Object> params = new HashMap<String, Object>();
					params.put(Variables.COMP, user);
					params.put(Variables.COMP_TOKEN, constructToken());
					params.put(Variables.CARDS, cards);

					target = target.path("/ai/swaphand");
					sendRequest(JsonUtil.stringify(params));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private static void sendRequest(String input) {
		Response response = target.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(input, MediaType.APPLICATION_JSON), Response.class);

		System.out.println(response);
		
		//System.out.println(response.readEntity(String.class));
		System.out.println(response.readEntity(Map.class));
		
		if (response.getStatus() == 200) {
			System.out.println("post success");
		}
	}
	
	private static String constructToken() throws Exception {
		return TokenUtil.encypt(Variables.COMPUTER, Variables.COMP_PASSWORD);
	}

}