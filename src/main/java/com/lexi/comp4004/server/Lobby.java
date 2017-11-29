package com.lexi.comp4004.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lexi.comp4004.encrypt.Token;
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
		return TokenUtil.encryt(t);
	}
	
	public boolean removeUser(String token) {
		String username = TokenUtil.pullUsername(token);
		for (Token t : activeUsers) {
			if (username.equals(t.getUserid())) {
				activeUsers.remove(t);
				return true;
			}
		}
		return false;
	}
	
	public boolean removeUserByName(String username) {
		for (Token t : activeUsers) {
			if (username.equals(t.getUserid())) {
				activeUsers.remove(t);
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
		// TODO Auto-generated method stub
		return false;
	}

	public Map<String, Object> getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
