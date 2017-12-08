package game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.lexi.comp4004.common.game.Poker;
import com.lexi.comp4004.common.game.data.AIPlayer;
import com.lexi.comp4004.common.game.data.Card;
import com.lexi.comp4004.common.game.data.Card.Rank;
import com.lexi.comp4004.common.game.data.Card.Suit;
import com.lexi.comp4004.common.game.data.Player;
import com.lexi.comp4004.common.game.strategy.PlayerStrategy;
import com.lexi.comp4004.common.game.strategy.Strategy1;
import com.lexi.comp4004.common.game.strategy.Strategy2;
import com.lexi.comp4004.common.template.ClientSetUp;
import com.lexi.comp4004.common.template.DevSetUp;
import com.lexi.comp4004.common.template.SetUp;
import com.lexi.comp4004.common.template.SetUpUtil;

public class TestSetup {

	@Test
	public void testSetup() {
		SetUp setUp = new ClientSetUp();
		setUp.setNumPlayers(4);
		setUp.addAiPlayer(1);
		setUp.addAiPlayer(2);
		setUp.addAiPlayer(1);

		Poker poker = SetUpUtil.setUpGame(new Poker(), setUp);

		assertEquals(setUp.getNumPlayers(), poker.getMaxNumPlayers());
		assertEquals(3, poker.getPlayers().size());

		for (Player p : poker.getPlayers()) {
			assertTrue(p.getClass().equals(AIPlayer.class));
		}

		assertEquals(Strategy1.class, poker.getPlayers().get(0).getStrategy().getClass());
		assertEquals(Strategy2.class, poker.getPlayers().get(1).getStrategy().getClass());
		assertEquals(Strategy1.class, poker.getPlayers().get(2).getStrategy().getClass());
	}

	@Test
	public void testDevSetup() {
		String name = "test1";
		DevSetUp setUp = new DevSetUp();
		setUp.setNumPlayers(3);

		setUp.addAiPlayer(1);
		setUp.addAiPlayer(2);

		List<Card> ai1 = Arrays.asList(new Card(Rank.Ace, Suit.Clubs), new Card(Rank.Eight, Suit.Diamonds));
		List<Card> ai2 = Arrays.asList(new Card(Rank.Five, Suit.Hearts), new Card(Rank.Four, Suit.Spades));

		setUp.addAiCards(ai1);
		setUp.addAiCards(ai2);

		setUp.addPlayer(name);
		List<Card> playerCards = Arrays.asList(new Card(Rank.Jack, Suit.Hearts), new Card(Rank.King, Suit.Spades));
		setUp.addPlayerCards(playerCards);

		List<Card> deck = Arrays.asList(new Card(Rank.Ace, Suit.Clubs), new Card(Rank.Eight, Suit.Diamonds),
				new Card(Rank.Five, Suit.Hearts), new Card(Rank.Four, Suit.Spades));
		setUp.setDeck(deck);

		Poker poker = SetUpUtil.setUpGame(new Poker(), setUp);

		assertEquals(setUp.getNumPlayers(), poker.getMaxNumPlayers());
		assertEquals(3, poker.getPlayers().size());

		assertEquals(Strategy1.class, poker.getPlayers().get(0).getStrategy().getClass());
		assertEquals(ai1, poker.getPlayers().get(0).getHiddenCards());

		assertEquals(Strategy2.class, poker.getPlayers().get(1).getStrategy().getClass());
		assertEquals(ai2, poker.getPlayers().get(1).getHiddenCards());

		assertEquals(PlayerStrategy.class, poker.getPlayers().get(2).getStrategy().getClass());
		assertEquals(playerCards, poker.getPlayers().get(2).getHiddenCards());
		assertEquals(name, poker.getPlayers().get(2).getName());

		assertEquals(deck, poker.getDeck().getCards());
	}

}
