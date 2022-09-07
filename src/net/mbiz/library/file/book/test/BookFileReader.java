package net.mbiz.library.file.book.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.mbiz.library.file.LocationConstants;

public class BookFileReader {
	
	public BookFileReader() {
	}

	
	public void readBookFile() throws IOException {
		String location = LocationConstants.BOOK_DATA_lOCATION + "\\bk31.txt";
		File file = new File(location);
		
		BufferedReader br = null;
		try {
			FileReader fileReader = new FileReader(file);
			br = new BufferedReader(fileReader);
			String str = br.readLine();
			System.out.println(str);

			
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
