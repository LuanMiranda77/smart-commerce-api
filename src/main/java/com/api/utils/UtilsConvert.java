package com.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilsConvert {
	
	public static Date convertStringByDate(String data) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			return formatter.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date convertStringByDate(String data, String format) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			return formatter.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
