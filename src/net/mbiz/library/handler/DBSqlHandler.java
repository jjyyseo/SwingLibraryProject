package net.mbiz.library.handler;

import java.util.List;

import net.mbiz.library.dao.BookDAO;
import net.mbiz.library.dao.BorrowDAO;
import net.mbiz.library.data.BookVO;
import net.mbiz.library.data.BorrowVO;
import net.mbiz.library.manager.LibraryManager;


// 비즈니스 로직 처리
public class DBSqlHandler extends LibraryManager {

	private BookDAO bookDAO = new BookDAO();
	private BorrowDAO borrowDAO = new BorrowDAO();
	
	public static void main(String[] args) {
		List<BookVO> list  = new DBSqlHandler().selectBookList();
		System.out.println(list);
	}
	
	@Override
	protected List<BookVO> selectBookList() {
		return bookDAO.selectBookList();
	}

	@Override
	protected int insertBook(BookVO vo) {
		return bookDAO.insertBook(vo);
	}

	@Override
	protected int updateBook(BookVO vo) {
		return bookDAO.updateBook(vo);
	}

	@Override
	protected int deletebook(String isbn) {
		return bookDAO.deleteBook(isbn);
	}

	//detail
	public BookVO selectBookOne(String isbn) {
		return bookDAO.selectBookOne(isbn);
	}
	//대출 신청 - 대출상태 update
	public int updateBookState(String isbn) {
		return bookDAO.updateBookState(isbn);
	}
	
	
	
	//-------------------------------------------------------------
	
	
	@Override
	protected List<BorrowVO> selectBorrowList() {
		return borrowDAO.selectBorrowkList();
	}

	@Override
	protected int insertBorrow(BorrowVO vo) {
		return borrowDAO.insertBorrow(vo);
	}

	@Override
	protected int updateBorrow(BorrowVO vo) {
		return borrowDAO.updateBorrow(vo);
	}

	@Override
	protected int deleteBorrow(int bwNo) {
		return borrowDAO.deleteBorrow(bwNo);
	}
	

	
	
	
	
	


}