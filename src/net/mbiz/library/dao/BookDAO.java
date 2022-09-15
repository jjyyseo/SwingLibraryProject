package net.mbiz.library.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.mapper.BookMapper;
import net.mbiz.library.mybatis.MyBatisConnectionFactory;

/* sqlSessionFactory를 이용하여 SqlSession을 얻는다. */
public class BookDAO {

    private BookMapper bookMapper;
    private SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();

	
	public List<BookVO> selectBookList() {
		//매퍼 연결
		bookMapper = session.getMapper(BookMapper.class);
		return bookMapper.selectBookList();
	}

	public int insertBook(BookVO vo) {
		bookMapper = session.getMapper(BookMapper.class);
		return bookMapper.insertBook(vo);
	}

	public int updateBook(BookVO vo) {
		bookMapper = session.getMapper(BookMapper.class);
		return bookMapper.updateBook(vo);
	}

	public int deleteBook(String isbn) {
		bookMapper = session.getMapper(BookMapper.class);
		return bookMapper.deleteBook(isbn);
	}

	public BookVO selectBookOne(String isbn) {
		bookMapper = session.getMapper(BookMapper.class);
		return bookMapper.selectBookOne(isbn);
	}

	public int updateBookState(String isbn) {
		bookMapper = session.getMapper(BookMapper.class);
		return bookMapper.updateBookState(isbn);
	}

}
