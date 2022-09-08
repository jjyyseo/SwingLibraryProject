package net.mbiz.library.file.borrow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import net.mbiz.library.data.BorrowVO;
import net.mbiz.library.file.LocationConstants;

/**
 * book data 파일을 읽어오는 클래스.
 * @author metabiz
 *
 */
public class BorrowFileReader {
	

	public BorrowFileReader() {
	}

	public List<BorrowVO> readBorrowFile() throws IOException {
		String location = LocationConstants.BORROW_DATA_lOCATION;
		File file = new File(location);
		
		BufferedReader br = null;
		String str;
		
		List<BorrowVO> borrowFileList = new ArrayList<>();
		
		if(file.exists()){
			
			try {
				FileReader fileReader = new FileReader(file);
				br = new BufferedReader(fileReader);

				while((str = br.readLine()) != null ) {
					borrowFileList.add(parseBorrowVO(str));
				}			
				
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				br.close();
			}
			
		}
		
		System.out.println(borrowFileList);
		
		return borrowFileList;
	}
	
	private BorrowVO parseBorrowVO(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d HH:mm:ss Z yyyy",Locale.ENGLISH);
		BorrowVO vo = new BorrowVO();
		
		String[] arr = str.split("#"); 
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i].trim();
			System.out.println(arr[i]);
		}

		//setting
		vo.setBorrowNo(Integer.parseInt(arr[1]));
		vo.setBookNo(Integer.parseInt(arr[2]));
		vo.setBookNm(arr[3]);
		vo.setBookWtr(arr[4]);
		vo.setIsBorrowed(Integer.parseInt(arr[5]));
		if (arr[9] != null && !arr[9].isEmpty()) {
			vo.setOverdue(Integer.parseInt(arr[9]));
		}else {
			vo.setOverdue(0);
		}
		
		
		try {
			vo.setEndDate(sdf.parse(arr[7]));
			vo.setStartDate(sdf.parse(arr[6]));
			
			if (arr[8] != null || !arr[8].isEmpty()) {
				vo.setReturnDate(sdf.parse(arr[8]));
			} else {
				vo.setReturnDate(null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("vo?? " + vo);
		
		return vo;
	}
	

	public static void main(String[] args) throws IOException {
		new BorrowFileReader().readBorrowFile();
	}
	
	
}
