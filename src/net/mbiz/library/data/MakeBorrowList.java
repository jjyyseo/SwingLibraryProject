package net.mbiz.library.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MakeBorrowList {
	
	// field
	private List<BookVO> bkList;
	private SimpleDateFormat sdf;

	private static MakeBorrowList makeBorrowList = new MakeBorrowList();
	
	private MakeBorrowList() {
	}
	
	public static MakeBorrowList getInstance() {
		 return makeBorrowList;
	}
	
	/*대출 객체 생성*/
	public List<BorrowVO> makeBorrowList() {
		List<BorrowVO> bwList = new ArrayList<>();
		
		sdf = new SimpleDateFormat("yyyy.MM.dd");
		
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
			BorrowVO bv = new BorrowVO();
			bv.setBorrowNo(i);
			bv.setBookNo(i);
			bv.setUserId("a001");
			bv.setStartDate(startDate);
			bv.setEndDate(endDate);
			bv.setReturnDate(returnDate);
			bv.setOverdue(2);
			bv.setBookNm(getBookNm(i));
			
			bwList.add(bv);
		}
		
		System.err.println("net.mbiz.library.data.MakeBorrowList : 대출 기록 추가완료");
		return bwList;
	}
	
	public String getBookNm(int bookNo) {
		bkList = MakeBookList.getInstance().addBookData();
		for (BookVO bv : bkList) {
			if (bookNo == bv.getBookNo()) {
				return bv.getBookNm();
			}
		}
		return null;
	}

	
}
