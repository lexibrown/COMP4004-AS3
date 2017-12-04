package com.lexi.comp4004.common.game.util;

import com.lexi.comp4004.common.game.Poker;
import com.lexi.comp4004.common.game.data.ClientPoker;
import com.lexi.comp4004.common.game.data.Opponent;
import com.lexi.comp4004.common.game.data.Player;

public class GameConverter {

	public static ClientPoker getClientView(Poker game, String name) {
		Player player = game.getPlayer(name);
		if (player == null) {
			return null;
		}
		return getClientView(game, player);
	}

	public static ClientPoker getClientView(Poker game, Player player) {
		ClientPoker client = new ClientPoker(player);
		
		client.setNumCardInDeck(game.getDeck().size());
		
		for (Player p : game.getPlayers()) {
			if (p.equals(player)) {
				continue;
			}
			client.addOpponent(new Opponent(p));
		}
		
		return client;
	}
	
}
