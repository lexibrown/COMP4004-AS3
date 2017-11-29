package com.lexi.comp4004.game;

import java.util.HashMap;
import java.util.Map;

import com.lexi.comp4004.game.data.Deck;
import com.lexi.comp4004.game.data.Player;

public class Poker {

	private Deck deck;
	private Map<Integer, Player> players;
	private int currentTurn = 0;
	
	public Poker() {
		deck = new Deck();
		deck.createDeckOfCards();
		players = new HashMap<Integer, Player>();
		currentTurn = 0;
	}
	
	public Player whoseTurn() {
		return players.get(currentTurn);
	}
	
	
	
}
