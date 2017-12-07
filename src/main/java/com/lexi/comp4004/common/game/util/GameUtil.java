package com.lexi.comp4004.common.game.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.lexi.comp4004.common.game.Poker;
import com.lexi.comp4004.common.game.data.Card;
import com.lexi.comp4004.common.game.data.Card.Rank;
import com.lexi.comp4004.common.game.data.ClientPoker;
import com.lexi.comp4004.common.game.data.Opponent;
import com.lexi.comp4004.common.game.data.Player;
import com.lexi.comp4004.common.game.data.Result;

public class GameUtil {

	public static ClientPoker getClientView(Poker game, String name) {
		Player player = game.getPlayer(name);
		if (player == null) {
			return null;
		}
		return getClientView(game, player);
	}

	public static ClientPoker getClientView(Poker game, Player player) {
		ClientPoker client = new ClientPoker(player.getName(), player.getHiddenCards(), player.getVisibleCards());

		client.setNumCardInDeck(game.getDeck().size());
		
		for (Player p : game.getPlayers()) {
			if (p.equals(player)) {
				continue;
			}
			client.addOpponent(new Opponent(p.getName(), p.getVisibleCards()));
		}

		if (player.equals(game.whoseTurn())) {
			client.setTurn(true);
		}
		if (player.equals(game.getPlayers().get(0))) {
			client.setFirst(true);
		}
		client.setCurrentTurn(game.whoseTurn().getName());
		return client;
	}

	public static List<Result> determineResults(List<Player> players) {
		List<Result> results = new ArrayList<Result>();
		for (Player p : players) {
			results.add(determineResults(p.getName(), p.getCards()));
		}

		Collections.sort(results, new Comparator<Result>() {
			@Override
			public int compare(Result r1, Result r2) {
				if (r1.getRank() != r2.getRank()) {
					return Integer.compare(r2.getRank(), r1.getRank());
				} else if (betterHand(r1, r2)) {
					return -1;
				}
				return 1;
			}
		});

		return results;
	}

	public static Result determineResults(String name, List<Card> cards) {
		Result result = new Result();
		result.setUser(name);
		result.setCards(cards);

		Ranking r = determineHand(cards);
		result.setOutcome(r.toString());
		result.setRank(r.getValue());
		return result;
	}

	public enum Ranking {
		ROYAL_FLUSH(9, "Royal Flush"), STRAIGHT_FLUSH(8, "Straight Flush"), FOUR_OF_A_KIND(7,
				"Four of a Kind"), FULL_HOUSE(6, "Full House"), FLUSH(5, "Flush"), STRAIGHT(4,
						"Straight"), THREE_OF_A_KIND(3, "Three of a Kind"), TWO_PAIR(2,
								"Two Pair"), ONE_PAIR(1, "One Pair"), HIGH_CARD(0, "High Card");

		int value;
		String name;

		private Ranking(int value, String name) {
			this.value = value;
			this.name = name;
		}

		public int getValue() {
			return value;
		}

		public String toString() {
			return name;
		}
	}

	private static Ranking determineHand(List<Card> cards) {
		if (isRoyalFlush(cards)) {
			return Ranking.ROYAL_FLUSH;
		} else if (isFlush(cards) && isStraight(cards)) {
			return Ranking.STRAIGHT_FLUSH;
		} else if (is4s(cards)) {
			return Ranking.FOUR_OF_A_KIND;
		} else if (isFullHouse(cards)) {
			return Ranking.FULL_HOUSE;
		} else if (isFlush(cards)) {
			return Ranking.FLUSH;
		} else if (isStraight(cards)) {
			return Ranking.STRAIGHT;
		} else if (is3s(cards)) {
			return Ranking.THREE_OF_A_KIND;
		} else if (is22s(cards)) {
			return Ranking.TWO_PAIR;
		} else if (is2s(cards)) {
			return Ranking.ONE_PAIR;
		}
		return Ranking.HIGH_CARD;
	}

	private static boolean isRoyalFlush(List<Card> cards) {
		if (isFlush(cards) && isStraight(cards)) {
			sortByRank(cards);
			return cards.get(0).getRank() == Card.Rank.Ten && cards.get(1).getRank() == Card.Rank.Jack
					&& cards.get(2).getRank() == Card.Rank.Queen && cards.get(3).getRank() == Card.Rank.King
					&& cards.get(4).getRank() == Card.Rank.Ace;
		}
		return false;
	}

	private static boolean isFlush(List<Card> cards) {
		sortBySuit(cards);
		return (cards.get(0).getSuit() == cards.get(cards.size() - 1).getSuit());
	}

