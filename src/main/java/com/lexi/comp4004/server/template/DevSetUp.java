package com.lexi.comp4004.server.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lexi.comp4004.game.Poker;
import com.lexi.comp4004.game.ai.AIPlayer;
import com.lexi.comp4004.game.ai.Strategy1;
import com.lexi.comp4004.game.ai.Strategy2;
import com.lexi.comp4004.game.data.Card;

public class DevSetUp extends SetUp {

	private String playerName = "";
	private List<Card> playerCards = new ArrayList<Card>();
	private Map<Integer, List<Card>> aiCards = new HashMap<Integer, List<Card>>();

	// TODO
	
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

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public List<Card> getPlayerCards() {
		return playerCards;
	}

	public void setPlayerCards(List<Card> playerCards) {
		this.playerCards = playerCards;
	}

	public Map<Integer, List<Card>> getAiCards() {
		return aiCards;
	}

	public void addAiCards(int ai, List<Card> aiCards) {
		this.aiCards.put(ai, aiCards);
	}

}
