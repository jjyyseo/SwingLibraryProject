package net.mbiz.library.ui.library.book.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.data.BorrowVO;
import net.mbiz.library.data.memory.AddBookList;
import net.mbiz.library.data.memory.AddBorrowList;
import net.mbiz.library.handler.FileHandler;
import net.mbiz.library.ui.common.CommonConstants;
import net.mbiz.library.ui.library.book.dialog.BookDetailDialog;

public class BookDetailPanel extends JPanel implements ActionListener{

	private JPanel pnMain;        
	// in pnMain
	private JPanel pnTop;		  
	private JPanel pnCenter;      
	// in pnTop
	private JPanel pnWest;        
	private JPanel pnEast;        
	// in pnWest
	private JPanel pnImg; 
	private JPanel pnBorrow;      
	// in pnEast
	private JPanel pnTitle;       
	private JPanel pnLeft;         
	private JPanel pnRight;      
	// in pnBottom
	private JPanel pnIntro;       
	
	private JLabel lblBookWtr;
	private JLabel lblPublisher;
	private JLabel lblCategory;
	private JLabel lblReleaseDate;
	private JLabel lblIsbn;   
	
	// 도서 정보 입력란
	private JTextArea tfBookNm;	  // in pnTitle
                                                                
	private JLabel bookWtr;                                  
	private JLabel category;                                 
	private JLabel publisher;                                
	private JLabel releaseDate;                              
	private JLabel isbn;
	                              