	private static boolean isStraight(List<Card> cards) {
		sortByRank(cards);

		if (cards.get(cards.size() - 1).getRank() == Card.Rank.Ace) {
			// 1, 2, 3, 4, 5
			boolean small = cards.get(0).getRank() == Card.Rank.Two && cards.get(1).getRank() == Card.Rank.Three
					&& cards.get(2).getRank() == Card.Rank.Four && cards.get(3).getRank() == Card.Rank.Five;
			// 10, J, Q, K, A
			boolean large = cards.get(0).getRank() == Card.Rank.Ten && cards.get(1).getRank() == Card.Rank.Jack
					&& cards.get(2).getRank() == Card.Rank.Queen && cards.get(3).getRank() == Card.Rank.King;

			return small || large;
		} else {
			int nextRank = cards.get(0).getRank().getValue() + 1;

			for (int i = 1; i < cards.size(); i++) {
				if (cards.get(i).getRank().getValue() != nextRank) {
					return false;
				}
				nextRank++;
			}
			return true;
		}
	}

	private static boolean is4s(List<Card> cards) {
		sortByRank(cards);

		// x x x x y
		boolean low = cards.get(0).getRank() == cards.get(1).getRank()
				&& cards.get(1).getRank() == cards.get(2).getRank() && cards.get(2).getRank() == cards.get(3).getRank();

		// y x x x x
		boolean high = cards.get(1).getRank() == cards.get(2).getRank()
				&& cards.get(2).getRank() == cards.get(3).getRank() && cards.get(3).getRank() == cards.get(4).getRank();
		return low || high;
	}

	private static boolean isFullHouse(List<Card> cards) {
		sortByRank(cards);

		// x x x y y
		boolean low = cards.get(0).getRank() == cards.get(1).getRank()
				&& cards.get(1).getRank() == cards.get(2).getRank() && cards.get(3).getRank() == cards.get(4).getRank();

		// y y x x x
		boolean high = cards.get(0).getRank() == cards.get(1).getRank()
				&& cards.get(2).getRank() == cards.get(3).getRank() && cards.get(3).getRank() == cards.get(4).getRank();
		return low || high;
	}

	private static boolean is3s(List<Card> cards) {
		if (is4s(cards) || isFullHouse(cards)) {
			return false;
		}
		sortByRank(cards);

		// x x x a b
		boolean low = cards.get(0).getRank() == cards.get(1).getRank()
				&& cards.get(1).getRank() == cards.get(2).getRank();

		// a x x x b
		boolean med = cards.get(1).getRank() == cards.get(2).getRank()
				&& cards.get(2).getRank() == cards.get(3).getRank();

		// a b x x x
		boolean high = cards.get(2).getRank() == cards.get(3).getRank()
				&& cards.get(3).getRank() == cards.get(4).getRank();

		return low || med || high;
	}

	private static boolean is22s(List<Card> cards) {
		if (is4s(cards) || isFullHouse(cards) || is3s(cards)) {
			return false;
		}
		sortByRank(cards);

		// a a b b x
		boolean low = cards.get(0).getRank() == cards.get(1).getRank()
				&& cards.get(2).getRank() == cards.get(3).getRank();

		// a a x b b
		boolean med = cards.get(0).getRank() == cards.get(1).getRank()
				&& cards.get(3).getRank() == cards.get(4).getRank();

		// x a a b b
		boolean high = cards.get(1).getRank() == cards.get(2).getRank()
				&& cards.get(3).getRank() == cards.get(4).getRank();

		return low || med || high;
	}

	private static boolean is2s(List<Card> cards) {
		if (is4s(cards) || isFullHouse(cards) || is3s(cards) || is22s(cards)) {
			return false;
		}
		sortByRank(cards);

		// a a x y z
		boolean low = cards.get(0).getRank() == cards.get(1).getRank();

		// x a a y z
		boolean lowMed = cards.get(1).getRank() == cards.get(2).getRank();

		// x y a a z
		boolean highMed = cards.get(2).getRank() == cards.get(3).getRank();

		// x y z a a
		boolean high = cards.get(3).getRank() == cards.get(4).getRank();

		return low || lowMed || highMed || high;
	}

	private static void sortByRank(List<Card> cards) {
		int i, j, min_j;

		for (i = 0; i < cards.size(); i++) {
			min_j = i; // Assume i is the minimum

			for (j = i + 1; j < cards.size(); j++) {
				if (cards.get(j).getRank().getValue() < cards.get(min_j).getRank().getValue()) {
					min_j = j; // found a smaller minimum, update min_j
				}
			}
			Card help = cards.get(i);
			cards.set(i, cards.get(min_j));
			cards.set(min_j, help);
		}
	}

