package net.mbiz.library.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFomatUtil {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 날짜 데이터를 받아 문자열로 포맷해주는 메서드.
	 * @param date
	 * @return
	 */
	public static String formatToString(String col, Date date) {
		if (!col.equals("returnDate") && date == null) {
			return "날짜 정보 없음";
		} else if(col.equals("returnDate") && date == null) {
			return "-";
		}
		return sdf.format(date);
	}
	
	public static Date formatToDate(String str) {
		Date date = null;

		if (str.equals("날짜 정보 없음")) {
			return new Date();
		} else if (str.equals("-")) {
			
		} else {
			try {
				date = sdf.parse(str);
			} catch (ParseException e) {
				System.out.println("문자열 -> 날짜 데이터로 파싱 중 에러!");
			}
		}
		
		return date;
	}
	
}
