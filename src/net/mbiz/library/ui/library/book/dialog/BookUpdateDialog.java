package net.mbiz.library.ui.library.book.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.data.BorrowVO;
import net.mbiz.library.data.ChildCategoryVO;
import net.mbiz.library.data.ParentCategoryVO;
import net.mbiz.library.manager.HandlerManager;
import net.mbiz.library.ui.common.CalenderDialog;
import net.mbiz.library.ui.common.CommonConstants;
import net.mbiz.library.util.DateFomatUtil;
import net.mbiz.library.util.LibraryVOParser;

public class BookUpdateDialog extends JDialog implements ActionListener{

	
	private JPanel pnMain;        
	private JPanel pnTop;		  // in pnMain
	private JPanel pnBottom;      // in pnMain
	private JPanel pnWest;        // in pnTop
	private JPanel pnEast;        // in pnTop
	private JPanel pnImg;         // in pnWest
	private JPanel pnAttach;      // in pnWest
	private JPanel pnCnt;         // in pnEast
	private JPanel pnCntWest;     // in pnEast
	private JPanel pnCntEast;     // in pnEast
	private JPanel pnIntro;       // in pnBottom
	private JPanel pnFooter;      // in pnBottom
	
	private JButton attachBtn;	  // in pnAttach
	private JButton updateBtn;	  // in pnFooter
	private JButton borrowBtn;	  // in pnFooter
	private JTextArea txtArea;    // in pnIntro
	
	// 도서 정보 입력란
	private JTextField tfBookNm;
	private JTextField tfBookWtr;
	private JTextField tfPublisher;
	private JTextField tfIsbn;
	private JTextField tfDate;
	private JPanel pnReleaseDate;
	private JPanel pnCal;
	private JButton calenderBtn;

	private JPanel pnCbb;		      
	private JComboBox<ParentCategoryVO> cbbCategoryP;
	private JComboBox<ChildCategoryVO> cbbCategoryC;
	
	private JLabel lblBookNm;
	private JLabel lblBookWtr;
	private JLabel lblPublisher;
	private JLabel lblCategory;
	private JLabel lblReleaseDate;
	private JLabel lblIsbn;
	
	private BookVO vo;
	private String bkNm;
	private HandlerManager manager = HandlerManager.getInstance();
	
	public BookUpdateDialog(){
		setTitle(bkNm);
		jbInit();
	}

	
	public void initializeBookOne(BookVO vo) {
		this.vo = vo;
		this.bkNm = vo.getBookNm();
		tfBookNm.setText(vo.getBookNm());
		tfBookWtr.setText(vo.getBookWtr());
		tfPublisher.setText(vo.getPublisher());
		tfDate.setText( DateFomatUtil.formatToString("releaseDate", vo.getReleaseDate()) );
		tfIsbn.setText(vo.getBookIsbn());
		txtArea.setText(vo.getBooksub());
		cbbCategoryP.setSelectedIndex(vo.getCCtgPnt());
	}


	/**
	 * 기본 UI Init
	 */
	private void jbInit() {
		
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setSize((new Dimension(600,700)));
		setModal(true); // 배경 클릭 ㄴㄴ
		
		this.add(Box.createHorizontalStrut(20), BorderLayout.WEST);
		this.add(Box.createHorizontalStrut(20), BorderLayout.EAST);
		this.add(Box.createVerticalStrut(20), BorderLayout.NORTH);
		this.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);
		
		/*pnMain - pnTop(NORTH),pnBottom(SOUTH)*/
		this.pnMain = new JPanel();
		pnMain.setLayout(new BorderLayout());
		pnMain.setPreferredSize(new Dimension(550,660));

		//NORTH
		this.pnTop = new JPanel();
		pnTop.setLayout(new BorderLayout());
		pnTop.setPreferredSize(new Dimension(0,318));
		//SOUTH
		this.pnBottom = new JPanel();
		pnBottom.setLayout(new BorderLayout());
		pnBottom.setPreferredSize(new Dimension(0,305));
		pnBottom.add(Box.createVerticalStrut(10), BorderLayout.NORTH);
		
		
		
