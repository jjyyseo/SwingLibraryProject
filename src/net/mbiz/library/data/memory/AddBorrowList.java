package net.mbiz.library.data.memory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.mbiz.library.data.BorrowVO;


public class AddBorrowList {

//	public static List<BorrowVO> borrowList = AddBorrowList.addData();
	
	private static List<BorrowVO> addData() {
		List<BorrowVO> bwList = new ArrayList<>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		
		Date startDate  =  null;
		Date endDate    =  null;
		Date returnDate =  null;
		
		try {
			startDate = sdf.parse("2022.05.01");
			endDate = sdf.parse("2022.05.15");
			returnDate = sdf.parse("2022.05.17");
			
		} catch (ParseException e) {
			e.printStackTrace();
			System.err.println("net.mbiz.library.data.MakeBorrowList : 날짜 데이터 포맷 중 오류");
		}
		
	    // 임의로 더미데이터 넣기
		for (int i = 1; i <=30; i++) {
			BorrowVO bwVO = new BorrowVO();
			bwVO.setBorrowNo(i);
			bwVO.setStartDate(startDate);
			bwVO.setEndDate(endDate);
			bwVO.setReturnDate(returnDate);
			bwVO.setOverdue(2);
			bwVO.setBookNm(getBookNm(i));
			bwVO.setBookWtr(getBookWtr(i));
			
			bwList.add(bwVO);
		}
		
		System.err.println("net.mbiz.library.data.MakeBorrowList : 대출 기록 추가완료");
		return bwList;
	}
	
	private static String getBookNm(int bookNo) {
//		for (BookVO bkVO : AddBookList.bookList) {
//			if (bookNo == bkVO.getBookNo()) {
//				return bkVO.getBookNm();
//			}
//		}
		return null;
	}

	private static String getBookWtr(int bookNo) {
//		for (BookVO bkVO : AddBookList.bookList) {
//			if (bookNo == bkVO.getBookNo()) {
//				return bkVO.getBookWtr();
//			}
//		}
		return null;
	}

	
}
