package net.mbiz.library.util;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.data.BorrowVO;

public class LibraryVOParser {
	
	public static String bookVOToString(BookVO vo) {
		
		String releaseDate = DateFomatUtil.formatToString("releaseDate", vo.getReleaseDate());
		String registDate = DateFomatUtil.formatToString("registDate", vo.getRegistDate());
		String updateDate = DateFomatUtil.formatToString("updateDate", vo.getUpdateDate());
		
		
		return  vo.getBookIsbn() + "@" + vo.getBookNm() + "@" + vo.getBookWtr() + "@" + vo.getPublisher() 
				+ "@" + releaseDate+ "@" + vo.getCategory() + "@" + vo.getIsBorrowed() + "@" + registDate
				+ "@" + updateDate + "@" + vo.getBooksub();
	}

	
	public static BookVO stringToBookVO(String bkStr) {
		BookVO vo = new BookVO();
		String[] arr = bkStr.split("@"); 
		
		// 공백 제거
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i].trim();
		} 
		
		int idx = 0;
		vo.setBookIsbn(arr[idx++]);
		vo.setBookNm(arr[idx++]);
		vo.setBookWtr(arr[idx++]);
		vo.setPublisher(arr[idx++]);
		vo.setReleaseDate( DateFomatUtil.formatToDate(arr[idx++]) );
		vo.setCategory(arr[idx++]);
		vo.setIsBorrowed( Integer.parseInt(arr[idx++]) );
		vo.setRegistDate( DateFomatUtil.formatToDate(arr[idx++]) );
		vo.setUpdateDate( DateFomatUtil.formatToDate(arr[idx++]) );
		vo.setBooksub(arr[idx++]);
		
		return vo;
	}
	
	
	public static String borrowVOToString(BorrowVO vo) {
		
		String startDate = DateFomatUtil.formatToString("startDate", vo.getStartDate());
		String endDate = DateFomatUtil.formatToString("endDate", vo.getEndDate());
		String returnDate = DateFomatUtil.formatToString("returnDate", vo.getReturnDate());
		
		
		return  vo.getBorrowNo() + "@" + vo.getBookIsbn() + "@" + vo.getBookNm() + "@" + vo.getBookWtr() 
				+ "@" + startDate + "@" + endDate + "@" + returnDate
				+ "@" + vo.getOverdue();
	}

	
	public static BorrowVO stringToBorrowVO(String bkStr) {
		BorrowVO vo = new BorrowVO();
		String[] arr = bkStr.split("@"); 
		
		// 공백 제거
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i].trim();
		} 
		
		int idx = 0;
		vo.setBorrowNo(Integer.parseInt(arr[idx++]) );
		vo.setBookIsbn(arr[idx++]);
		vo.setBookNm(arr[idx++]);
		vo.setBookWtr(arr[idx++]);
		vo.setStartDate( DateFomatUtil.formatToDate(arr[idx++]) );
		vo.setEndDate( DateFomatUtil.formatToDate(arr[idx++]) );
		vo.setReturnDate( DateFomatUtil.formatToDate(arr[idx++]) );
		vo.setOverdue( Integer.parseInt(arr[idx++]) );
		
		return vo;
	}
	
	
	public static String addUpToString(String isbn, String bkNm, String bkWtr, String publisher, String releaseDate
				, String category, String registDate, String updateDate, String booksub) {
		
		
		return  isbn + "@" + bkNm + "@" + bkWtr + "@" + publisher 
				+ "@" + releaseDate+ "@" + category + "@" + "1" + "@" + registDate
				+ "@" + updateDate + "@" + booksub;
	}
	
}
