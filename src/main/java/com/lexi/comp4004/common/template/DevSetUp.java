package com.lexi.comp4004.common.template;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexi.comp4004.common.game.Poker;
import com.lexi.comp4004.common.game.data.AIPlayer;
import com.lexi.comp4004.common.game.data.Deck;
import com.lexi.comp4004.common.game.data.Player;

public class DevSetUp extends SetUp implements Serializable {

	private static final long serialVersionUID = 965947400573115615L;

	private List<Player> players = new ArrayList<Player>();
	private List<AIPlayer> aiPlayers = new ArrayList<AIPlayer>();
	private Deck deck;
	
	@Override
	public Poker setUpGame(Poker poker) {
		poker.setMaxNumPlayers(getNumPlayers());
		for (int i = 0; i < aiPlayers.size(); i++) {
			poker.addPlayer(aiPlayers.get(i));
		}
		for (int i = 0; i < players.size(); i++) {
			poker.addPlayer(players.get(i));
		}
		if (deck != null) {
			poker.setDeck(deck);
		}
		return poker;
	}

	public void addPlayer(Player player) {
		players.add(player);
	}
	
	public void addAiPlayer(AIPlayer aiPlayer) {
		aiPlayers.add(aiPlayer);
	}
	
	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<AIPlayer> getAIPlayers() {
		return aiPlayers;
	}

	public void setAIPlayers(List<AIPlayer> aiPlayers) {
		this.aiPlayers = aiPlayers;
	}
	
	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	
}
