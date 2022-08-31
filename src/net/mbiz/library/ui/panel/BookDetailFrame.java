package net.mbiz.library.ui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.mbiz.library.data.AddBookList;
import net.mbiz.library.data.AddBorrowList;
import net.mbiz.library.data.BookVO;
import net.mbiz.library.data.BorrowVO;
import net.mbiz.library.ui.common.CommonConstants;
import net.mbiz.library.ui.main.LibraryMain;

public class BookDetailFrame extends JDialog{

	private JPanel pnMain;        
	private JPanel pnTop;		  // in pnMain
	private JPanel pnBottom;      // in pnMain
	private JPanel pnWest;        // in pnTop
	private JPanel pnEast;        // in pnTop
	private JPanel pnImg;         // in pnWest
	private JPanel pnBorrow;      // in pnWest
	private JPanel pnTitle;       // in pnEast
	private JPanel pnCnt;         // in pnEast
	private JPanel pnFooter;      // in pnEast
	private JPanel pnIntro;       // in pnBottom
	
	private JLabel pnWtr;       
	private JLabel pnPublisher;       
	private JLabel pnRelease;       
	private JLabel pnCategory;       
	private JLabel pnIsbn;       
	                              
	private JLabel lblTitle;      // in pnTitle
	private JTextArea txtArea;    // in pnIntro
	private JTextArea txtTitle;
	private JButton borrowBtn;	
	
	
	public BookDetailFrame(BookVO bv) {
		this.setTitle(bv.getBookNm());
		jbInit(bv);
	}

	/**
	 * 기본 UI Init
	 */
	private void jbInit(BookVO bv) {
		setLocationRelativeTo(this); // 화면 중앙 설정
		setLayout(new BorderLayout());
		setSize(new Dimension(600,700));
		setBackground(CommonConstants.COLOR_WHITE_BACKGROUND);
		setModal(true);
		// 여백
		this.add(Box.createHorizontalStrut(20), BorderLayout.WEST);
		this.add(Box.createHorizontalStrut(20), BorderLayout.EAST);
		this.add(Box.createVerticalStrut(20), BorderLayout.NORTH);
		this.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);
		
		
		/*pnMain - pnTop(NORTH),pnBottom(SOUTH)*/
		this.pnMain = new JPanel();
		pnMain.setLayout(new BorderLayout());
		pnMain.setPreferredSize(new Dimension(560,660));
		pnMain.setBackground(CommonConstants.COLOR_WHITE_BACKGROUND);

		//NORTH
		this.pnTop = new JPanel();
		pnTop.setLayout(new BorderLayout());
		pnTop.setPreferredSize(new Dimension(0,363));
		pnTop.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		pnTop.setBackground(CommonConstants.COLOR_WHITE_BACKGROUND);
		//SOUTH
		this.pnBottom = new JPanel();
		pnBottom.setLayout(new BorderLayout());
		pnBottom.setPreferredSize(new Dimension(560,260));
		pnBottom.setBackground(CommonConstants.COLOR_WHITE_BACKGROUND);
		pnBottom.add(Box.createHorizontalStrut(10), BorderLayout.WEST);
		pnBottom.add(Box.createHorizontalStrut(10), BorderLayout.EAST);
		pnBottom.add(Box.createVerticalStrut(10), BorderLayout.NORTH);
		pnBottom.add(Box.createVerticalStrut(10), BorderLayout.SOUTH);
		
		
		
//---------- pnTop - pnWest(WEST), pnEast(EAST)---------------------------------
		//WEST
		this.pnWest = new JPanel();
		pnWest.setLayout(new BorderLayout());
		pnWest.setPreferredSize(new Dimension(215,0));
		pnWest.setBackground(CommonConstants.COLOR_WHITE_BACKGROUND);
		//EAST
		this.pnEast = new JPanel();
		pnEast.setLayout(new BorderLayout());
		pnEast.setPreferredSize(new Dimension(332,0));
		pnEast.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		pnEast.setBackground(CommonConstants.COLOR_WHITE_BACKGROUND);
		
		/*pnWest - pnImg(CENTER), pnBorrow(SOUTH)*/
		this.pnImg = new JPanel();
		pnImg.setPreferredSize(new Dimension(205,230));
		pnImg.setBackground(Color.magenta);
		pnWest.add(pnImg, BorderLayout.CENTER);
		
		this.pnBorrow = new JPanel();
		pnBorrow.setLayout(new BorderLayout());
		pnBorrow.setPreferredSize(new Dimension(205,100));
		pnBorrow.setBackground(CommonConstants.COLOR_WHITE_BACKGROUND);
		pnBorrow.add(Box.createVerticalStrut(10), BorderLayout.NORTH);
		pnBorrow.add(Box.createVerticalStrut(10), BorderLayout.SOUTH);
		
		if (bv.getIsBorrowed() == 1) {
			this.borrowBtn = new JButton("대출 신청");
		} else {
			this.borrowBtn = new JButton("대출중");
			borrowBtn.setEnabled(false); 	// 버튼 비활성화
		}
		pnBorrow.add(borrowBtn, BorderLayout.CENTER);
		pnWest.add(pnBorrow, BorderLayout.SOUTH);
		
		
		
		/*pnEast - pnTitle(NORTH), pnCnt(CENTER), pnFooter(SOUTH)*/
		//NORTH
		this.pnTitle = new JPanel();
		pnTitle.setLayout(new BorderLayout());
		pnTitle.setPreferredSize(new Dimension(0, 130));
		pnTitle.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		pnTitle.setBackground(CommonConstants.COLOR_WHITE_BACKGROUND);
		
