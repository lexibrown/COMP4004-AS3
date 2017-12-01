package com.lexi.comp4004.game;

import java.util.ArrayList;
import java.util.List;

import com.lexi.comp4004.game.data.Deck;
import com.lexi.comp4004.game.data.Player;

public class Poker {

	private Deck deck;
	private List<Player> players;
	private int currentTurn = 0;
	private int maxNumPlayers = 2;
	
	public Poker() {
		deck = new Deck();
		deck.createDeckOfCards();
		players = new ArrayList<Player>();
		currentTurn = 0;
	}
	
	public Player whoseTurn() {
		return players.get(currentTurn);
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
	
}
