package net.mbiz.library.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFomatUtil {
	SimpleDateFormat sdf = new SimpleDateFormat();
	
	/**
	 * 날짜 데이터를 받아 문자열로 포맷해주는 메서드.
	 * @param date
	 * @return
	 */
	public String formatToString(Date date) {
		if (date == null) {
			return "";
		}
		return sdf.format(date);
	}
	
	public Date formatToDate(String str) {
		Date date = null;
		
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			System.out.println("문자열 -> 날짜 데이터로 파싱 중 에러!");
		}
		
		return date;
	}
	
}
