package com.lexi.comp4004.common.game.data;

import java.util.ArrayList;
import java.util.List;

public class Opponent {

	private String name;
	private int numCards = 0;
	private List<Card> visibleCards;

	public Opponent() {
		this.name = null;
		this.numCards = 0;
		visibleCards = null;
	}

	public Opponent(String name) {
		this.name = name;
		this.numCards = 0;
		this.visibleCards = new ArrayList<Card>();
	}
	
	public Opponent(Player player) {
		this.name = player.getName();
		this.numCards = player.getCards().size();
		this.visibleCards = player.getVisibleCards();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumCards() {
		return numCards;
	}

	public void setNumCards(int numCards) {
		this.numCards = numCards;
	}

	public List<Card> getVisibleCards() {
		return visibleCards;
	}

	public void setVisibleCards(List<Card> visibleCards) {
		this.visibleCards = visibleCards;
	}

	public void clear() {
		numCards = 0;
		visibleCards.clear();
	}

}
