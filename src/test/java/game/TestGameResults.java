package game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.lexi.comp4004.common.game.data.Card;
import com.lexi.comp4004.common.game.data.Card.Rank;
import com.lexi.comp4004.common.game.data.Card.Suit;
import com.lexi.comp4004.common.game.data.Player;
import com.lexi.comp4004.common.game.data.Result;
import com.lexi.comp4004.common.game.util.GameUtil;
import com.lexi.comp4004.common.game.util.GameUtil.Ranking;

public class TestGameResults {

	List<Player> players = new ArrayList<Player>();

	@Test
	public void testRoyalFlush() {
		Player p = new Player("test1");
		p.receiveCard(new Card(Rank.Ace, Suit.Spades));
		p.receiveCard(new Card(Rank.King, Suit.Spades));
		p.receiveCard(new Card(Rank.Queen, Suit.Spades));
		p.receiveCard(new Card(Rank.Jack, Suit.Spades));
		p.receiveCard(new Card(Rank.Ten, Suit.Spades));

		players.add(p);

		List<Result> results = GameUtil.determineResults(players);

		assertNotNull(results);
		assertTrue(results.size() == 1);

		Result r = results.get(0);
		assertEquals(p.getName(), r.getUser());
		assertEquals(Ranking.ROYAL_FLUSH.toString(), r.getOutcome());
		assertEquals(Ranking.ROYAL_FLUSH.getValue(), r.getRank());
		assertEquals(p.getCards(), r.getCards());
	}

	@Test
	public void testStraightFlush() {
		Player p = new Player("test1");
		p.receiveCard(new Card(Rank.Three, Suit.Spades));
		p.receiveCard(new Card(Rank.Four, Suit.Spades));
		p.receiveCard(new Card(Rank.Five, Suit.Spades));
		p.receiveCard(new Card(Rank.Six, Suit.Spades));
		p.receiveCard(new Card(Rank.Seven, Suit.Spades));

		players.add(p);

		List<Result> results = GameUtil.determineResults(players);

		assertNotNull(results);
		assertTrue(results.size() == 1);

		Result r = results.get(0);
		assertEquals(p.getName(), r.getUser());
		assertEquals(Ranking.STRAIGHT_FLUSH.toString(), r.getOutcome());
		assertEquals(Ranking.STRAIGHT_FLUSH.getValue(), r.getRank());
		assertEquals(p.getCards(), r.getCards());
	}

	@Test
	public void testFourOfAKind() {
		Player p = new Player("test1");
		p.receiveCard(new Card(Rank.Jack, Suit.Spades));
		p.receiveCard(new Card(Rank.Jack, Suit.Diamonds));
		p.receiveCard(new Card(Rank.Jack, Suit.Hearts));
		p.receiveCard(new Card(Rank.Jack, Suit.Clubs));
		p.receiveCard(new Card(Rank.Seven, Suit.Spades));

		players.add(p);

		List<Result> results = GameUtil.determineResults(players);

		assertNotNull(results);
		assertTrue(results.size() == 1);

		Result r = results.get(0);
		assertEquals(p.getName(), r.getUser());
		assertEquals(Ranking.FOUR_OF_A_KIND.toString(), r.getOutcome());
		assertEquals(Ranking.FOUR_OF_A_KIND.getValue(), r.getRank());
		assertEquals(p.getCards(), r.getCards());
	}

	@Test
	public void testFullHouse() {
		Player p = new Player("test1");
		p.receiveCard(new Card(Rank.Ace, Suit.Spades));
		p.receiveCard(new Card(Rank.Ace, Suit.Diamonds));
		p.receiveCard(new Card(Rank.Ace, Suit.Hearts));
		p.receiveCard(new Card(Rank.Jack, Suit.Clubs));
		p.receiveCard(new Card(Rank.Jack, Suit.Spades));

		players.add(p);

		List<Result> results = GameUtil.determineResults(players);

		assertNotNull(results);
		assertTrue(results.size() == 1);

		Result r = results.get(0);
		assertEquals(p.getName(), r.getUser());
		assertEquals(Ranking.FULL_HOUSE.toString(), r.getOutcome());
		assertEquals(Ranking.FULL_HOUSE.getValue(), r.getRank());
		assertEquals(p.getCards(), r.getCards());
	}

