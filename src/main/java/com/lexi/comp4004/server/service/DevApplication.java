package com.lexi.comp4004.server.service;

import java.util.HashMap;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.lexi.comp4004.game.Poker;
import com.lexi.comp4004.server.Lobby;
import com.lexi.comp4004.server.util.GameController;
import com.lexi.comp4004.server.util.JsonUtil;
import com.lexi.comp4004.server.util.TokenUtil;
import com.lexi.comp4004.server.util.Variables;

@Path("/dev")
@Produces(MediaType.APPLICATION_JSON)
public class DevApplication {

	public static final String SERVICE = "DEV";
	
	public static final String TEST = "test";	
	public static final String DEV = "dev";
	public static final String ADMIN = "admin";	
	public static final String PASSWORD = "password";
	
	public static final String TOKEN = "token";
	public static final String GAME = "game";

	@POST
	public String dev(HashMap<String, Object> params, @QueryParam(TOKEN) String token) {
		try {
			if (token.isEmpty()) {
				return JsonUtil.errorJson(SERVICE + "-5000", "No token provided.");
			} else if (!Lobby.getInstance().verifyUser(token)) {
				return JsonUtil.errorJson(SERVICE + "-5001", "Invalid token.");
			} else if (!params.containsKey(ADMIN) || !params.containsKey(PASSWORD)) {
				return JsonUtil.errorJson(SERVICE + "-5000", "Required parameters not found.");
			}
			
			if (Variables.ADMIN.equals(params.get(ADMIN)) && Variables.ADMIN_PASSWORD.equals(params.get(PASSWORD))) {
				return JsonUtil.makeJson(TOKEN, TokenUtil.encypt(Variables.ADMIN, Variables.ADMIN_PASSWORD));
			}
			return JsonUtil.errorJson(SERVICE + "-5001", "Invalid parameters.");
		} catch (Exception e) {
			return JsonUtil.fail(e);
		}
	}
	
	@POST
	@Path(TEST)
	public String test(HashMap<String, Object> params, @QueryParam(TOKEN) String adminToken) {
		try {
			if (adminToken.isEmpty()) {
				return JsonUtil.errorJson(SERVICE + "-5000", "No token provided.");
			} else if (!params.containsKey(GAME)) {
				return JsonUtil.errorJson(SERVICE + "-5001", "Invalid parameters.");
			}
			
			// TODO
			Poker p = (Poker) params.get(GAME);
			if (p == null) {
				return JsonUtil.errorJson(SERVICE + "-5001", "Invalid parameters.");
			}
			
			GameController.getInstance().setGame(p);
			return JsonUtil.makeMessage("Successfully set up game.");
		} catch (Exception e) {
			return JsonUtil.fail(e);
		}
	}
	
}
