package com.lexi.comp4004.common.game.data;

import java.util.ArrayList;
import java.util.List;

public class Deck {

	private List<Card> packsOfCards;
	private List<Card> deck;

	public Deck() {
		packsOfCards = new ArrayList<Card>();
		deck = new ArrayList<Card>();
	}

	// creates packs of cards
	public void createDeckOfCards() {
		Card.Rank[] ranks = Card.Rank.values();
		Card.Suit[] suits = Card.Suit.values();
		for (int j = 0; j < suits.length; j++) {
			Card.Suit suit = suits[j];
			for (int k = 0; k < ranks.length; k++) {
				Card.Rank rank = ranks[k];
				packsOfCards.add(new Card(rank, suit));
			}
		}
		shuffle();
	}

	// shuffles cards
	public void shuffle() {
		deck.clear();
		while (packsOfCards.size() > 0) {
			int nextCard = (int) (Math.random() * packsOfCards.size());
			deck.add(packsOfCards.remove(nextCard));
		}
	}

	public Card deal() {
		return (Card) deck.remove(deck.size() - 1);
	}

	public void addCard(Card card) {
		deck.add(card);
	}

	public int size() {
		return deck.size();
	}
	
	public boolean empty() {
		return deck.size() == 0;
	}

	public void clear() {
		deck.clear();
		packsOfCards.clear();
	}

}
