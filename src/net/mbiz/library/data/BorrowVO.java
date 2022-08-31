package net.mbiz.library.data;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BorrowVO implements Comparable<BorrowVO>{

	private int borrowNo;          /* 대출 번호*/
	private int bookNo;            /* 도서 번호*/
	private String bookNm;         /* 도서 명*/                          
	private String userId;         /* 유저 아이디*/
	private int isBorrowed;        /* 대출중=0 대출가능=1*/
	private Date startDate;        /* 대출 시작 일자*/
	private Date endDate;          /* 대출 마감 일자*/
	private Date returnDate;       /* 반납 일자*/
	private int overdue;           /* 연체일 수*/
	
	@Override
	public int compareTo(BorrowVO o) {
		return this.borrowNo - o.borrowNo;
	}

}
