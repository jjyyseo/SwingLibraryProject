package net.mbiz.library.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.mbiz.library.data.AddBookList;
import net.mbiz.library.data.AddBorrowList;
import net.mbiz.library.data.BookVO;
import net.mbiz.library.data.BorrowVO;
import net.mbiz.library.ui.common.CommonConstants;

public class BookDetailDialog extends JDialog{

	private JPanel pnMain;        
	// in pnMain
	private JPanel pnTop;		  
	private JPanel pnCenter;      
	private JPanel pnBottom;      
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
	
	
	public BookDetailDialog(BookVO bv) {
		this.setTitle(bv.getBookNm());
		jbInit(bv);
	}

	/**
	 * 기본 UI Init
	 */
	private void jbInit(BookVO bv) {
		setLocationRelativeTo(this); // 화면 중앙 설정
		setLayout(new BorderLayout());
		setSize((new Dimension(600,700)));
		setModal(true);
		// 여백
		this.add(Box.createHorizontalStrut(20), BorderLayout.WEST);
		this.add(Box.createHorizontalStrut(20), BorderLayout.EAST);
		this.add(Box.createVerticalStrut(20), BorderLayout.NORTH);
		this.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);
		
		
		/*pnMain - pnTop(NORTH), pnCenter(CENTER), pnBottom(SOUTH)*/
		this.pnMain = new JPanel();
		pnMain.setLayout(new BorderLayout());
		pnMain.setPreferredSize(new Dimension(560,660));

		//NORTH
		this.pnTop = new JPanel();
		pnTop.setLayout(new BorderLayout());
		pnTop.setPreferredSize(new Dimension(0,290));
		pnTop.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		//CENTER
		this.pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		pnCenter.setPreferredSize(new Dimension(0,250));
		pnCenter.add(Box.createVerticalStrut(10), BorderLayout.NORTH);
		pnCenter.add(Box.createVerticalStrut(10), BorderLayout.SOUTH);
		//SOUTH
		this.pnBottom = new JPanel();
		pnBottom.setLayout(new BorderLayout());
		pnBottom.setPreferredSize(new Dimension(0,80));
		
		this.pnBorrow = new JPanel();
		pnBorrow.setLayout(new BorderLayout());
		pnBorrow.setPreferredSize(new Dimension(205,80));
		pnBorrow.add(Box.createVerticalStrut(10), BorderLayout.NORTH);
		pnBorrow.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);
		pnBorrow.add(Box.createHorizontalStrut(200), BorderLayout.WEST);
		pnBorrow.add(Box.createHorizontalStrut(200), BorderLayout.EAST);
		
		if (bv.getIsBorrowed() == 0) {
			this.borrowBtn = new JButton("대출 신청");
		} else {
			this.borrowBtn = new JButton("대출중");
			borrowBtn.setEnabled(false); 	// 버튼 비활성화
		}
		pnBorrow.add(borrowBtn, BorderLayout.CENTER);
		pnBottom.add(pnBorrow, BorderLayout.CENTER);
		
		
//---------- pnTop - pnWest(WEST), pnEast(EAST)---------------------------------
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
//		pnImg.setPreferredSize(new Dimension(0,170));
		pnImg.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		pnWest.add(pnImg, BorderLayout.CENTER);
		
		
		
		
		
		/*pnEast*/
		//NORTH
		this.pnTitle = new JPanel();
		pnTitle.setLayout(new BorderLayout());
		pnTitle.setPreferredSize(new Dimension(0, 80));
		pnTitle.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		
		this.tfBookNm = new JTextArea(bv.getBookNm());
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
		
		this.bookWtr = new JLabel(bv.getBookWtr());
		this.category = new JLabel(bv.getCategory());
		this.publisher = new JLabel(bv.getPublisher());
		this.releaseDate = new JLabel(sdf.format(bv.getReleaseDate()));
		this.isbn = new JLabel(Long.toString(bv.getBookIsbn()));
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
		
		this.txtArea = new JTextArea(bv.getBooksub());
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
		pnMain.add(pnBottom, BorderLayout.SOUTH);
		this.add(pnMain, BorderLayout.CENTER);
		
		
		
		/*EVENT - 대출 신청하기*/
		borrowBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BookVO updateVO = null;
				int rslt = JOptionPane.showConfirmDialog(null, bv.getBookNm()+ " 을(를) 대출 신청 하시겠습니까?", bv.getBookNm(), JOptionPane.YES_NO_OPTION);
				if (rslt == JOptionPane.YES_OPTION) { // '예' 선택
					insertBorrow(bv);
					JOptionPane.showMessageDialog(null, "대출 신청이 완료되었습니다.");
					dispose();
				} else { 
					System.out.println(bv.getBookNm() + " 대출 신청을 취소합니다.");
				}
				
				System.out.println("대출 상태 ----> " + bv.getIsBorrowed());
			}
		});
		
	}
	
	public void setLocationCenter() {
		Dimension d = this.getToolkit().getScreenSize(); 
		this.setLocation((int) d.getWidth() / 2 - this.getWidth() / 2, (int) d.getHeight() / 2 - this.getHeight() / 2);
		this.setVisible(true);
	}
	
	/**
	 * 대출 기록을 insert하는 메서드. 
	 * @param BookVO
	 */
	private void insertBorrow(BookVO p_bv) {
		// borrowList에 대출 기록 추가
		
		BorrowVO borrowVO = new BorrowVO();
		borrowVO.setBorrowNo(AddBorrowList.borrowList.size()+1);
		borrowVO.setBookNm(p_bv.getBookNm());
		borrowVO.setBookNo(p_bv.getBookNo());
		borrowVO.setUserId("a001");
		
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(new Date()); 	
		cal.add(Calendar.DATE, 14);
		Date endDate = cal.getTime();
		
		borrowVO.setStartDate(new Date());
		borrowVO.setEndDate(endDate);
		borrowVO.setIsBorrowed(1); //대출중
		
		AddBorrowList.borrowList.add(borrowVO);
		
		
		// bookList update
		int idx = p_bv.getBookNo()-1; //도서번호 - 1 = 인덱스
		System.out.println("도서리스트의 인덱스" + idx);
		System.out.println("도서리스트의 사이즈" + AddBookList.bookList.size());
		AddBookList.bookList.get(idx).setIsBorrowed(1);
	}	


}
