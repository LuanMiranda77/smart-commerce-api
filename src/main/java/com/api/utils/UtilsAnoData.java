package com.api.utils;

import java.util.Calendar;
import java.util.Date;

public class UtilsAnoData {
	
	public static Date subtrair( Date data, int ano) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.YEAR, -ano);
		return calendar.getTime();
	}
	
	public static Date adicionar( Date data, int ano) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.YEAR, ano);
		return calendar.getTime();
	}
	
	public static Date getUltimoDiaAnoByData( Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}
	
	public static Date getPrimeiroDiaAnoByData( Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

}
