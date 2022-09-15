package net.mbiz.library.handler;

import java.util.List;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.data.BorrowVO;

// ui에서 생성하여 사용
public interface DataHandler {
	
	//book
	public List<BookVO> selectBookList();
	
	public int insertBook(BookVO vo);
	
	public int updateBook(BookVO vo);
	
	public int deletebook(String isbn);

	//borrow
	public List<BorrowVO> selectBorrowList();
	
	public int insertBorrow(BorrowVO vo);
	
	public int updateBorrow(BorrowVO vo);
	
	public int deleteBorrow(int bwNo);
	
}
