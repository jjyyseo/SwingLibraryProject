package net.mbiz.library.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.mbiz.library.handler.DBSqlHandler;

@Getter
@Setter
@ToString
public class BorrowVO implements Comparable<BorrowVO>, Serializable{

	private int borrowNo;          /* 대출 번호*/
	private String bookIsbn;       /* 도서 isbn*/
	private String bookNm;         /* 도서 명*/                          
	private String bookWtr;        /* 저자*/                          
	private Date startDate;        /* 대출 시작 일자*/
	private Date endDate;          /* 대출 마감 일자*/
	private Date returnDate;       /* 반납 일자*/
	private int overdue;           /* 연체일 수*/
	
	private boolean isSelect;	   /* 체크박스 선택 여부!*/


	
	@Override
	public int compareTo(BorrowVO o) {
		return this.borrowNo - o.borrowNo;
	}
	

}
