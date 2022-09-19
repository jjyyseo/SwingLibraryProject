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
import net.mbiz.library.util.FileLocationConstants;
import net.mbiz.library.util.LibraryVOParser;


//TODO dataHandler 양식에 맞게 수정하깅.
public class FileHandler extends DataHandler{
	
	private static FileHandler fileHandler = new FileHandler();

	private FileHandler(){
	}
	
	public static FileHandler getInstance() {
	    if(fileHandler == null){
	    	fileHandler = new FileHandler();
	    }
		return fileHandler;
	}

	
	
	/**
	 * bookData.txt 파일을 읽는 메서드.  
	 * List<BookVO>를 리턴한다.
	 * @return bookFileList
	 * @throws IOException
	 */
	public List<BookVO> selectBookList() {
		File file = new File(FileLocationConstants.BOOK_DATA_lOCATION);
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
				System.err.println("도서 기록 조회 중 에러 발생!");
			} finally {
				
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		} else {
			System.out.println("bookData.txt 파일이 존재하지 않음.");
		}
		
		
		return bookFileList;
	}

	
	
	/**
	 * toStringfile(bookVO)의 리턴값 = String type.을 파라미터로 받아 파일에 write하는 메서드.
	 * @param bkStr
	 * @return 		 성공 시 = 1, 실패 시 = 0
	 * @throws IOException
	 */
	public int insertBook(BookVO vo) {
		System.out.println("여기는 file핸들러");
		File file = new File(FileLocationConstants.BOOK_DATA_lOCATION);
		String bkStr = LibraryVOParser.bookVOToString(vo);

		PrintWriter pw = null;
		FileWriter fileWriter = null; 
		
		try {
			
			fileWriter = new FileWriter(file, true); // true - 이어쓰기 가능
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
	 * bookData.txt 파일을 update하는 메서드.
	 * 원본 파일 삭제 후 새 파일 생성, update된 정보 추가
	 * @param vo     도서 객체
	 * @return 		 성공 시 = 1, 실패 시 = 0
	 */
	public int updateBook(BookVO vo) {
		deleteBook(vo.getBookIsbn()); 
		insertBook(vo);
		return 1;
	}

	
	
	
	/**
	 * 도서 정보를 삭제하는 메서드.
	 * 해당 도서 정보를 제외한 대출 기혹으로 새 파일 생성 후 rename한다.
	 * @param isbn
	 * @return 		 성공 시 = 1, 실패 시 = 0
	 */
	public int deleteBook(String isbn) {
		File oldFile = new File(FileLocationConstants.BOOK_DATA_lOCATION);
		String newPath = "C:\\LibraryData\\book\\udtbookData.txt";

		// 파일 객체 생성
		Path path = Paths.get(FileLocationConstants.BOOK_DATA_lOCATION);
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
		
		File newFile = new File(FileLocationConstants.BOOK_DATA_lOCATION);
		udtfile.renameTo(newFile);
		
		
		return 1;
	}

	
	
	/**
	 * borrowData.txt 파일을 읽는 메서드.  
	 * List<BorrowVO>를 리턴한다.
	 * @return borrowFileList
	 * @throws IOException
	 */
	public List<BorrowVO> selectBorrowList(){
		File file = new File(FileLocationConstants.BORROW_DATA_lOCATION);
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
				System.err.println("대출 기록 조회 중 에러 발생!");
			} finally {
				
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
		} else {
			System.out.println("net.mbiz.library.handler.FileHandler.readBorrowList : borrowData.txt 파일이 존재하지 않음.");
		}
		
		
		return borrowFileList;
	}



	
	/**
	 * toStringfile(bookVO)의 리턴값 = String type.을 파라미터로 받아 파일에 write하는 메서드.
	 * @param bkStr
	 * @return 		 성공 시 = 1, 실패 시 = 0
	 * @throws IOException
	 */
	public int insertBorrow(BorrowVO vo) {
		PrintWriter pw = null;
		File file = new File(FileLocationConstants.BORROW_DATA_lOCATION);
		FileWriter fileWriter = null;
		
		String bwStr = LibraryVOParser.borrowVOToString(vo);
		
		try {
			fileWriter = new FileWriter(file, true); // true - 이어쓰기 가능
			pw = new PrintWriter(fileWriter);
			pw.println(bwStr);
			pw.flush();
			
		} catch (Exception e) {
			System.err.println("net.mbiz.library.handler.FileHandler.writeBorrowFile : 대출 정보 파일에 write 도중 에러 발생!");
			return 0;
		} finally {
			pw.close();
		}
		return 1;
	}

	
	
	/**
	 * borrowData.txt 파일을 update하는 메서드.
	 * 원본 파일 삭제 후 새 파일 생성, update된 정보 추가
	 * @param vo	  대출 객체
	 * @return 		  성공 시 = 1, 실패 시 = 0
	 */
	public int updateBorrow(BorrowVO vo) {
		deleteBorrow(vo.getBorrowNo());
		insertBorrow(vo);
		return 1;
	}


	
	
	/**
	 * 대츌 정보를 삭제하는 메서드.
	 * 해당 대출 정보를 제외한 대출 기혹으로 새 파일 생성 후 rename한다.
	 * @param bwNo
	 * @return 		 성공 시 = 1, 실패 시 = 0
	 */
	public int deleteBorrow(int bwNo) {
		
		String borrowNo = Integer.toString(bwNo);
		File oldFile = new File(FileLocationConstants.BORROW_DATA_lOCATION);
		String newPath = "C:\\LibraryData\\borrow\\udtbookData.txt";

		// 파일 객체 생성
		Path path = Paths.get(FileLocationConstants.BORROW_DATA_lOCATION);
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
		
		File newFile = new File(FileLocationConstants.BORROW_DATA_lOCATION);
		udtfile.renameTo(newFile);
		
		
		return 1;
	}

	@Override
	public BookVO selectBookOne(String isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int borrowBook(BorrowVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int returnBook(BorrowVO bwvo, BookVO bkvo) {
		// TODO Auto-generated method stub
		return 0;
	}



}
