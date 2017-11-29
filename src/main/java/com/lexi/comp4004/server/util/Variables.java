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
	
	public static enum NotificationType {
		MESSAGE("NEW_MESSAGE"), JOINED("EVENT_JOINED"), APPROVED("EVENT_APPROVED"), DISAPPROVED(
				"EVENT_DISAPPROVED"), REMOVED("EVENT_REMOVED"), FOLLOW("USER_FOLLOW");

		private String value;

		private NotificationType(String val) {
			this.value = val;
		}

		public String getValue() {
			return this.value;
		}
	}

	public static enum Frequency {
		ONE_TIME("One Time"), WEEKLY("Weekly"), BIWEEKLY("Bi-weekly"), MONTHLY("Monthly");

		private String value;

		private Frequency(String val) {
			this.value = val;
		}

		public String getValue() {
			return this.value;
		}
	}

	public static final String[] frequencies = { Frequency.ONE_TIME.getValue(), Frequency.WEEKLY.getValue(),
			Frequency.BIWEEKLY.getValue(), Frequency.MONTHLY.getValue() };

	public class EventParams {
        public static final String ID = "id";
		public static final String CREATOR = "creator";
		public static final String ICON = "creatorIcon";
		public static final String GAME = "gameType";
		public static final String LOOKING = "lookingFor";
		public static final String NEXT = "nextGame";
		public static final String MAX = "maxPlayers";
		public static final String FREQUENCY = "frequency";
		public static final String OCCURRENCES = "occurrences";
		public static final String LANG = "language";
		public static final String NEW = "newPlayers";
		public static final String MATURE = "matureContent";
		public static final String DESC = "description";
	}

	public static final List<String> eventParams = Arrays.asList(EventParams.ID, EventParams.CREATOR, EventParams.GAME,
			EventParams.LOOKING, EventParams.NEXT, EventParams.MAX, EventParams.FREQUENCY, EventParams.OCCURRENCES,
			EventParams.LANG, EventParams.NEW, EventParams.MATURE, EventParams.DESC);

	public class ProfileParams {
        public static final String STATUS = "status";
        public static final String ICON = "icon";
        public static final String BACKGROUND = "background";
	}

	public static final List<String> profileParams = Arrays.asList(ProfileParams.STATUS, ProfileParams.ICON,
			ProfileParams.BACKGROUND);

	public static final int TIMEOUT_TIME = 5 * 60; // 5 minutes
	public static int NOTIFICATION_DELAY = 3 * 1000; // 3 seconds

	public static final int MAX_PASSWORD_LENGTH = 16;
	public static final int MIN_PASSWORD_LENGTH = 5;

	public static final String defaultName = "default";
	public static final String defaultPassword = "password";
	public static final String defaultStatus = "Let's play!";
	
}
