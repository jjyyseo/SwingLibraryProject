package net.mbiz.library.util;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.data.BorrowVO;
import net.mbiz.library.data.ChildCategoryVO;
import net.mbiz.library.data.ParentCategoryVO;

public class LibraryVOParser {
	
	
	public static BookVO stringToBookVO(String str) {
		BookVO vo = new BookVO();
		String[] arr = str.split("@"); 
		
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
		vo.setIsBorrowed( Integer.parseInt(arr[idx++]) );
		vo.setRegistDate( DateFomatUtil.formatToDate(arr[idx++]) );
		vo.setUpdateDate( DateFomatUtil.formatToDate(arr[idx++]) );
		vo.setBooksub(arr[idx++]);
		vo.setCCtgNm(arr[idx++]);
		vo.setCCtgIdx(Integer.parseInt(arr[idx++]));
		vo.setCCtgPnt(Integer.parseInt(arr[idx++]));
		
		return vo;
	}
	public static BorrowVO stringToBorrowVO(String str) {
		BorrowVO vo = new BorrowVO();
		String[] arr = str.split("@"); 
		// 공백 제거
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i].trim();
		} 
		System.out.println("stringToBorrowVO: " + str);
		int idx = 1;
		vo.setBorrowNo(Integer.parseInt(arr[idx++]) );
		vo.setBookIsbn(arr[idx++]);
		vo.setBookNm(arr[idx++]);
		vo.setBookWtr(arr[idx++]);
		vo.setCCtgNm(arr[idx++]);
		vo.setCCtgIdx( Integer.parseInt(arr[idx++]) );
		vo.setCCtgPnt( Integer.parseInt(arr[idx++]) );
		vo.setStartDate( DateFomatUtil.formatToDate(arr[idx++]) );
		vo.setEndDate( DateFomatUtil.formatToDate(arr[idx++]) );
		vo.setReturnDate( DateFomatUtil.formatToDate(arr[idx++]) );
		vo.setOverdue( Integer.parseInt(arr[idx++]) );
		
		return vo;
	}
	public static ParentCategoryVO stringToParentCategoryVO(String str) {
		ParentCategoryVO vo = new ParentCategoryVO();
		
		String[] arr = str.split("@"); 
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i].trim();
			System.out.println(arr[i]);
		} 
		
		int idx = 1;
		vo.setPCtgIdx(Integer.valueOf(arr[idx++]));
		vo.setPCtgNm(arr[idx++]);
		vo.setPCtgReg( DateFomatUtil.formatToDate(arr[idx++]) );
		vo.setPCtgMdf( DateFomatUtil.formatToDate(arr[idx++]) );
		return vo;
	}
	public static ChildCategoryVO stringToChildCategoryVO(String str) {
		ChildCategoryVO vo = new ChildCategoryVO();
		String[] arr = str.split("@"); 
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i].trim();
		} 
		int idx = 0;
		vo.setCCtgIdx(Integer.parseInt(arr[idx++]));
		vo.setCCtgPnt(Integer.parseInt(arr[idx++]));
		vo.setCCtgNm(arr[idx++]);
		vo.setCCtgReg( DateFomatUtil.formatToDate(arr[idx++]) );
		vo.setCCtgMdf( DateFomatUtil.formatToDate(arr[idx++]) );
		return vo;
	}
	
	
	public static String bookVOToString(BookVO vo) {
		String releaseDate = DateFomatUtil.formatToString("releaseDate", vo.getReleaseDate());
		String registDate = DateFomatUtil.formatToString("registDate", vo.getRegistDate());
		String updateDate = DateFomatUtil.formatToString("updateDate", vo.getUpdateDate());
		
		return  vo.getBookIsbn() + "@" + vo.getBookNm() + "@" + vo.getBookWtr() + "@" + vo.getPublisher() 
				+ "@" + releaseDate + "@" + vo.getIsBorrowed() + "@" + registDate
				+ "@" + updateDate + "@" + vo.getBooksub() + "@" + vo.getCCtgNm() +"@"+ vo.getCCtgIdx() + "@" + vo.getCCtgPnt();
	}
	public static String borrowVOToString(BorrowVO vo) {
		String startDate = DateFomatUtil.formatToString("startDate", vo.getStartDate());
		String endDate = DateFomatUtil.formatToString("endDate", vo.getEndDate());
		String returnDate = DateFomatUtil.formatToString("returnDate", vo.getReturnDate());
		
		return    "@" + vo.getBorrowNo() + "@" + vo.getBookIsbn() + "@" + vo.getBookNm() + "@" + vo.getBookWtr()
				+ "@" + vo.getCCtgNm() + "@"  + vo.getCCtgIdx() + "@" + vo.getCCtgPnt()
 				+ "@" + startDate + "@" + endDate + "@" + returnDate + "@" + vo.getOverdue();
	}
	public static String parentCategoryVOToString(ParentCategoryVO vo) {
		String regDate = DateFomatUtil.formatToString("returnDate", vo.getPCtgReg());
		String mdfDate = DateFomatUtil.formatToString("returnDate", vo.getPCtgMdf());
		
		return  "@" + vo.getPCtgIdx()+ "@" + vo.getPCtgNm() + "@" + regDate + "@" + mdfDate;
	}
	public static String childCategoryVOToString(ChildCategoryVO vo) {
		String regDate = DateFomatUtil.formatToString("returnDate", vo.getCCtgReg());
		String mdfDate = DateFomatUtil.formatToString("returnDate", vo.getCCtgMdf());
		
		return  vo.getCCtgIdx()+ "@" + vo.getCCtgPnt() + "@" + vo.getCCtgNm() + "@" + regDate + "@" + mdfDate;
	}

	
	
	
	
	public static String addUpToString(String isbn, String bkNm, String bkWtr, String publisher, String releaseDate
				, String cCtgNm, String cCtgIdx, String cCtgPnt, String registDate, String updateDate, String booksub) {
		
		return  isbn + "@" + bkNm + "@" + bkWtr + "@" + publisher 
				+ "@" + releaseDate + "@" + "0" + "@" + registDate
				+ "@" + updateDate + "@" + booksub+ "@" + cCtgNm + "@" +cCtgIdx + "@" +cCtgPnt ;
	}
	
}
