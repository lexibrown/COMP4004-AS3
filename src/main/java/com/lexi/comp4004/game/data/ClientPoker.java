package com.lexi.comp4004.game.data;

import java.util.List;
import java.util.ArrayList;

public class ClientPoker {

	private int numCardInDeck = 0;
	private Player player;
	private List<Opponent> opponents;
	
	public ClientPoker() {
		numCardInDeck = 0;
		player = null;
		opponents = new ArrayList<Opponent>();
	}
	
	public ClientPoker(Player player) {
		numCardInDeck = 0;
		this.player = player;
		opponents = new ArrayList<Opponent>();
	}

	public int getNumCardInDeck() {
		return numCardInDeck;
	}

	public void setNumCardInDeck(int numCardInDeck) {
		this.numCardInDeck = numCardInDeck;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public List<Opponent> getOpponents() {
		return opponents;
	}

	public void setOpponents(List<Opponent> opponents) {
		this.opponents = opponents;
	}
	
	public void addOpponent(Opponent opponent) {
		this.opponents.add(opponent);
	}

}