	@Test
	public void testFlush() {
		Player p = new Player("test1");
		p.receiveCard(new Card(Rank.Ace, Suit.Diamonds));
		p.receiveCard(new Card(Rank.Two, Suit.Diamonds));
		p.receiveCard(new Card(Rank.King, Suit.Diamonds));
		p.receiveCard(new Card(Rank.Eight, Suit.Diamonds));
		p.receiveCard(new Card(Rank.Ten, Suit.Diamonds));

		players.add(p);

		List<Result> results = GameUtil.determineResults(players);

		assertNotNull(results);
		assertTrue(results.size() == 1);

		Result r = results.get(0);
		assertEquals(p.getName(), r.getUser());
		assertEquals(Ranking.FLUSH.toString(), r.getOutcome());
		assertEquals(Ranking.FLUSH.getValue(), r.getRank());
		assertEquals(p.getCards(), r.getCards());
	}

	@Test
	public void testStraight() {
		Player p = new Player("test1");
		p.receiveCard(new Card(Rank.Five, Suit.Hearts));
		p.receiveCard(new Card(Rank.Six, Suit.Diamonds));
		p.receiveCard(new Card(Rank.Seven, Suit.Clubs));
		p.receiveCard(new Card(Rank.Eight, Suit.Spades));
		p.receiveCard(new Card(Rank.Nine, Suit.Diamonds));

		players.add(p);

		List<Result> results = GameUtil.determineResults(players);

		assertNotNull(results);
		assertTrue(results.size() == 1);

		Result r = results.get(0);
		assertEquals(p.getName(), r.getUser());
		assertEquals(Ranking.STRAIGHT.toString(), r.getOutcome());
		assertEquals(Ranking.STRAIGHT.getValue(), r.getRank());
		assertEquals(p.getCards(), r.getCards());
	}

	@Test
	public void testThreeOfAKind() {
		Player p = new Player("test1");
		p.receiveCard(new Card(Rank.Ace, Suit.Diamonds));
		p.receiveCard(new Card(Rank.Ace, Suit.Spades));
		p.receiveCard(new Card(Rank.Ace, Suit.Clubs));
		p.receiveCard(new Card(Rank.Six, Suit.Hearts));
		p.receiveCard(new Card(Rank.Four, Suit.Clubs));

		players.add(p);

		List<Result> results = GameUtil.determineResults(players);

		assertNotNull(results);
		assertTrue(results.size() == 1);

		Result r = results.get(0);
		assertEquals(p.getName(), r.getUser());
		assertEquals(Ranking.THREE_OF_A_KIND.toString(), r.getOutcome());
		assertEquals(Ranking.THREE_OF_A_KIND.getValue(), r.getRank());
		assertEquals(p.getCards(), r.getCards());
	}

	@Test
	public void testTwoPair() {
		Player p = new Player("test1");
		p.receiveCard(new Card(Rank.King, Suit.Diamonds));
		p.receiveCard(new Card(Rank.King, Suit.Spades));
		p.receiveCard(new Card(Rank.Queen, Suit.Diamonds));
		p.receiveCard(new Card(Rank.Queen, Suit.Clubs));
		p.receiveCard(new Card(Rank.Two, Suit.Diamonds));

		players.add(p);

		List<Result> results = GameUtil.determineResults(players);

		assertNotNull(results);
		assertTrue(results.size() == 1);

		Result r = results.get(0);
		assertEquals(p.getName(), r.getUser());
		assertEquals(Ranking.TWO_PAIR.toString(), r.getOutcome());
		assertEquals(Ranking.TWO_PAIR.getValue(), r.getRank());
		assertEquals(p.getCards(), r.getCards());
	}

