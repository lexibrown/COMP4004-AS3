package com.lexi.comp4004.common.game.data;

import java.util.ArrayList;
import java.util.List;

import com.lexi.comp4004.common.game.strategy.PlayerStrategy;
import com.lexi.comp4004.common.game.strategy.Strategy;

public class Player {

	private String name;
	private List<Card> hiddenCards;
	private List<Card> visibleCards;

	private Strategy strategy;
	
	public Player() {
		this.name = null;
		hiddenCards = null;
		visibleCards = null;
		strategy = null;
	}

	public Player(String name) {
		this.name = name;
		hiddenCards = new ArrayList<Card>();
		visibleCards = new ArrayList<Card>();
		strategy = new PlayerStrategy();
	}

	public Player(String name, Strategy strategy) {
		this(name);
		this.strategy = strategy;
	}
		
	public String getName() {
		return name;
	}

	public void clear() {
		hiddenCards.clear();
		visibleCards.clear();
	}

	public boolean isPlayer() {
		return true;
	}

	public void receiveCard(Card card) {
		hiddenCards.add(card);
	}

	public void exchangeCard(Card exchanged, Card newCard) {
		hiddenCards.remove(exchanged);
		visibleCards.add(newCard);
	}

	public List<Card> getCards() {
		List<Card> allCards = new ArrayList<Card>();
		allCards.addAll(hiddenCards);
		allCards.addAll(visibleCards);
		return allCards;
	}

	public List<Card> getHiddenCards() {
		return hiddenCards;
	}

	public List<Card> getVisibleCards() {
		return visibleCards;
	}

	public Strategy getStrategy() {
		return strategy;
	}
	
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
	public boolean equals(Object other) {
		if (!other.getClass().equals(Player.class)) {
			return false;
		}
		Player otherPlayer = (Player) other;

		return this.getName().equals(otherPlayer.getName())
				&& this.getHiddenCards().equals(otherPlayer.getHiddenCards())
				&& this.getVisibleCards().equals(otherPlayer.getVisibleCards());
	}

}
