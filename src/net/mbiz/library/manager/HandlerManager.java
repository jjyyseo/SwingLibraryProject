package net.mbiz.library.manager;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.data.BorrowVO;
import net.mbiz.library.handler.DBSqlHandler;
import net.mbiz.library.handler.DataHandler;
import net.mbiz.library.handler.FileHandler;
import net.mbiz.library.mybatis.MyBatisConnectionFactory;
import net.mbiz.library.util.FileLocationConstants;

/**
 * 핸들러 사용을 관리하는 매니저 클래스.
 * DB 연결 가능 여부에 따라 핸들러를 골라 사용한다. 
 * @author metabiz
 *
 */
public class HandlerManager {
	
	//field
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
	
	
//	public void initializeHandbler(){
//		if (!isDBConnection()) {
//			DataHandler handler = new DBSqlHandler(sqlSessionFactory);
//			handlerList.add(handler);
//			System.out.println("DB연결에 성공 하였습니다.");
//		} else {
//			System.err.println("DB연결에 실패 하였습니다. 파일 로드를 시도합니다.");
//			if (isExistDirectory()) {
//				DataHandler handler = FileHandler.getInstance();
//				handlerList.add(handler);
//			} else {
//				System.err.println("디렉토리가 존재하지 않습니다. 파일 로드에 실패 하였습니다.");
//			}
//		}
//	}
	
	public void initializeHandbler(){
		
		if (!isDBConnection()) {
			DataHandler handler = new DBSqlHandler(sqlSessionFactory);
			handlerList.add(handler);
			System.out.println("DB연결에 성공 하였습니다. DB핸들러 생성.");
		} else {
			System.err.println("initializeHandbler : DB연결에 실패 하였습니다. 저장된 파일로 프로그램을 로드합니다.");
		}
		
		if (isExistDirectory()) {
			DataHandler handler = FileHandler.getInstance();
			handlerList.add(handler);
			System.out.println("파일핸들러 생성.");
		} else {
			System.err.println("initializeHandbler : 디렉토리가 존재하지 않습니다. 파일 로드에 실패 하였습니다.");
		}
		
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
	
	

	public int insertBookData(BookVO vo) {
		int rslt1 = 0;
		int rslt2 = 0;
		
		try {
			rslt1 = handlerList.get(0).insertBook(vo);
			rslt2 = handlerList.get(1).insertBook(vo);
		} catch (Exception e) {
			System.err.println("FileHandler : 도서 정보 insert 도중 예외 발생! 실행결과 ---> " + rslt1 + rslt2);
		}
		
		if (rslt1 == 1 && rslt2 == 1) {
			return 1;
		}
		return 0;
	}
	
	public int updateBookData(BookVO vo) {
		int rslt1 = 0;
		int rslt2 = 0;
		
		try {
			rslt1 = handlerList.get(0).updateBook(vo);
			rslt2 = handlerList.get(1).updateBook(vo);
		} catch (Exception e) {
			System.err.println("FileHandler : 도서 정보 update 도중 예외 발생! 실행결과 ---> " + rslt1 + rslt2);
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
			rslt1 = handlerList.get(0).deleteBook(isbn);
			rslt2 = handlerList.get(1).deleteBook(isbn);
		} catch (Exception e) {
			System.err.println("FileHandler : 도서 정보 delete 도중 예외 발생! 실행결과 ---> " + rslt1 + rslt2);
		}
		
		if (rslt1 == 1 && rslt2 == 1) {
			return 1;
		}
		return 0;
	}
	
	public List<BookVO> selectBookList() {
		if (isDBConnection()) {
			for (int i = 0; i < handlerList.size(); i++) {
				if (handlerList.get(i) instanceof FileHandler) {
					return handlerList.get(i).selectBookList();
				}
			}
		} else {
			for (int i = 0; i < handlerList.size(); i++) {
				if (handlerList.get(i) instanceof DBSqlHandler) {
					return handlerList.get(i).selectBookList();
				}
			}	
		}
		return null;
	}
	public BookVO selectBookOne(String isbn) {
		for (int i = 0; i < handlerList.size(); i++) {
			return handlerList.get(i).selectBookOne(isbn);
		}
		return null;
	}

	/*도서 대출하기*/
	public int borrowBook(BorrowVO vo) {
		for (int i = 0; i < handlerList.size(); i++) {
			return handlerList.get(i).borrowBook(vo);
		}
		return 0;
	};
	/*도서 반납하기*/
	public int returnBook(BorrowVO bwvo, BookVO bkvo) {
		for (int i = 0; i < handlerList.size(); i++) {
			return handlerList.get(i).returnBook(bwvo, bkvo);
		}
		return 0;
	}
	

	public List<BorrowVO> selectBorrowList(){
		if (isDBConnection()) { //DB 커넥션이 닫힌 경우.
			for (int i = 0; i < handlerList.size(); i++) {
				if (handlerList.get(i) instanceof FileHandler) {
					return handlerList.get(i).selectBorrowList();
				}
			}	
		} else {
			
			for (int i = 0; i < handlerList.size(); i++) {
				if (handlerList.get(i) instanceof DBSqlHandler) {
					return handlerList.get(i).selectBorrowList();
				}
			}
		}
		return null;
	}
	
	public int deleteBorrow(int bwNo) {
		for (int i = 0; i < handlerList.size(); i++) {
			return handlerList.get(i).deleteBorrow(bwNo);
		}
		return 0;
	}
	

}