	@Test
	public void testOnePair() {
		Player p = new Player("test1");
		p.receiveCard(new Card(Rank.Three, Suit.Hearts));
		p.receiveCard(new Card(Rank.Six, Suit.Clubs));
		p.receiveCard(new Card(Rank.Eight, Suit.Hearts));
		p.receiveCard(new Card(Rank.Jack, Suit.Spades));
		p.receiveCard(new Card(Rank.Jack, Suit.Hearts));

		players.add(p);

		List<Result> results = GameUtil.determineResults(players);

		assertNotNull(results);
		assertTrue(results.size() == 1);

		Result r = results.get(0);
		assertEquals(p.getName(), r.getUser());
		assertEquals(Ranking.ONE_PAIR.toString(), r.getOutcome());
		assertEquals(Ranking.ONE_PAIR.getValue(), r.getRank());
		assertEquals(p.getCards(), r.getCards());
	}

	@Test
	public void testHighestCard() {
		Player p = new Player("test1");
		p.receiveCard(new Card(Rank.Three, Suit.Hearts));
		p.receiveCard(new Card(Rank.Six, Suit.Clubs));
		p.receiveCard(new Card(Rank.Eight, Suit.Hearts));
		p.receiveCard(new Card(Rank.Jack, Suit.Spades));
		p.receiveCard(new Card(Rank.Ace, Suit.Diamonds));

		players.add(p);

		List<Result> results = GameUtil.determineResults(players);

		assertNotNull(results);
		assertTrue(results.size() == 1);

		Result r = results.get(0);
		assertEquals(p.getName(), r.getUser());
		assertEquals(Ranking.HIGH_CARD.toString(), r.getOutcome());
		assertEquals(Ranking.HIGH_CARD.getValue(), r.getRank());
		assertEquals(p.getCards(), r.getCards());
	}

	@Test
	public void testPlayerRankings() {
		Player p1 = new Player("test1");
		p1.receiveCard(new Card(Rank.Three, Suit.Spades));
		p1.receiveCard(new Card(Rank.Four, Suit.Spades));
		p1.receiveCard(new Card(Rank.Five, Suit.Spades));
		p1.receiveCard(new Card(Rank.Six, Suit.Spades));
		p1.receiveCard(new Card(Rank.Seven, Suit.Spades));

		Player p2 = new Player("test2");
		p2.receiveCard(new Card(Rank.Jack, Suit.Spades));
		p2.receiveCard(new Card(Rank.Jack, Suit.Diamonds));
		p2.receiveCard(new Card(Rank.Jack, Suit.Hearts));
		p2.receiveCard(new Card(Rank.Jack, Suit.Clubs));
		p2.receiveCard(new Card(Rank.Seven, Suit.Spades));

		Player p3 = new Player("test3");
		p3.receiveCard(new Card(Rank.Three, Suit.Hearts));
		p3.receiveCard(new Card(Rank.Six, Suit.Clubs));
		p3.receiveCard(new Card(Rank.Eight, Suit.Hearts));
		p3.receiveCard(new Card(Rank.Jack, Suit.Spades));
		p3.receiveCard(new Card(Rank.Ace, Suit.Diamonds));

		players.add(p2);
		players.add(p1);
		players.add(p3);

		List<Result> results = GameUtil.determineResults(players);

		assertNotNull(results);
		assertTrue(results.size() == 3);
		
		Result r1 = results.get(0);
		assertEquals(p1.getName(), r1.getUser());
		assertEquals(Ranking.STRAIGHT_FLUSH.toString(), r1.getOutcome());
		assertEquals(Ranking.STRAIGHT_FLUSH.getValue(), r1.getRank());
		assertEquals(p1.getCards(), r1.getCards());
		

		Result r2 = results.get(1);
		assertEquals(p2.getName(), r2.getUser());
		assertEquals(Ranking.FOUR_OF_A_KIND.toString(), r2.getOutcome());
		assertEquals(Ranking.FOUR_OF_A_KIND.getValue(), r2.getRank());
		assertEquals(p2.getCards(), r2.getCards());
		
		Result r3 = results.get(2);
		assertEquals(p3.getName(), r3.getUser());
		assertEquals(Ranking.HIGH_CARD.toString(), r3.getOutcome());
		assertEquals(Ranking.HIGH_CARD.getValue(), r3.getRank());
		assertEquals(p3.getCards(), r3.getCards());
	}
	
