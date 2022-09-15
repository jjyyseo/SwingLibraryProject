package net.mbiz.library.mapper;

import java.util.List;

import net.mbiz.library.data.BorrowVO;

/**
 * Mybatis 매핑XML에 기재된 SQL을 호출하기 위한 매퍼 클래스.
 * @author metabiz
 */
public interface BorrowMapper {
	
	public List<BorrowVO> selectBorrowkList();

	public int insertBorrow(BorrowVO vo);
	
	public int updateBorrow(BorrowVO vo);
	
	public int deleteBorrow(int borrowNo);

}
