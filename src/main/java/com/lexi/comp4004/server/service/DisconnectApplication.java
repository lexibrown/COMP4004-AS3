package com.lexi.comp4004.server.service;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lexi.comp4004.common.game.util.Config.Key;
import com.lexi.comp4004.server.Lobby;
import com.lexi.comp4004.server.util.JsonUtil;

@Path("/disconnect")
@Produces(MediaType.APPLICATION_JSON)
public class DisconnectApplication {

	public static final String SERVICE = "DISC";

	public static final String DISCONNECT = "disconnect";

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String defaultEndpoint() {
		return "Disconnection service.";
	}

	@POST
	public String disconnect(HashMap<String, Object> params) {
		try {
			if (!params.containsKey(Key.TOKEN)) {
				return JsonUtil.errorJson(SERVICE + "-2000", "No token provided.");
			}

			String token = params.get(Key.TOKEN).toString();
			if (!Lobby.getInstance().verifyUser(token)) {
				return JsonUtil.errorJson(SERVICE + "-2001", "Invalid token.");
			}

			if (Lobby.getInstance().removeUser(token)) {
				return JsonUtil.makeMessage("Successfully disconnected.");
			}
			return JsonUtil.errorJson(SERVICE + "-2001", "Invalid token.");
		} catch (Exception e) {
			return JsonUtil.fail(e);
		}
	}

}
