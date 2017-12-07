package com.lexi.comp4004.common.template;

import java.util.ArrayList;
import java.util.List;

import com.lexi.comp4004.common.game.data.Card;

public class SetUpPoker {

	private List<Card> deck = new ArrayList<Card>();
	private List<String> players = new ArrayList<String>();
	private List<List<Card>>  playerCards = new ArrayList<List<Card>> ();

	private List<Integer> aiPlayers = new ArrayList<Integer>();
	private List<List<Card>> aiCards = new ArrayList<List<Card>>();
	
	private int maxNumPlayers = 2;
	
	public SetUpPoker() {
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

	public List<List<Card>>  getPlayerCards() {
		return playerCards;
	}

	public void setPlayerCards(List<List<Card>>  playerCards) {
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

	public List<String> getPlayers() {
		return players;
	}

	public void setPlayers(List<String> players) {
		this.players = players;
	}
	
}
