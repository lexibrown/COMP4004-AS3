package com.lexi.comp4004.common.template;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexi.comp4004.common.game.data.Card;

public class DevSetUp extends SetUp implements Serializable {

	private static final long serialVersionUID = 965947400573115615L;

	private List<String> players = new ArrayList<String>();
	private List<List<Card>> playerCards = new ArrayList<List<Card>> ();
	private List<List<Card>> aiCards = new ArrayList<List<Card>>();
	private List<Card> deck;

	@Override
	public SetUpPoker setUpGame(SetUpPoker poker) {
		poker.setMaxNumPlayers(getNumPlayers());
		poker.setPlayers(getPlayers());
		poker.setPlayerCards(getPlayerCards());
		poker.setAiPlayers(getAiPlayers());
		poker.setAiCards(getAiCards());
		poker.setDeck(deck);
		return poker;
	}

	public List<List<Card>>  getPlayerCards() {
		return playerCards;
	}

	public void setPlayerCards(List<List<Card>> playerCards) {
		this.playerCards = playerCards;
	}

	public void addPlayerCards(List<Card> cards) {
		this.playerCards.add(cards);
	}
	
	public List<Card> getDeck() {
		return deck;
	}

	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}

	public List<List<Card>> getAiCards() {
		return aiCards;
	}

	public void setAiCards(List<List<Card>> aiCards) {
		this.aiCards = aiCards;
	}
	
	public void addAiCards(List<Card> aiCards) {
		this.aiCards.add(aiCards);
	}

	public List<String> getPlayers() {
		return players;
	}

	public void setPlayers(List<String> players) {
		this.players = players;
	}
	
	public void addPlayer(String player) {
		this.players.add(player);
	}

}
