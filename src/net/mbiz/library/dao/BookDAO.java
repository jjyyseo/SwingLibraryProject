package net.mbiz.library.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.mapper.BookMapper;
import net.mbiz.library.mybatis.MyBatisConnectionFactory;

/* sqlSessionFactory를 이용하여 SqlSession을 얻는다. */
public class BookDAO {

    private BookMapper bookMapper;
    private SqlSessionFactory sqlSessionFactory = null;
    
    public BookDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }
	
	
	public List<BookVO> selectBookList() {
		SqlSession session = sqlSessionFactory.openSession();
		bookMapper = session.getMapper(BookMapper.class);
		return bookMapper.selectBookList();
	}

	public int insertBook(BookVO vo) {
		SqlSession session = sqlSessionFactory.openSession();
		bookMapper = session.getMapper(BookMapper.class);
		return bookMapper.insertBook(vo);
	}

	public int updateBook(BookVO vo) {
		SqlSession session = sqlSessionFactory.openSession();
		bookMapper = session.getMapper(BookMapper.class);
		return bookMapper.updateBook(vo);
	}

	public int deleteBook(String isbn) {
		SqlSession session = sqlSessionFactory.openSession();
		bookMapper = session.getMapper(BookMapper.class);
		return bookMapper.deleteBook(isbn);
	}

	public BookVO selectBookOne(String isbn) {
		SqlSession session = sqlSessionFactory.openSession();
		bookMapper = session.getMapper(BookMapper.class);
		return bookMapper.selectBookOne(isbn);
	}

	public int updateBookState(String isbn) {
		SqlSession session = sqlSessionFactory.openSession();
		bookMapper = session.getMapper(BookMapper.class);
		return bookMapper.updateBookState(isbn);
	}

}
