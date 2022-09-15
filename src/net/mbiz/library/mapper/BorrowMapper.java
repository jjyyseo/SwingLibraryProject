package net.mbiz.library.mapper;

import java.util.List;

import net.mbiz.library.data.BorrowVO;

public interface BorrowMapper {
	
	public List<BorrowVO> selectBorrowkList();

	public int insertBorrow(BorrowVO vo);
	
	public int updateBorrow(BorrowVO vo);
	
	public int deleteBorrow(int borrowNo);

}
