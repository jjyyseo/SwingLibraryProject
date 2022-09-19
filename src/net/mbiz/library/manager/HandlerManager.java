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
	
	
	public void initializeHandbler(){
		if (!isDBConnection()) {
			DataHandler handler = new DBSqlHandler(sqlSessionFactory);
			handlerList.add(handler);
			System.out.println("DB연결에 성공 하였습니다.");
		} else {
			System.err.println("DB연결에 실패 하였습니다. 파일 연결을 시도합니다.");
			if (isExistDirectory()) {
				DataHandler handler = FileHandler.getInstance();
				handlerList.add(handler);
			} else {
				System.err.println("디렉토리가 존재하지 않습니다. 파일 연결에 실패 하였습니다.");
			}
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
        File file = new File(FileLocationConstants.BOOK_DATA_lOCATION);         
        return file.isDirectory();     
	}
	
	
	//book
	public int insertBookData(BookVO vo) {
		for (int i = 0; i < handlerList.size(); i++) {
			return handlerList.get(i).insertBook(vo);
		}
		return 0;
	}
	public int updateBookData(BookVO vo) {
		for (int i = 0; i < handlerList.size(); i++) {
			return handlerList.get(i).updateBook(vo);
		}
		return 0;
	}
	public int deleteBookData(String isbn) {
		for (int i = 0; i < handlerList.size(); i++) {
			return handlerList.get(i).deleteBook(isbn);
		}
		return 0;
	}
	public List<BookVO> selectBookList() {
		for (int i = 0; i < handlerList.size(); i++) {
			return handlerList.get(i).selectBookList();
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
		for (int i = 0; i < handlerList.size(); i++) {
			return handlerList.get(i).selectBorrowList();
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
