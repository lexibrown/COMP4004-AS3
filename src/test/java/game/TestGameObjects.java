package game;

import static org.junit.Assert.*;

import org.junit.Test;

import com.lexi.comp4004.common.game.data.Card;
import com.lexi.comp4004.common.game.data.Card.Rank;
import com.lexi.comp4004.common.game.data.Card.Suit;
import com.lexi.comp4004.common.game.data.Deck;

public class TestGameObjects {

	@Test
	public void testCard() {
		Card c1 = new Card(Rank.Ace, Suit.Diamonds);
		assertEquals(Rank.Ace, c1.getRank());
		assertEquals(Suit.Diamonds, c1.getSuit());
		
		Card c2 = new Card(Rank.Ace, Suit.Diamonds);
		assertTrue(c1.equals(c2));
	}
	
	@Test
	public void testDeck() {
		Deck d = new Deck();
		d.createDeckOfCards();
		
		assertEquals(52, d.size());

		Card.Rank[] ranks = Card.Rank.values();
		Card.Suit[] suits = Card.Suit.values();
		for (int j = 0; j < suits.length; j++) {
			Card.Suit suit = suits[j];
			for (int k = 0; k < ranks.length; k++) {
				Card.Rank rank = ranks[k];
				assertTrue(d.getCards().contains(new Card(rank, suit)));
			}
		}
		
		d.deal();
		assertEquals(51, d.size());
	}
	
}
