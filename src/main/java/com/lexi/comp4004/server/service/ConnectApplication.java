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
public class ConnectApplication {

	public static final String SERVICE = "CONT";
	
	public static final String CONNECT = "connect";
	public static final String DISCONNECT = "disconnect";
	
	public static final String USERID = "userid";
	public static final String TOKEN = "token";

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String defaultEndpoint() {
		return "Connection service.";
	}
	
	@GET
	@Path(CONNECT)
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
	
	@GET
	@Path(DISCONNECT)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String disconnect(@QueryParam(TOKEN) String token) {
		try {
			if (token.isEmpty()) {
				return JsonUtil.errorJson(SERVICE + "-2000", "No token provided.");
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