		/*pnTop - pnWest(WEST), pnEast(EAST)*/
		//WEST
		this.pnWest = new JPanel();
		pnWest.setLayout(new BorderLayout());
		pnWest.setPreferredSize(new Dimension(180,0));
		pnWest.setBorder(BorderFactory.createEmptyBorder(18,10,10,10));
		//EAST
		this.pnEast = new JPanel();
		pnEast.setLayout(new BorderLayout());
		pnEast.setPreferredSize(new Dimension(367,0));
		pnEast.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		

		
		/*pnWest - pnImg(CENTER), pnBorrow(SOUTH)*/
		this.pnImg = new JPanel();
		pnImg.setPreferredSize(new Dimension(185,260));
		pnImg.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		pnWest.add(pnImg, BorderLayout.CENTER);
		this.pnAttach = new JPanel();
		pnAttach.setLayout(new BorderLayout());
		pnAttach.setPreferredSize(new Dimension(185,70));
		pnAttach.add(Box.createVerticalStrut(10), BorderLayout.NORTH);
		pnAttach.add(Box.createVerticalStrut(8), BorderLayout.SOUTH);		
		this.attachBtn = new JButton("사진 첨부");
		attachBtn.setFont(CommonConstants.FONT_BASE_17);

		pnAttach.add(attachBtn, BorderLayout.CENTER);
		pnWest.add(pnAttach, BorderLayout.SOUTH);
		
		
		
		/*pnEast - pnCnt(CENTER)*/
		//CENTER
		this.pnCnt = new JPanel();
		pnCnt.setLayout(new BorderLayout());
		pnCnt.setPreferredSize(new Dimension(0, 163));
		this.pnCntWest = new JPanel();
		pnCntWest.setLayout(new GridLayout(6,1));
		pnCntWest.setPreferredSize(new Dimension(80, 0));
		this.pnCntEast = new JPanel();
		pnCntEast.setLayout(new GridLayout(6,1, 5 ,10));
		pnCntEast.setPreferredSize(new Dimension(273, 0));
		pnCntEast.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

		pnCnt.add(pnCntEast, BorderLayout.EAST);
		pnCnt.add(pnCntWest, BorderLayout.WEST);
		
		this.pnCbb = new JPanel();
		pnCbb.setLayout(new BorderLayout());
		this.cbbCategoryP = new JComboBox<>();
		cbbCategoryP.setModel(new DefaultComboBoxModel<>());
		cbbCategoryP.setFont(CommonConstants.FONT_BASE_15);
		cbbCategoryP.setPreferredSize(new Dimension(127,60));
		
		setParentValues();

		this.cbbCategoryC = new JComboBox<>();
		cbbCategoryC.setFont(CommonConstants.FONT_BASE_15);
		cbbCategoryC.setPreferredSize(new Dimension(127,60));

		pnCbb.add(cbbCategoryP, BorderLayout.WEST);
		pnCbb.add(cbbCategoryC, BorderLayout.EAST);
		
		
		this.tfBookNm = new JTextField();
		this.tfBookWtr = new JTextField();
		this.tfPublisher = new JTextField();
		this.tfIsbn = new JTextField(14);
		this.tfDate = new JTextField();
		tfDate.setPreferredSize(new Dimension(190,0));
		tfDate.setEditable(false);
		
		tfBookNm.setFont(CommonConstants.FONT_BASE_17);
		tfBookWtr.setFont(CommonConstants.FONT_BASE_17);
		tfPublisher.setFont(CommonConstants.FONT_BASE_17);
		tfIsbn.setFont(CommonConstants.FONT_BASE_17);
		tfDate.setFont(CommonConstants.FONT_BASE_17);
		tfDate.setForeground(Color.black);

		
		this.calenderBtn = new JButton("달력");
		
		this.pnCal = new JPanel();
		pnCal.setLayout(new BorderLayout());
		pnCal.setPreferredSize(new Dimension(65,0));
		pnCal.add(calenderBtn, BorderLayout.CENTER);
		
		this.lblBookNm = new JLabel("도서명");
		this.lblBookWtr = new JLabel("저자");
		this.lblPublisher = new JLabel("출판사");
		this.lblIsbn = new JLabel("ISBN");
		this.lblCategory = new JLabel("카테고리");
		this.lblReleaseDate = new JLabel("출간일");
		lblBookNm.setFont(CommonConstants.FONT_BASE_17);
		lblBookWtr.setFont(CommonConstants.FONT_BASE_17); 
		lblPublisher.setFont(CommonConstants.FONT_BASE_17);
		lblIsbn.setFont(CommonConstants.FONT_BASE_17);
		lblCategory.setFont(CommonConstants.FONT_BASE_17);
		lblReleaseDate.setFont(CommonConstants.FONT_BASE_17);
		

		this.pnReleaseDate= new JPanel();
		pnReleaseDate.setLayout(new BorderLayout());;
		
		pnReleaseDate.add(pnCal, BorderLayout.EAST);
		pnReleaseDate.add(tfDate, BorderLayout.WEST);
		
		pnCntEast.add(tfBookNm);
		pnCntEast.add(tfBookWtr);
		pnCntEast.add(tfPublisher);
		pnCntEast.add(tfIsbn);
		pnCntEast.add(pnCbb);
		pnCntEast.add(pnReleaseDate);
		
