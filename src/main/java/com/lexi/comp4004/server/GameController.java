package com.lexi.comp4004.server;

import com.lexi.comp4004.common.game.Poker;
import com.lexi.comp4004.common.template.SetUp;

public class GameController {

	private static GameController instance;
	
	private Poker game;
	private boolean gameStarted = false;
	private boolean gameSetUp = false;
	
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
		gameSetUp = true;
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
		gameSetUp = false;
		game = new Poker();
	}
	
	public boolean isGameStarted() {
		return gameStarted;
	}
	
	public boolean isGameSetUp() {
		return gameSetUp;
	}
	
	public boolean isGameFull() {
		return game.isFull();
	}

	public boolean joinGame(String user) {
		if (isGameFull()) {
			return false;
		} else if (isGameStarted()) {
			return false;
		} else if (!isGameSetUp()) {
			return false;
		} else if (game.getPlayer(user) != null) {
			return true;
		}
		return game.addPlayer(user);
	}

	public boolean startGame(String user) {
		if (isGameStarted()) {
			return false;
		} else if (!isGameSetUp()) {
			return false;
		} else if (!isGameFull()) {
			return false;
		} else if (game.getPlayer(user) == null) {
			return false;
		}
		
		
		// TODO
		// send first player start
		return true;
	}
	
}
