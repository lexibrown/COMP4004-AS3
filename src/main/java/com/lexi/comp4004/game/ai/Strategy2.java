package com.lexi.comp4004.game.ai;

public class Strategy2 extends Strategy {

	public void doStrategy() {
		// TODO
	}
	
	public String toString() {
		String s = "Strategy 2:\n"
				+ "IF you are the first player to play, use Option 1 Strategy 1\n"
				+ "ELSE IF any player before you has 3 visible cards of the same kind: keep your pair(s) if any, and exchange other cards"
				+ "ELSE use  Option 1 Strategy 1";
		return s;
	}
	
}
