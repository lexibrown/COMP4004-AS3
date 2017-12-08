package com.lexi.comp4004.common.game.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.lexi.comp4004.common.game.data.Card;
import com.lexi.comp4004.common.game.data.ClientPoker;
import com.lexi.comp4004.common.game.data.Opponent;
import com.lexi.comp4004.common.game.util.GameUtil;
import com.lexi.comp4004.common.game.util.GameUtil.Ranking;
import com.lexi.comp4004.server.AIConnection;

public class Strategy2 extends Strategy {

	public void doStrategy(ClientPoker poker) {
		if (!poker.isTurn()) {
			return;
		} else if (poker.isFirst()) {
			doStrategy1(poker);
		} else {
			boolean threeOfAKindExists = false;

			for (Opponent o : poker.getOpponents()) {
				if (o.getVisibleCards().size() >= 3) {
					List<Card> cards = o.getVisibleCards();

					int count = 0;
					int dup = GameUtil.getMostCommonRank(cards);
					ListIterator<Card> iter = cards.listIterator();
					while (iter.hasNext()) {
						if (iter.next().getRank().getValue() == dup) {
							count++;
						}
					}
					if (count >= 3) {
						threeOfAKindExists = true;
						break;
					}
				}
			}
			if (threeOfAKindExists) {
				int rank = GameUtil.determineResults(poker.getName(), poker.getCards()).getRank();
				List<Card> cards = new ArrayList<Card>(poker.getHiddenCards());
				if (rank == Ranking.FULL_HOUSE.getValue()) {
					AIConnection.sendKeep(poker.getName());
					return;
				} else if (rank == Ranking.TWO_PAIR.getValue() || rank == Ranking.ONE_PAIR.getValue()
						|| rank == Ranking.THREE_OF_A_KIND.getValue() || rank == Ranking.FOUR_OF_A_KIND.getValue()) {
					int dup = GameUtil.getMostCommonRank(cards);
					ListIterator<Card> iter = cards.listIterator();
					while (iter.hasNext()) {
						if (iter.next().getRank().getValue() == dup) {
							iter.remove();
						}
					}
					// two pairs, so remove again
					if (rank == Ranking.TWO_PAIR.getValue()) {
						dup = GameUtil.getMostCommonRank(cards);
						iter = cards.listIterator();
						while (iter.hasNext()) {
							if (iter.next().getRank().getValue() == dup) {
								iter.remove();
							}
						}
					}
				}
				AIConnection.sendSwap(poker.getName(), cards);
			} else {
				doStrategy1(poker);
			}
		}
	}

	private void doStrategy1(ClientPoker poker) {
		if (!poker.isTurn()) {
			return;
		}
		int rank = GameUtil.determineResults(poker.getName(), poker.getCards()).getRank();
		if (rank >= Ranking.STRAIGHT.getValue()) {
			AIConnection.sendKeep(poker.getName());
		} else {
			List<Card> cards = new ArrayList<Card>(poker.getHiddenCards());
			if (rank != Ranking.HIGH_CARD.getValue()) {
				int dup = GameUtil.getMostCommonRank(cards);
				ListIterator<Card> iter = cards.listIterator();
				while (iter.hasNext()) {
					if (iter.next().getRank().getValue() == dup) {
						iter.remove();
					}
				}

				// two pairs, so remove again
				if (rank == Ranking.TWO_PAIR.getValue()) {
					dup = GameUtil.getMostCommonRank(cards);
					iter = cards.listIterator();
					while (iter.hasNext()) {
						if (iter.next().getRank().getValue() == dup) {
							iter.remove();
						}
					}
				}
			}
			AIConnection.sendSwap(poker.getName(), cards);
		}
	}

	public String toString() {
		String s = "Strategy 2:\n" + "IF you are the first player to play, use Option 1 Strategy 1\n"
				+ "ELSE IF any player before you has 3 visible cards of the same kind: keep your pair(s) if any, and exchange other cards"
				+ "ELSE use Option 1 Strategy 1";
		return s;
	}

}
