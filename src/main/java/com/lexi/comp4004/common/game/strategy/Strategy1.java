package com.lexi.comp4004.common.game.strategy;

import java.util.List;
import java.util.ListIterator;

import com.lexi.comp4004.common.game.data.Card;
import com.lexi.comp4004.common.game.data.ClientPoker;
import com.lexi.comp4004.common.game.util.GameUtil;
import com.lexi.comp4004.common.game.util.GameUtil.Ranking;
import com.lexi.comp4004.server.AIConnection;

public class Strategy1 extends Strategy {

	public void doStrategy(ClientPoker poker) {
		if (!poker.isTurn()) {
			return;
		}

		int rank = GameUtil.determineResults(poker.getPlayer()).getRank();
		if (rank >= Ranking.STRAIGHT.getValue()) {
			AIConnection.sendKeep(poker.getPlayer().getName());
		} else {
			List<Card> cards = poker.getPlayer().getHiddenCards();
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
			AIConnection.sendSwap(poker.getPlayer().getName(), cards);
		}
	}

	public String toString() {
		String s = "Strategy 1:\n" + "- if this AI player has a straight or better, hold (ie do not exchange any card\n"
				+ "- else this AI player attempts to get a full house by exchanging everything that is not a pair or 3 of a kind";
		return s;
	}

}
