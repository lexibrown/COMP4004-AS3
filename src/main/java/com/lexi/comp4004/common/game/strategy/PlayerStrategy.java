package com.lexi.comp4004.common.game.strategy;

import com.lexi.comp4004.common.game.data.ClientPoker;
import com.lexi.comp4004.common.game.data.Result;
import com.lexi.comp4004.server.Connection;

public class PlayerStrategy extends Strategy {

	public void doStrategy(ClientPoker poker) {
		Connection.broadcastGame(poker.getName(), poker);
	}

	public void informWin(String user, Result results) {
		Connection.broadcastEndGame(user, results);
	}

	public String toString() {
		return "Contracts the player to play their turn.";
	}

}
