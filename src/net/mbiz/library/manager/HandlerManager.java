package net.mbiz.library.manager;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.data.BorrowVO;
import net.mbiz.library.handler.DBSqlHandler;
import net.mbiz.library.handler.DataHandler;
import net.mbiz.library.handler.FileHandler;
import net.mbiz.library.mybatis.MyBatisConnectionFactory;

/**
 * 핸들러 사용을 관리하는 매니저 클래스. DB 연결 가능 여부에 따라 핸들러를 골라 사용한다.
 * 
 * @author metabiz
 *
 */
public class HandlerManager {

	// field
	private List<DataHandler> handlerList = new ArrayList<>();
	private SqlSessionFactory sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();

	
	private static HandlerManager handlerManager;

	private HandlerManager() {

	}
	public static HandlerManager getInstance() {
		if (handlerManager == null) {
			handlerManager = new HandlerManager();
		}
		return handlerManager;
	}

	
	
	// 이벤트리스너를 impl한 핸들러를 handlerList에 추가한다.
	public void addBookEventListener(DataHandler handler) {
		handlerList.add(handler);
	}


	/**
	 * 핸들러 매니저를 초기화하는 메서드.
	 * 파일 핸들러와 DB핸들러를 생성한다.
	 */
	public void initializeHandler() {

		if (!isDBConnection()) {
			DataHandler dbHandler = new DBSqlHandler(sqlSessionFactory);
			System.out.println("DB연결에 성공 하였습니다. DB핸들러 생성.");
			
		} else {
			System.err.println("initializeHandbler : DB연결에 실패 하였습니다. 저장된 파일로 프로그램을 로드합니다.");
		}

		if (isExistDirectory()) {
			DataHandler fileHandler = new FileHandler();
			System.out.println("파일핸들러 생성.");
			
		} else {
			System.err.println("initializeHandbler : 디렉토리가 존재하지 않습니다. 파일 로드에 실패 하였습니다.");
		}
		System.out.println("HandlerManager.initializeHandler : 핸들러 초기화. handlerList -->" + handlerList.toString());

	}



	
	
	
	
	public List<BookVO> selectBookList() {
		for (int i = 0; i < handlerList.size(); i++) {
			if (handlerList.get(i) instanceof DBSqlHandler) {
				return handlerList.get(i).selectBookList();
			}
		}
		return null;
	}
	public List<BorrowVO> selectBorrowList() {
		for (int i = 0; i < handlerList.size(); i++) {
			if (handlerList.get(i) instanceof DBSqlHandler) {
				return handlerList.get(i).selectBorrowList();
			}
		}
		
		return null;
	}
	public BookVO selectBookOne(String isbn) {
		for (int i = 0; i < handlerList.size(); i++) {
			if (handlerList.get(i) instanceof DBSqlHandler) {
				return handlerList.get(i).selectBookOne(isbn);
			}
		}
		return null;
	}
	public int insertBookData(BookVO vo) {
		int rslt1 = 0;
		int rslt2 = 0;

		try {
			rslt1 = handlerList.get(0).bookAdded(vo);
			rslt2 = handlerList.get(1).bookAdded(vo);

			System.err.println("FileHandler : 도서 정보 insert 실행결과 ---> " + "rslt1: " + rslt1 + "rslt2: " + rslt2);
		} catch (Exception e) {
			System.out.println("에러에러");
		}
		

		if (rslt1 == 1 && rslt2 == 1) {
			return 1;
		} 
		System.err.println("FileHandler : 도서 정보 insert 도중 예외 발생! 실행결과 ---> " + "rslt1: " + rslt1 + "rslt2: " + rslt2);
		return 0;
	}
	
	public int updateBookData(BookVO vo) {
		int rslt1 = 0;
		int rslt2 = 0;

		try {
			rslt1 = handlerList.get(0).bookUpdated(vo);
			rslt2 = handlerList.get(1).bookUpdated(vo);
		} catch (Exception e) {
			System.err.println("FileHandler : 도서 정보 update 도중 예외 발생! 실행결과 ---> " + "rslt1: " + rslt1 + "rslt2: " + rslt2);
		}

		if (rslt1 == 1 && rslt2 == 1) {
			return 1;
		}
		return 0;
	}
	public int deleteBookData(String isbn) {

		int rslt1 = 0;
		int rslt2 = 0;

		try {
			rslt1 = handlerList.get(0).bookDeleted(isbn);
			rslt2 = handlerList.get(1).bookDeleted(isbn);
		} catch (Exception e) {
			System.err.println("FileHandler : 도서 정보 delete 도중 예외 발생! 실행결과 ---> " + "rslt1: " + rslt1 + "rslt2: " + rslt2);
		}

		if (rslt1 == 1 && rslt2 == 1) {
			return 1;
		}
		return 0;
	}

	public int deleteBorrowData(int bwNo) {
		int rslt1 = 0;
		int rslt2 = 0;

		try {
			rslt1 = handlerList.get(0).borrowDeleted(bwNo);
			rslt2 = handlerList.get(1).borrowDeleted(bwNo);
		} catch (Exception e) {
			System.err.println("FileHandler : 대출 정보 delete 도중 예외 발생! 실행결과 ---> " + "rslt1: " + rslt1 + "rslt2: " + rslt2);
		}

		if (rslt1 == 1 && rslt2 == 1) {
			return 1;
		}
		return 0;

	}
	/* 도서 대출하기 */
	public int borrowBook(BorrowVO vo) {
		int rslt1 = 0;
		int rslt2 = 0;

		try {
			rslt1 = handlerList.get(0).borrowAdded(vo);
			rslt2 = handlerList.get(1).borrowAdded(vo);
		} catch (Exception e) {
			System.err.println("FileHandler : 도서 대출 도중 예외 발생! 실행결과 ---> " + "rslt1: " + rslt1 + "rslt2: " + rslt2);
		}

		if (rslt1 == 1 && rslt2 == 1) {
			return 1;
		}
		return 0;
	};

	/* 도서 반납하기 */
	public int returnBook(BorrowVO bwvo) {
		int rslt1 = 0;
		int rslt2 = 0;

		try {
			rslt1 = handlerList.get(0).borrowUpdated(bwvo);
			rslt2 = handlerList.get(1).borrowUpdated(bwvo);
		} catch (Exception e) {
			System.err.println("FileHandler : 도서 반납 도중 예외 발생! 실행결과 ---> "  + "rslt1: " + rslt1 + "rslt2: " + rslt2);
		}

		if (rslt1 == 1 && rslt2 == 1) {
			return 1;
		}
		return 0;
	}
	
	/*도서 리스트에서 검색하기*/	
	public List<BookVO> searchBookList(BookVO vo) {
		for (int i = 0; i < handlerList.size(); i++) {
			if (handlerList.get(i) instanceof DBSqlHandler) {
				return handlerList.get(i).searchBookList(vo);
			}
		}
		return null;
	}
	
	
	private boolean isDBConnection() {
		try {
			return sqlSessionFactory.openSession().getConnection().isClosed();
		} catch (SQLException e) {
			System.err.println("데이터베이스 연결에 실패 하였습니다.");
		}
		return true;
	}

	private boolean isExistDirectory() {
		File file = new File("C:\\LibraryData\\book");
		return file.isDirectory();
	}

}
