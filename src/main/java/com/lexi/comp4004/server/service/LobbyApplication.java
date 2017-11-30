package com.lexi.comp4004.server.service;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lexi.comp4004.server.Lobby;
import com.lexi.comp4004.server.util.JsonUtil;

@Path("/lobby")
@Produces(MediaType.APPLICATION_JSON)
public class LobbyApplication implements Application {

	public static final String SERVICE = "LOBY";
	
	public static final String STATUS = "status";
	public static final String USERS = "users";
		
	public static final String SETUPGAME = "setupgame";
	public static final String JOINGAME = "joingame";
	public static final String STARTGAME = "startgame";
	
	public static final String LOBBY = "lobby";
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String defaultEndpoint() {
		return "Lobby service.";
	}
	
	@POST
	@Path(USERS)
	public String getUsers(HashMap<String, Object> params) {
		try {
			if (!params.containsKey(TOKEN)) {
				return JsonUtil.errorJson(SERVICE + "-1000", "No token provided.");
			} else if (!Lobby.getInstance().verifyUser(params.get(TOKEN).toString())) {
				return JsonUtil.errorJson(SERVICE + "-1001", "Invalid token.");
			}
			return JsonUtil.makeJson(LOBBY, JsonUtil.stringify(Lobby.getInstance().getUsers()));
		} catch (Exception e) {
			return JsonUtil.fail(e);
		}
	}
	
	/*
	@GET
	@Path(USERS)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String getUsersHtml(@QueryParam(TOKEN) String token) {
		try {
			if (token.isEmpty()) {
				return JsonUtil.errorJson(SERVICE + "-1000", "No token provided.");
			}
			
			List<String> users = Lobby.getInstance().getUsers();
			
			if (users.isEmpty()) { // this should never display
				return "<html><title>COMP4004-Poker-Lobby</title><body><h1>No active users in the lobby.</h1></body></html>";
			}
			
			String html = "<html><title>COMP4004-Poker-Lobby</title><body><h1>Users:</h1><h2>";
			for (String u : users) {
				html += u + "\n";
			}
			
			return html + "</h2></body></html>";

		} catch (Exception e) {
			return JsonUtil.fail(e);
		}
	}
	*/
	
	@GET
	@Path(STATUS)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String lobbyStatus(HashMap<String, Object> params) {
		try {
			if (!params.containsKey(TOKEN)) {
				return JsonUtil.errorJson(SERVICE + "-2000", "No token provided.");
			} else if (!Lobby.getInstance().verifyUser(params.get(TOKEN).toString())) {
				return JsonUtil.errorJson(SERVICE + "-2001", "Invalid token.");
			}			
			return JsonUtil.makeJson(STATUS, Lobby.getInstance().isGameInSession());
		} catch (Exception e) {
			return JsonUtil.fail(e);
		}
	}
	
	/*
	@GET
	@Path(STATUS)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String lobbyStatusHtml(@QueryParam(TOKEN) String token) {
		try {
			if (token.isEmpty()) {
				return JsonUtil.errorJson(SERVICE + "-1000", "No token provided.");
			}
			
			if (Lobby.getInstance().isGameInSession()) {
				return "<html><title>COMP4004-Poker-Lobby</title><body><h1>A game is currently in session.</h1></body></html>";
			}
			return "<html><title>COMP4004-Poker-Lobby</title><body><h1>No game currently in session.</h1></body></html>";
		} catch (Exception e) {
			return JsonUtil.fail(e);
		}
	}
	*/
	
	@POST
	@Path(SETUPGAME)
	public String setupGame(HashMap<String, Object> params) {
		try {
			if (!params.containsKey(TOKEN)) {
				return JsonUtil.errorJson(SERVICE + "-3000", "No token provided.");
			} else if (!Lobby.getInstance().verifyUser(params.get(TOKEN).toString())) {
				return JsonUtil.errorJson(SERVICE + "-3001", "Invalid token.");
			}
			//TODO
			return JsonUtil.errorJson(SERVICE + "-3001", "Invalid token.");
		} catch (Exception e) {
			return JsonUtil.fail(e);
		}
	}
	
	@GET
	@Path(STARTGAME)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String startGame(HashMap<String, Object> params) {
		try {
			if (!params.containsKey(TOKEN)) {
				return JsonUtil.errorJson(SERVICE + "-4000", "No token provided.");
			} else if (!Lobby.getInstance().verifyUser(params.get(TOKEN).toString())) {
				return JsonUtil.errorJson(SERVICE + "-4001", "Invalid token.");
			}
			//TODO
			return JsonUtil.errorJson(SERVICE + "-4001", "Invalid token.");
		} catch (Exception e) {
			return JsonUtil.fail(e);
		}
	}
	
}
