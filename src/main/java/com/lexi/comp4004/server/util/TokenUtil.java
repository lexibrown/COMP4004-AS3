package com.lexi.comp4004.server.util;

import com.lexi.comp4004.encrypt.AESEncrypter;
import com.lexi.comp4004.encrypt.Token;
import com.lexi.comp4004.server.util.Variables.Response;

public class TokenUtil {

	public static Token constuctToken(String username) throws Exception {
		return new Token(username, TimeUtil.now());
	}
	
	public static String encypt(String username, String timestamp) throws Exception {
		String token = AESEncrypter.encrypt(username + AESEncrypter.DELIMITER + timestamp);
		return token;
	}

	public static String encryt(Token t) throws Exception {
		String token = AESEncrypter.encrypt(t.getUserid() + AESEncrypter.DELIMITER + t.getTimestamp());
		return token;
	}

	public static Response verify(String encryptToken) {
		return verify(encryptToken, true);
	}

	public static Response verify(String encryptToken, boolean update) {
		String token = null;
		String userid = null;

		try {
			token = AESEncrypter.decrypt(encryptToken);
		} catch (Exception e) {
			return Response.UNKNOWN_FAILURE;
		}

		try {
			userid = token.split(AESEncrypter.DELIMITER)[0];
			if (!Lobby.userExists(userid)) {
				return Response.INVALID_USER;
			}
		} catch (Exception e) {
			return Response.INVALID_USER;
		}

		try {
			String timestamp = token.split(AESEncrypter.DELIMITER)[1];
			if (!TimeUtil.isTimestamp(timestamp)) {
				return Response.NO_TIMESTAMP;
			}
		} catch (Exception e) {
			return Response.NO_TIMESTAMP;
		}
		return Response.OK;
	}

	public static String pullUsername(String token) {
		try {
			return AESEncrypter.decrypt(token).split(AESEncrypter.DELIMITER)[0];
		} catch (Exception e) {
			return null;
		}
	}

	public static String pullTimestamp(String token) {
		try {
			return AESEncrypter.decrypt(token).split(AESEncrypter.DELIMITER)[1];
		} catch (Exception e) {
			return null;
		}
	}

}
