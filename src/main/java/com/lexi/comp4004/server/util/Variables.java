package com.lexi.comp4004.server.util;

import java.util.Arrays;
import java.util.List;

public class Variables {

	public static enum Response {
		OK(0), UNKNOWN_FAILURE(1), TIMEOUT(2), INVALID_USER(3), INVALID_PASSWORD(4), NO_TIMESTAMP(5), NOT_LOGGED_IN(6);

		private int value;

		private Response(int val) {
			this.value = val;
		}

		public int getValue() {
			return this.value;
		}
	}

	public static final String TYPE = "TYPE";
	public static final String USERID = "USER_ID";
	public static final String GROUPID = "GROUP_ID";
	public static final String EVENTID = "EVENT_ID";
	public static final String MESSAGE = "MESSAGE";
	

	
}
