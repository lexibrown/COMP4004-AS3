package com.lexi.comp4004.common.game.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Result implements Serializable {

	private static final long serialVersionUID = -3903381767066500464L;

	private int rank;
	private String user;
	private String outcome;
	private List<Card> cards;

	public Result() {
		this.setRank(0);
		this.setUser(null);
		this.setOutcome(null);
		this.setCards(new ArrayList<Card>());
	}

	public Result(int rank, String user, String outcome, List<Card> cards) {
		this.setRank(rank);
		this.setUser(user);
		this.setOutcome(outcome);
		this.setCards(cards);
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
	public String toString() {
		String str = getUser() + ": " + getOutcome() + "[" + getRank() + "]\n";
		str += "\tCards: ";
		for (Card c : getCards()) {
			str += c.toString() + ", ";
		}
		return str;
	}

}
