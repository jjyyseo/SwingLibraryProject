package net.mbiz.library.file.book.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.file.LocationConstants;

public class BookPrintWriter {

	private BookVO vo;
	
	public BookPrintWriter(BookVO bookVO) {
		this.vo = bookVO;
	}
	
	public void writeBookFile() throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		Map<String, String> bkMap = new HashMap<>();
		String location = LocationConstants.BOOK_DATA_lOCATION + "\\bk" + vo.getBookNo() + ".txt"; 
		
		PrintWriter pw = null;
		File file = new File(location);
		FileWriter fileWriter = new FileWriter(file);

		try {
			pw = new PrintWriter(fileWriter);
			pw.print(vo);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}

	}

	
}
