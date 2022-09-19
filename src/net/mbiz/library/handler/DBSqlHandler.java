package net.mbiz.library.handler;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.data.BorrowVO;


// 비즈니스 로직 처리
public class DBSqlHandler extends DataHandler {
	
	private SqlSessionFactory sqlSessionFactory = null;
	
	public DBSqlHandler(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	


	public List<BookVO> selectBookList() {
		List<BookVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			list = session.selectList("BookMapper.selectBookList"); // namespace.id
		} finally {
			session.close();
		}
		return list;
	}

	
	public int insertBook(BookVO vo) {
		int rslt = 0;
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			BookVO overlap = session.selectOne("BookMapper.selectBookOne", vo.getBookIsbn());
			if(overlap==null) {
				
				rslt = session.insert("BookMapper.insertBook", vo); // namespace.id , param
			} else {
				return 2;
			}

		} finally {
			session.commit();
			session.close();
		}
		return rslt;
	}

	/**
	 * 도서 정보를 수정하는 메서드
	 * @return 삭제 성공 = 1, 삭제 실패 = 0, 대출 중인 도서 = 2. 
	 */
	public int updateBook(BookVO vo) {
		int rslt = 0;
		SqlSession session = sqlSessionFactory.openSession();
		System.out.println("수정할 도서 vo" + vo.toString());
		try {
			if (vo.getIsBorrowed()==1) {
				System.out.println("도서가 대출 중 입니다.");
				return 2;
			}
			rslt = session.update("BookMapper.updateBook", vo); // namespace.id, param
		} finally {
			session.commit();
			session.close();
		}
		return rslt;
	}

	/**
	 * 도서 정보를 삭제하는 메서드
	 * @return 삭제 성공 = 1, 삭제 실패 = 0, 대출 중인 도서 = 2. 
	 */
	public int deleteBook(String isbn) {
		int rslt = 0;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			BookVO vo = session.selectOne("BookMapper.selectBookOne", isbn);
			if (vo.getIsBorrowed()==1) {
				System.out.println("도서가 대출 중 입니다.");
				return 2;
			}
			
			rslt = session.delete("BookMapper.deleteBook", isbn); // namespace.id, param
		} finally {
			session.commit();
			session.close();
		}
		return rslt;
	}

	public BookVO selectBookOne(String isbn) {
		BookVO vo = null;
		SqlSession session = sqlSessionFactory.openSession();
		System.out.println("isbn : " + isbn);
		try {
			vo = session.selectOne("BookMapper.selectBookOne", isbn); // namespace.id, param
		} finally {
			session.close();
		}
		return vo;
	}


	public int borrowBook(BorrowVO vo) {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			BookVO bkvo = session.selectOne("BookMapper.selectBookOne", vo.getBookIsbn()); // namespace.id, param
			int state = session.update("BookMapper.updateBookState", bkvo); // namespace.id, param
			int insert = session.insert("BorrowMapper.insertBorrow", vo);
			
			if (state == 0 || insert == 0) {
				session.rollback();
				System.out.println("대출 실패!!! 도서 대출 상태 update 작업이나 대출 기록 insert 작업 도중 실패 하였습니다. 롤백합니다.");
				return 0;
			}
		} finally {
			session.commit();
			session.close();
		}
		return 1;
	}
	
	public int returnBook(BorrowVO bwvo, BookVO bkvo) {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			System.out.println("update Data : " + bkvo.toString());
			
			int state = session.update("BookMapper.updateBookState", bkvo); // namespace.id, param
			int update = session.update("BorrowMapper.updateBorrow", bwvo);
			
			if (state == 0 || update == 0) {
				session.rollback();
				System.out.println("DBHandler.returnBook : 반납 실패!!!! 롤백합니다.");
				System.err.println("DBHandler.returnBook : 도서 상태 업데이트: " + state + ", 대출 기록 업데이트: " + update);
				return 0;
			}
			
		} finally {
			session.commit();
			session.close();
		}
		return 1;
	}

	
	public List<BorrowVO> selectBorrowList() {
		List<BorrowVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			list = session.selectList("BorrowMapper.selectBorrowkList");
		} finally {
			session.close();
		}
		return list;
	}


	/**
	 * 대출 정보를 삭제하는 메서드
	 * @return 삭제 성공 = 1, 삭제 실패 = 0, 대출 중인 도서 = 2. 
	 */
	public int deleteBorrow(int bwNo) {
		int rslt = 0;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			BorrowVO vo = session.selectOne("BorrowMapper.selectBorrowOne", bwNo);
			if (vo.getReturnDate() == null) {
				return 2;
			}
			rslt = session.delete("BorrowMapper.deleteBorrow", bwNo);
			
			if (rslt==0) {
				session.rollback();
				System.out.println("체크한 기록 삭제에 실패 하였습니다.");
				return 0;
			}
			
			
		} finally {
			session.commit();
			session.close();
		}
		return rslt;
	}
	



}
