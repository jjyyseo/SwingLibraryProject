package net.mbiz.library.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import net.mbiz.library.data.BorrowVO;
import net.mbiz.library.mapper.BorrowMapper;
import net.mbiz.library.mybatis.MyBatisConnectionFactory;

public class BorrowDAO {
	
	private SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
	private BorrowMapper borrowMapper = session.getMapper(BorrowMapper.class);
	
	public List<BorrowVO> selectBorrowkList(){
		return borrowMapper.selectBorrowkList();
	}

	public int insertBorrow(BorrowVO vo) {
		return borrowMapper.insertBorrow(vo);
	}
	
	public int updateBorrow(BorrowVO vo) {
		return borrowMapper.updateBorrow(vo);
	}
	
	public int deleteBorrow(int borrowNo) {
		return borrowMapper.deleteBorrow(borrowNo);
	}

}
