package com.lexi.comp4004.server;

import com.lexi.comp4004.game.Poker;
import com.lexi.comp4004.server.template.SetUp;

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
	
	public void setUpGame(SetUp setup) {
		setGame(setup.setUpGame(getGame()));
	}
	
	public void setGame(Poker p) {
		this.game = p;
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
