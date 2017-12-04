package com.lexi.comp4004.common.game;

import java.util.ArrayList;
import java.util.List;

import com.lexi.comp4004.common.game.data.Card;
import com.lexi.comp4004.common.game.data.Deck;
import com.lexi.comp4004.common.game.data.Player;
import com.lexi.comp4004.common.game.util.Config;

public class Poker {

	private Deck deck;
	private List<Player> players;
	private int currentTurn = 1;
	private int maxNumPlayers = 2;
	
	public Poker() {
		deck = new Deck();
		deck.createDeckOfCards();
		players = new ArrayList<Player>();
		currentTurn = 1;
	}
	
	public Player whoseTurn() {
		if (players.size() < currentTurn) {
			return null;
		}
		return players.get(currentTurn - 1);
	}
	
	public boolean isTurn(String user) {
		return whoseTurn().getName().equals(user);
	}

	public Player getPlayer(String name) {
		for (Player p : players) {
			if (name.equals(p.getName())) {
				return p;
			}
		}
		return null;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public boolean addPlayer(String name) {
		return addPlayer(new Player(name));
	}
	
	public boolean addPlayer(Player player) {
		if (isFull()) {
			return false;
		}
		players.add(player);
		return true;
	}

	public Deck getDeck() {
		return deck;
	}

	public int getMaxNumPlayers() {
		return maxNumPlayers;
	}

	public void setMaxNumPlayers(int maxNumPlayers) {
		this.maxNumPlayers = maxNumPlayers;
	}
	
	public boolean isFull() {
		return players.size() == maxNumPlayers;
	}

	public void dealCards() {
		for (int i = 0; i < Config.MAX_CARDS; i++) {
			for (Player p : players) {
				p.receiveCard(deck.deal());
			}
		}
	}

	public Card deal() {
		if (deck.empty()) {
			return null;
		}
		return deck.deal();
	}

	public void nextTurn() {
		currentTurn++;
	}
	
}
