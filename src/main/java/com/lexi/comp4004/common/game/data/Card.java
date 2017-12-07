package com.lexi.comp4004.common.game.data;

import java.io.Serializable;

public class Card implements Comparable<Card>, Serializable {

	private static final long serialVersionUID = -6027718155028936960L;

	public enum Rank {
		Ace(14, 1), Two(2, 2), Three(3, 3), Four(4, 4), Five(5, 5), Six(6, 6), Seven(7, 7), Eight(8, 8), Nine(9,
				9), Ten(10, 10), Jack(11, 11), Queen(12, 12), King(13, 13);

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
		Diamonds(1), Clubs(2), Hearts(3), Spades(4);
		
		int value;

		private Suit(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	private final Rank rank;
	private final Suit suit;

	public Card() {
		this.rank = null;
		this.suit = null;
	}
	
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

	public int compareTo(Card other) {
		if (this == other) {
			return 0;
		} else if (other == null || getClass() != other.getClass()) {
			return 0;
		}

		Card otherCard = (Card) other;

		if (this.getRank().getValue() > otherCard.getRank().getValue()) {
			return -1;
		} else if (this.getRank().getValue() < otherCard.getRank().getValue()) {
			return 1;
		}
		return 0;
	}

}
