package net.mbiz.library.ui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

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
import net.mbiz.library.data.BookVO;
import net.mbiz.library.ui.common.CommonConstants;
import net.mbiz.library.ui.main.LibraryMain;
import net.mbiz.library.ui.mypage.MyPagePanel;

public class BookListTablePanel extends JPanel {

	private JPanel pnBody;

	private JPanel pnHeader;
	private JPanel pnTbl;
	private JPanel pnFooter;
	private JPanel pnSch;
	private JPanel pnPadding;
	private JPanel pnTitle;
	private JPanel pnPvs;

	private JTable bookTbl;

	private JLabel title;
	private JLabel pvsLbl;

	private JTextField schFd;
	private JButton schBtn;
	private JButton pvsBtn;

	public BookListTablePanel(MainPanel pn) {
		jbInit();
		initTable();	// 테이블 생성
		initialize();   // 데이터 초기화
	}

	private void initialize() {
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
		pnHeader.add(Box.createHorizontalStrut(920), BorderLayout.WEST);
		pnHeader.add(Box.createVerticalStrut(15), BorderLayout.SOUTH);

		// CENTER - 테이블.
		this.pnTbl = new JPanel();
		pnTbl.setLayout(new BorderLayout());
		pnTbl.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		this.bookTbl = new JTable(CommonConstants.bkModel); // 데이터가 들어가는 테이블
		bookTbl.setRowHeight(32);
		bookTbl.setFont(CommonConstants.FONT_BASE_17);

		// 테이블 스크롤
		JScrollPane sclpn = new JScrollPane(bookTbl);
		sclpn.setCorner(JScrollPane.UPPER_LEFT_CORNER, bookTbl.getTableHeader());

		pnTbl.add(sclpn, BorderLayout.CENTER);
		pnBody.add(pnTbl, BorderLayout.CENTER);


		// SOUTH
		this.pnFooter = new JPanel();
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
		this.pnSch = new JPanel();
		pnSch.setLayout(new BorderLayout());
		pnSch.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		pnSch.setBorder(BorderFactory.createEmptyBorder(33, 0, 0, 0));

		// WEST
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

		pnSch.add(pnPadding, BorderLayout.CENTER);
		pnSch.add(schFd, BorderLayout.WEST);
		pnSch.add(schBtn, BorderLayout.EAST);

		pnPvs.add(pvsBtn, BorderLayout.WEST);
		pnTitle.add(title, BorderLayout.WEST);

		pnHeader.add(pnTitle, BorderLayout.NORTH);
		pnHeader.add(pnSch, BorderLayout.EAST);
		pnHeader.add(pnPvs, BorderLayout.WEST);

		pnBody.add(pnFooter, BorderLayout.SOUTH);
		pnBody.add(pnHeader, BorderLayout.NORTH);

		this.add(pnBody, BorderLayout.CENTER);

		// 검색 이벤트
		schBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.err.println("검색버튼 킁ㄹㄹ릭!!!");
				searchBookList();
			}
		});

		// 전체보기
		pvsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 테이블 리셋
				CommonConstants.bkModel.addDataList((ArrayList) AddBookList.bookList); 
				CommonConstants.bkModel.fireTableDataChanged();
			}
		});
		
		bookTbl.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			// 도서 상세정보 창
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					int idx = bookTbl.getSelectedRow();
					System.out.println("BookListTablePanel.mouseClicked 북테이블 클릭!! 선택된 행은? --> " + idx);
					System.out.println("BookListTablePanel.mouseClicked 선택된 VO객체 --> " + AddBookList.bookList.get(idx));
					
					// 도서 상세청보 창 생성
					BookDetailFrame detailFrame = new BookDetailFrame(AddBookList.bookList.get(idx));
					detailFrame.setLocationCenter();
					CommonConstants.bkModel.fireTableDataChanged();
					bookTbl.repaint();
					
					//마이페이지 테이블 repaint()
					CommonConstants.repaintBorrowTable();
					System.err.println("여기는 도서 상세 창 닫음?");
				}
			}
		});

	}
	
	/**
	 * 테이블 생성 메서드
	 */
	private void initTable() {
		/* 테이블을 위한 setting */
		String topHeader[] = { "도서명", "저자", "출판사", "출간일", "카테고리", "대출상태" };	// 헤더 setting
		int[] col = { 100, 100, 100, 100, 100, 100 };								// 열 넓이
		
		CommonConstants.bkModel = new BeanTableModel<BookVO>(topHeader, col) {
			// 객체의 컬럼 별 데이터를 한꺼번에 테이블에 뿌려준다.
			@Override 
			public Object getValueByColumIndex(int row, int col) {
				BookVO vo = getRowAt(row);
				
				switch (col) {
				case 0:
					return vo.getBookNm();
				case 1:
					return vo.getBookWtr();
				case 2:
					return vo.getPublisher();
				case 3:
					return vo.getReleaseDate();
				case 4:
					return vo.getCategory();
				case 5:
					if (vo.getIsBorrowed() == 1) {
						return "대출가능";
					} else if(vo.getIsBorrowed() == 0) {
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
		CommonConstants.bkModel.setNumbering(true); // 넘버링 여부 O
		
		this.bookTbl.setModel(CommonConstants.bkModel);
	}
	
	/**
	 * 도서 검색 메서드
	 */
	private void searchBookList() {
		
		if (!schFd.getText().isEmpty() && !schFd.getText().equals("")) {
			CommonConstants.bkModel.removeAll();
			for (BookVO bv : AddBookList.bookList) {
				if (bv.getBookNm().contains(schFd.getText())) {
					CommonConstants.bkModel.addData(bv);
				}
			}
			this.bookTbl.setModel(CommonConstants.bkModel);
			
		} else {
			JOptionPane.showMessageDialog(pnBody, "검색어를 입력해주세요");
		}
	}

	

}
