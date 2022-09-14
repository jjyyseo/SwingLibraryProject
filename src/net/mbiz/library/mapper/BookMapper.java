package net.mbiz.library.mapper;

import java.util.List;

import net.mbiz.library.data.BookVO;

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
