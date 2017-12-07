package com.lexi.comp4004.common.game.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexi.comp4004.common.game.util.Config;

public class Opponent implements Serializable {

	private static final long serialVersionUID = 410694835369462376L;

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

	public Opponent(String name, List<Card> cards) {
		this.name = name;
		this.numCards = Config.MAX_CARDS;
		this.visibleCards = cards;
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
