package game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.lexi.comp4004.common.game.Poker;
import com.lexi.comp4004.common.game.data.Card;
import com.lexi.comp4004.common.game.data.Card.Rank;
import com.lexi.comp4004.common.game.data.Card.Suit;
import com.lexi.comp4004.common.game.data.ClientPoker;
import com.lexi.comp4004.common.game.data.Deck;
import com.lexi.comp4004.common.game.data.Player;
import com.lexi.comp4004.common.game.util.GameUtil;

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

	@Test
	public void testPlayer() {
		Player p = new Player("test1");
		p.receiveCard(new Card(Rank.Ace, Suit.Clubs));
		p.receiveCard(new Card(Rank.Ace, Suit.Diamonds));
		p.receiveCard(new Card(Rank.Ace, Suit.Hearts));
		p.receiveCard(new Card(Rank.Ace, Suit.Spades));
		p.receiveCard(new Card(Rank.Two, Suit.Clubs));

		assertEquals(5, p.getCards().size());
		assertEquals(5, p.getHiddenCards().size());
		assertTrue(p.getVisibleCards().isEmpty());
		
		Card newCard1 = new Card(Rank.King, Suit.Spades);
		Card newCard2 = new Card(Rank.Queen, Suit.Spades);
		
		p.exchangeCard(new Card(Rank.Two, Suit.Clubs), newCard1);
		p.exchangeCard(new Card(Rank.Ace, Suit.Hearts), newCard2);
		
		assertEquals(5, p.getCards().size());
		assertEquals(3, p.getHiddenCards().size());
		assertEquals(2, p.getVisibleCards().size());
		
		assertTrue(p.getHiddenCards().contains(new Card(Rank.Ace, Suit.Clubs)));
		assertTrue(p.getHiddenCards().contains(new Card(Rank.Ace, Suit.Diamonds)));
		assertFalse(p.getHiddenCards().contains(new Card(Rank.Ace, Suit.Hearts)));
		assertTrue(p.getHiddenCards().contains(new Card(Rank.Ace, Suit.Spades)));
		assertFalse(p.getHiddenCards().contains(new Card(Rank.Two, Suit.Clubs)));

		assertTrue(p.getVisibleCards().contains(newCard1));
		assertTrue(p.getVisibleCards().contains(newCard2));
		
		Poker poker = new Poker();
		poker.addPlayer(p);
		ClientPoker game = GameUtil.getClientView(poker, p);
		
		assertEquals(5, game.getCards().size());	
	}
	
	
}
