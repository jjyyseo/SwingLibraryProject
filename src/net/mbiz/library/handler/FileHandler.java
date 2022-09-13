package net.mbiz.library.handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.data.BorrowVO;
import net.mbiz.library.file.LocationConstants;
import net.mbiz.library.util.LibraryVOParser;

/**
 * 파일 쓰시, 읽기를 처리하는 클래스.
 * 매니저를 상속받아 이벤트 처리함.
 * @author metabiz
 *
 */
public class FileHandler {
	
	private static FileHandler fileHandler = new FileHandler();

	private FileHandler(){
	}
	
	public static FileHandler getInstance() {
		return fileHandler;
	}
	
	/**
	 * toStringfile(bookVO)의 리턴값 = String type.을 파라미터로 받아 파일에 write하는 메서드.
	 * @param bkStr
	 * @return 		 성공 시 = 1, 실패 시 = 0
	 * @throws IOException
	 */
	public static int writeBookFile(BookVO vo) throws IOException {
		
		PrintWriter pw = null;
		File file = new File(LocationConstants.BOOK_DATA_lOCATION);
		FileWriter fileWriter = new FileWriter(file, true); // true - 이어쓰기 가능
		
		String bkStr = LibraryVOParser.bookVOToString(vo);
		
		try {
			
			pw = new PrintWriter(fileWriter);
			pw.println(bkStr);
			pw.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("net.mbiz.library.handler.FileHandler.writeBookFile : 도서 정보 파일에 write 도중 에러 발생!");
			return 0;
		} finally {
			pw.close();
		}
		
		return 1;

	}
	
	/**
	 * toStringfile(bookVO)의 리턴값 = String type.을 파라미터로 받아 파일에 write하는 메서드.
	 * @param bkStr
	 * @return 		 성공 시 = 1, 실패 시 = 0
	 * @throws IOException
	 */
	public static int writeBorrowFile(BorrowVO vo) throws IOException {
		PrintWriter pw = null;
		File file = new File(LocationConstants.BORROW_DATA_lOCATION);
		FileWriter fileWriter = new FileWriter(file, true); // true - 이어쓰기 가능
		
		String bwStr = LibraryVOParser.borrowVOToString(vo);
		
		try {
			
			pw = new PrintWriter(fileWriter);
			pw.println(bwStr);
			pw.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("net.mbiz.library.handler.FileHandler.writeBorrowFile : 대출 정보 파일에 write 도중 에러 발생!");
			return 0;
		} finally {
			pw.close();
		}
		return 1;

	}
	
	
	/**
	 * bookData.txt 파일을 읽는 메서드.  
	 * List<BookVO>를 리턴한다.
	 * @return bookFileList
	 * @throws IOException
	 */
	public List<BookVO> readBookFile() throws IOException {
		
		File file = new File(LocationConstants.BOOK_DATA_lOCATION);
		BufferedReader br = null;
		String str;
		
		List<BookVO> bookFileList = new ArrayList<>();
		
		if(file.exists()){
			
			try {
				FileReader fileReader = new FileReader(file);
				br = new BufferedReader(fileReader);

				while((str = br.readLine()) != null ) {
					bookFileList.add( LibraryVOParser.stringToBookVO(str));
				}			
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				br.close();
			}
			
			
		} else {
			System.out.println("bookData.txt 파일이 존재하지 않음.");
		}
		
		
		return bookFileList;
	}


	/**
	 * borrowData.txt 파일을 읽는 메서드.  
	 * List<BorrowVO>를 리턴한다.
	 * @return borrowFileList
	 * @throws IOException
	 */
	public List<BorrowVO> readBorrowList() throws IOException {
		
		File file = new File(LocationConstants.BORROW_DATA_lOCATION);
		BufferedReader br = null;
		String str;
		
		List<BorrowVO> borrowFileList = new ArrayList<>();
		
		if(file.exists()){
			
			try {
				FileReader fileReader = new FileReader(file);
				br = new BufferedReader(fileReader);

				while((str = br.readLine()) != null ) {
					borrowFileList.add(LibraryVOParser.stringToBorrowVO(str));
				}			
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				br.close();
			}
			
			
		} else {
			System.out.println("net.mbiz.library.handler.FileHandler.readBorrowList : borrowData.txt 파일이 존재하지 않음.");
		}
		
		
		return borrowFileList;
	}
	
