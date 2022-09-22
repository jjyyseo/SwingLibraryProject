package net.mbiz.library.handler;

import java.util.List;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.data.BorrowVO;
import net.mbiz.library.data.ChildCategoryVO;
import net.mbiz.library.data.ParentCategoryVO;
import net.mbiz.library.listener.BookEventListener;

public abstract class DataHandler{
	
	
	public abstract int insertBook(BookVO vo);                            /*도서 정보 insert*/ 
	public abstract int updateBook(BookVO vo);                            /*도서 정보 update*/  
	public abstract int deleteBook(String isbn);                          /*도서 정보 delete*/    
	public abstract BookVO selectBookOne(String isbn);                    /*도서 detail*/      
	public abstract int borrowBook(BorrowVO vo);                          /*도서 대출하기*/   
	public abstract int returnBook(BorrowVO bwvo);                        /*도서 반납하기*/  
	public abstract List<BookVO> selectBookList();                        /*도서리스트 select*/ 
	public abstract List<BookVO> searchBookList(BookVO vo);               /*도서 리스트 검색*/  
	public abstract List<BookVO> selectCategoryBookList(BookVO vo);       /*카테고리로 조회*/  

	
	public abstract int deleteBorrow(int bwNo);                           /*대출 기록 delete*/    
	public abstract List<BorrowVO> selectBorrowList();                    /*대출 리스트 select*/  
	public abstract List<BorrowVO> searchBorrowList(BorrowVO vo);         /*대출 리스트 검색*/

	
	public abstract List<ParentCategoryVO> selectParentCategoryList();	  		/*부모 카테고리 select*/
	public abstract List<ChildCategoryVO> selectChildCategoryList(int pCtgIdx); /*자식 카테고리 select*/
	public abstract String selectChildCategoryNm(int cIdx);					    /*자식 카테고리 이름 select*/
	public abstract int selectChildCategoryIdx(String cName);					/*자식 카테고리 인덱스 select*/
	public abstract String selectParentCategoryNm(int pIdx);					/*부모 카테고리 이름 select*/
	public abstract int selectParentCategoryIdx(String pName);					/*부모 카테고리 인덱스 select*/
	
}
