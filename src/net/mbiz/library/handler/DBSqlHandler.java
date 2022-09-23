package net.mbiz.library.handler;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.data.BorrowVO;
import net.mbiz.library.data.ChildCategoryVO;
import net.mbiz.library.data.ParentCategoryVO;


// 비즈니스 로직 처리
public class DBSqlHandler extends DataHandler {

	private SqlSessionFactory sqlSessionFactory = null;
	
	public DBSqlHandler(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	

	public int insertBook(BookVO vo) {

		int rslt = 0;
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			BookVO overlap = session.selectOne("BookMapper.selectBookOne", vo.getBookIsbn());
			if(overlap==null) {
				
				rslt = session.insert("BookMapper.insertBook", vo); // namespace.id , param
			} else {
				return 2;	//중복되는 ISBN이 있음.
			}

		} finally {
			session.commit();
			session.close();
		}
		return rslt;
	}
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
	public List<BookVO> searchBookList(BookVO vo) {
		List<BookVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		System.out.println("DB핸들러 searchBookList~ vo?---> " + vo);
		try {
			list = session.selectList("BookMapper.searchBookList", vo); // namespace.id
		} finally {
			session.close();
		}
		return list;
	}
	public List<BookVO> selectCategoryBookList(ChildCategoryVO vo) {
		List<BookVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			list = session.selectList("BookMapper.selectCategoryBookList", vo); // namespace.id
		} finally {
			session.close();
		}
		return list;
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
	public int returnBook(BorrowVO bwvo) {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			BookVO bkvo = selectBookOne(bwvo.getBookIsbn());
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
	public List<BorrowVO> selectBorrowList() {
		List<BorrowVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			list = session.selectList("BorrowMapper.selectBorrowList");
		} finally {
			session.close();
		}
		return list;
	}
	public List<BorrowVO> searchBorrowList(BorrowVO vo) {
		List<BorrowVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			list = session.selectList("BorrowMapper.searchBorrowList", vo); // namespace.id
		} finally {
			session.close();
		}
		return list;
	}


	
	//------카테고리-----
	public List<ParentCategoryVO> selectParentCategoryList() {
		List<ParentCategoryVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			list = session.selectList("ParentCategoryMapper.selectParentCategoryList"); 
		} finally {
			session.close();
		}
		return list;
	}

	public List<ChildCategoryVO> selectChildCategoryList(int pCtgIdx) {
		List<ChildCategoryVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			list = session.selectList("ChildCategoryMapper.selectChildCategoryList" , pCtgIdx); 
		} finally {
			session.close();
		}
		return list;
	}
	
	


	

}
