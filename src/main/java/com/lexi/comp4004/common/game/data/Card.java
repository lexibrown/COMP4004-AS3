package com.lexi.comp4004.common.game.data;

public class Card {

	public enum Rank {
		Ace(11, 1), Two(2, 2), Three(3, 3), Four(4, 4), Five(5, 5), Six(6, 6), Seven(7, 7), Eight(8, 8), Nine(9,
				9), Ten(10, 10), Jack(10, 10), Queen(10, 10), King(10, 10);

		int value, altValue;

		private Rank(int value, int altValue) {
			this.value = value;
			this.altValue = altValue;
		}

		public int getValue() {
			return value;
		}

		public int getAltValue() {
			return altValue;
		}
	}

	public enum Suit {
		Clubs, Diamonds, Hearts, Spades
	}

	private final Rank rank;
	private final Suit suit;

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public String toString() {
		return rank + " of " + suit;
	}

	public boolean equals(Object other) {
		if (!other.getClass().equals(Card.class)) {
			return false;
		}
		Card otherCard = (Card) other;

		return this.getRank().equals(otherCard.getRank()) && this.getSuit().equals(otherCard.getSuit());
	}

}
