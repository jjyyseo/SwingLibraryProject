package net.mbiz.library.ui.library.myPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.mbiz.edt.barcode.ag.ui.common.table.BeanTableModel;
import net.mbiz.library.data.AddBookList;
import net.mbiz.library.data.AddBorrowList;
import net.mbiz.library.data.BookVO;
import net.mbiz.library.data.BorrowVO;
import net.mbiz.library.ui.common.CommonConstants;
import net.mbiz.library.ui.main.LibraryMain;

public class MyPageTablePanel extends JPanel implements ActionListener, MouseListener{
	
	private JPanel pnBody;
	private JPanel pnTbl;
	private JPanel pnHeader;
	private JPanel pnFooter;
	private JPanel pnSch;
	private JPanel pnPadding;
	private JPanel pnTitle;
	private JPanel pnPvs;
	private JPanel pnBtnSet;
	
	private JLabel title;
	private JLabel pvsLbl;
	
	private JTextField schFd;
	
	private JButton schBtn;
	private JButton pvsBtn;
	private JButton returnBtn;
	private JButton deleteBtn;
	
	private JTable borrowTbl;
	
	public MyPageTablePanel(LibraryMain f) {
		jbInit();
		initTable();	// 테이블 생성 먼저.
		initialize();	// 데이터 초기화
	}

	private void initialize() {
		Collections.sort(AddBorrowList.borrowList, Collections.reverseOrder());
		CommonConstants.bwModel.addDataList((ArrayList) AddBorrowList.borrowList);
		CommonConstants.bwModel.fireTableDataChanged();	// 테이블에 변경된 데이터 반영
	}

	private void jbInit() {
		setLayout(new BorderLayout());
		setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		
		/*pnBody - pnHeader(NORTH), pnTbl(CENTER), pnFooter(SOUTH)*/
		this.pnBody = new JPanel();
		pnBody.setLayout(new BorderLayout());
		pnBody.setBackground(Color.DARK_GRAY);

		
		//NORTH
		this.pnHeader = new JPanel();
		pnHeader.setLayout(new BorderLayout());
		pnHeader.setPreferredSize(new Dimension(0,150));
		pnHeader.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		pnHeader.add(Box.createHorizontalStrut(920), BorderLayout.WEST);
		pnHeader.add(Box.createVerticalStrut(15), BorderLayout.SOUTH);

		
		//CENTER - 테이블 영역 패널
		this.pnTbl = new JPanel(); 
		pnTbl.setLayout(new BorderLayout());
		pnTbl.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		this.borrowTbl  = new JTable(CommonConstants.bwModel); // 데이터가 들어가는 테이블 
		borrowTbl.setRowHeight(32);
		borrowTbl.setFont(CommonConstants.FONT_BASE_17);
		// 테이블 스크롤
		JScrollPane sclpn = new JScrollPane(borrowTbl);
		sclpn.setCorner(JScrollPane.UPPER_LEFT_CORNER, borrowTbl.getTableHeader());
		
		
		//SOUTH		
		this.pnFooter = new JPanel();
		pnFooter.setLayout(new BorderLayout());
		pnFooter.setPreferredSize(new Dimension(0,50));
		pnFooter.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);

		
		
		
		/* pnHeander - pnTitle(NORTH), pnSch(EAST), pnPvs(WEST) */
		// NORTH
		this.pnTitle = new JPanel();
		pnTitle.setLayout(new BorderLayout());
		pnTitle.setPreferredSize(new Dimension(0,55));
		pnTitle.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		this.title = new JLabel("나의 대출리스트");
		title.setFont(CommonConstants.FONT_TITLE_25);
		title.setHorizontalAlignment(JLabel.LEFT);
		title.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

		//EAST
		this.pnSch = new JPanel();
		pnSch.setLayout(new BorderLayout());
		pnSch.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		pnSch.setBorder(BorderFactory.createEmptyBorder(33, 0, 0, 0));
		
