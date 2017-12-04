package com.lexi.comp4004.common.game.strategy;

import com.lexi.comp4004.common.game.data.ClientPoker;
import com.lexi.comp4004.common.game.data.Results;

public abstract class Strategy {
	public abstract void doStrategy(ClientPoker poker);
	public abstract String toString();

	public void informWin(String user, Results results) {
		// do nothing
	}

}
