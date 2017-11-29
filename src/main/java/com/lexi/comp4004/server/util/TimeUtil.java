package com.lexi.comp4004.server.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class TimeUtil {

	private static String pattern = "yyyy-MM-dd HH:mm:ss Z";
	
	public static String now() {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(Calendar.getInstance(TimeZone.getDefault()).getTime());
	}
	
	public static boolean timeout(String timestamp) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date nowDate = sdf.parse(now());
		Date timestampDate = sdf.parse(timestamp);
		return TimeUnit.MILLISECONDS.toSeconds(nowDate.getTime() - timestampDate.getTime()) >= Variables.TIMEOUT_TIME;			
	}
	
	public static boolean newer(String timestamp1, String timestamp2) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date1 = sdf.parse(timestamp1);
		Date date2 = sdf.parse(timestamp2);
		return TimeUnit.MILLISECONDS.toSeconds(date1.getTime() - date2.getTime()) >= 0;			
	}
	
	public static boolean isTimestamp(String timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			sdf.parse(timestamp);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
	
}
