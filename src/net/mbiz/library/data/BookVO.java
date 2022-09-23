package net.mbiz.library.data;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookVO implements Serializable{
	private String bookIsbn;		/* 도서 isbn*/   //PK
	private String bookNm;          /* 도서명*/
	private String bookWtr;         /* 저자*/
	private String publisher;       /* 출판사*/
	private Date releaseDate;		/* 출간일*/
	private int isBorrowed;         /* 대출가능=0 대출중=1*/
	private Date registDate;		/* 등록일자*/
	private Date updateDate;		/* 수정일자*/
	private String booksub;			/* 도서 소개글*/
	private String cCtgNm;			/* 자식 카테고리 명*/
	private int cCtgIdx;			/* 자식 카테고리 idx*/
	private int cCtgPnt;			/* 부모 카테고리 idx*/
	
	private boolean isSelect;		/* 체크박스 선택 여부*/
	private String query;			/* 검색어*/
	private String option;			/* 콤보박스 선택 값*/


}