		pnCntWest.add(lblBookNm);
		pnCntWest.add(lblBookWtr);
		pnCntWest.add(lblPublisher);
		pnCntWest.add(lblIsbn);
		pnCntWest.add(lblCategory);
		pnCntWest.add(lblReleaseDate);
		
		
		
		/*pnBottom - pnIntro(NORTH), pnFooter(SOUTH)*/
		//NORTH
		this.pnIntro = new JPanel();
		pnIntro.setLayout(new BorderLayout());
		pnIntro.setPreferredSize(new Dimension(0,230));
		pnIntro.setBorder(BorderFactory.createEmptyBorder(20,10,0,10));
		this.txtArea = new JTextArea();
		txtArea.setLineWrap(true); 
		txtArea.setPreferredSize(new Dimension(0,210));
		txtArea.setFont(CommonConstants.FONT_BASE_17);
		
		JScrollPane srlPn = new JScrollPane(txtArea);
		pnIntro.add(srlPn, BorderLayout.CENTER);
		
		//SOUTH		
		this.pnFooter = new JPanel();
		pnFooter.setLayout(new FlowLayout());
		pnFooter.setPreferredSize(new Dimension(0,70));
		pnFooter.setBorder(BorderFactory.createEmptyBorder(5,0,0,0));
		
		this.updateBtn = new JButton("수정");
		updateBtn.setPreferredSize(new Dimension(100,50));
		updateBtn.setFont(CommonConstants.FONT_BASE_17);
		this.borrowBtn = new JButton("대출신청");
		borrowBtn.setPreferredSize(new Dimension(100,50));
		borrowBtn.setFont(CommonConstants.FONT_BASE_17);
		pnFooter.add(borrowBtn, BorderLayout.CENTER);
		pnFooter.add(updateBtn, BorderLayout.CENTER);
		
		
		pnEast.add(pnCnt, BorderLayout.CENTER);

		pnTop.add(pnWest, BorderLayout.WEST);
		pnTop.add(pnEast, BorderLayout.EAST);
		pnBottom.add(pnIntro, BorderLayout.NORTH);
		pnBottom.add(pnFooter, BorderLayout.SOUTH);
		
