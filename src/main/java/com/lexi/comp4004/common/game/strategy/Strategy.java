package com.lexi.comp4004.common.game.strategy;

import java.util.List;

import com.lexi.comp4004.common.game.data.ClientPoker;
import com.lexi.comp4004.common.game.data.Result;

public abstract class Strategy {
	public abstract void doStrategy(ClientPoker poker);
	public abstract String toString();

	public void informWin(String user, List<Result> results) {
		// do nothing
	}

}
