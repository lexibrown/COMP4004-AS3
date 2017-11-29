package com.lexi.comp4004.game.data;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private String name;
	private List<Card> hiddenCards = new ArrayList<Card>();
	private List<Card> visibleCards = new ArrayList<Card>();
	
	public Player(String name) {
		this.name = name;
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

}
