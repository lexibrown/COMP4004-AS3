package com.lexi.comp4004.server.service;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lexi.comp4004.common.game.data.Card;
import com.lexi.comp4004.common.game.data.ClientPoker;
import com.lexi.comp4004.server.GameController;
import com.lexi.comp4004.server.util.JsonUtil;
import com.lexi.comp4004.server.util.TokenUtil;
import com.lexi.comp4004.server.util.Variables;

@Path("/ai")
@Produces(MediaType.APPLICATION_JSON)
public class AIApplication implements Application {

	public static final String SERVICE = "AI";

	public static final String KEEPHAND = "keephand";
	public static final String SWAPHAND = "swaphand";
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String defaultEndpoint() {
		return "Ai service.";
	}

	@GET
	@Path("test/{user}")
	public String test(@PathParam("user") String user) {
		try {
			ClientPoker poker = GameController.getInstance().getClientView(user);
			return JsonUtil.makeJson(GAME, poker);
		} catch (Exception e) {
			return JsonUtil.fail(e);
		}
	}
	
	@POST
	@Path(KEEPHAND)
	public String keepHand(HashMap<String, Object> params) {
		try {
			if (!params.containsKey(Variables.COMP_TOKEN)) {
				return JsonUtil.errorJson(SERVICE + "-6000", "No token provided.");
			} else if (!verifyComputerToken(params.get(Variables.COMP_TOKEN).toString())) {
				return JsonUtil.errorJson(SERVICE + "-6001", "Invalid token.");
			} else if (!params.containsKey(Variables.COMP)) {
				return JsonUtil.errorJson(SERVICE + "-6000", "No name provided.");
			}

			String user = params.get(Variables.COMP).toString();
			ClientPoker poker = GameController.getInstance().keepHand(user);
			if (poker == null) {
				return JsonUtil.errorJson(SERVICE + "-6002", "Failed to keep hand.");
			}
			return JsonUtil.makeComplexJson(GAME, poker);
		} catch (Exception e) {
			return JsonUtil.fail(e);
		}
	}

	@POST
	@Path(SWAPHAND)
	public String swapHand(HashMap<String, Object> params) {
		try {
			if (!params.containsKey(Variables.COMP_TOKEN)) {
				return JsonUtil.errorJson(SERVICE + "-6000", "No token provided.");
			} else if (!verifyComputerToken(params.get(Variables.COMP_TOKEN).toString())) {
				return JsonUtil.errorJson(SERVICE + "-6001", "Invalid token.");
			} else if (!params.containsKey(Variables.COMP)) {
				return JsonUtil.errorJson(SERVICE + "-6000", "No name provided.");
			} else if (!params.containsKey(Variables.CARDS)) {
				return JsonUtil.errorJson(SERVICE + "-6000", "No cards provided.");
			}

			String user = params.get(Variables.COMP).toString();

			@SuppressWarnings("unchecked")
			List<Card> cards = (List<Card>) params.get(Variables.CARDS);

			ClientPoker poker = GameController.getInstance().swapCards(user, cards);
			if (poker == null) {
				return JsonUtil.errorJson(SERVICE + "-6002", "Failed to swap hand.");
			}
			return JsonUtil.makeComplexJson(GAME, poker);
		} catch (Exception e) {
			return JsonUtil.fail(e);
		}
	}

	private boolean verifyComputerToken(String token) {
		String comp = TokenUtil.pullUsername(token);
		String password = TokenUtil.pullPassowrd(token);
		return Variables.COMPUTER.equals(comp) && Variables.COMP_PASSWORD.equals(password);
	}

}