		this.txtTitle = new JTextArea(bv.getBookNm());
		txtTitle.setFont(CommonConstants.FONT_TITLE_22);
		txtTitle.setEditable(false);
		txtTitle.setLineWrap(true); 
		txtTitle.setBackground(CommonConstants.COLOR_WHITE_BACKGROUND);
		txtTitle.setBorder(BorderFactory.createEmptyBorder(15,10,10,10));
		pnTitle.add(txtTitle, BorderLayout.NORTH);
		
//		this.lblTitle = new JLabel(bv.getBookNm());
//		lblTitle.setFont(CommonConstants.FONT_TITLE_22);
//		lblTitle.setBorder(BorderFactory.createEmptyBorder(15,10,10,10));
//		pnTitle.add(lblTitle, BorderLayout.NORTH);
		
		//CENTER
		this.pnCnt = new JPanel();
		pnCnt.setLayout(new GridLayout(2,2, 10,10));
		pnCnt.setPreferredSize(new Dimension(0, 173));
		pnCnt.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		pnCnt.setBackground(CommonConstants.COLOR_WHITE_BACKGROUND);
		//SOUTH
		this.pnFooter = new JPanel();
		pnFooter.setLayout(new BorderLayout());
		pnFooter.setPreferredSize(new Dimension(0, 60));
		pnFooter.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
		pnFooter.setBackground(CommonConstants.COLOR_WHITE_BACKGROUND);

		/*도서 정보를 출력할 패널. (in pnCnt) */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		
		this.pnWtr = new JLabel( "저자     | " + bv.getBookWtr());
		pnWtr.setPreferredSize(new Dimension(0, 0));
		pnWtr.setFont(CommonConstants.FONT_BASE_17);
		pnWtr.setBackground(CommonConstants.COLOR_WHITE_BACKGROUND);
		pnWtr.setOpaque(true);
		this.pnCategory = new JLabel(bv.getCategory());
		pnCategory.setPreferredSize(new Dimension(0, 0));
		pnCategory.setFont(CommonConstants.FONT_BASE_17);
		pnCategory.setBackground(CommonConstants.COLOR_WHITE_BACKGROUND);
		pnCategory.setOpaque(true);
		this.pnPublisher = new JLabel("출판사 | " + bv.getPublisher());
		pnPublisher.setPreferredSize(new Dimension(0, 0));
		pnPublisher.setFont(CommonConstants.FONT_BASE_17);
		pnPublisher.setBackground(CommonConstants.COLOR_WHITE_BACKGROUND);
		pnPublisher.setOpaque(true);
		this.pnRelease = new JLabel("출간 | " + sdf.format(bv.getReleaseDate()));
		pnRelease.setPreferredSize(new Dimension(0, 0));
		pnRelease.setFont(CommonConstants.FONT_BASE_17);
		pnRelease.setBackground(CommonConstants.COLOR_WHITE_BACKGROUND);
		pnRelease.setOpaque(true);

		String isbn = "ISBN   | " + Long.toString(bv.getBookIsbn());

		this.pnIsbn = new JLabel();
		pnIsbn.setText(isbn);
		pnIsbn.setPreferredSize(new Dimension(0, 0));
		pnIsbn.setFont(CommonConstants.FONT_BASE_17);
		pnIsbn.setBackground(CommonConstants.COLOR_WHITE_BACKGROUND);
		pnIsbn.setOpaque(true);

		pnCnt.add(pnWtr);
		pnCnt.add(pnCategory);
		pnCnt.add(pnPublisher);
		pnCnt.add(pnRelease);
		pnFooter.add(pnIsbn, BorderLayout.CENTER);

		
		
		
//---------------pnBottom - pnIntro(CENTER)---------------------------------------
		this.pnIntro = new JPanel();
		pnIntro.setLayout(new BorderLayout());
		pnIntro.setBackground(Color.GREEN);
		
		this.txtArea = new JTextArea(bv.getBooksub());
		txtArea.setBackground(CommonConstants.COLOR_WHITE_BACKGROUND);
		txtArea.setEditable(false);
		txtArea.setLineWrap(true); 
		txtArea.setFont(CommonConstants.FONT_BASE_17);
		JScrollPane srlPn = new JScrollPane(txtArea);
		pnIntro.add(srlPn, BorderLayout.CENTER);
		
		

		pnEast.add(pnTitle, BorderLayout.NORTH);
		pnEast.add(pnCnt, BorderLayout.CENTER);
		pnEast.add(pnFooter, BorderLayout.SOUTH);

		pnTop.add(pnWest, BorderLayout.WEST);
		pnTop.add(pnEast, BorderLayout.EAST);
		pnBottom.add(pnIntro, BorderLayout.CENTER);
		
		pnMain.add(pnTop, BorderLayout.NORTH);
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
		borrowVO.setBookNm(p_bv.getBookNm());
		borrowVO.setBookNo(p_bv.getBookNo());
		borrowVO.setUserId("a001");
		
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(new Date()); 	
		cal.add(Calendar.DATE, 14);
		Date endDate = cal.getTime();
		
		borrowVO.setStartDate(new Date());
		borrowVO.setEndDate(endDate);
		borrowVO.setIsBorrowed(0); //대출중
		
		AddBorrowList.borrowList.add(borrowVO);
		
		
		// bookList update
		int idx = p_bv.getBookNo()-1; //도서번호 - 1 = 인덱스
		AddBookList.bookList.get(idx).setIsBorrowed(0);
		
//		return p_bv; // setIsBorrowed(0)한 bookVO 리턴
	}	


}
