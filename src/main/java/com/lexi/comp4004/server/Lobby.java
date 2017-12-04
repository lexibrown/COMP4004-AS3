package com.lexi.comp4004.server;

import java.util.ArrayList;
import java.util.List;

import com.lexi.comp4004.common.template.SetUp;
import com.lexi.comp4004.server.encrypt.Token;
import com.lexi.comp4004.server.util.TokenUtil;

public class Lobby {

	private static Lobby instance;
	
	private List<Token> activeUsers;

	private Lobby() {
		activeUsers = new ArrayList<Token>();
	}
	
	public static Lobby getInstance() {
		if (instance == null) {
			instance = new Lobby();
		}
		return instance;
	}
	
	public boolean verifyUser(String token) {
		return TokenUtil.verify(token, getUsers());
	}
	
	public boolean userExists(String username) {
		for (Token t : activeUsers) {
			if (username.equals(t.getUserid())) {
				return true;
			}
		}
		return false;
	}
	
	public String addUser(String username) throws Exception {
		if (userExists(username)) {
			return null;
		}
		
		Token t = TokenUtil.constuctToken(username);
		Connection.broadcastLobby(getUsers());
		return TokenUtil.encryt(t);
	}
	
	public boolean removeUser(String token) {
		String username = TokenUtil.pullUsername(token);
		for (Token t : activeUsers) {
			if (username.equals(t.getUserid())) {
				activeUsers.remove(t);
				Connection.broadcastLobby(getUsers());
				return true;
			}
		}
		return false;
	}
	
	public boolean removeUserByName(String username) {
		for (Token t : activeUsers) {
			if (username.equals(t.getUserid())) {
				activeUsers.remove(t);
				Connection.broadcastLobby(getUsers());
				return true;
			}
		}
		return false;
	}
	
	public List<String> getUsers() {
		List<String> users = new ArrayList<String>();
		for (Token t : activeUsers) {
			users.add(t.getUserid());
		}
		return users;
	}

	public boolean isGameInSession() {
		return GameController.getInstance().isGameStarted();
	}
	
	public boolean setUpGame(String token, SetUp setup) {
		if (!verifyUser(token)) {
			return false;
		} else if (GameController.getInstance().isGameSetUp()) {
			return false;
		} else if (GameController.getInstance().isGameStarted()) {
			return false;
		}
		
		String user = TokenUtil.pullUsername(token);
		GameController.getInstance().setUpGame(setup);
		GameController.getInstance().joinGame(user);
		return true;
	}
	
	public boolean joinGame(String token) {
		if (!verifyUser(token)) {
			return false;
		} else if (!GameController.getInstance().isGameSetUp()) {
			return false;
		} else if (GameController.getInstance().isGameStarted()) {
			return false;
		}
		
		String user = TokenUtil.pullUsername(token);
		return GameController.getInstance().joinGame(user);
	}

	public boolean startGame(String token) {
		if (!verifyUser(token)) {
			return false;
		} else if (!GameController.getInstance().isGameSetUp()) {
			return false;
		} else if (GameController.getInstance().isGameStarted()) {
			return false;
		}
		
		String user = TokenUtil.pullUsername(token);
		return GameController.getInstance().startGame(user);
	}
	
}
