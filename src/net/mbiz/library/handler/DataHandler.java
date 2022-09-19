package net.mbiz.library.handler;

import java.util.List;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.data.BorrowVO;

public abstract class DataHandler {
	
	/*도서리스트 select*/
	public abstract List<BookVO> selectBookList();
	/*도서 정보 insert*/
	public abstract int insertBook(BookVO vo);
	/*도서 정보 update*/
	public abstract int updateBook(BookVO vo);
	/*도서 정보 delete*/
	public abstract int deleteBook(String isbn);
	/*도서 detail*/
	public abstract BookVO selectBookOne(String isbn);
	/*도서 대출하기*/
	public abstract int borrowBook(BorrowVO vo);
	/*도서 반납하기*/
	public abstract int returnBook(BorrowVO bwvo, BookVO bkvo);
	
	
	/*대출 리스트 select*/
	public abstract List<BorrowVO> selectBorrowList();
	/*대출 기록 delete*/
	public abstract int deleteBorrow(int bwNo);
	
}