		//WEST
		this.pnPvs = new JPanel();
		pnPvs.setLayout(new BorderLayout());
		pnPvs.setBorder(BorderFactory.createEmptyBorder(33, 0, 0, 0));
		pnPvs.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		/* 전체 리스트로. */
		this.pvsBtn = new JButton();
		pvsBtn.setPreferredSize(new Dimension(200, 30));
		pvsBtn.setBorderPainted(false);
		pvsBtn.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);

		this.pvsLbl = new JLabel("전체보기");
		pvsLbl.setFont(CommonConstants.FONT_BASE_17);
		pvsLbl.setHorizontalAlignment(JLabel.CENTER);
		pvsBtn.add(pvsLbl);	
		
		
		
		
		/* pnFooter - pnBtnSet(EAST) - returnBtn(WEST), deleteBtn(EAST) */
		this.pnBtnSet = new JPanel();
		pnBtnSet.setLayout(new BorderLayout());
		pnBtnSet.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		pnBtnSet.add(Box.createVerticalStrut(10), BorderLayout.NORTH);
		pnBtnSet.add(Box.createHorizontalStrut(10), BorderLayout.CENTER);
		
		
		this.returnBtn = new JButton("반납");
		returnBtn.setPreferredSize(new Dimension(70, 70));
		returnBtn.setFont(CommonConstants.FONT_BASE_15);
		this.deleteBtn = new JButton("삭제");
		deleteBtn.setPreferredSize(new Dimension(70, 70));
		deleteBtn.setFont(CommonConstants.FONT_BASE_15);
		
		
		
		/* pnSch - schFd(WEST), pnPadding(CENTER), schBtn(EAST) */
		// WEST - 검색어 입력란
		this.schFd = new JTextField();
		schFd.setPreferredSize(new Dimension(550, 30));
		schFd.setFont(CommonConstants.FONT_BASE_17);
		
		// EAST - 검색 버튼
		this.schBtn = new JButton();
		schBtn.setPreferredSize(new Dimension(70, 30));
		schBtn.setFont(CommonConstants.FONT_BASE_12);

		// CENTER
		this.pnPadding = new JPanel();
		pnPadding.setPreferredSize(new Dimension(10, 10));
		pnPadding.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);


		//footer
		pnBtnSet.add(returnBtn, BorderLayout.WEST);
		pnBtnSet.add(deleteBtn, BorderLayout.EAST);
		pnFooter.add(pnBtnSet, BorderLayout.EAST);
		
		//header
		pnSch.add(pnPadding, BorderLayout.CENTER);
		pnSch.add(schFd, BorderLayout.WEST);
		pnSch.add(schBtn, BorderLayout.EAST);
		
		pnPvs.add(pvsBtn, BorderLayout.WEST);
		pnTitle.add(title, BorderLayout.WEST);
		
		pnHeader.add(pnTitle, BorderLayout.NORTH);
		pnHeader.add(pnSch, BorderLayout.EAST);
		pnHeader.add(pnPvs, BorderLayout.WEST);

		//center
		pnTbl.add(sclpn, BorderLayout.CENTER);
		
		pnBody.add(pnFooter, BorderLayout.SOUTH);
		pnBody.add(pnTbl, BorderLayout.CENTER);
		pnBody.add(pnHeader, BorderLayout.NORTH);
		
		this.add(pnBody, BorderLayout.CENTER);
		
		
		
		
		schBtn.addActionListener(this);        /*검색 이벤트*/
		pvsBtn.addActionListener(this);        /*대출 기록 전체보기*/ 
		returnBtn.addActionListener(this);     /*도서 반납하기*/
		deleteBtn.addActionListener(this);     /*대출 기록 삭제 Event*/ 
	}
	
	
	/**
	 * 대출 기록 테이블을 출력하는 메서드.
	 * */
	private void initTable() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		String topHeader[] = {"도서명", "대출일", "반납예정일", "반납일", "연체일"};	
		int col[] = {100 ,100 ,100 ,100 ,100};
		

		
		CommonConstants.bwModel = new BeanTableModel<BorrowVO>(topHeader, col) {
			
			// 객체의 컬럼 별 데이터를 한꺼번에 테이블에 뿌려줍니다.
			@Override
			public Object getValueByColumIndex(int row, int col) {
				BorrowVO bv = getRowAt(row);
				switch (col) {
				case 0:
					return bv.getBorrowNo();
				case 1:
					return bv.getBookNm();
				case 2:
					return sdf.format(bv.getStartDate());
				case 3:
					return sdf.format(bv.getEndDate());
				case 4:
					if (bv.getReturnDate()!=null) {
						return sdf.format(bv.getReturnDate());
					} else {
						return "-";
					}
				case 5:
					return bv.getOverdue() + "(일)";
				
				}
				return null;
			}

			
			@Override
			public void setValueByColumIndex(int row, int col, Object obj) {
				
			}
		};
		
		this.borrowTbl.setModel(CommonConstants.bwModel);

		borrowTbl.addMouseListener(this);
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(pvsBtn)) {
			CommonConstants.repaintBorrowTable();
		} else if (e.getSource().equals(schBtn)) {
			searchBorrow();
		} else if (e.getSource().equals(returnBtn)) {
			returnBook();
		} else if (e.getSource().equals(deleteBtn)) {
			getDeleteMessege();
		}
		
	}
	


	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		if (arg0.getSource().equals(borrowTbl)) {
			BorrowVO seletedVO = AddBorrowList.borrowList.get(borrowTbl.getSelectedRow());
			System.out.println("선택한 도서기록은?? -----> " + seletedVO);
			
			if (seletedVO.getIsBorrowed() == 0) {
				System.err.println("seletedVO.getIsBorrowed()? ----> " + seletedVO.getIsBorrowed());
				returnBtn.setEnabled(false);
			} else if (seletedVO.getIsBorrowed() == 1) {
				returnBtn.setEnabled(true);
			}
			
			if (seletedVO.getReturnDate() == null) {
				deleteBtn.setEnabled(false);
				System.err.println("반납일이 null임. seletedVO.getReturnDate()? ----> " + seletedVO.getReturnDate());
			} else if (seletedVO.getReturnDate() != null){
				deleteBtn.setEnabled(true);
				System.err.println("반납일이 null이 아님!! seletedVO.getReturnDate()? ----> " + seletedVO.getReturnDate());
			}
		}
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
	/**
	 * 대출 기록 삭제 시, 확인 메세지를 띄워주는 메서드.
	 */
	private void getDeleteMessege() {
		BorrowVO seletedVO = AddBorrowList.borrowList.get(borrowTbl.getSelectedRow());

		int rslt = JOptionPane.showConfirmDialog(null, " '" + seletedVO.getBookNm()+ "' " + " 대출 기록을 삭제 하시겠습니까?", seletedVO.getBookNm(), JOptionPane.YES_NO_OPTION);
		if (rslt == JOptionPane.YES_OPTION) { // '예' 선택
			deleteBorrowVO(seletedVO);
			JOptionPane.showMessageDialog(null,"대출 기록이 삭제 되었습니다.");
		} else { 
			System.out.println(seletedVO.getBookNm() + " 대출 기록 삭제를 취소합니다.");
			JOptionPane.showMessageDialog(null, "삭제가 취소 되었습니다.", seletedVO.getBookNm(), JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * 대출한 도서를 반납하는 메서드. 
	 * 반납일, 연체일을 update 한다.
	 */
	private void returnBook() {
		int row = borrowTbl.getSelectedRow();
		BorrowVO seletedVO = CommonConstants.bwModel.getData(row);
		
		System.err.println(seletedVO.toString());
			
		int result = JOptionPane.showConfirmDialog(null, seletedVO.getBookNm()+" 을(를) 반납 하시겠습니까??"
				, seletedVO.getBookNm(),JOptionPane.YES_NO_OPTION);
		
		if (result==JOptionPane.YES_OPTION) { 		 

			long gap = new Date().getTime() - seletedVO.getEndDate().getTime();
			int overDay = (int) (gap / (1000*60*60*24));
			
			// 대출상태, 반납일 update
			seletedVO.setIsBorrowed(0);				
			seletedVO.setReturnDate(new Date());	
			if (overDay < 0) {
				seletedVO.setOverdue(0);
			} else {
				seletedVO.setOverdue(overDay);
			}

			
			// 북리스트에서 해당 도서 상태 set 1. (대출가능)
			int idx = seletedVO.getBookNo()-1; 
			AddBookList.bookList.get(idx).setIsBorrowed(0);
			
			CommonConstants.bkModel.fireTableDataChanged();  // 바뀐 정보 테이블에 반영 후,
			CommonConstants.repaintBorrowTable();			 // 양쪽 테이블 다시 그림.
			CommonConstants.repaintBookTable();
			
		} else {										
			System.out.println("반납을 취소합니다.");
		}
	}
	
	
	/**
	 * 대출 기록을 검색하는 메서드
	 */
	private void searchBorrow() {
		
		if (!schFd.getText().isEmpty() && !schFd.getText().equals("")) {
			CommonConstants.bwModel.removeAll();
			for (BorrowVO bv : AddBorrowList.borrowList) {
				if (bv.getBookNm().contains(schFd.getText())) {
					CommonConstants.bwModel.addData(bv);
					System.err.println("여기는 searchBorrow. for문 안. 검색결과 bv는? --->" + bv);
				}
			}
			this.borrowTbl.setModel(CommonConstants.bwModel);

		} else {
			JOptionPane.showMessageDialog(pnBody, "검색어를 입력해주세요.");
		}
	}
	
	/**
	 * 대출 기록을 삭제하는 메서드
	 */
	private void deleteBorrowVO(BorrowVO vo) {
		CommonConstants.bwModel.remove(vo);
		AddBorrowList.borrowList.remove(vo);
		
		CommonConstants.repaintBorrowTable();
			
	}

}
