package com.lexi.comp4004.common.template;

import com.lexi.comp4004.common.game.Poker;
import com.lexi.comp4004.common.game.data.AIPlayer;
import com.lexi.comp4004.common.game.data.Card;
import com.lexi.comp4004.common.game.data.Deck;
import com.lexi.comp4004.common.game.data.Player;
import com.lexi.comp4004.common.game.strategy.Strategy1;
import com.lexi.comp4004.common.game.strategy.Strategy2;

public class SetUpUtil {

	public static Poker setUpGame(Poker game, SetUp setup) {
		SetUpPoker setUpGame = setup.setUpGame(new SetUpPoker());

		game.setMaxNumPlayers(setUpGame.getMaxNumPlayers());
		
		// if player, set up player
		if (setUpGame.getPlayerName() != null && !setUpGame.getPlayerName().isEmpty()) {
			Player p = new Player(setUpGame.getPlayerName());
			for (Card c : setUpGame.getPlayerCards()) {
				p.receiveCard(c);
			}
			game.addPlayer(p);
		}
		
		for (int i = 0; i < setUpGame.getAiPlayers().size(); i++) {
			int strat = setUpGame.getAiPlayers().get(i);
			// if ai cards, set up ai cards
			Player ai = null;
			if (strat == 1) {
				ai = new AIPlayer("Computer" + i, new Strategy1());
			} else if (strat == 2) {
				ai = new AIPlayer("Computer" + i, new Strategy2());
			}
			// will crash if strat isn't 1 or 2
			if (setUpGame.getAiCards() != null && !!setUpGame.getAiCards().isEmpty()) {
				for (Card c : setUpGame.getAiCards().get(i)) {
					ai.receiveCard(c);
				}
			}
			game.addPlayer(ai);
		}
		
		if (setUpGame.getDeck() != null && !setUpGame.getDeck().isEmpty()) {
			Deck deck = new Deck();
			for (Card c : setUpGame.getDeck()) {
				deck.addCard(c);
			}
			game.setDeck(deck);
		}

		return game;
	}
	
}
