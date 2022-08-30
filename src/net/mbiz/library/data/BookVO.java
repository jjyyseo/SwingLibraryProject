package net.mbiz.library.data;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookVO {
	
	private int bookNo;			    /* 도서번호*/
	private String bookNm;          /* 도서명*/
	private String bookWtr;         /* 저자*/
	private String publisher;       /* 출판사*/
	private Date releaseDate;		/* 출간일*/
	private String category;        /* 카테고리*/
	private int isBorrowed;         /* 대출중=0 대출가능=1*/
	private long bookIsbn;			/* 도서 isbn*/
	private String booksub;			/* 도서 소개글*/
	
	private Date registDate;		/* 등록일자*/
	private Date updayeDate;		/* 수정일자*/
	
	
	
	
}
