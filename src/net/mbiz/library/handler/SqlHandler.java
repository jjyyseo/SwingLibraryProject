package net.mbiz.library.handler;

import java.util.List;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.data.BorrowVO;
import net.mbiz.library.manager.LibraryManager;

public class SqlHandler extends LibraryManager{

	@Override
	protected List<BookVO> selectBookList() {
		return null;
	}

	@Override
	protected int insertBook(BookVO vo) {
		return 0;
	}

	@Override
	protected int updateBook(BookVO vo) {
		return 0;
	}

	@Override
	protected int deletebook(String isbn) {
		return 0;
	}

	
	
	
	
	
	@Override
	protected List<BorrowVO> selectBorrowList() {
		return null;
	}

	@Override
	protected int insertBorrow(BorrowVO vo) {
		return 0;
	}

	@Override
	protected int updateBorrow(BorrowVO vo) {
		return 0;
	}

	@Override
	protected int deleteBorrow(int bwNo) {
		return 0;
	}

}
