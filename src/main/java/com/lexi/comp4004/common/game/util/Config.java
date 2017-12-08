package com.lexi.comp4004.common.game.util;

public class Config {

	public static final int MAX_CARDS = 5; 
		
	public class Key {
		public static final String TOKEN = "token";
		public static final String USER = "user";

		public static final String GAME = "game";
		public static final String SETUP = "setup";
		public static final String CARDS = "cards";
		public static final String OUTCOME = "outcome";
		public static final String LOBBY = "lobby";
		public static final String STATUS = "status";
		public static final String RESULTS = "results";
		public static final String GAMESTARTED = "gamestarted";

		public static final String ADMIN = "admin";
		public static final String PASSWORD = "password";
		public static final String ADMIN_TOKEN = "admin_token";

		public static final String COMP_TOKEN = "comp_token";
		public static final String COMP = "comp";

		public static final String ERROR = "ERROR";
		public static final String MESSAGE = "MESSAGE";
	}
	
	public class Endpoint {

		public class Connect {
			public static final String CONNECT = "/connect";
		}

		public class Disconnect {
			public static final String DISCONNECT = "/disconnect";
		}

		public class Lobby {
			public static final String STATUS = "/lobby/status";
			public static final String USERS = "/lobby/users";
			public static final String SETUPGAME = "/lobby/setupgame";
			public static final String JOINGAME = "/lobby/joingame";
			public static final String STARTGAME = "/lobby/startgame";
		}

		public class Game {
			public static final String STATUS = "/poker/status";
			public static final String KEEPHAND = "/poker/keephand";
			public static final String SWAPHAND = "/poker/swaphand";

		}

		public class Dev {
			public static final String TEST = "/dev/test";
			public static final String DEV = "/dev";
			public static final String RESET = "/dev/reset";
		}

	}
	
}
