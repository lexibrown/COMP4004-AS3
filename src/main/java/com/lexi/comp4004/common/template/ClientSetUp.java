package com.lexi.comp4004.common.template;

import java.io.Serializable;

public class ClientSetUp extends SetUp implements Serializable {

	private static final long serialVersionUID = 577920321916495774L;

	@Override
	public SetUpPoker setUpGame(SetUpPoker poker) {
		poker.setMaxNumPlayers(getNumPlayers());
		poker.setAiPlayers(getAiPlayers());
		return poker;
	}

}
