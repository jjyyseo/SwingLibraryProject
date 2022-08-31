package net.mbiz.library.ui.mypage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
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
import net.mbiz.library.data.BorrowVO;
import net.mbiz.library.ui.common.CommonConstants;
import net.mbiz.library.ui.main.LibraryMain;

public class MyPagePanel extends JPanel{
	
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
	
	public static JTable borrowTbl;
	
	public MyPagePanel(LibraryMain f) {
		jbInit();
		initTable();	// 테이블 생성 먼저.
		initialize();	// 데이터 초기화
	}

	private void initialize() {
		
//		List<BorrowVO> myBwList = new ArrayList<>();
//		for (BorrowVO bv : AddBorrowList.borrowList) {
//			// 로그인한 아이디의 대출 기록을 생성. 
//			String logInID = "a001";
//			if (logInID.equals(bv.getUserId())) {
//				myBwList.add(bv);
//			}
//		}
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
//		pvsLbl.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
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
		schBtn.setPreferredSize(new Dimension(50, 30));

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
		
		
		
		
		
		/*검색 이벤트*/
		schBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchBorrow();
			}
		});
		
		/*대출 기록 전체보기*/
		pvsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CommonConstants.repaintBorrowTable();
			}
		});
		
		/*도서 반납하기*/
		returnBtn.addActionListener(new ActionListener() {
			SimpleDateFormat sdf = new SimpleDateFormat();

			public void actionPerformed(ActionEvent e) {
				
				BorrowVO seletedVO = AddBorrowList.borrowList.get(borrowTbl.getSelectedRow());
				
				int result = JOptionPane.showConfirmDialog(null, seletedVO.getBookNm()+" 을(를) 반납 하시겠습니까??"
						, seletedVO.getBookNm(),JOptionPane.YES_NO_OPTION);
				
				if (result==JOptionPane.YES_OPTION) { 		 

					long gap = new Date().getTime() - seletedVO.getEndDate().getTime();
					int overDay = (int) (gap / (1000*60*60*24));
					
					seletedVO.setIsBorrowed(1);				// 대출가능 상태로
					seletedVO.setReturnDate(new Date());	// 반납일 update.
					seletedVO.setOverdue(overDay);
					
					
					// 북리스트의 도서 정보 대출가능으로 change
					int idx = seletedVO.getBookNo()-1; 
					AddBookList.bookList.get(idx).setIsBorrowed(1);
					
					CommonConstants.bkModel.fireTableDataChanged(); //바뀐 정보 테이블에 반영

					CommonConstants.repaintBorrowTable();	
					CommonConstants.repaintBookTable();
					
				} else {										
					System.out.println("반납을 취소합니다.");
				}
			}
			
		});
		
		/*대출 기록 삭제하기*/
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BorrowVO seletedVO = AddBorrowList.borrowList.get(borrowTbl.getSelectedRow());
				System.err.println("삭제 버튼 클릭");
				
				CommonConstants.bwModel.remove(seletedVO);
				AddBorrowList.borrowList.remove(AddBorrowList.borrowList.get(borrowTbl.getSelectedRow()));
				
				CommonConstants.repaintBorrowTable();
			}
		});

	}
	
	/*테이블을 출력하는 메서드*/
	private void initTable() {
		
		String topHeader[] = {"도서명", "대출일", "반납예정일", "반납일", "연체일"};	
		int col[] = {100 ,100 ,100 ,100 ,100};
		
		CommonConstants.bwModel = new BeanTableModel<BorrowVO>(topHeader, col) {
			
			// 객체의 컬럼 별 데이터를 한꺼번에 테이블에 뿌려줍니다.
			@Override
			public Object getValueByColumIndex(int row, int col) {
				BorrowVO bv = getRowAt(row);
				switch (col) {
				case 0:
					return bv.getBookNm();
				case 1:
					return bv.getStartDate();
				case 2:
					return bv.getEndDate();
				case 3:
					return bv.getReturnDate();
				case 4:
					return bv.getOverdue() + "(일)";
				}
				return null;
			}

			@Override
			public void setValueByColumIndex(int row, int col, Object obj) {
				
			}
		};
		
		
		CommonConstants.bwModel.setNumbering(true);
		this.borrowTbl.setModel(CommonConstants.bwModel);
	}
	
	/**
	 * 대출 기록을 삭제하는 메서드
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
	


}

