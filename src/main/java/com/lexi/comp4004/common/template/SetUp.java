package com.lexi.comp4004.common.template;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class SetUp implements Serializable {

	private static final long serialVersionUID = -5529556614307700040L;

	private int numPlayers = 1;
	private List<Integer> aiPlayers = new ArrayList<Integer>();;

	public abstract SetUpPoker setUpGame(SetUpPoker poker);

	public int getNumPlayers() {
		return numPlayers;
	}

	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}

	public List<Integer> getAiPlayers() {
		return aiPlayers;
	}

	public void setAiPlayers(List<Integer> aiPlayers) {
		this.aiPlayers = aiPlayers;
	}

	public void addAiPlayer(int stragegy) {
		aiPlayers.add(stragegy);
	}

}
