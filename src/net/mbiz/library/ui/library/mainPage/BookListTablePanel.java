package net.mbiz.library.ui.library.mainPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.event.EventListenerList;

import net.mbiz.edt.barcode.ag.ui.common.table.BeanTableModel;
import net.mbiz.library.data.BookVO;
import net.mbiz.library.data.BorrowVO;
import net.mbiz.library.listener.BookEventListener;
import net.mbiz.library.manager.HandlerManager;
import net.mbiz.library.ui.common.CommonConstants;
import net.mbiz.library.ui.library.book.dialog.BookRegistDialog;
import net.mbiz.library.ui.library.book.dialog.BookUpdateDialog;

public class BookListTablePanel extends JPanel implements ActionListener, MouseListener, BookEventListener{

	private JPanel pnBody;
	//body
	private JPanel pnHeader;
	private JPanel pnTbl;
	private JPanel pnFooter;

	// pnHeader
	private JPanel pnTitle;
	private JPanel pnWest;
	private JPanel pnEast;
	
	private JPanel pnCbb;
	
	private JPanel pnEastBtn;
	private JPanel pnWestBtn;
	
	private JTable bookTbl;

	private JLabel title;

	private JButton schBtn;
	private JButton pvsBtn;
	private JButton registBtn;
	private JButton deleteBtn;
	
	private JTextField schFd;

	private JComboBox<String> cbbSearch;
	private List<BookVO> checkedList = new ArrayList<>(); 
	
	private BeanTableModel<BookVO> bkModel;
	private HandlerManager manager = HandlerManager.getInstance();

	
	public BookListTablePanel(MainPanel pn) {
		jbInit();
		initTable();	// 테이블 생성
		initialize();   // 데이터 초기화
	}

	private void initialize() {
		this.bkModel.addDataList((ArrayList) manager.selectBookList()); // 리스트로 한꺼번에 집어넣기 가능
		this.bkModel.fireTableDataChanged();	// 테이블에 변경된 데이터 반영
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
		title.setBorder(BorderFactory.createEmptyBorder(30, 3, 30, 30));
		
		// EAST
		this.pnEast = new JPanel();
		pnEast.setLayout(new BorderLayout());
		pnEast.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		pnEast.setBorder(BorderFactory.createEmptyBorder(33, 0, 0, 0));
		
		
		/* pnEast - pnCbb(WEST), schFd(CENTER), schBtn(EAST) */
		this.schFd = new JTextField();
		schFd.setPreferredSize(new Dimension(550, 30));
		schFd.setFont(CommonConstants.FONT_BASE_17);
		schFd.add(Box.createHorizontalStrut(10),BorderLayout.EAST);
		
		this.schBtn = new JButton("검색");
		schBtn.setPreferredSize(new Dimension(70, 30));
		schBtn.setFont(CommonConstants.FONT_BASE_12);

		this.pnCbb = new JPanel();
		pnCbb.setLayout(new BorderLayout());
		pnCbb.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		pnCbb.add(Box.createVerticalStrut(1),BorderLayout.SOUTH);
		pnCbb.add(Box.createHorizontalStrut(10),BorderLayout.EAST);
		this.cbbSearch = new JComboBox<>();
		cbbSearch.setModel(new DefaultComboBoxModel<>(new String[] {"전체","도서명","저자","출판사"}));
		cbbSearch.setFont(CommonConstants.FONT_BASE_15);
		cbbSearch.setPreferredSize(new Dimension(100,60));
		
		pnCbb.add(cbbSearch);
		
	
		// WEST
		this.pnWest = new JPanel();
		pnWest.setLayout(new BorderLayout());
		pnWest.setBorder(BorderFactory.createEmptyBorder(33, 0, 0, 0));
		pnWest.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
	
		this.pvsBtn = new JButton("전체보기");
		pvsBtn.setPreferredSize(new Dimension(100, 30));
		pvsBtn.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		pvsBtn.setForeground(CommonConstants.COLOR_MENU_FONT2);


		
		
		

	
		
		
		
		
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
		manager.addBookEventListener(this);
	}
	
	
	