	/**
	 * 도서 정보를 삭제하는 메서드.
	 * 해당 도서 정보를 제외한 대출 기혹으로 새 파일 생성 후 rename한다.
	 * @param isbn
	 * @return 		 성공 시 = 1, 실패 시 = 0
	 */
	public static int deleteBookOne(String isbn) {

		File oldFile = new File(LocationConstants.BOOK_DATA_lOCATION);
		String newPath = "C:\\LibraryData\\book\\udtbookData.txt";

		// 파일 객체 생성
		Path path = Paths.get(LocationConstants.BOOK_DATA_lOCATION);
		// 기존 파일 내용 담을 list
		List<String> list = new ArrayList<>();
		// 수정 파일 내용 담을 list
		List<String> udtList = new ArrayList<>();
		
		try {
			list = Files.readAllLines(path);
		} catch (IOException e) {
			System.out.println("net.mbiz.library.handler.FileHandler.deleteBookOne : 기존 파일 읽는 중 에러 발생!");
			return 0;
		}
		
		for (String line : list) {
			//해당 도서 정보를 제외한 도서 정보 새 리스트에 모두 추가하기.
			if (line.contains(isbn)) { 
				continue;
			} else {
				udtList.add(line);
			}
		}
		
		
		/*새 파일 내용쓰기*/
		File udtfile = new File(newPath);
		try {
			BufferedWriter bfw = new BufferedWriter(new FileWriter(udtfile));
			
			if (udtfile.isFile() && udtfile.canWrite()) {
				for (String line : udtList) {
					bfw.write(line);
					bfw.newLine();
				}
				bfw.close();
			}
			
		} catch (IOException e) {
			System.err.println("net.mbiz.library.handler.FileHandler.deleteBookOne : 새 파일 내용 쓰기 실패.");
			return 0;
		}
		
		if(oldFile.delete()){
			System.out.println("net.mbiz.library.handler.FileHandler.deleteBookOne : bookData.txt 파일삭제 성공");
		}else{
			System.err.println("net.mbiz.library.handler.FileHandler.deleteBookOne : bookData.txt 파일삭제 실패");
			return 0;
		}
		
		File newFile = new File(LocationConstants.BOOK_DATA_lOCATION);
		udtfile.renameTo(newFile);
		
		
		return 1;
		
	}

	/**
	 * 대츌 정보를 삭제하는 메서드.
	 * 해당 대출 정보를 제외한 대출 기혹으로 새 파일 생성 후 rename한다.
	 * @param bwNo
	 * @return 		 성공 시 = 1, 실패 시 = 0
	 */
	public static int deleteBorrowOne(int bwNo) {
		System.err.println("수정 제외할 대출번호?? " + bwNo);
		
		String borrowNo = Integer.toString(bwNo);
		File oldFile = new File(LocationConstants.BORROW_DATA_lOCATION);
		String newPath = "C:\\LibraryData\\borrow\\udtbookData.txt";

		// 파일 객체 생성
		Path path = Paths.get(LocationConstants.BORROW_DATA_lOCATION);
		// 기존 파일 내용 담을 list
		List<String> list = new ArrayList<>();
		// 수정 파일 내용 담을 list
		List<String> udtList = new ArrayList<>();
		
		try {
			list = Files.readAllLines(path);
		} catch (IOException e) {
			System.err.println("net.mbiz.library.handler.FileHandler.deleteBorrowOne : 기존 파일 읽는 중 에러 발생!");
			return 0;
		}
		System.err.println("전체" + list);
		
		for (String line : list) {
			//해당 도서 정보를 제외한 도서 정보 새 리스트에 모두 추가하기.
			
			String[] arr = line.split("@");
			
			if (arr[0].equals(borrowNo)) { 
				continue;
			} else {
				udtList.add(line);
			}
		}
		System.err.println("해당 도서 정보를 제외한 도서 정보" + udtList);
		
		
		/*새 파일 내용쓰기*/
		File udtfile = new File(newPath);
		try {
			BufferedWriter bfw = new BufferedWriter(new FileWriter(udtfile));
			
			if (udtfile.isFile() && udtfile.canWrite()) {
				for (String line : udtList) {
					bfw.write(line);
					bfw.newLine();
				}
				bfw.close();
			}
			
		} catch (IOException e) {
			System.out.println("새 파일 내용쓰기 실패");
			return 0;
		}
		
		if(oldFile.delete()){
			System.out.println("net.mbiz.library.handler.FileHandler.deleteBorrowOne : borrowData.txt 파일삭제 성공");
		}else{
			System.out.println("net.mbiz.library.handler.FileHandler.deleteBorrowOne : borrowData.txt 파일삭제 실패");
			return 0;
		}
		
		File newFile = new File(LocationConstants.BORROW_DATA_lOCATION);
		udtfile.renameTo(newFile);
		
		
		return 1;
	}

	
	/**
	 * bookData.txt 파일을 update하는 메서드.
	 * 원본 파일 삭제 후 새 파일 생성, update된 정보 추가
	 * @param isbn   도서 ISBN
	 * @param vo     도서 객체
	 * @return 		 성공 시 = 1, 실패 시 = 0
	 */
	public static int updateBookFile(String isbn, BookVO vo) {
		deleteBookOne(isbn); 
		try {
			writeBookFile(vo);
		} catch (IOException e) {
			System.err.println("net.mbiz.library.handler.FileHandler.updateBookFile : 도서 정보 수정 중 예외 발생!");
			return 0;
		}
		return 1;
	}

	/**
	 * borrowData.txt 파일을 update하는 메서드.
	 * 원본 파일 삭제 후 새 파일 생성, update된 정보 추가
	 * @param bwNo    대출 번호
	 * @param vo	  대출 객체
	 * @return 		  성공 시 = 1, 실패 시 = 0
	 */
	public static int updateBorrowFile(int bwNo, BorrowVO vo) {
		deleteBorrowOne(bwNo);
		try {
			writeBorrowFile(vo);
		} catch (IOException e) {
			System.err.println("net.mbiz.library.handler.FileHandler.updateBookFile : 도서 정보 수정 중 예외 발생!");
			return 0;
		}
		return 1;
	}
	
	
	
}
