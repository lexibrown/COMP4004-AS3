package com.lexi.comp4004.server.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/poker")
@Produces(MediaType.APPLICATION_JSON)
public class GameApplication implements Application {
	
	public static final String SERVICE = "GAME";

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String defaultEndpoint() {
		return "Game service.";
	}
	
}
