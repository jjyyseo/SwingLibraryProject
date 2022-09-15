package net.mbiz.library.handler;

import java.util.List;

import net.mbiz.library.dao.BookDAO;
import net.mbiz.library.data.BookVO;
import net.mbiz.library.data.BorrowVO;
import net.mbiz.library.manager.LibraryManager;


// 비즈니스 로직 처리
public class DBSqlHandler extends LibraryManager {

	private BookDAO bookDAO = new BookDAO();
	
	public static void main(String[] args) {
		List<BookVO> list  = new DBSqlHandler().selectBookList();
		System.out.println(list);
	}
	
	@Override
	protected List<BookVO> selectBookList() {
		bookDAO.selectBookList();
		System.out.println("bookDAO.selectBookList() : " + bookDAO.selectBookList());
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
	
	
	
	
	
	
	
	//detail
	public BookVO selectBookOne(String isbn) {
		return null;
	}
	//대출 신청 - 대출상태 update
	public int updateBookState(String isbn) {
		return 0;
	}
	
	
	
	
	
	


}