	@Test
	public void testPlayerRankingsSameRank() {
		Player p1 = new Player("test1");
		p1.receiveCard(new Card(Rank.Six, Suit.Spades));
		p1.receiveCard(new Card(Rank.Six, Suit.Hearts));
		p1.receiveCard(new Card(Rank.Six, Suit.Diamonds));
		p1.receiveCard(new Card(Rank.Six, Suit.Clubs));
		p1.receiveCard(new Card(Rank.Seven, Suit.Spades));

		Player p2 = new Player("test2");
		p2.receiveCard(new Card(Rank.Jack, Suit.Spades));
		p2.receiveCard(new Card(Rank.Jack, Suit.Diamonds));
		p2.receiveCard(new Card(Rank.Jack, Suit.Hearts));
		p2.receiveCard(new Card(Rank.Jack, Suit.Clubs));
		p2.receiveCard(new Card(Rank.Ace, Suit.Spades));

		Player p3 = new Player("test3");
		p3.receiveCard(new Card(Rank.Queen, Suit.Spades));
		p3.receiveCard(new Card(Rank.Queen, Suit.Diamonds));
		p3.receiveCard(new Card(Rank.Queen, Suit.Hearts));
		p3.receiveCard(new Card(Rank.Queen, Suit.Clubs));
		p3.receiveCard(new Card(Rank.Two, Suit.Spades));

		players.add(p1);
		players.add(p2);
		players.add(p3);
		
		List<Result> results = GameUtil.determineResults(players);

		assertNotNull(results);
		assertTrue(results.size() == 3);
		
		Result r1 = results.get(0);
		assertEquals(p3.getName(), r1.getUser());
		assertEquals(Ranking.FOUR_OF_A_KIND.toString(), r1.getOutcome());
		assertEquals(Ranking.FOUR_OF_A_KIND.getValue(), r1.getRank());
		assertEquals(p3.getCards(), r1.getCards());
		

		Result r2 = results.get(1);
		assertEquals(p2.getName(), r2.getUser());
		assertEquals(Ranking.FOUR_OF_A_KIND.toString(), r2.getOutcome());
		assertEquals(Ranking.FOUR_OF_A_KIND.getValue(), r2.getRank());
		assertEquals(p2.getCards(), r2.getCards());
		
		Result r3 = results.get(2);
		assertEquals(p1.getName(), r3.getUser());
		assertEquals(Ranking.FOUR_OF_A_KIND.toString(), r3.getOutcome());
		assertEquals(Ranking.FOUR_OF_A_KIND.getValue(), r3.getRank());
		assertEquals(p1.getCards(), r3.getCards());
	}
	
	@Test
	public void testPlayerRankingsSameRankWithAce() {
		Player p1 = new Player("test1");
		p1.receiveCard(new Card(Rank.Ace, Suit.Spades));
		p1.receiveCard(new Card(Rank.Two, Suit.Spades));
		p1.receiveCard(new Card(Rank.Three, Suit.Spades));
		p1.receiveCard(new Card(Rank.Four, Suit.Spades));
		p1.receiveCard(new Card(Rank.Five, Suit.Spades));

		Player p2 = new Player("test2");
		p2.receiveCard(new Card(Rank.Eight, Suit.Spades));
		p2.receiveCard(new Card(Rank.Nine, Suit.Spades));
		p2.receiveCard(new Card(Rank.Ten, Suit.Spades));
		p2.receiveCard(new Card(Rank.Jack, Suit.Spades));
		p2.receiveCard(new Card(Rank.Queen, Suit.Spades));

		players.add(p1);
		players.add(p2);
		
		List<Result> results = GameUtil.determineResults(players);

		assertNotNull(results);
		assertTrue(results.size() == 2);
		
		Result r1 = results.get(0);
		assertEquals(p2.getName(), r1.getUser());
		assertEquals(Ranking.STRAIGHT_FLUSH.toString(), r1.getOutcome());
		assertEquals(Ranking.STRAIGHT_FLUSH.getValue(), r1.getRank());
		assertEquals(p2.getCards(), r1.getCards());
		

		Result r2 = results.get(1);
		assertEquals(p1.getName(), r2.getUser());
		assertEquals(Ranking.STRAIGHT_FLUSH.toString(), r2.getOutcome());
		assertEquals(Ranking.STRAIGHT_FLUSH.getValue(), r2.getRank());
		assertEquals(p1.getCards(), r2.getCards());
	}
	
}
