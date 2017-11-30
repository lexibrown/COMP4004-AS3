package com.lexi.comp4004.server.util;

import com.lexi.comp4004.game.Poker;

public class GameController {

	private static GameController instance;
	
	private Poker game;

	private GameController() {
		game = new Poker();
	}
	
	public static GameController getInstance() {
		if (instance == null) {
			instance = new GameController();
		}
		return instance;
	}
	
	public void setGame(Poker p) {
		game = p;
	}
	
	public Poker getGame() {
		return game;
	}

	public boolean isGameStarted() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
