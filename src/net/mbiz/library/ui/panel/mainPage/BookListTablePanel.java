package net.mbiz.library.ui.panel.mainPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.mbiz.edt.barcode.ag.ui.common.table.BeanTableModel;
import net.mbiz.library.data.AddBookList;
import net.mbiz.library.data.BookVO;
import net.mbiz.library.ui.common.CommonConstants;
import net.mbiz.library.ui.common.CommonTableRenderer;
import net.mbiz.library.ui.dialog.BookRegistDialog;
import net.mbiz.library.ui.dialog.BookUpdateDialog;

public class BookListTablePanel extends JPanel implements ActionListener, MouseListener{

	private JPanel pnBody;
	private JPanel pnHeader;
	private JPanel pnTbl;
	private JPanel pnFooter;
	private JPanel pnWest;
	private JPanel pnTitle;
	private JPanel pnEast;
	private JPanel pnEastBtn;
	private JPanel pnWestBtn;
	private JPanel pnCbb;
	private JTable bookTbl;

	private JLabel title;
	private JLabel pvsLbl;

	private JTextField schFd;
	private JButton schBtn;
	private JButton pvsBtn;
	private JButton registBtn;
	private JButton deleteBtn;
	
	private JComboBox<String> cbbSearch;
//	private DefaultComboBoxModel<String> schModel;

	public BookListTablePanel(MainPanel pn) {
		jbInit();
		initTable();	// 테이블 생성
		initialize();   // 데이터 초기화
	}

	private void initialize() {
		Collections.sort(AddBookList.bookList, Collections.reverseOrder());
		CommonConstants.bkModel.addDataList((ArrayList) AddBookList.bookList); // 리스트로 한꺼번에 집어넣기 가능
		CommonConstants.bkModel.fireTableDataChanged();	// 테이블에 변경된 데이터 반영
	}


	private void jbInit() {
		this.setLayout(new BorderLayout());
		this.setBackground(CommonConstants.COLOR_BASE_BACKGROUND);

		
		/* pnBody - pnHeander(NORTH), pnTbl(CENTER), pnFooter(SOUTH) */
		this.pnBody = new JPanel();
		pnBody.setLayout(new BorderLayout());
		pnBody.setBackground(Color.DARK_GRAY);

		// NORTH
		this.pnHeader = new JPanel();
		pnHeader.setLayout(new BorderLayout());
		pnHeader.setPreferredSize(new Dimension(0, 150));
		pnHeader.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		pnHeader.add(Box.createVerticalStrut(15), BorderLayout.SOUTH);

		// CENTER - 테이블.
		this.pnTbl = new JPanel();
		pnTbl.setLayout(new BorderLayout());
		pnTbl.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		
		this.bookTbl = new JTable(); // 데이터가 들어가는 테이블
		bookTbl.setRowHeight(32);
		bookTbl.setFont(CommonConstants.FONT_BASE_17);

		// 테이블 스크롤
		JScrollPane sclpn = new JScrollPane(bookTbl);
		pnTbl.add(sclpn, BorderLayout.CENTER);
		pnBody.add(pnTbl, BorderLayout.CENTER);

		// SOUTH
		this.pnFooter = new JPanel();
		pnFooter.setLayout(new BorderLayout());
		pnFooter.setPreferredSize(new Dimension(0, 50));
		pnFooter.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);

		
		
		
		
		
		
		/* pnHeander - pnTitle(NORTH), pnSch(EAST), pnPvs(WEST) */
		// NORTH
		this.pnTitle = new JPanel();
		pnTitle.setLayout(new BorderLayout());
		pnTitle.setPreferredSize(new Dimension(0, 55));
		pnTitle.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		this.title = new JLabel("전체 도서");
		title.setFont(CommonConstants.FONT_TITLE_25);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		
		// EAST
		this.pnEast = new JPanel();
		pnEast.setLayout(new BorderLayout());
		pnEast.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		pnEast.setBorder(BorderFactory.createEmptyBorder(33, 0, 0, 0));
		
		
		
		/* pnEast - schFd(WEST), pnPadding(CENTER), schBtn(EAST) */
		this.schFd = new JTextField();
		schFd.setPreferredSize(new Dimension(550, 30));
		schFd.setFont(CommonConstants.FONT_BASE_17);
		this.schBtn = new JButton("검색");
		schBtn.setPreferredSize(new Dimension(70, 30));
		schBtn.setFont(CommonConstants.FONT_BASE_12);
		
		this.cbbSearch = new JComboBox<>();
		cbbSearch.setModel(new DefaultComboBoxModel<>(new String[] {"도서명","저자","출판사","카테고리"}));
		cbbSearch.setFont(CommonConstants.FONT_BASE_15);
		cbbSearch.setPreferredSize(new Dimension(90,50));
		
		this.pnCbb = new JPanel();
		pnCbb.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		pnCbb.add(cbbSearch);
		
	
		// WEST
		this.pnWest = new JPanel();
		pnWest.setLayout(new BorderLayout());
		pnWest.setBorder(BorderFactory.createEmptyBorder(33, 0, 0, 0));
		pnWest.setBackground(Color.green);
	
