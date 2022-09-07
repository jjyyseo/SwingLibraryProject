package net.mbiz.library.file.book;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

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
		String location = LocationConstants.BOOK_DATA_lOCATION + "\\bookData.txt"; 
		
		PrintWriter pw = null;
		File file = new File(location);
		FileWriter fileWriter = new FileWriter(file, true); // true - 이어쓰기 가능

		String insertVO = "";
		
		try {
			pw = new PrintWriter(fileWriter);
			
			insertVO +=
					    "#" + vo.getBookNo()
					  + "#" + vo.getBookNm() 
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
