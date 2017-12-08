package game;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.lexi.comp4004.common.game.data.Card;
import com.lexi.comp4004.common.game.data.Card.Rank;
import com.lexi.comp4004.common.game.data.Card.Suit;
import com.lexi.comp4004.common.game.util.GameUtil;

public class TestCommon {

	@Test
	public void testCommon() {
		List<Card> ai1 = new ArrayList<Card>();
		ai1.add(new Card(Rank.Three, Suit.Hearts));
		ai1.add(new Card(Rank.Six, Suit.Clubs));
		ai1.add(new Card(Rank.Eight, Suit.Hearts));
		ai1.add(new Card(Rank.Jack, Suit.Spades));
		ai1.add(new Card(Rank.Jack, Suit.Hearts));
		
		System.out.println(GameUtil.getMostCommonRank(ai1));

		List<Card> ai2 = new ArrayList<Card>();
		ai2.add(new Card(Rank.Queen, Suit.Diamonds));
		ai2.add(new Card(Rank.Queen, Suit.Spades));
		ai2.add(new Card(Rank.Five, Suit.Clubs));
		ai2.add(new Card(Rank.Six, Suit.Hearts));
		ai2.add(new Card(Rank.Four, Suit.Clubs));

		System.out.println(GameUtil.getMostCommonRank(ai2));

		List<Card> ai3 = new ArrayList<Card>();
		ai3.add(new Card(Rank.Three, Suit.Hearts));
		ai3.add(new Card(Rank.Ace, Suit.Clubs));
		ai3.add(new Card(Rank.Ace, Suit.Hearts));
		ai3.add(new Card(Rank.Seven, Suit.Spades));
		ai3.add(new Card(Rank.Three, Suit.Clubs));

		System.out.println(GameUtil.getMostCommonRank(ai3));

		ai3 = new ArrayList<Card>();
		ai3.add(new Card(Rank.Ace, Suit.Clubs));
		ai3.add(new Card(Rank.Ace, Suit.Hearts));
		ai3.add(new Card(Rank.Seven, Suit.Spades));

		System.out.println(GameUtil.getMostCommonRank(ai3));

		ai3 = new ArrayList<Card>();
		ai3.add(new Card(Rank.Three, Suit.Hearts));
		ai3.add(new Card(Rank.Seven, Suit.Spades));
		ai3.add(new Card(Rank.Three, Suit.Clubs));

		System.out.println(GameUtil.getMostCommonRank(ai3));

	}
	
}
