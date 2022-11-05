package com.api.utils;

import java.util.Calendar;
import java.util.Date;

public class UtilsMesData {
	
	public static Date subtrair( Date data, int mes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.MONTH, -mes);
		return calendar.getTime();
	}
	
	public static Date adicionar( Date data, int mes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.MONTH, mes);
		return calendar.getTime();
	}
	
	public static Date getUltimoDiaMesByData( Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}
	
	public static Date getPrimeiroDiaMesByData( Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}
	
	public static int getMesByData( Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return calendar.get(Calendar.MONTH);
	}

}
