package com.lexi.comp4004.game.ai;

import com.lexi.comp4004.game.data.Player;

public class AIPlayer extends Player {

	private Strategy strategy;
	
	public AIPlayer(String name, Strategy strategy) {
		super(name);
		this.strategy = strategy;
	}
	
	public boolean isPlayer() {
		return false;
	}
	
	public Strategy getStrategy() {
		return strategy;
	}
	
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
}
