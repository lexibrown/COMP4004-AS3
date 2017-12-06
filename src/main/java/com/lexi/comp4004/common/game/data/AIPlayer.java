package com.lexi.comp4004.common.game.data;

import java.io.Serializable;

import com.lexi.comp4004.common.game.strategy.Strategy;

public class AIPlayer extends Player implements Serializable {

	private static final long serialVersionUID = 8618065876039487575L;

	public AIPlayer(String name, Strategy strategy) {
		super(name, strategy);
	}
	
	public boolean isPlayer() {
		return false;
	}
	
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		} else if (!other.getClass().equals(AIPlayer.class)) {
			return false;
		}
		AIPlayer otherPlayer = (AIPlayer) other;

		return this.getName().equals(otherPlayer.getName())
				&& this.getHiddenCards().equals(otherPlayer.getHiddenCards())
				&& this.getVisibleCards().equals(otherPlayer.getVisibleCards());
	}
	
}
