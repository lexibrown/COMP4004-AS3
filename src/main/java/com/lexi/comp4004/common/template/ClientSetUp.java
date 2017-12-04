package com.lexi.comp4004.common.template;

import com.lexi.comp4004.common.game.Poker;
import com.lexi.comp4004.common.game.ai.AIPlayer;
import com.lexi.comp4004.common.game.ai.Strategy1;
import com.lexi.comp4004.common.game.ai.Strategy2;

public class ClientSetUp extends SetUp {
	
	@Override
	public Poker setUpGame(Poker poker) {
		poker.setMaxNumPlayers(getNumPlayers());
		for (int i = 0; i < getAiPlayers().size(); i++) {
			int strat = getAiPlayers().get(i);
			if (strat == 1) {
				poker.addPlayer(new AIPlayer("Computer" + i, new Strategy1()));
			} else if (strat == 2) {
				poker.addPlayer(new AIPlayer("Computer" + i, new Strategy2()));
			}
		}
		return poker;
	}

}
