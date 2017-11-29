package com.lexi.comp4004.game.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.*;

public class Player {

	String name;
	int cardIndex = 0;
	Card[] cards = new Card[21];
	Player split = null;
	double money = 500.00;
	double totalBet = 0;
	DecimalFormat x = new DecimalFormat("$##0.00");

	public Player(String name) {
		this.name = name;
	}

	public void clear() {
		for (int i = 0; i < 21; i++) {
			cards[i] = null;
		}
		cardIndex = 0;
		totalBet = 0;
	}

	public boolean isPlayer() {
		return true;
	}
	
	public boolean deal(Card card) {
		int total = 0;
		boolean printedTotal = false;

		cards[cardIndex++] = card;

		if (isPlayer() || (!isPlayer() && cardIndex > 1)) {
			System.out.println(name + "'s new card is the " + card + ".");
		}
		System.out.print(name + "'s cards: ");
		if (isPlayer() || cardIndex > 2) {
			for (int i = 0; i < cardIndex; i++) {
				if (i > 0) {
					System.out.print(", ");
				}
				System.out.print(cards[i]);
			}
			System.out.print(", Total is ");
		} else {
			System.out.print("Down Card");
			for (int i = 1; i < cardIndex; i++) {
				if (i > 0) {
					System.out.print(", ");
				}
				System.out.print(cards[i]);
			}
		}

		total = total(-1);
		
		if (total > 21) {
			for (int x = 0; x < cardIndex; x++) {
				if (cards[x].getRank() == Card.Rank.Ace) {
					total = total(x);
					if (printedTotal) {
						System.out.print(" or ");
					}
					if (total <= 21) {
						System.out.print(total);
						printedTotal = true;
					}
				}
			}
			if (total > 21) {
				if (!isPlayer()) {
					System.out.print(", Total is ");
				}
				System.out.println(total + ".");
				return true;
			}
		} else {
			if (isPlayer() || cardIndex > 2) {
				System.out.print(total);
			}
		}
		System.out.println(".");
		return false;
	}

	// totals card points
	public int total(int x) {
		int total = 0;
		for (int i = 0; i < cardIndex; i++) {
			if (i <= x && cards[i].getRank() == Card.Rank.Ace) {
				total += cards[i].getRank().getAltValue();
			} else {
				total += cards[i].getRank().getValue();
			}
		}
		return total;
	}

	public int finalTotal() {
		int total = total(-1);

		if (total > 21) {
			for (int x = 0; x < cardIndex; x++) {
				if (cards[x].getRank() == Card.Rank.Ace) {
					total = total(x);
					if (total <= 21) {
						break;
					}
				}
			}
		}
		return total;
	}
	
	// asks the player if s/he want to hit or stay
	public boolean hit(BufferedReader c) {
		String answer = null;

		while (true) {
			System.out.println();
			System.out.println("Will you stay or hit?");
			try {
				answer = c.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if ("hit".equalsIgnoreCase(answer)) {
				return true;
			} else if ("stay".equalsIgnoreCase(answer)) {
				return false;
			} else {
				System.out.println("Please enter 'hit' or 'stay'.");
			}
		}
	}

	// takes money bet from players money
	public double bet(double bet) {
		if (bet > 0.0) {
			money -= bet;
			System.out.println(name + " bet " + x.format(bet));
			System.out.println(name + "'s money is " + x.format(money));
			totalBet += bet;
		}
		return totalBet;
	}

	// returns the total amount the player has bet
	public double totalBet() {
		return totalBet;
	}

	// ask if player wants to split
	public Player split(BufferedReader c) {
		if (cards[0].getRank().equals(cards[1].getRank())) {
			String answer = null;

			while (true) {
				System.out.println("Do you want to split?");
				try {
					answer = c.readLine();
				} catch (IOException e) {
				}

				if ("yes".equalsIgnoreCase(answer)) {
					split = new Player(name + " (split)");
					split.deal(cards[1]);
					cards[1] = null;
					cardIndex--;
					return split;
				} else if ("no".equalsIgnoreCase(answer)) {
					return null;
				} else {
					System.out.println("Please enter 'yes' or 'no'.");
				}
			}
		}
		return null;
	}

	// asks the player if s/he wants to play again
	public boolean playAgain(BufferedReader c) {
		String answer = null;

		while (true) {
			System.out.println("Do you want to play again?");
			try {
				answer = c.readLine();
			} catch (IOException e) {
			}

			if ("yes".equalsIgnoreCase(answer)) {
				clear();
				System.out.println();
				return true;
			} else if ("no".equalsIgnoreCase(answer)) {
				return false;
			} else {
				System.out.println("Please enter 'yes' or 'no'.");
			}
		}
	}
}