	private static void sortBySuit(List<Card> cards) {
		int i, j, min_j;
		for (i = 0; i < cards.size(); i++) {
			min_j = i; // Assume i is the minimum

			for (j = i + 1; j < cards.size(); j++) {
				if (cards.get(j).getSuit().getValue() < cards.get(min_j).getSuit().getValue()) {
					min_j = j; // found a smaller minimum, update min_j
				}
			}
			Card help = cards.get(i);
			cards.set(i, cards.get(min_j));
			cards.set(min_j, help);
		}
	}

	private static boolean betterHand(Result r1, Result r2) {
		if (r1.getRank() == Ranking.ROYAL_FLUSH.getValue()) {
			return r1.getCards().get(0).getSuit().getValue() > r2.getCards().get(0).getSuit().getValue();
		} else if (r1.getRank() == Ranking.STRAIGHT_FLUSH.getValue()) {
			if (r1.getCards().get(0).getSuit().getValue() == r2.getCards().get(0).getSuit().getValue()) {
				int high1, high2;

				if (hasCardRank(Rank.Ace, r1.getCards()) && hasCardRank(Rank.Two, r1.getCards())) {
					high1 = highestCardWithAce(r1.getCards());
				} else {
					high1 = highestCard(r1.getCards());
				}

				if (hasCardRank(Rank.Ace, r2.getCards()) && hasCardRank(Rank.Two, r2.getCards())) {
					high2 = highestCardWithAce(r2.getCards());
				} else {
					high2 = highestCard(r2.getCards());
				}

				return high1 > high2;
			}
			return r1.getCards().get(0).getSuit().getValue() > r2.getCards().get(0).getSuit().getValue();
		} else if (r1.getRank() == Ranking.FOUR_OF_A_KIND.getValue()) {
			return getMostCommonRank(r1.getCards()) > getMostCommonRank(r2.getCards());
		} else if (r1.getRank() == Ranking.FULL_HOUSE.getValue()) {
			return getMostCommonRank(r1.getCards()) > getMostCommonRank(r2.getCards());
		} else if (r1.getRank() == Ranking.FLUSH.getValue()) {
			if (r1.getCards().get(0).getSuit().getValue() == r2.getCards().get(0).getSuit().getValue()) {
				return highestCard(r1.getCards()) > highestCard(r2.getCards());
			}
			return r1.getCards().get(0).getSuit().getValue() > r2.getCards().get(0).getSuit().getValue();
		} else if (r1.getRank() == Ranking.STRAIGHT.getValue()) {
			return highestCard(r1.getCards()) > highestCard(r2.getCards());
		} else if (r1.getRank() == Ranking.THREE_OF_A_KIND.getValue()) {
			return getMostCommonRank(r1.getCards()) > getMostCommonRank(r2.getCards());
		} else if (r1.getRank() == Ranking.TWO_PAIR.getValue()) {
			return getMostCommonRank(r1.getCards()) > getMostCommonRank(r2.getCards());
		} else if (r1.getRank() == Ranking.ONE_PAIR.getValue()) {
			return getMostCommonRank(r1.getCards()) > getMostCommonRank(r2.getCards());
		}
		return highestCard(r1.getCards()) > highestCard(r2.getCards());
	}

	private static int highestCard(List<Card> cards) {
		int highest = cards.get(0).getRank().getValue();
		for (Card c : cards) {
			if (c.getRank().getValue() > highest) {
				highest = c.getRank().getValue();
			}
		}
		return highest;
	}

	private static int highestCardWithAce(List<Card> cards) {
		int highest = cards.get(0).getRank().getAltValue();
		for (Card c : cards) {
			if (c.getRank().getAltValue() > highest) {
				highest = c.getRank().getAltValue();
			}
		}
		return highest;
	}

	private static boolean hasCardRank(Rank rank, List<Card> cards) {
		for (Card c : cards) {
			if (c.getRank() == rank) {
				return true;
			}
		}
		return false;
	}

	public static int getMostCommonRank(List<Card> cards) {
		int count = 1, tempCount;
		int popular = cards.get(0).getRank().getValue();
		int temp = 0;
		for (int i = 0; i < cards.size() - 1; i++) {
			temp = cards.get(i).getRank().getValue();
			tempCount = 0;
			for (int j = 1; j < cards.size(); j++) {
				if (temp == cards.get(j).getRank().getValue())
					tempCount++;
			}
			if (tempCount > count) {
				popular = temp;
				count = tempCount;
			} else if (tempCount == count) {
				if (temp > popular) {
					popular = temp;
					count = tempCount;
				}
			}
		}
		return popular;
	}

}
