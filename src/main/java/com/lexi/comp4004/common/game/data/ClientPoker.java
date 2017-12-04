package com.lexi.comp4004.common.game.data;

import java.util.List;
import java.util.ArrayList;

public class ClientPoker {

	private int numCardInDeck = 0;
	private Player player;
	private List<Opponent> opponents;
	
	private boolean isTurn = false;
	
	public ClientPoker() {
		numCardInDeck = 0;
		player = null;
		opponents = new ArrayList<Opponent>();
		setTurn(false);
	}
	
	public ClientPoker(Player player) {
		numCardInDeck = 0;
		this.player = player;
		opponents = new ArrayList<Opponent>();
		setTurn(false);
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

	public boolean isTurn() {
		return isTurn;
	}

	public void setTurn(boolean isTurn) {
		this.isTurn = isTurn;
	}
	
}
