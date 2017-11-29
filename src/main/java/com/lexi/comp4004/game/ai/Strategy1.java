package com.lexi.comp4004.game.ai;

public class Strategy1 implements Strategy {

	public void doStrategy() {
		// TODO
	}
	
	public String toString() {
		String s = "Strategy 1:\n"
				+ "- if this AI player has a straight or better, hold (ie do not exchange any card\n"
				+ "- else this AI player attempts to get a full house by exchanging everything that is not a pair or 3 of a kind";
		return s;
	}
	
}
