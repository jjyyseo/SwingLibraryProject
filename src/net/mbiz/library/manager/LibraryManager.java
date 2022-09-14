package net.mbiz.library.manager;

import java.util.List;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.data.BorrowVO;

//crud 동작으로 묶기. 경우의 수: file, db
public abstract class LibraryManager {
	
	//book
	protected abstract List<BookVO> selectBookList();
	
	protected abstract int insertBook(BookVO vo);
	
	protected abstract int updateBook(BookVO vo);
	
	protected abstract int deletebook(String isbn);

	//borrow
	protected abstract List<BorrowVO> selectBorrowList();
	
	protected abstract int insertBorrow(BorrowVO vo);
	
	protected abstract int updateBorrow(BorrowVO vo);
	
	protected abstract int deleteBorrow(int bwNo);
	
}
