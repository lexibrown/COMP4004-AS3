package com.lexi.comp4004.server.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.lexi.comp4004.server.Lobby;
import com.lexi.comp4004.server.util.JsonUtil;

@Path("/connect")
@Produces(MediaType.APPLICATION_JSON)
public class ConnectApplication implements Application {

	public static final String SERVICE = "CONT";

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String defaultEndpoint() {
		return "Connection service.";
	}

	@GET
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String connect(@QueryParam(USERID) String userid) {
		try {
			if (userid.isEmpty()) {
				return JsonUtil.errorJson(SERVICE + "-1000", "No username provided.");
			}

			String token = Lobby.getInstance().addUser(userid);
			if (token == null) {
				return JsonUtil.errorJson(SERVICE + "-1001", "Username is already in use.");
			}
			return JsonUtil.makeJson(TOKEN, token);
		} catch (Exception e) {
			return JsonUtil.fail(e);
		}
	}

}
