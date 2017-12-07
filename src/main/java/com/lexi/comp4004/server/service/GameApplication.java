package com.lexi.comp4004.server.service;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lexi.comp4004.common.game.data.Card;
import com.lexi.comp4004.common.game.data.ClientPoker;
import com.lexi.comp4004.common.game.util.Config.Key;
import com.lexi.comp4004.server.GameController;
import com.lexi.comp4004.server.Lobby;
import com.lexi.comp4004.server.util.JsonUtil;

@Path("/poker")
@Produces(MediaType.APPLICATION_JSON)
public class GameApplication {

	public static final String SERVICE = "GAME";

	public static final String STATUS = "status";
	public static final String KEEPHAND = "keephand";
	public static final String SWAPHAND = "swaphand";

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String defaultEndpoint() {
		return "Game service.";
	}

	@POST
	@Path(STATUS)
	public String gameStatus(HashMap<String, Object> params) {
		try {
			if (!params.containsKey(Key.TOKEN)) {
				return JsonUtil.errorJson(SERVICE + "-1000", "No token provided.");
			} else if (!Lobby.getInstance().verifyUser(params.get(Key.TOKEN).toString())) {
				return JsonUtil.errorJson(SERVICE + "-1001", "Invalid token.");
			}

			String user = Lobby.getInstance().getUser(params.get(Key.TOKEN).toString());
			ClientPoker poker = GameController.getInstance().getClientView(user);
			if (poker == null) {
				return JsonUtil.errorJson(SERVICE + "-1002", "Failed to retrieve game status.");
			}
			return JsonUtil.makeComplexJson(Key.GAME, poker);
		} catch (Exception e) {
			return JsonUtil.fail(e);
		}
	}

	@POST
	@Path(KEEPHAND)
	public String keepHand(HashMap<String, Object> params) {
		try {
			if (!params.containsKey(Key.TOKEN)) {
				return JsonUtil.errorJson(SERVICE + "-2000", "No token provided.");
			} else if (!Lobby.getInstance().verifyUser(params.get(Key.TOKEN).toString())) {
				return JsonUtil.errorJson(SERVICE + "-2001", "Invalid token.");
			}

			String user = Lobby.getInstance().getUser(params.get(Key.TOKEN).toString());
			ClientPoker poker = GameController.getInstance().keepHand(user);
			if (poker == null) {
				return JsonUtil.errorJson(SERVICE + "-2002", "Failed to keep hand.");
			}
			return JsonUtil.makeComplexJson(Key.GAME, poker);
		} catch (Exception e) {
			return JsonUtil.fail(e);
		}
	}

	@POST
	@Path(SWAPHAND)
	public String swapHand(HashMap<String, Object> params) {
		try {
			if (!params.containsKey(Key.TOKEN)) {
				return JsonUtil.errorJson(SERVICE + "-3000", "No token provided.");
			} else if (!Lobby.getInstance().verifyUser(params.get(Key.TOKEN).toString())) {
				return JsonUtil.errorJson(SERVICE + "-3001", "Invalid token.");
			} else if (!params.containsKey(Key.CARDS)) {
				return JsonUtil.errorJson(SERVICE + "-3000", "No cards provided.");
			}

			String user = Lobby.getInstance().getUser(params.get(Key.TOKEN).toString());

			@SuppressWarnings("unchecked")
			List<Card> cards = (List<Card>) JsonUtil.parseList(params.get(Key.CARDS).toString(), Card.class);

			ClientPoker poker = GameController.getInstance().swapCards(user, cards);
			if (poker == null) {
				return JsonUtil.errorJson(SERVICE + "-3002", "Failed to swap hand.");
			}
			return JsonUtil.makeComplexJson(Key.GAME, poker);

		} catch (Exception e) {
			return JsonUtil.fail(e);
		}
	}

}