		this.pvsBtn = new JButton();
		pvsBtn.setPreferredSize(new Dimension(200, 30));
		pvsBtn.setBorderPainted(false);
		pvsBtn.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		this.pvsLbl = new JLabel("전체보기");
		pvsLbl.setFont(CommonConstants.FONT_BASE_17);
		pvsLbl.setHorizontalAlignment(JLabel.CENTER);
		pvsBtn.add(pvsLbl);


		
		
		

	
		
		
		
		
		/* pnFooter - pnBtnSet(EAST) - returnBtn(WEST), deleteBtn(EAST) */
		this.pnEastBtn = new JPanel();
		pnEastBtn.setLayout(new BorderLayout());
		pnEastBtn.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		pnEastBtn.add(Box.createVerticalStrut(5), BorderLayout.NORTH);
		pnEastBtn.add(Box.createHorizontalStrut(10), BorderLayout.CENTER);
	
		this.pnWestBtn = new JPanel();
		pnWestBtn.setLayout(new BorderLayout());
		pnWestBtn.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		pnWestBtn.add(Box.createVerticalStrut(5), BorderLayout.NORTH);
		pnWestBtn.add(Box.createHorizontalStrut(10), BorderLayout.CENTER);
		
		
		this.registBtn = new JButton("추가");
		registBtn.setPreferredSize(new Dimension(70, 50));
		registBtn.setFont(CommonConstants.FONT_BASE_15);
		this.deleteBtn = new JButton("삭제");
		deleteBtn.setPreferredSize(new Dimension(70, 50));
		deleteBtn.setFont(CommonConstants.FONT_BASE_15);
		
		

		
		//footer
		pnEastBtn.add(registBtn, BorderLayout.WEST);
		pnEastBtn.add(deleteBtn, BorderLayout.EAST);
		
		pnFooter.add(pnEastBtn, BorderLayout.EAST);
		pnFooter.add(pnWestBtn, BorderLayout.WEST);
		
		//header
		pnEast.add(schFd, BorderLayout.CENTER);
		pnEast.add(pnCbb, BorderLayout.WEST);
		pnEast.add(schBtn, BorderLayout.EAST);

		pnWest.add(pvsBtn, BorderLayout.WEST);
		pnTitle.add(title, BorderLayout.WEST);

		pnHeader.add(pnTitle, BorderLayout.NORTH);
		pnHeader.add(pnEast, BorderLayout.EAST);
		pnHeader.add(pnWest, BorderLayout.WEST);
		
		//center
		pnBody.add(pnFooter, BorderLayout.SOUTH);
		pnBody.add(pnHeader, BorderLayout.NORTH);

		this.add(pnBody, BorderLayout.CENTER);
		
		
		
		schBtn.addActionListener(this);      /*도서 검색*/ 
		pvsBtn.addActionListener(this);      /*전체 보기*/  
		registBtn.addActionListener(this);   /*도서 등록하기*/  
		deleteBtn.addActionListener(this);   /*도서 삭제하기*/  
		
