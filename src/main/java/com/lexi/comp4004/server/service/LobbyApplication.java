package com.lexi.comp4004.server.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.lexi.comp4004.server.Lobby;
import com.lexi.comp4004.server.util.JsonUtil;

@Path("/lobby")
@Produces(MediaType.APPLICATION_JSON)
public class LobbyApplication {

	public static final String SERVICE = "LOBY";
	
	public static final String STATUS = "status";
	public static final String USERS = "users";
	public static final String DISCONNECT = "disconnect";
	
	public static final String LOBBY = "lobby";
	public static final String TOKEN = "token";

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String defaultEndpoint() {
		return "Lobby service.";
	}
	
	@GET
	@Path(USERS)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String getUsers(@QueryParam(TOKEN) String token) {
		try {
			if (token.isEmpty()) {
				return JsonUtil.errorJson(SERVICE + "-1000", "No token provided.");
			}
			
			return JsonUtil.makeJson(LOBBY, JsonUtil.stringify(Lobby.getInstance().getUsers()));
		} catch (Exception e) {
			return JsonUtil.fail(e);
		}
	}
	
	
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
	
	@GET
	@Path(STATUS)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String lobbyStatus(@QueryParam(TOKEN) String token) {
		try {
			if (token.isEmpty()) {
				return JsonUtil.errorJson(SERVICE + "-1000", "No token provided.");
			}
			
			return JsonUtil.makeJson(STATUS, Lobby.getInstance().isGameInSession());
		} catch (Exception e) {
			return JsonUtil.fail(e);
		}
	}
	
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
