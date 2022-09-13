package net.mbiz.library.file.book;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.file.LocationConstants;

/**
 * book data를 파일로 저장하는 클래스.
 * 
 * @author metabiz
 *
 */
public class BookPrintWriter {

	private BookVO vo;
	
	public BookPrintWriter(BookVO bookVO) {
		this.vo = bookVO;
	}
	
	public void writeBookFile() throws IOException {
		String location = LocationConstants.BOOK_DATA_lOCATION; 
		
		PrintWriter pw = null;
		File file = new File(location);
		FileWriter fileWriter = new FileWriter(file, true); // true - 이어쓰기 가능

		String insertVO = "";
		
		try {
			pw = new PrintWriter(fileWriter);
			
			if (vo.getUpdateDate() == null) {
				vo.setUpdateDate(new Date());
			}
			
			insertVO +=
//					    "#" + vo.getBookNo()
					    "#" + vo.getBookNm() 
					  + "#" + vo.getBookWtr()
					  + "#" + vo.getPublisher()
					  + "#" + vo.getReleaseDate()
					  + "#" + vo.getIsBorrowed()
					  + "#" + vo.getBookIsbn()
					  + "#" + vo.getCategory()
					  + "#" + vo.getRegistDate()
					  + "#" + vo.getUpdateDate()
					  + "#" + vo.getBooksub();
			
			pw.println(insertVO);
			pw.flush(); 
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}

	}

	
}
