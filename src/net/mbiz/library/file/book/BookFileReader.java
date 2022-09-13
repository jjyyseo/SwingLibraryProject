package net.mbiz.library.file.book;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.file.LocationConstants;

/**
 * book data 파일을 읽어오는 클래스.
 * @author metabiz
 *
 */
public class BookFileReader {
	

	public BookFileReader() {
	}

	public List<BookVO> readBookFile() throws IOException {
		String location = LocationConstants.BOOK_DATA_lOCATION ;
		File file = new File(location);
		
		BufferedReader br = null;
		String str;
		
		List<BookVO> bookFileList = new ArrayList<>();
		
		if(file.exists()){
			
			try {
				FileReader fileReader = new FileReader(file);
				br = new BufferedReader(fileReader);

				while((str = br.readLine()) != null ) {
					bookFileList.add(parseBookVO(str));
				}			
				
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				br.close();
			}
			
		}
		
		System.out.println(bookFileList);
		return bookFileList;
	}
	
	private BookVO parseBookVO(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d HH:mm:ss Z yyyy",Locale.ENGLISH);
		BookVO vo = new BookVO();
		
		String[] arr = str.split("#"); 
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i].trim();
			System.out.println("arr["+i+"]" + arr[i]);
		}
		int idx = 0;
//		arr[idx++];

		
		
//		vo.setBookNo(Integer.parseInt(arr[1]));         /* 도서번호*/
		vo.setBookNm(arr[2]);                           /* 도서명*/
		vo.setBookWtr(arr[3]);                          /* 저자*/
		vo.setPublisher(arr[4]);                        /* 출판사*/
		vo.setCategory(arr[8]);                         /* 카테고리*/
		vo.setIsBorrowed(Integer.parseInt(arr[6]));     /* 대출상태*/
//		vo.setBookIsbn(Long.parseLong(arr[7]));         /* isbn*/
		vo.setBooksub(arr[0]);                          /* 소개글*/
		
		try {
			vo.setReleaseDate(sdf.parse(arr[5]));       /* 출간일*/
                                                      
			vo.setRegistDate(sdf.parse(arr[9]));        /* 등록일*/
			
			if (arr[10] != null && !arr[10].isEmpty()) {
				vo.setUpdateDate(sdf.parse(arr[10]));	/* 수정일*/
			} else {
				vo.setUpdateDate(null);
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return vo;
	}
	

	public static void main(String[] args) throws IOException {
		new BookFileReader().readBookFile();
	}
	
}
