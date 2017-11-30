package com.lexi.comp4004.server;

import com.lexi.comp4004.game.Poker;

public class GameController {

	private static GameController instance;
	
	private Poker game;
	private boolean gameStarted = false;
	
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

	
	public void startGame() {
		gameStarted = true;
		
	}
	
	public void reset() {
		gameStarted = false;
		game = new Poker();
	}
	
	public boolean isGameStarted() {
		return gameStarted;
	}
	
}
