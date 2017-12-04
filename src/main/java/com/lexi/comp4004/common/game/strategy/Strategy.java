package com.lexi.comp4004.common.game.strategy;

import com.lexi.comp4004.common.game.data.ClientPoker;

public abstract class Strategy {
	public abstract void doStrategy(ClientPoker poker);
	public abstract String toString();
}
