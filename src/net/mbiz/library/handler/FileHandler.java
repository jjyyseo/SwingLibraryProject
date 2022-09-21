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
import net.mbiz.library.listener.BookEventListener;
import net.mbiz.library.manager.HandlerManager;
import net.mbiz.library.util.FileLocationConstants;
import net.mbiz.library.util.LibraryVOParser;



public class FileHandler extends DataHandler implements BookEventListener{

	public FileHandler(){
		initialize();
	}
	
	public void initialize() {
    	HandlerManager.getInstance().addBookEventListener(this);
	}
    
	
	
	@Override
	public int bookAdded(BookVO vo) {
		System.out.println("여기는 FileHandler~~bookAdded");
		return insertBook(vo);
	}

	@Override
	public int bookUpdated(BookVO vo) {
		return updateBook(vo);
	}

	@Override
	public int bookDeleted(String isbn) {
		return deleteBook(isbn);
	}
    
	@Override
	public int borrowAdded(BorrowVO vo) {
		return borrowBook(vo);
	}

	@Override
	public int borrowUpdated(BorrowVO vo) {
		return returnBook(vo);
	}

	@Override
	public int borrowDeleted(int bwNo) {
		return deleteBorrow(bwNo);
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
		System.err.println("여기는 FileHandler~~insertBook");
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
		if (deleteBook(vo.getBookIsbn())==1 && insertBook(vo)==1) {
			return 1;	
		}
		return 0;
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
		
		Path path = Paths.get(FileLocationConstants.BOOK_DATA_lOCATION);	// 파일 객체 생성
		List<String> list = new ArrayList<>();								// 기존 파일 내용 담을 list
		List<String> udtList = new ArrayList<>();							// 수정 파일 내용 담을 list
		
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
		BufferedWriter bfw = null;
		try {
			bfw = new BufferedWriter(new FileWriter(udtfile));
			
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
		Path path = Paths.get(FileLocationConstants.BOOK_DATA_lOCATION);
		
		String str;
		List<String> list = new ArrayList<>();
		BookVO vo =null;
		
		try {
			list = Files.readAllLines(path);
		} catch (IOException e) {
			System.out.println("selectBookOne : 파일을 읽을 수 없음");
			return null;
		}
		for (String line : list) {
			String[] arr = line.split("@");
			
			if (arr[0].toString().equals(isbn)) { 
				vo =LibraryVOParser.stringToBookVO(line);
				break;
			} 
		}
		return vo;
	}
	


	/**
	 * bookData.txt 파일을 update하는 메서드.
	 * 원본 파일 삭제 후 새 파일 생성, update된 정보 추가
	 * @param vo     도서 객체
	 * @return 		 성공 시 = 1, 실패 시 = 0
	 */
	public int updateBookState(String isbn) {
		BookVO vo = selectBookOne(isbn);
		if (vo.getIsBorrowed()==1) {
			vo.setIsBorrowed(0);
		} else {
			vo.setIsBorrowed(1);
		}
		
		if (deleteBook(isbn)==1 && insertBook(vo)==1) {
			return 1;	
		}
		return 0;
	}

	/**
	 * 도서 대출하기.
	 * 도서 정보 update & 대출 정보 insert
	 */
	@Override
	public int borrowBook(BorrowVO vo) {
		if (updateBookState(vo.getBookIsbn())==1 && insertBorrow(vo)==1) {
			return 1;
		}
		return 0;
	}
	
	
	/**
	 * 도서 반납하기.
	 * 도서 정보 update & 대출 정보 update
	 */
	@Override
	public int returnBook(BorrowVO bwvo) {
		if(updateBorrow(bwvo)==1 && updateBookState(bwvo.getBookIsbn())==1) {
			return 1;
		}
		return 0;
	}


	@Override
	public List<BookVO> searchBookList(BookVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BorrowVO> searchBorrowkList(BorrowVO vo) {
		// TODO Auto-generated method stub
		return null;
	}



}