	/**
	 * 테이블 생성 메서드
	 */
	private void initTable() {
		/* 테이블을 위한 setting */
		
		String topHeader[] = {"check", "No","도서명", "저자", "출판사", "출간일", "카테고리", "대출상태" };	// 헤더 setting
		int[] col = {67, 67, 600, 296, 200, 180, 180, 180 };								// 열 넓이
		
		this.bkModel = new BeanTableModel<BookVO>(topHeader, col) {
			// 객체의 컬럼 별 데이터를 한꺼번에 테이블에 뿌려준다.
			@Override 
			public Object getValueByColumIndex(int row, int col) {
				BookVO vo = getRowAt(row);
				return vo;
			}
			
			@Override
			public void setValueByColumIndex(int row, int col, Object obj) { 
				// 체크박스 선택 시 --> row, 0, true(선택 여부)
				
				
				
				if ((Boolean)obj == true) {
					System.out.println("선택한 열은?? ===> " + row);
					BookVO vo = getRowAt(row);
					
					if (vo.isSelect()) {
						vo.setSelect(false);
					}
					else {
						vo.setSelect(true);
					}
					
					checkedList.add(vo);
				}
				else {
					BookVO vo = getRowAt(row);
					vo.setSelect(false);
				}
				
			}
	
		};
		CommonConstants.setTableModelColumnWithCommonTableRenderer(this.bookTbl, this.bkModel, "book");
	}
	
	
	
