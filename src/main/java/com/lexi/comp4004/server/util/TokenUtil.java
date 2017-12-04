package com.lexi.comp4004.server.util;

import java.util.List;

import com.lexi.comp4004.server.encrypt.AESEncrypter;
import com.lexi.comp4004.server.encrypt.Token;

public class TokenUtil {

	public static Token constuctToken(String username) throws Exception {
		return new Token(username, TimeUtil.now());
	}

	public static String encypt(String s1, String s2) throws Exception {
		return AESEncrypter.encrypt(s1 + AESEncrypter.DELIMITER + s2);
	}

	public static String encryt(Token t) throws Exception {
		String token = AESEncrypter.encrypt(t.getUserid() + AESEncrypter.DELIMITER + t.getTimestamp());
		return token;
	}

	public static boolean verify(String encryptToken, List<String> users) {
		String token = null;
		String userid = null;

		try {
			token = AESEncrypter.decrypt(encryptToken);
			userid = token.split(AESEncrypter.DELIMITER)[0];
			String timestamp = token.split(AESEncrypter.DELIMITER)[1];

			if (!users.contains(userid)) {
				return false;
			} else if (!TimeUtil.isTimestamp(timestamp)) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
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

	public static String pullPassowrd(String token) {
		try {
			return AESEncrypter.decrypt(token).split(AESEncrypter.DELIMITER)[1];
		} catch (Exception e) {
			return null;
		}
	}
	
}
