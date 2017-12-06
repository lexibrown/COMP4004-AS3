package com.lexi.comp4004.common.template;

import java.util.ArrayList;
import java.util.List;

import com.lexi.comp4004.common.game.data.Card;

public class SetUpPoker {

	private List<Card> deck = new ArrayList<Card>();
	private String playerName;
	private List<Card> playerCards = new ArrayList<Card>();

	private List<Integer> aiPlayers = new ArrayList<Integer>();
	private List<List<Card>> aiCards = new ArrayList<List<Card>>();
	
	private int maxNumPlayers = 2;
	
	public SetUpPoker() {
		playerName = null;
	}
	
	public List<Card> getDeck() {
		return deck;
	}
	
	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}
	
	public int getMaxNumPlayers() {
		return maxNumPlayers;
	}

	public void setMaxNumPlayers(int maxNumPlayers) {
		this.maxNumPlayers = maxNumPlayers;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public List<Card> getPlayerCards() {
		return playerCards;
	}

	public void setPlayerCards(List<Card> playerCards) {
		this.playerCards = playerCards;
	}


	public List<Integer> getAiPlayers() {
		return aiPlayers;
	}


	public void setAiPlayers(List<Integer> aiPlayers) {
		this.aiPlayers = aiPlayers;
	}


	public List<List<Card>> getAiCards() {
		return aiCards;
	}


	public void setAiCards(List<List<Card>> aiCards) {
		this.aiCards = aiCards;
	}
	
}
