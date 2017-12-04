package com.lexi.comp4004.common.game.data;

import com.lexi.comp4004.common.game.strategy.Strategy;

public class AIPlayer extends Player {

	public AIPlayer(String name, Strategy strategy) {
		super(name, strategy);
	}
	
	public boolean isPlayer() {
		return false;
	}
	
}
