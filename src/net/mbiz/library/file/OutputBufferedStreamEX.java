package net.mbiz.library.file;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OutputBufferedStreamEX {

	public static void main(String[] args) throws IOException {
	
		BufferedOutputStream bs = null;
		try {
			bs = new BufferedOutputStream(new FileOutputStream("C:\\LibrartData\\test.txt"));
			String str ="오늘 날씨는 아주 좋습니다.";
			bs.write(str.getBytes()); //Byte형으로만 넣을 수 있음
	
		} catch (Exception e) {
	                e.getStackTrace();
		} finally {
			bs.close();
		}
		
    }
}
