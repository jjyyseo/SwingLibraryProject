package net.mbiz.library.file.book;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import net.mbiz.library.file.LocationConstants;

/**
 * book data 파일을 읽어오는 클래스.
 * @author metabiz
 *
 */
public class BookFileReader {
	
	public BookFileReader() {
	}

	public void readBookFile() throws IOException {
		String location = LocationConstants.BOOK_DATA_lOCATION + "\\bookData.txt";
		File file = new File(location);
		
		BufferedReader br = null;
		try {
			FileReader fileReader = new FileReader(file);
			br = new BufferedReader(fileReader);
			String str = br.readLine();
			System.out.println(str);

			// br.readLine(); 별로 작업 필요...?
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
		
		
		
	}
	
	public static void main(String[] args) throws IOException {
		new BookFileReader().readBookFile();
	}
	
	
}