		bookTbl.addMouseListener(this);		 /*도서 상세정보 창 띄우기*/

	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(schBtn)) {
			getSearchBookList();
		} else if(e.getSource().equals(pvsBtn)) { // 전체보기 : 테이블 리셋.
			CommonConstants.repaintBookTable();
		} else if(e.getSource().equals(registBtn)) {
			getRedistBookDialog();
		} else if(e.getSource().equals(deleteBtn)) {
			getDeleteMessege();
		}
		
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if (e.getSource().equals(bookTbl)) {
			if(e.getClickCount()==2) {
				getBookDetailDialog();
			}
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	
	


	/**
	 * 테이블 생성 메서드
	 */
	private void initTable() {
		/* 테이블을 위한 setting */
		String topHeader[] = { "No","도서명", "저자", "출판사", "출간일", "카테고리", "대출상태" };	// 헤더 setting
		int[] col = {  80, 500, 150, 150, 150, 150, 150  };								// 열 넓이
		
		CommonConstants.bkModel = new BeanTableModel<BookVO>(topHeader, col) {
			// 객체의 컬럼 별 데이터를 한꺼번에 테이블에 뿌려준다.
			@Override 
			public Object getValueByColumIndex(int row, int col) {
				BookVO vo = getRowAt(row);
				
				switch (col) {
				case 0:
					return vo.getBookNo();
				case 1:
					return vo.getBookNm();
				case 2:
					return vo.getBookWtr();
				case 3:
					return vo.getPublisher();
				case 4:
					return vo.getReleaseDate();
				case 5:
					return vo.getCategory();
					
				case 6:
					if (vo.getIsBorrowed() == 0) {
						return "대출가능";
					} else if(vo.getIsBorrowed() == 1) {
						return "대출중";
					} else {
						return "알수없음";
					}
				}
				
				return null;
			}

			@Override
			public void setValueByColumIndex(int row, int col, Object obj) {
				// 체크박스 처럼 체크한 위치의 값을 받고 싶을 때 사용.
			}
	
		};
		
		
		
		CommonConstants.setTableModelColumnWithCommonTableRenderer(this.bookTbl, CommonConstants.bkModel);
	}
	
	
	/**
	 * 도서 검색 메서드
	 */
	private void getSearchBookList() {
		
		if (!schFd.getText().isEmpty() && !schFd.getText().equals("")) {
			CommonConstants.bkModel.removeAll();
			
			
			for (BookVO bv : AddBookList.bookList) {
				String cbb = (String) cbbSearch.getSelectedItem();
				
				if (cbb.equals("도서명")) {
					if (bv.getBookNm().contains(schFd.getText())) {
						CommonConstants.bkModel.addData(bv);				
					} 
				} else if(cbb.equals("저자")) {
					if (bv.getBookWtr().contains(schFd.getText())) {
						CommonConstants.bkModel.addData(bv);				
					} 
				} else if(cbb.equals("출판사")) {
					if (bv.getPublisher().contains(schFd.getText())) {
						CommonConstants.bkModel.addData(bv);				
					} 
				} else if(cbb.equals("카테고리")) {
					if (bv.getCategory().contains(schFd.getText())) {
						CommonConstants.bkModel.addData(bv);				
					} 
				} 
			}
			this.bookTbl.setModel(CommonConstants.bkModel);
			
		} else {
			JOptionPane.showMessageDialog(pnBody, "검색어를 입력해주세요");
		}
	}
	
	
	/**
	 * 도서 정보를 등록하는 Dialog를 띄우고 테이블을 새로고침하는 메서드.
	 */
	private void getRedistBookDialog() {
		BookRegistDialog registDialog = new BookRegistDialog();
		registDialog.setLocationCenter();
		
		// Dialog 종료 후 repaint
		CommonConstants.repaintBookTable();
		bookTbl.removeAll();
		bookTbl.setModel(CommonConstants.bkModel);
		
	}
	
	
	/**
	 * 도서 데이터를 삭제하는 메서드.
	 * @param vo BookVO
	 */
	private void deleteBookVO(BookVO vo) {
		CommonConstants.bkModel.remove(vo);
		AddBookList.bookList.remove(vo);
		
		CommonConstants.repaintBookTable();
		bookTbl.removeAll();
		bookTbl.setModel(CommonConstants.bkModel);
		
	}

	/**
	 * 도서 데이터 삭제를 체크하는 messege를 띄우는 메서드.
	 */
	private void getDeleteMessege() {
		if(bookTbl.getSelectedRow()>-1) {
			BookVO seletedVO = AddBookList.bookList.get(bookTbl.getSelectedRow());
			System.err.println("삭제 버튼 클릭." );

			int rslt = JOptionPane.showConfirmDialog(null, " '" + seletedVO.getBookNm()+ "' " +" 을(를) 삭제 하시겠습니까?", seletedVO.getBookNm(), JOptionPane.YES_NO_OPTION);
			if (rslt == JOptionPane.YES_OPTION) { // '예' 선택
				deleteBookVO(seletedVO);
				JOptionPane.showMessageDialog(null, seletedVO.getBookNm()+" 이(가) 삭제 되었습니다.");
				
			} else { 
				System.out.println(" '" + seletedVO.getBookNm() + " 도서 삭제를 취소합니다.");
				JOptionPane.showMessageDialog(null," '" + seletedVO.getBookNm() + "' "+" 삭제를 취소합니다.", seletedVO.getBookNm(), JOptionPane.INFORMATION_MESSAGE);
			}
			
		} else {
			JOptionPane.showMessageDialog(null,"삭제할 도서를 선택하세요.","선택된 도서 없음", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	/**
	 * 도서 상세 정보 Dialog를 띄우는 메서드.
	 * Dialog가 종료되면 테이블을 repaint한다.
	 */
	private void getBookDetailDialog() {
		int idx = bookTbl.getSelectedRow();
		BookVO seletedVO = AddBookList.bookList.get(bookTbl.getSelectedRow());
		System.out.println("선택한 도서기록은?? -----> " + seletedVO);

		BookUpdateDialog uptDialog = new BookUpdateDialog();
		uptDialog.initializeBookOne(seletedVO);
		uptDialog.setLocationCenter();
		
		CommonConstants.bkModel.fireTableDataChanged();
		bookTbl.repaint();
		
		//마이페이지 테이블 repaint()
		CommonConstants.repaintBorrowTable();
		
		
		
//		// 도서 상세청보 창 생성
//		BookDetailDialog.bkDatilVO = AddBookList.bookList.get(idx);
//		System.out.println("이번 bkDatilVO?? -----> " + BookDetailDialog.bkDatilVO);
//		BookDetailDialog detailDialog = new BookDetailDialog();
//		detailDialog.setLocationCenter();
	}


}
