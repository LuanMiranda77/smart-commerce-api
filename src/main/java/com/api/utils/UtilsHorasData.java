package com.api.utils;

import java.util.Calendar;
import java.util.Date;

public class UtilsHorasData {
	
	public static Date subtrair( Date data, int horas) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.HOUR_OF_DAY, -horas);
		return calendar.getTime();
	}
	
	public static Date adicionar( Date data, int horas) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.HOUR_OF_DAY, horas);
		return calendar.getTime();
	}

}
