package net.mbiz.library.data;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class BookVO implements Comparable<BookVO>, Serializable{
	
	private int bookNo;			    /* 도서번호*/
	private String bookNm;          /* 도서명*/
	private String bookWtr;         /* 저자*/
	private String publisher;       /* 출판사*/
	private Date releaseDate;		/* 출간일*/
	private String category;        /* 카테고리*/
	private int isBorrowed;         /* 대출가능=0 대출중=1*/
	private long bookIsbn;			/* 도서 isbn*/
	private String booksub;			/* 도서 소개글*/
	
	private Date registDate;		/* 등록일자*/
	private Date updateDate;		/* 수정일자*/
	
	private boolean isSelect;		/* 체크박스 선택 여부!*/
	
	@Override
	public int compareTo(BookVO o) {
		return this.bookNo - o.bookNo;
	}

	@Override
	public String toString() {
		return bookNo + "@" + bookNm + "@" + bookWtr + "@" + publisher
				+ "@" + releaseDate + "@" + category + "@" + isBorrowed
				+ "@" + bookIsbn + "@" + booksub + "@" + registDate + "@"
				+ updateDate + "@" + isSelect ;
	}
	
	
	
	
}
