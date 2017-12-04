package com.lexi.comp4004.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lexi.comp4004.common.game.Poker;
import com.lexi.comp4004.common.game.data.AIPlayer;
import com.lexi.comp4004.common.game.data.Card;
import com.lexi.comp4004.common.game.data.ClientPoker;
import com.lexi.comp4004.common.game.data.Player;
import com.lexi.comp4004.common.game.util.GameConverter;
import com.lexi.comp4004.common.template.SetUp;

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
		setGame(setup.setUpGame(getGame()));
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
		game.setPlayers(ai);

		game.dealCards();

		for (Player p : game.getPlayers()) {
			p.play(GameConverter.getClientView(getGame(), p));
		}
	}

	public ClientPoker getClientView(String user) {
		if (canDo(user)) {
			return GameConverter.getClientView(getGame(), user);
		}
		return null;
	}

	public ClientPoker keepHand(String user) {
		if (!canDo(user)) {
			return null;
		}
		
		if (!game.whoseTurn().getName().equals(user)) {
			return null;
		}

		game.nextTurn();
		
		for (Player p : game.getPlayers()) {
			if (p.getName().equals(user)) {
				continue;
			}
			p.play(GameConverter.getClientView(getGame(), p));
		}
		return GameConverter.getClientView(getGame(), game.getPlayer(user));
	}

	public ClientPoker swapCards(String user, List<Card> cards) {
		if (!canDo(user)) {
			return null;
		}

		if (!game.whoseTurn().getName().equals(user)) {
			return null;
		}

		// TODO verify swap
		game.nextTurn();
		
		for (Player p : game.getPlayers()) {
			if (p.getName().equals(user)) {
				continue;
			}
			p.play(GameConverter.getClientView(getGame(), p));
		}
		return GameConverter.getClientView(getGame(), game.getPlayer(user));
	}

	private boolean canDo(String user) {
		if (!isGameStarted()) {
			return false;
		} else if (!isGameSetUp()) {
			return false;
		} else if (!isGameFull()) {
			return false;
		} else if (game.getPlayer(user) == null) {
			return false;
		}
		return true;
	}

}
