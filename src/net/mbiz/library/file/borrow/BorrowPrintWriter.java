package net.mbiz.library.file.borrow;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.data.BorrowVO;
import net.mbiz.library.file.LocationConstants;

/**
 * book data를 파일로 저장하는 클래스.
 * 
 * @author metabiz
 *
 */
public class BorrowPrintWriter {

	private BorrowVO vo;
	
	public BorrowPrintWriter(BorrowVO borrowVO) {
		this.vo = borrowVO;
	}
	
	public void writeBorrowFile() throws IOException {
		String location = LocationConstants.BORROW_DATA_lOCATION; 
		
		PrintWriter pw = null;
		File file = new File(location);
		FileWriter fileWriter = new FileWriter(file, true); // true - 이어쓰기 가능

		String insertVO = "";
		
		try {
			pw = new PrintWriter(fileWriter);
			
			insertVO +=
					    "#" + vo.getBorrowNo()
//					  + "#" + vo.getBookNo()
					  + "#" + vo.getBookNm() 
					  + "#" + vo.getBookWtr()
					  + "#" + vo.getIsBorrowed()
					  + "#" + vo.getStartDate()
					  + "#" + vo.getEndDate()
					  + "#" + vo.getReturnDate()
					  + "#" + vo.getOverdue();
					  
			
			pw.println(insertVO);
			pw.flush(); 
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}

	}

	
}
