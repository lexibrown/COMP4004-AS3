package com.lexi.comp4004.common.game.strategy;

import java.util.List;

import com.lexi.comp4004.common.game.data.ClientPoker;
import com.lexi.comp4004.common.game.data.Result;
import com.lexi.comp4004.server.Connection;

public class PlayerStrategy extends Strategy {

	public void doStrategy(ClientPoker poker) {
		Connection.broadcastGame(poker.getName(), poker);
	}

	public void informWin(String user,  List<Result> results) {
		Connection.broadcastEndGame(user, results);
	}

	public void init(String user) {
		Connection.broadcastStartGame(user);
	}

	public String toString() {
		return "Contracts the player to play their turn.";
	}

}