	private JTextArea txtArea;    // in pnIntro
	private JButton borrowBtn;	
	
	
	public BookDetailPanel(BookDetailDialog dl) {
		jbInit();
	}

	
	/**
	 * 기본 UI Init
	 */
	private void jbInit() {
		setLayout(new BorderLayout());
		
		/*pnMain - pnTop(NORTH), pnCenter(CENTER), pnBottom(SOUTH)*/
		this.pnMain = new JPanel();
		pnMain.setLayout(new BorderLayout());
		pnMain.setPreferredSize(new Dimension(560,660));

		//NORTH
		this.pnTop = new JPanel();
		pnTop.setLayout(new BorderLayout());
		pnTop.setPreferredSize(new Dimension(0,320));
		pnTop.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		//CENTER
		this.pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		pnCenter.setPreferredSize(new Dimension(0,230));
		pnCenter.add(Box.createVerticalStrut(10), BorderLayout.NORTH);
		pnCenter.add(Box.createVerticalStrut(10), BorderLayout.SOUTH);

		
		/*pnTop - pnWest(WEST), pnEast(EAST)*/
		//WEST
		this.pnWest = new JPanel();
		pnWest.setLayout(new BorderLayout());
		pnWest.setPreferredSize(new Dimension(205,0));
		pnWest.setBorder(BorderFactory.createEmptyBorder(10,0,0,5));
		//EAST
		this.pnEast = new JPanel();
		pnEast.setLayout(new BorderLayout());
		pnEast.setPreferredSize(new Dimension(342,0));
		pnEast.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		
		/*pnWest - pnImg(CENTER), pnBorrow(SOUTH)*/
		this.pnImg = new JPanel();
		pnImg.setPreferredSize(new Dimension(0,0));
		pnImg.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		pnWest.add(pnImg, BorderLayout.CENTER);
		this.pnBorrow = new JPanel();
		pnBorrow.setLayout(new BorderLayout());
		pnBorrow.setPreferredSize(new Dimension(0,70));
		pnBorrow.setBorder(BorderFactory.createEmptyBorder(10,0,5,0));
		pnBorrow.setBackground(CommonConstants.COLOR_BASE_BACKGROUND);
		pnWest.add(pnBorrow, BorderLayout.SOUTH);
		
		if (BookDetailDialog.bkDatilVO.getIsBorrowed() == 0) {
			this.borrowBtn = new JButton("대출 신청");
		} else {
			this.borrowBtn = new JButton("대출중");
			borrowBtn.setEnabled(false); 	// 버튼 비활성화
		}
		
		pnBorrow.add(borrowBtn, BorderLayout.CENTER);
		
		
		
		/*pnEast*/
		//NORTH
		this.pnTitle = new JPanel();
		pnTitle.setLayout(new BorderLayout());
		pnTitle.setPreferredSize(new Dimension(0, 80));
		pnTitle.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		
		this.tfBookNm = new JTextArea(BookDetailDialog.bkDatilVO.getBookNm());
		tfBookNm.setFont(CommonConstants.FONT_TITLE_22);
		tfBookNm.setEditable(false);
		tfBookNm.setLineWrap(true); // 줄바꿈 되도록.
		tfBookNm.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		tfBookNm.setBackground(CommonConstants.COLOR_BASE_BACKGROUND);
		pnTitle.add(tfBookNm, BorderLayout.NORTH);

		
		
		
		
		//WEST
		this.pnLeft = new JPanel();
		pnLeft.setLayout(new GridLayout(5,1,5,10));
		
		pnLeft.setPreferredSize(new Dimension(80,0));
		pnLeft.setBorder(BorderFactory.createEmptyBorder(10,10,0,10));
		
		this.lblBookWtr = new JLabel("저자");
		this.lblCategory = new JLabel("카테고리");
		this.lblPublisher = new JLabel("출판사");
		this.lblReleaseDate = new JLabel("출간일");
		this.lblIsbn = new JLabel("ISBN");
		lblBookWtr.setFont(CommonConstants.FONT_BASE_17); 
		lblCategory.setFont(CommonConstants.FONT_BASE_17);
		lblPublisher.setFont(CommonConstants.FONT_BASE_17);
		lblReleaseDate.setFont(CommonConstants.FONT_BASE_17);
		lblIsbn.setFont(CommonConstants.FONT_BASE_17);

		pnLeft.add(lblBookWtr);
		pnLeft.add(lblCategory);
		pnLeft.add(lblPublisher);
		pnLeft.add(lblReleaseDate);
		pnLeft.add(lblIsbn);
		
		
		//EAST
		this.pnRight = new JPanel();
		pnRight.setLayout(new GridLayout(5,1,5,10));
		pnRight.setPreferredSize(new Dimension(242,0));
		pnRight.setBorder(BorderFactory.createEmptyBorder(10,10,0,0));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
		
		this.bookWtr = new JLabel(BookDetailDialog.bkDatilVO.getBookWtr());
		this.category = new JLabel(BookDetailDialog.bkDatilVO.getCategory());
		this.publisher = new JLabel(BookDetailDialog.bkDatilVO.getPublisher());
		this.releaseDate = new JLabel(sdf.format(BookDetailDialog.bkDatilVO.getReleaseDate()));
		this.isbn = new JLabel(BookDetailDialog.bkDatilVO.getBookIsbn());
		bookWtr.setFont(CommonConstants.FONT_BASE_17); 
		category.setFont(CommonConstants.FONT_BASE_17);
		publisher.setFont(CommonConstants.FONT_BASE_17);
		releaseDate.setFont(CommonConstants.FONT_BASE_17);
		isbn.setFont(CommonConstants.FONT_BASE_17);

		pnRight.add(bookWtr);
		pnRight.add(category);
		pnRight.add(publisher);
		pnRight.add(releaseDate);
		pnRight.add(isbn);


		
		
		
	    /*pnCenter - pnIntro(CENTER)*/
		this.pnIntro = new JPanel();
		pnIntro.setLayout(new BorderLayout());
		pnIntro.setBackground(Color.GREEN);
		
		this.txtArea = new JTextArea(BookDetailDialog.bkDatilVO.getBooksub());
		txtArea.setBackground(CommonConstants.COLOR_BASE_BACKGROUND);
		txtArea.setEditable(false);
		txtArea.setLineWrap(true); 
		txtArea.setFont(CommonConstants.FONT_BASE_17);
		JScrollPane srlPn = new JScrollPane(txtArea);
		pnIntro.add(srlPn, BorderLayout.CENTER);
		
		
		/*pnCenter - pnIntro(CENTER)*/
		pnEast.add(pnLeft, BorderLayout.WEST);
		pnEast.add(pnRight, BorderLayout.EAST);
		pnEast.add(pnTitle, BorderLayout.NORTH);

		pnTop.add(pnWest, BorderLayout.WEST);
		pnTop.add(pnEast, BorderLayout.EAST);
		pnCenter.add(pnIntro, BorderLayout.CENTER);
		
		pnMain.add(pnTop, BorderLayout.NORTH);
		pnMain.add(pnCenter, BorderLayout.CENTER);
		this.add(pnMain, BorderLayout.CENTER);
		
		
		/*EVENT - 대출 신청하기*/
		borrowBtn.addActionListener(this);
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(borrowBtn)) {
			
			int rslt = JOptionPane.showConfirmDialog(null, BookDetailDialog.bkDatilVO.getBookNm()+ " 을(를) 대출 신청 하시겠습니까?", BookDetailDialog.bkDatilVO.getBookNm(), JOptionPane.YES_NO_OPTION);
			if (rslt == JOptionPane.YES_OPTION) { // '예' 선택
				insertBorrowInfo();
				updateBookState();
				JOptionPane.showMessageDialog(null, "대출 신청이 완료되었습니다.");
			} else { 
				System.out.println(BookDetailDialog.bkDatilVO.getBookNm() + " 대출 신청을 취소합니다.");
			}
			
		} 
	}
	
	
	/**
	 * 대출 기록을 insert하는 메서드. 
	 */
	private void insertBorrowInfo() {
		
		BorrowVO borrowVO = new BorrowVO();
//		borrowVO.setBorrowNo();
		borrowVO.setBookNm(BookDetailDialog.bkDatilVO.getBookNm());
		borrowVO.setBookIsbn(BookDetailDialog.bkDatilVO.getBookIsbn());
		
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(new Date()); 	
		cal.add(Calendar.DATE, 14);
		Date endDate = cal.getTime();
		
		borrowVO.setStartDate(new Date());
		borrowVO.setEndDate(endDate);
		borrowVO.setIsBorrowed(1); //대출중
		
		try {
			FileHandler.getInstance().writeBorrowFile(borrowVO);
		} catch (IOException e) {
			System.out.println("BookDetailPanel.insertBorrowInfo : 대출기록 insert 중 에러 발생");
		}
		
	}

	
	/**
	 * 도서의 대출 상태를 update하는 메서드.
	 */
	private void updateBookState() {
		// bookList update
		String isbn = BookDetailDialog.bkDatilVO.getBookIsbn();
		for (BookVO vo : CommonConstants.readBookFileList()) {
			if (vo.getBookIsbn().equals(isbn)) {
				vo.setIsBorrowed(1);
			}
		}
		
	}

}
