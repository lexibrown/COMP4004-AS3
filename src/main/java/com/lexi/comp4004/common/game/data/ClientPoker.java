package com.lexi.comp4004.common.game.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClientPoker implements Serializable {

	private static final long serialVersionUID = 4679252163486617194L;
	
	private int numCardInDeck = 0;
	
	private String name;
	private List<Card> hiddenCards;
	private List<Card> visibleCards;
	private List<Opponent> opponents;
	
	private String currentTurn = null;
	
	private boolean isTurn = false;
	private boolean isFirst = false;
	
	public ClientPoker() {
		numCardInDeck = 0;
		setName(null);
		setHiddenCards(new ArrayList<Card>());
		setVisibleCards(new ArrayList<Card>());
		opponents = new ArrayList<Opponent>();
		setCurrentTurn(null);
		setTurn(false);
		setFirst(false);
	}
	
	public ClientPoker(String name, List<Card> hidden, List<Card> visible) {
		numCardInDeck = 0;
		setName(name);
		setHiddenCards(hidden);
		setVisibleCards(visible);
		opponents = new ArrayList<Opponent>();
		setCurrentTurn(null);
		setTurn(false);
		setFirst(false);
	}

	public int getNumCardInDeck() {
		return numCardInDeck;
	}

	public void setNumCardInDeck(int numCardInDeck) {
		this.numCardInDeck = numCardInDeck;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Card> getHiddenCards() {
		return hiddenCards;
	}

	public void setHiddenCards(List<Card> hiddenCards) {
		this.hiddenCards = hiddenCards;
	}

	public List<Card> getVisibleCards() {
		return visibleCards;
	}

	public void setVisibleCards(List<Card> visibleCards) {
		this.visibleCards = visibleCards;
	}
	
	public List<Card> getCards() {
		List<Card> allCards = new ArrayList<Card>();
		allCards.addAll(hiddenCards);
		allCards.addAll(visibleCards);
		return allCards;
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

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	public String getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(String currentTurn) {
		this.currentTurn = currentTurn;
	}
	
}
