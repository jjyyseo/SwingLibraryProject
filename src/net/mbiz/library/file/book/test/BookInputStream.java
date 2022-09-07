package net.mbiz.library.file.book.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.file.LocationConstants;

public class BookInputStream {
	
	public BookInputStream() {
	}

	
	public void readBookFile() throws IOException {
		String location = LocationConstants.BOOK_DATA_lOCATION + "\\bk31.txt";
		
		Map<String, String> bkMap = new HashMap<>();
		
		ObjectInputStream ois = null;
		
		ois = new ObjectInputStream(new FileInputStream(location));
		
		try {
			bkMap = (HashMap<String, String>) ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		parseBookVO(bkMap);
	
	}
	
	private BookVO parseBookVO(Map<String, String> bkMap) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		BookVO vo = new BookVO();
		
		vo.setBookNo(Integer.parseInt((bkMap.get("bookNo"))));
		vo.setBookNm((bkMap.get("bookNm")));
		vo.setBookWtr((bkMap.get("bookWtr")));
		vo.setCategory((bkMap.get("category")));
		vo.setPublisher((bkMap.get("publisher")));
		vo.setBooksub((bkMap.get("booksub")));
		try {
			vo.setRegistDate(sdf.parse((bkMap.get("registDate"))) );
			vo.setReleaseDate(sdf.parse((bkMap.get("releaseDate"))));
			
			if (bkMap.get("updateDate") == null) {
				vo.setUpdateDate(null);
			} else {
				vo.setUpdateDate(sdf.parse((bkMap.get("updateDate"))));
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
			System.err.println("net.mbiz.library.file.book.BookInputStream: 날짜 포맷 오류 발생!");
		}
		vo.setIsBorrowed(Integer.parseInt((bkMap.get("isBorrowed"))));
		vo.setBookIsbn(Long.parseLong((bkMap.get("bookIsbn"))));
		System.out.println(vo);
		return vo;
	}


	public static void main(String[] args) throws IOException {
		new BookInputStream().readBookFile();
	}
	
	
}