		pnMain.add(pnTop, BorderLayout.NORTH);
		pnMain.add(pnBottom, BorderLayout.SOUTH);
		this.add(pnMain, BorderLayout.CENTER);
		
		
		calenderBtn.addActionListener(this);
		attachBtn.addActionListener(this);
		borrowBtn.addActionListener(this);
		updateBtn.addActionListener(this);
		cbbCategoryP.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(updateBtn)) {
			if (validateTextField()==1) {
				updateBookVO();
			} 
		} else if (e.getSource().equals(calenderBtn)) {
			openCalenderDialog();
		} else if (e.getSource().equals(attachBtn)) {
			openAttachMsgDialog();
		} else if (e.getSource().equals(borrowBtn)) {
			openBorrowDialog();
		} else if(e.getSource().equals(cbbCategoryP)) {
			initParentCbbIdx();
		} 
		
	}



	
	private void openBorrowDialog() {
		int rslt = JOptionPane.showConfirmDialog(null, vo.getBookNm()+ " 을(를) 대출 신청 하시겠습니까?", vo.getBookNm(), JOptionPane.YES_NO_OPTION);
		if (rslt == JOptionPane.YES_OPTION) { // '예' 선택
			if(vo.getIsBorrowed()!=1) {
				borrowBook();
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "해당 도서는 대출 중입니다.");
			}
		} else { 
			System.out.println(vo.getBookNm() + " 대출 신청을 취소합니다.");
		}
	}

	private void openAttachMsgDialog() {
		JOptionPane.showMessageDialog(null, "서비스 준비 중입니다.", "도서 사진 첨부하기", JOptionPane.INFORMATION_MESSAGE);
		
	}

	private void openCalenderDialog() {
		new CalenderDialog().setLocationCenter();
		tfDate.setText(CalenderDialog.rsltDate);
	}

	


	/**
	 * 도서 정보를 update하는 메서드.
	 * @return 성공 = 1, 실패 = 0
	 */
	private void updateBookVO() {


			ChildCategoryVO ctgVO = (ChildCategoryVO) cbbCategoryC.getSelectedItem();

			String bkNm = tfBookNm.getText();
			String bkWtr = tfBookWtr.getText();
			String publisher = tfPublisher.getText();
			String bookIsbn = tfIsbn.getText();
			String releaseDate = tfDate.getText();
			String booksub = txtArea.getText().replaceAll("\n","").replace("\n", ""); //enter 제거
			
			BookVO vo = new BookVO();
			vo.setBookNm(bkNm);
			vo.setBookWtr(bkWtr);
			vo.setPublisher(publisher);
			vo.setBookIsbn(bookIsbn);
			vo.setReleaseDate( DateFomatUtil.formatToDate(releaseDate));
			vo.setCCtgIdx(ctgVO.getCCtgIdx());
			vo.setCCtgPnt(cbbCategoryP.getSelectedIndex());
			vo.setCCtgNm(cbbCategoryC.getSelectedItem().toString());
			vo.setBooksub(booksub);
			
			try {
				manager.bookUpdated(vo);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(updateBtn, e);
				try {
					manager.bookUpdated(vo);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(updateBtn, e);
				}
			}
			
			manager.updatedBook(vo); //리스너 
			JOptionPane.showMessageDialog(null, vo.getBookNm() + "(이)가 수정되었습니다.", vo.getBookNm(), JOptionPane.INFORMATION_MESSAGE);
		
		dispose();
	}

	/**
	 * 대출 기록을 insert하는 메서드.
	 * @return 성공 = 1, 실패 = 0
	 */
	private void borrowBook() {
		ChildCategoryVO ctgVO = (ChildCategoryVO) cbbCategoryC.getSelectedItem();

		BorrowVO vo = new BorrowVO();
		vo.setBookNm(bkNm);
		vo.setBookIsbn(tfIsbn.getText());
		vo.setBookWtr(tfBookWtr.getText());
		vo.setCCtgIdx(ctgVO.getCCtgIdx());
		vo.setCCtgPnt(cbbCategoryP.getSelectedIndex());
		vo.setCCtgNm(cbbCategoryC.getSelectedItem().toString());
		
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(new Date()); 	
		cal.add(Calendar.DATE, 14);
		Date endDate = cal.getTime();
		
		vo.setStartDate(new Date());
		vo.setEndDate(endDate);
		
		try {
			manager.borrowAdded(vo);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(borrowBtn, e);
			
		}
		
		JOptionPane.showMessageDialog(null,vo.getBookNm() + "(을)를 대출 하였습니다.",vo.getBookNm(), JOptionPane.INFORMATION_MESSAGE);
		manager.addedBorrow(vo); //리스너
		
	}
	
	/**
	 * 텍스트필드에 작성된 내용이 양식에 맞는지 검사하는 메서드.
	 * @return 성공 시 = 1, 실패 시 = 0
	 */
	private int validateTextField() {
		if(vo.getIsBorrowed()==1){
			JOptionPane.showMessageDialog(null, "해당 도서는 대출 중입니다.");
			dispose();
			return 0;
		} else {
			// 어느 하나 빈칸이 있는 경우
			if ( tfBookNm.getText().isEmpty() || tfBookWtr.getText().isEmpty()
					|| tfPublisher.getText().isEmpty() || tfDate.getText().isEmpty() 
					|| tfIsbn.getText().isEmpty() || txtArea.getText().isEmpty() ) {
				JOptionPane.showMessageDialog(null, "정보가 모두 입력되지 않았습니다. 모두 입력해 주세요.", "도서 추가 실패", JOptionPane.INFORMATION_MESSAGE);
				return 0;
				
			} else if (tfIsbn.getText().length() != 13) {	  
				JOptionPane.showMessageDialog(null, "도서 isbn은 13자리 숫자로 입력해 주세요.", "isbn이 유효하지 않습니다. ", JOptionPane.INFORMATION_MESSAGE);
				return 0;
			} 
		}
		return 1;
	}



	private void setParentValues() {
		List<ParentCategoryVO> list= manager.selectParentCategoryList();
		ParentCategoryVO vo = new ParentCategoryVO();
		vo.setPCtgNm("전체 카테고리");
		cbbCategoryP.addItem(vo);
		
		for (ParentCategoryVO parentVO : list) {
			cbbCategoryP.addItem(parentVO);
		}
	}

	private void setChildValues(int pIdx) {
		List<ChildCategoryVO> list= manager.selectChildCategoryList(pIdx);
		for (ChildCategoryVO vo : list) {
			cbbCategoryC.addItem(vo);
		}
	}
	
	private void initParentCbbIdx() {
		cbbCategoryC.removeAllItems();
		int parentIdx = cbbCategoryP.getSelectedIndex();
		setChildValues(parentIdx);
		cbbCategoryC.repaint();
	}
	
	public void setLocationCenter() {
		Dimension d = this.getToolkit().getScreenSize(); 
		this.setLocation((int) d.getWidth() / 2 - this.getWidth() / 2, (int) d.getHeight() / 2 - this.getHeight() / 2);
		this.setVisible(true);
	}






}
