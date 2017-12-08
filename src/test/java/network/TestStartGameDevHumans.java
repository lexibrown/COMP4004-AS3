package network;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.ClientBuilder;

import org.junit.Before;
import org.junit.Test;

import com.lexi.comp4004.common.game.data.Card;
import com.lexi.comp4004.common.game.data.Card.Rank;
import com.lexi.comp4004.common.game.data.Card.Suit;
import com.lexi.comp4004.common.game.util.Config.Endpoint;
import com.lexi.comp4004.common.game.util.Config.Key;
import com.lexi.comp4004.common.template.DevSetUp;
import com.lexi.comp4004.server.util.JsonUtil;
import com.lexi.comp4004.server.util.Variables;

public class TestStartGameDevHumans extends TestNetworkFramework {

	private String token1;
	private String token2;
	private String token3;

	@Before
	public void setUp() {
		client = ClientBuilder.newClient();
		target = client.target(Variables.baseUri);
	}

	@Test
	public void testStartGameDevAi() {
		Map<String, Object> response = null;
		Map<String, Object> params = null;
		String name1 = "testStartGameDevAi1";
		String name2 = "testStartGameDevAi2";
		String name3 = "testStartGameDevAi3";

		try {
			// connect
			reloadUri();
			target = target.path(Endpoint.Connect.CONNECT);
			response = connectRequest(name1);
			assertTrue(response.containsKey(Key.TOKEN));
			token1 = response.get(Key.TOKEN).toString();

			// connect
			reloadUri();
			target = target.path(Endpoint.Connect.CONNECT);
			response = connectRequest(name2);
			assertTrue(response.containsKey(Key.TOKEN));
			token2 = response.get(Key.TOKEN).toString();

			// connect
			reloadUri();
			target = target.path(Endpoint.Connect.CONNECT);
			response = connectRequest(name3);
			assertTrue(response.containsKey(Key.TOKEN));
			token3 = response.get(Key.TOKEN).toString();

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
			DevSetUp setUp = getSetup(name1, name2, name3);

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

			// disconnect
			reloadUri();
			target = target.path(Endpoint.Disconnect.DISCONNECT);
			params = new HashMap<String, Object>();
			params.put(Key.TOKEN, token2);
			response = postRequest(JsonUtil.stringify(params));
			assertTrue(response.containsKey(Key.MESSAGE));
			assertFalse(response.containsKey(Key.ERROR));

			// disconnect
			reloadUri();
			target = target.path(Endpoint.Disconnect.DISCONNECT);
			params = new HashMap<String, Object>();
			params.put(Key.TOKEN, token3);
			response = postRequest(JsonUtil.stringify(params));
			assertTrue(response.containsKey(Key.MESSAGE));
			assertFalse(response.containsKey(Key.ERROR));

			// reset
			this.token = token1;
			reset();

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

	private DevSetUp getSetup(String name1, String name2, String name3) {
		DevSetUp setUp = new DevSetUp();
		setUp.setNumPlayers(3);

		setUp.addPlayer(name1);
		setUp.addPlayer(name2);
		setUp.addPlayer(name3);

		List<Card> playerCards1 = Arrays.asList(new Card(Rank.Jack, Suit.Hearts), new Card(Rank.King, Suit.Spades),
				new Card(Rank.Five, Suit.Hearts), new Card(Rank.Four, Suit.Spades), new Card(Rank.Four, Suit.Spades));
		List<Card> playerCards2 = Arrays.asList(new Card(Rank.Ace, Suit.Clubs), new Card(Rank.Eight, Suit.Diamonds),
				new Card(Rank.Eight, Suit.Diamonds), new Card(Rank.Eight, Suit.Diamonds),
				new Card(Rank.Eight, Suit.Diamonds));
		List<Card> playerCards3 = Arrays.asList(new Card(Rank.Five, Suit.Hearts), new Card(Rank.Four, Suit.Spades),
				new Card(Rank.Four, Suit.Spades), new Card(Rank.Four, Suit.Spades), new Card(Rank.Four, Suit.Spades));

		setUp.addPlayerCards(playerCards1);
		setUp.addPlayerCards(playerCards2);
		setUp.addPlayerCards(playerCards3);

		List<Card> deck = Arrays.asList(new Card(Rank.Ace, Suit.Clubs), new Card(Rank.Eight, Suit.Diamonds),
				new Card(Rank.Five, Suit.Hearts), new Card(Rank.Four, Suit.Spades));
		setUp.setDeck(deck);
		return setUp;
	}
}
