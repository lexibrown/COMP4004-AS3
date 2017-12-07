package com.lexi.comp4004.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lexi.comp4004.common.game.Poker;
import com.lexi.comp4004.common.game.data.AIPlayer;
import com.lexi.comp4004.common.game.data.Card;
import com.lexi.comp4004.common.game.data.ClientPoker;
import com.lexi.comp4004.common.game.data.Player;
import com.lexi.comp4004.common.game.data.Result;
import com.lexi.comp4004.common.game.util.GameUtil;
import com.lexi.comp4004.common.template.SetUp;
import com.lexi.comp4004.common.template.SetUpUtil;
import com.lexi.comp4004.server.util.Variables;

public class GameController {

	private static GameController instance;

	private Poker game;
	private boolean gameStarted = false;
	private boolean gameSetUp = false;

	private GameController() {
		game = new Poker();
		gameSetUp = false;
		gameStarted = false;
	}

	public static GameController getInstance() {
		if (instance == null) {
			instance = new GameController();
		}
		return instance;
	}

	public void setUpGame(SetUp setup) {
		setGame(SetUpUtil.setUpGame(getGame(), setup));
		gameSetUp = true;
	}

	public void setGame(Poker p) {
		this.game = p;
	}

	public Poker getGame() {
		return game;
	}

	public void reset() {
		gameStarted = false;
		gameSetUp = false;
		game = new Poker();
	}

	public boolean isGameStarted() {
		return gameStarted;
	}

	public boolean isGameSetUp() {
		return gameSetUp;
	}

	public boolean isGameFull() {
		return game.isFull();
	}

	public boolean joinGame(String user) {
		if (isGameFull()) {
			return false;
		} else if (isGameStarted()) {
			return false;
		} else if (!isGameSetUp()) {
			return false;
		} else if (game.getPlayer(user) != null) {
			return true;
		}
		return game.addPlayer(user);
	}

	public boolean startGame(String user) {
		if (isGameStarted()) {
			return false;
		} else if (!isGameSetUp()) {
			return false;
		} else if (!isGameFull()) {
			return false;
		} else if (game.getPlayer(user) == null) {
			return false;
		}

		startGame();
		return true;
	}

	private void startGame() {
		gameStarted = true;

		// human players play in reserve order of joining
		List<Player> ai = new ArrayList<Player>();
		List<Player> humans = new ArrayList<Player>();
		for (int i = 0; i < game.getPlayers().size(); i++) {
			Player p = game.getPlayers().get(i);
			if (p.getClass().equals(AIPlayer.class)) {
				ai.add(p);
			} else {
				humans.add(p);
			}
		}
		Collections.reverse(humans);
		ai.addAll(humans);
		
		System.out.println(ai);
		
		game.setPlayers(ai);

		game.dealCards();

		System.out.println(game.getPlayers());
		
		// so that ai don't play before everyone is updated that game started
		for (Player p : game.getPlayers()) {
			p.init();
		}
		for (Player p : game.getPlayers()) {
			p.play(GameUtil.getClientView(getGame(), p));
		}
	}

	public ClientPoker getClientView(String user) {
		if (canDo(user)) {
			return GameUtil.getClientView(getGame(), user);
		}
		return null;
	}

	public ClientPoker keepHand(String user) {
		if (!canDo(user)) {
			return null;
		}

		game.nextTurn();
		return updatePlayers(user);
	}

	public ClientPoker swapCards(String user, List<Card> cards) {
		if (!canDo(user)) {
			System.out.println("cantdo");
			return null;
		} else if (!game.getPlayer(user).hasCards(cards)) {
			System.out.println("nohas");
			return null;
		}

		for (Card exchanged : cards) {
			Card newCard = game.deal();
			if (newCard == null) {
				System.out.println("nullcard");
				return null;
			}
			game.getPlayer(user).exchangeCard(exchanged, newCard);
		}
		
		game.nextTurn();
		return updatePlayers(user);
	}

	private boolean canDo(String user) {
		if (!isGameStarted()) {
			System.out.println("1");
			return false;
		} else if (!isGameSetUp()) {
			System.out.println("2");
			return false;
		} else if (!isGameFull()) {
			System.out.println("3");
			return false;
		} else if (game.getPlayer(user) == null) {
			System.out.println("4");
			return false;
		} else if (!game.isTurn(user)) {
			System.out.println("5");
			return false;
		}
		return true;
	}

	private ClientPoker updatePlayers(String user) {
		for (Player p : game.getPlayers()) {
			if (p.getName().equals(user)) {
				continue;
			}
			p.play(GameUtil.getClientView(getGame(), p));
		}
		verifyWinner();
		return GameUtil.getClientView(getGame(), user);
	}

	private void verifyWinner() {
		if (game.whoseTurn() == null) {

			List<Result> results = GameUtil.determineResults(getGame().getPlayers());
			
			// so everyone give updated first (Websockets are faster than http
			// requests)
			(new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(Variables.SMALL_DELAY);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					for (Player p : game.getPlayers()) {
						p.informWinner(results);
					}
				}
			})).start();
		}
	}

}
