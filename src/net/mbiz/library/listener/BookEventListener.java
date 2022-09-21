package net.mbiz.library.listener;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.data.BorrowVO;

/**
 * 도서 관련 Event가 일어날 경우 정해진 동작을 실행한다.
 * @author metabiz
 *
 */
public interface BookEventListener {


	public void bookAdded(BookVO vo);               /*도서 추가*/
	                                                
	public void bookUpdated(BookVO vo);             /*도서 수정*/
                                                    
	public void bookDeleted(String isbn);           /*도서 삭제*/
	                                                
	                                                
	public void borrowAdded(BorrowVO vo);           /*대출 하기*/
	                                               
	public void borrowUpdated(BorrowVO vo);         /*반납 하기*/
                                                    
	public void borrowDeleted(int bwNo);            /*대출 기록 삭제*/

}
