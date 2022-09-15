package net.mbiz.library.mapper;

import java.util.List;

import net.mbiz.library.data.BookVO;

/**
 * Mybatis 매핑XML에 기재된 SQL을 호출하기 위한 매퍼 클래스.
 * @author metabiz
 */
public interface BookMapper {

	public List<BookVO> selectBookList();

	public int insertBook(BookVO vo);
	
	public int updateBook(BookVO vo);
	
	public int deleteBook(String isbn);
	//detail
	public BookVO selectBookOne(String isbn);
	//대출 신청 - 대출상태 update
	public int updateBookState(String isbn);

}

