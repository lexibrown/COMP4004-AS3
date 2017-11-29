package com.lexi.comp4004.game.data;

import java.util.ArrayList;
import java.util.List;


public class Deck {

	private List packsOfCards = new ArrayList();
	private List deck = new ArrayList();
	private int halfOfDeck = 0; 
	
	public Deck(int numDecks) {
		createPacksOfCards(numDecks);
	}
	
	// creates packs of cards
	public void createPacksOfCards(int numDecks) {
		Card.Rank[] ranks = Card.Rank.values();
		Card.Suit[] suits = Card.Suit.values();
		for (int i = 0; i < numDecks; i++) {
			for (int j = 0; j < suits.length; j++) {
				Card.Suit suit = suits[j];
				for (int k = 0; k < ranks.length; k++) {
					Card.Rank rank = ranks[k];
					packsOfCards.add(new Card(rank, suit));
				}
			}
		}
		halfOfDeck = packsOfCards.size() / 2;
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
	
	public boolean halfWay() {
		return halfOfDeck >= deck.size();	
	}
}
