package com.lexi.comp4004.server.util;

public class Variables {

	public static final int SMALL_DELAY = 500; // half a second
	public static final int MED_DELAY = 750; // 3/4 a second

	// for dev testing only
	public static final String ADMIN = "lexibrown";
	public static final String ADMIN_PASSWORD = "password1";
	
	// for ai only
	public static final String COMPUTER = "computer";
	public static final String COMP_PASSWORD = ADMIN_PASSWORD;
	public static final String COMP_TOKEN = "comp_token";
	public static final String COMP = "comp";
	public static final String CARDS = "cards";

	public static final String baseUri = "http://localhost:8080/COMP4004-AS3";

}