	//ActionListener
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(schBtn)) {
			getSearchBookList();
		} else if(e.getSource().equals(pvsBtn)) { // 전체보기 : 테이블 리셋.
			repaintBookTable();
		} else if(e.getSource().equals(registBtn)) {
			getRegistBookDialog();
		} else if(e.getSource().equals(deleteBtn)) {
			getDeleteMessege();
		}
		
	}
	
	
	//MouseListener
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
	

	//BookEventListener
	@Override
	public void bookAdded(BookVO vo) {
		System.out.println("BookList : book Added~~");
		repaintBookTable();
	}

	@Override
	public void bookUpdated(BookVO vo) {
		repaintBookTable();
	}

	@Override
	public void bookDeleted(String isbn) {
		this.bkModel.fireTableDataChanged();
	}

	@Override
	public void borrowAdded(BorrowVO vo) {
		System.out.println("BookList : borrow Added!");
		repaintBookTable();
	}

	@Override
	public void borrowUpdated(BorrowVO vo) {
		System.out.println("BookList : borrow updated!");
		repaintBookTable();
	}

	@Override
	public void borrowDeleted(int bwNo) {
	}

	
	
	/**
	 * 도서 검색 메서드
	 */
	private void getSearchBookList() {

		if (!schFd.getText().isEmpty() && !schFd.getText().equals("")) {
			
			String cbb = (String) cbbSearch.getSelectedItem();
			if (cbb.equals("") || cbb.isEmpty() ) {
				cbb = "전체" ;
			}
			
			if	(cbb.equals("전체")) cbb = "all";
			else if(cbb.equals("도서명")) cbb = "bookNm";
			else if(cbb.equals("저자")) cbb = "bookWtr";
			else if(cbb.equals("출판사")) cbb = "publisher";
			
			BookVO vo = new BookVO();
			vo.setOption(cbb);
			vo.setQuery(schFd.getText().toString());
			
			this.bkModel.removeAll();
			this.bkModel.addDataList((ArrayList) manager.searchBookList(vo));;	
			this.bookTbl.setModel(this.bkModel);
			
		} else {
			// 검색어 없을 시, 새로고침.
			repaintBookTable();
		}
	}
	
	
	/**
	 * 도서 정보를 등록하는 Dialog를 띄우고 테이블을 새로고침하는 메서드.
	 */
	private void getRegistBookDialog() {
		BookRegistDialog registDialog = new BookRegistDialog();
		registDialog.setLocationCenter();
		
		// Dialog 종료 후 repaint
		
	}
	
	


	
	/**
	 * 체크박스로 선택된 도서 데이터를 삭제하는 메서드.
	 * @param vo BookVO
	 * @return 삭제 성공 시 = 1, 실패 시 = 0
	 */
	private int deleteCheckedList(List<BookVO> checkedList) {
		
		List<BookVO> delList = new ArrayList<>();
		
		// cnt = 대출중인 도서의 갯수
		int cnt = 0;
		
		for (BookVO vo : checkedList) {
			if (vo.getIsBorrowed() == 1) {
				cnt++;
				break;
			} else if(vo.getIsBorrowed() == 0) {
				delList.add(vo);
			}
		}
		
		if (cnt == 0) {
			for (BookVO vo : delList) {
				deleteBookVO(vo);
			}	
		} else {
			checkedList.clear();
			return 0;	
		}
		checkedList.clear();
		return 1;
	}
	
	
	
	/**
	 * 도서 데이터를 삭제하는 메서드.
	 * @param vo BookVO
	 * @return 삭제 성공 시 = 1, 실패 시 = 0 
	 */
	private void deleteBookVO(BookVO vo) {
		//대출 상태 체크 
		if (vo.getIsBorrowed() == 1) {
			JOptionPane.showMessageDialog(null, "대출 중인 도서가 있습니다. 삭제가 취소 되었습니다.");
		} else {
			
			try {
				manager.bookDeleted(vo.getBookIsbn());
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(deleteBtn, e);
			}
			manager.deletedBook(vo.getBookIsbn());
			JOptionPane.showMessageDialog(null, vo.getBookNm()+" 이(가) 삭제 되었습니다.");
		}
	}
	
	

	/**
	 * 도서 데이터 삭제를 체크하는 messege를 띄우는 메서드.
	 */
	private void getDeleteMessege() {
		//체크박스를 하나라도 체크한 경우
		if (checkedList.size()> 0) { 
			int rslt = JOptionPane.showConfirmDialog(null, "선택한 도서를 삭제 하시겠습니까?", "도서 정보를 삭제합니다.", JOptionPane.YES_NO_OPTION);
			if (rslt == JOptionPane.YES_OPTION) {
				if (deleteCheckedList(checkedList) == 1) {
					JOptionPane.showMessageDialog(null, "삭제 완료 되었습니다.");
				} else {
					
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "삭제 취소 되었습니다.");
			}
			
		} else {
			
			if(bookTbl.getSelectedRow()>-1) { 			//선택한 열이 있는 경우
				
				BookVO delVO = this.bkModel.getRowAt(bookTbl.getSelectedRow());
				int rslt = JOptionPane.showConfirmDialog(null, " '" + delVO.getBookNm()+ "' " +" 을(를) 삭제 하시겠습니까?", delVO.getBookNm(), JOptionPane.YES_NO_OPTION);
				
				if (rslt == JOptionPane.YES_OPTION) { 
					deleteBookVO(delVO);
				} else { 
					JOptionPane.showMessageDialog(null," '" + delVO.getBookNm() + "' "+" 삭제를 취소합니다.", delVO.getBookNm(), JOptionPane.INFORMATION_MESSAGE);
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "삭제할 도서를 선택하세요.","선택된 행 없음", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		
		
		
	}
	

	/**
	 * 도서 상세 정보 Dialog를 띄우는 메서드.
	 * Dialog가 종료되면 테이블을 repaint한다.
	 */
	private void getBookDetailDialog() {
		BookVO vo = this.bkModel.getRowAt(bookTbl.getSelectedRow());
		
		BookUpdateDialog uptDialog = new BookUpdateDialog();
		uptDialog.initializeBookOne(vo);
		uptDialog.setLocationCenter();
		
//		BookDetailDialog detail = new BookDetailDialog(vo);
//		detail.setLocationCenter();
//		manager.selectBorrowList();
		
	}
	
	/**
	 * 도서 테이블을 다시 그리는 메서드.
	 * model의 데이터를 모두 지운 후 데이터를 다시 초기화 한다.
	 */
	private void repaintBookTable() {
		this.bkModel.removeAll();
		initialize();
		
	}

}
