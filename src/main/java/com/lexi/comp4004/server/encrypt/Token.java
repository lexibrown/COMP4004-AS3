package com.lexi.comp4004.server.encrypt;

public class Token {

	private String timestamp;
	private String userid;

	public Token() {
		this.userid = null;
		this.timestamp = null;
	}
	
	public Token(String userid, String timestamp) {
		this.userid = userid;
		this.timestamp = timestamp;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}