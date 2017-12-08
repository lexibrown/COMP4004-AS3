package strategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.ClientBuilder;

import org.junit.Before;
import org.junit.Test;

import com.lexi.comp4004.common.game.data.Card;
import com.lexi.comp4004.common.game.data.ClientPoker;
import com.lexi.comp4004.common.game.data.Result;
import com.lexi.comp4004.common.game.data.Card.Rank;
import com.lexi.comp4004.common.game.data.Card.Suit;
import com.lexi.comp4004.common.game.util.Config;
import com.lexi.comp4004.common.game.util.Config.Endpoint;
import com.lexi.comp4004.common.game.util.Config.Key;
import com.lexi.comp4004.common.game.util.GameUtil.Ranking;
import com.lexi.comp4004.common.template.DevSetUp;
import com.lexi.comp4004.server.util.JsonUtil;
import com.lexi.comp4004.server.util.Variables;

public class TestStrategy2Case1 extends TestStrategyFramework {

	private String token1;

	@Before
	public void setUp() {
		client = ClientBuilder.newClient();
		target = client.target(Variables.baseUri);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testStrategy2Case1() {
		Map<String, Object> response = null;
		Map<String, Object> params = null;
		String name1 = "TestStrategy2Case1";

		try {
			// connect
			reloadUri();
			target = target.path(Endpoint.Connect.CONNECT);
			response = connectRequest(name1);
			assertTrue(response.containsKey(Key.TOKEN));
			token1 = response.get(Key.TOKEN).toString();

			// websocket connect
			Config.token = token1;
			TestSocket ts = new TestSocket();
			ts.connectToWebSocket();

			// get dev token
			reloadUri();
			target = target.path(Endpoint.Dev.DEV);
			params = new HashMap<String, Object>();
			params.put(Key.TOKEN, token1);
			params.put(Key.ADMIN, Variables.ADMIN);
			params.put(Key.PASSWORD, Variables.ADMIN_PASSWORD);
			response = postRequest(JsonUtil.stringify(params));
			assertTrue(response.containsKey(Key.ADMIN_TOKEN));
			String adminToken = response.get(Key.ADMIN_TOKEN).toString();

			// set up dev game
			DevSetUp setUp = getSetup(name1);

			reloadUri();
			target = target.path(Endpoint.Dev.TEST);
			params = new HashMap<String, Object>();
			params.put(Key.TOKEN, token1);
			params.put(Key.ADMIN_TOKEN, adminToken);
			params.put(Key.SETUP, JsonUtil.stringify(setUp));
			response = postRequest(JsonUtil.stringify(params));
			assertTrue(response.containsKey(Key.MESSAGE));
			assertFalse(response.containsKey(Key.ERROR));

			// start game
			reloadUri();
			target = target.path(Endpoint.Lobby.STARTGAME);
			params = new HashMap<String, Object>();
			params.put(Key.TOKEN, token1);
			response = postRequest(JsonUtil.stringify(params));
			assertTrue(response.containsKey(Key.MESSAGE));
			assertFalse(response.containsKey(Key.ERROR));

			// wait for ai to play
			Thread.sleep(Variables.MED_DELAY);

			// keep hand
			reloadUri();
			target = target.path(Endpoint.Game.KEEPHAND);
			params = new HashMap<String, Object>();
			params.put(Key.TOKEN, token1);
			response = postRequest(JsonUtil.stringify(params));
			assertTrue(response.containsKey(Key.GAME));

			ClientPoker poker = JsonUtil.parse(response.get(Key.GAME).toString(), ClientPoker.class);
			assertEquals(0, poker.getNumCardInDeck());
			assertEquals(3, poker.getOpponents().size());

			assertEquals(3, poker.getOpponents().get(0).getVisibleCards().size());
			assertEquals(3, poker.getOpponents().get(1).getVisibleCards().size());
			assertEquals(1, poker.getOpponents().get(2).getVisibleCards().size());

			// end game results
			// wait for results to come in
			Thread.sleep(Variables.SMALL_DELAY);
			Map<String, Object> msg = JsonUtil.parse(ts.getMessages().pop(), Map.class);
			assertTrue(msg.containsKey(Key.RESULTS));
			verifyResults(name1, (List<Result>) JsonUtil.parseList(msg.get(Key.RESULTS).toString(), Result.class));

			// reset
			this.token = token1;
			reset();

			// disconnect websocket
			ts.disconnectFromWebSocket();

			// disconnect
			reloadUri();
			target = target.path(Endpoint.Disconnect.DISCONNECT);
			params = new HashMap<String, Object>();
			params.put(Key.TOKEN, token1);
			response = postRequest(JsonUtil.stringify(params));
			assertTrue(response.containsKey(Key.MESSAGE));
			assertFalse(response.containsKey(Key.ERROR));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private DevSetUp getSetup(String name) {
		DevSetUp setUp = new DevSetUp();
		setUp.setNumPlayers(4);

		setUp.addAiPlayer(2);
		setUp.addAiPlayer(2);
		setUp.addAiPlayer(2);

		List<Card> ai1 = new ArrayList<Card>();
		ai1.add(new Card(Rank.Three, Suit.Hearts));
		ai1.add(new Card(Rank.Six, Suit.Clubs));
		ai1.add(new Card(Rank.Eight, Suit.Hearts));
		ai1.add(new Card(Rank.Jack, Suit.Spades));
		ai1.add(new Card(Rank.Jack, Suit.Hearts));

		List<Card> ai2 = new ArrayList<Card>();
		ai2.add(new Card(Rank.Queen, Suit.Diamonds));
		ai2.add(new Card(Rank.Queen, Suit.Spades));
		ai2.add(new Card(Rank.Five, Suit.Clubs));
		ai2.add(new Card(Rank.Six, Suit.Hearts));
		ai2.add(new Card(Rank.Four, Suit.Clubs));

		List<Card> ai3 = new ArrayList<Card>();
		ai3.add(new Card(Rank.Three, Suit.Hearts));
		ai3.add(new Card(Rank.Ace, Suit.Clubs));
		ai3.add(new Card(Rank.Ace, Suit.Hearts));
		ai3.add(new Card(Rank.Seven, Suit.Spades));
		ai3.add(new Card(Rank.Three, Suit.Clubs));

		setUp.addAiCards(ai1);
		setUp.addAiCards(ai2);
		setUp.addAiCards(ai3);

		setUp.addPlayer(name);

		List<Card> playerCards = new ArrayList<Card>();
		playerCards.add(new Card(Rank.Three, Suit.Hearts));
		playerCards.add(new Card(Rank.Six, Suit.Clubs));
		playerCards.add(new Card(Rank.Eight, Suit.Hearts));
		playerCards.add(new Card(Rank.Jack, Suit.Spades));
		playerCards.add(new Card(Rank.Ace, Suit.Diamonds));

		setUp.addPlayerCards(playerCards);

		List<Card> deck = new ArrayList<Card>();
		deck.add(new Card(Rank.Two, Suit.Clubs));
		deck.add(new Card(Rank.Seven, Suit.Diamonds));
		deck.add(new Card(Rank.King, Suit.Hearts));

		deck.add(new Card(Rank.Nine, Suit.Diamonds));
		deck.add(new Card(Rank.Nine, Suit.Clubs));
		deck.add(new Card(Rank.Nine, Suit.Spades));

		deck.add(new Card(Rank.Ace, Suit.Diamonds));

		setUp.setDeck(deck);
		return setUp;
	}

	private void verifyResults(String user, List<Result> results) {
		Result ai3 = results.get(0);
		assertEquals("3", ai3.getUser().substring(ai3.getUser().length() - 1));
		assertEquals(Ranking.FULL_HOUSE.toString(), ai3.getOutcome());
		assertEquals(Ranking.FULL_HOUSE.getValue(), ai3.getRank());
		
		Result ai2 = results.get(1);
		assertEquals("2", ai2.getUser().substring(ai2.getUser().length() - 1));
		assertEquals(Ranking.FULL_HOUSE.toString(), ai2.getOutcome());
		assertEquals(Ranking.FULL_HOUSE.getValue(), ai2.getRank());
		
		Result ai1 = results.get(2);
		assertEquals(Ranking.ONE_PAIR.toString(), ai1.getOutcome());
		assertEquals(Ranking.ONE_PAIR.getValue(), ai1.getRank());
		
		Result human = results.get(3);
		assertEquals(user, human.getUser());
		assertEquals(Ranking.HIGH_CARD.toString(), human.getOutcome());
		assertEquals(Ranking.HIGH_CARD.getValue(), human.getRank());
	}

}