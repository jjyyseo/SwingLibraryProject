package net.mbiz.library.file.book.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.file.LocationConstants;

public class BookOutputStream {

	private BookVO vo;
	
	public BookOutputStream(BookVO bookVO) {
		this.vo = bookVO;
	}
	
	public void writeBookFile() throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		
		String fileName = "\\bk" + vo.getBookNo() + ".txt"; 
		String location = LocationConstants.BOOK_DATA_lOCATION + fileName;
		
		ObjectOutputStream oos = null;

		try {

			oos = new ObjectOutputStream(new FileOutputStream(location));
			
			Map<String, String> bkMap = new HashMap<>();
			bkMap.put("bookNo"		, String.valueOf(vo.getBookNo()));
			bkMap.put("bookNm"		, vo.getBookNm());
			bkMap.put("bookWtr"		, vo.getBookWtr());
			bkMap.put("publisher"	, vo.getPublisher());
			bkMap.put("releaseDate"	, sdf.format(vo.getReleaseDate()));
			bkMap.put("category"	, vo.getCategory());
			bkMap.put("isBorrowed"	, String.valueOf(vo.getIsBorrowed()));
			bkMap.put("bookIsbn"	, Long.toString(vo.getBookIsbn()));
			bkMap.put("booksub"		, vo.getBooksub());
			bkMap.put("registDate"	, sdf.format(vo.getRegistDate()));
			
			if (vo.getUpdateDate() != null) {
				bkMap.put("updateDate"	, sdf.format(vo.getUpdateDate()));	
			}
			
			oos.writeObject(bkMap);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			oos.close();
		}

	}

	
}
