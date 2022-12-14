package net.mbiz.library.ui.library.myPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
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

import net.mbiz.edt.barcode.ag.ui.common.table.BeanTableModel;
import net.mbiz.library.data.BookVO;
import net.mbiz.library.data.BorrowVO;
import net.mbiz.library.data.ChildCategoryVO;
import net.mbiz.library.data.ParentCategoryVO;
import net.mbiz.library.listener.BookEventListener;
import net.mbiz.library.main.LibraryMain;
import net.mbiz.library.manager.HandlerManager;
import net.mbiz.library.ui.common.CommonConstants;

public class MyPageTablePanel extends JPanel implements ActionListener, MouseListener, BookEventListener{
	
	private JPanel pnBody;
	//body
	private JPanel pnTbl;
	private JPanel pnHeader;
	private JPanel pnFooter;
	//header
	private JPanel pnTitle;
	private JPanel pnEast;
	private JPanel pnWest;

	private JPanel pnCbb;
	
	private JPanel pnBtnSet;
	private JLabel title;

	private JButton schBtn;
	private JButton pvsBtn;
	private JButton returnBtn;
	private JButton deleteBtn;
	
	private JTextField schFd;
	
	private JTable borrowTbl;
	
	private JComboBox<String> cbbSearch;
	private JComboBox<ParentCategoryVO> cbbCategoryP;
	private JComboBox<ChildCategoryVO> cbbCategoryC;
	private List<BorrowVO> checkedList = new ArrayList<>(); 
	
	private BeanTableModel<BorrowVO> bwModel;
	private HandlerManager manager = HandlerManager.getInstance();
	
	public MyPageTablePanel(LibraryMain f) {
		jbInit();
		initTable();	// 테이블 생성 먼저.
		initialize();	// 데이터 초기화
	}

	private void initialize() {
		List<BorrowVO> list = manager.selectBorrowList();
		list.sort(Comparator.reverseOrder());
		this.bwModel.addDataList((ArrayList) list);
		this.bwModel.fireTableDataChanged();	// 테이블에 변경된 데이터 반영
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
		//CENTER
		this.pnTbl = new JPanel(); 
		pnTbl.setLayout(new BorderLayout());
		pnTbl.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		this.borrowTbl  = new JTable(this.bwModel); // 데이터가 들어가는 테이블 
		borrowTbl.setRowHeight(32);
		borrowTbl.setFont(CommonConstants.FONT_BASE_17);

		JScrollPane sclpn = new JScrollPane(borrowTbl);
		sclpn.setCorner(JScrollPane.UPPER_LEFT_CORNER, borrowTbl.getTableHeader());
		pnTbl.add(sclpn, BorderLayout.CENTER);
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
		title.setBorder(BorderFactory.createEmptyBorder(30, 3, 30, 30));
		
		pnTitle.add(title, BorderLayout.WEST);

		//EAST
		this.pnEast = new JPanel();
		pnEast.setLayout(new BorderLayout());
		pnEast.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		pnEast.setBorder(BorderFactory.createEmptyBorder(33, 0, 0, 0));
		
		// WEST
		this.pnWest = new JPanel();
		pnWest.setLayout(new BorderLayout());
		pnWest.setBorder(BorderFactory.createEmptyBorder(33, 0, 0, 0));
		pnWest.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
	
		this.pvsBtn = new JButton("전체보기");
		pvsBtn.setPreferredSize(new Dimension(100, 30));
		pvsBtn.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		pvsBtn.setForeground(CommonConstants.COLOR_MENU_FONT2);
	
		pnWest.add(pvsBtn, BorderLayout.WEST);
		
		
		
		/* pnEast - pnCbb(WEST), schFd(CENTER), schBtn(EAST) */
		this.schFd = new JTextField();
		schFd.setPreferredSize(new Dimension(550, 30));
		schFd.setFont(CommonConstants.FONT_BASE_17);
		
		this.schBtn = new JButton("검색");
		schBtn.setPreferredSize(new Dimension(70, 30));
		schBtn.setFont(CommonConstants.FONT_BASE_12);

		this.pnCbb = new JPanel();
		pnCbb.setLayout(new BorderLayout());
		pnCbb.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		pnCbb.add(Box.createVerticalStrut(1),BorderLayout.SOUTH);
		pnCbb.add(Box.createHorizontalStrut(10),BorderLayout.EAST);

		this.cbbSearch = new JComboBox<>();
		cbbSearch.setModel(new DefaultComboBoxModel<>(new String[] {"전체","도서명","저자"}));
		cbbSearch.setFont(CommonConstants.FONT_BASE_15);
		cbbSearch.setPreferredSize(new Dimension(100,50));
		this.cbbCategoryP = new JComboBox<>();
		cbbCategoryP.setModel(new DefaultComboBoxModel<>());
		cbbCategoryP.setFont(CommonConstants.FONT_BASE_15);
		cbbCategoryP.setPreferredSize(new Dimension(140,60));
		
		setParentValues();
		this.cbbCategoryC = new JComboBox<>();
		cbbCategoryC.setFont(CommonConstants.FONT_BASE_15);
		cbbCategoryC.setPreferredSize(new Dimension(140,60));

		pnCbb.add(cbbCategoryP, BorderLayout.WEST);
		pnCbb.add(cbbCategoryC, BorderLayout.CENTER);
		pnCbb.add(cbbSearch, BorderLayout.EAST);
		
		pnEast.add(schFd, BorderLayout.CENTER);
		pnEast.add(pnCbb, BorderLayout.WEST);
		pnEast.add(schBtn, BorderLayout.EAST);
		
		
		pnHeader.add(pnTitle, BorderLayout.NORTH);
		pnHeader.add(pnEast, BorderLayout.EAST);
		pnHeader.add(pnWest, BorderLayout.WEST);
		
		
		
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
		
		pnBtnSet.add(returnBtn, BorderLayout.WEST);
		pnBtnSet.add(deleteBtn, BorderLayout.EAST);
		pnFooter.add(pnBtnSet, BorderLayout.EAST);
		
		

		
		pnBody.add(pnFooter, BorderLayout.SOUTH);
		pnBody.add(pnTbl, BorderLayout.CENTER);
		pnBody.add(pnHeader, BorderLayout.NORTH);
		this.add(pnBody, BorderLayout.CENTER);
		
		
		
		
		schBtn.addActionListener(this);        /*검색 이벤트*/
		pvsBtn.addActionListener(this);        /*대출 기록 전체보기*/ 
		returnBtn.addActionListener(this);     /*도서 반납하기*/
		deleteBtn.addActionListener(this);     /*대출 기록 삭제 Event*/ 
		cbbCategoryP.addActionListener(this);
		manager.addBookEventListener(this);
	}
	
	
	/**
	 * 대출 기록 테이블을 출력하는 메서드.
	 * */
	private void initTable() {
		String topHeader[] = {"check", "no", "도서명", "저자", "카테고리", "대출일", "반납예정일", "반납일", "연체일"};	
		int[] col = {60, 60, 560, 276, 180, 170, 170, 170, 80 };
		
		this.bwModel = new BeanTableModel<BorrowVO>(topHeader, col) {
			@Override
			public Object getValueByColumIndex(int row, int col) {
				BorrowVO bv = getRowAt(row);
				return bv;
			}

			@Override
			public void setValueByColumIndex(int row, int col, Object obj) {
				// 체크박스 선택 시 --> row, 0, true(선택 여부)
				
				if ((Boolean)obj == true) {
					System.out.println("선택한 열은?? ===> " + row);
					BorrowVO vo = getRowAt(row);
					
					if (vo.isSelect()) {
						vo.setSelect(false);
						checkedList.remove(vo);
					} else {
						vo.setSelect(true);
						checkedList.add(vo);
					}
					
					
					
				}
				else {
					BorrowVO vo = getRowAt(row);
					checkedList.remove(vo);
					vo.setSelect(false);
				}
			}
		};
		CommonConstants.setTableModelColumnWithCommonTableRenderer(borrowTbl, this.bwModel, "borrow");
		
		borrowTbl.addMouseListener(this);
	}
	
	
	
	//ActionListener
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(pvsBtn)) {
			repaintBorrowTable();
		} else if (e.getSource().equals(schBtn)) {
			getSearchBorrowList();
			resetSearchField();
		} else if (e.getSource().equals(returnBtn)) {
			getReturnMessege();
		} else if (e.getSource().equals(deleteBtn)) {
			getDeleteMessege();
		} else if(e.getSource().equals(cbbCategoryP)) {
			initParentCbbIdx();
		} 
		
	}
	

	//MouseListener
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// 테이블 row 선택 시 해당 도서의 상태에 따라, 반납/삭제 버튼 -> 활성화/비활성화
		if (arg0.getSource().equals(borrowTbl)) {
			BorrowVO vo = this.bwModel.getRowAt(borrowTbl.getSelectedRow());
			changeButtonDisable(vo);
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	
	
	//BookEventListener
	@Override
	public void bookAdded(BookVO vo) {
	}
	@Override
	public void bookUpdated(BookVO vo) {
	}
	@Override
	public void bookDeleted(String isbn) {
	}
	@Override
	public void borrowAdded(BorrowVO vo) {
		System.out.println("mypage : borrow added!");
		repaintBorrowTable();
	}
	@Override
	public void borrowUpdated(BorrowVO vo) {
		System.out.println("mypage : borrow updated!");
		vo.setSelect(false);
		this.bwModel.fireTableDataChanged();	
	}
	@Override
	public void borrowDeleted(int bwNo) {
		System.out.println("mypage : borrow added!");
		repaintBorrowTable();
	}
	
	/**
	 * 대출 기록 삭제 시, 확인 메세지를 띄워주는 메서드.
	 */
	private void getDeleteMessege() {
		/*checkbox를 이용한 삭제일 경우*/
		if (checkedList.size() > 0) {
			int rslt = JOptionPane.showConfirmDialog(null,  checkedList.size() + "개의" + " 대출 기록을 삭제 하시겠습니까?", "대출 기록을 삭제합니다.", JOptionPane.YES_NO_OPTION);
			if (rslt == JOptionPane.YES_OPTION) { // '예' 선택
				deleteCheckedList(checkedList);
				JOptionPane.showMessageDialog(null,"삭제 완료 되었습니다.");
				checkedList.clear();
			} else { 
				JOptionPane.showMessageDialog(null, "삭제 취소 되었습니다.");
			}	
			
		} else  {
			/*row 선택하여 삭제할 경우*/
			if (borrowTbl.getSelectedRow()> -1) { 
				BorrowVO vo = this.bwModel.getRowAt(borrowTbl.getSelectedRow());
				
				int rslt = JOptionPane.showConfirmDialog(null, " '" + vo.getBookNm()+ "' " + " 대출 기록을 삭제 하시겠습니까?", vo.getBookNm(), JOptionPane.YES_NO_OPTION);
				if (rslt == JOptionPane.YES_OPTION) { // '예' 선택
					deleteBorrowVO(vo);
					JOptionPane.showMessageDialog(null,"대출 기록이 삭제 되었습니다.");
				} else { 
					JOptionPane.showMessageDialog(null, "삭제가 취소 되었습니다.", vo.getBookNm(), JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "삭제할 기록을 선택하세요.","선택된 행 없음", JOptionPane.INFORMATION_MESSAGE);
			}
		}

	}
	
	

	private void getReturnMessege() {
		/*checkbox를 이용한 반납일 경우*/
		if (checkedList.size()> 0) {
			
			int rslt = JOptionPane.showConfirmDialog(null, "선택한 도서를 반납 하시겠습니까?", "도서 반납", JOptionPane.YES_NO_OPTION);
			if (rslt == JOptionPane.YES_OPTION) {
				if (returnCheckedList(checkedList) == 1) {
					JOptionPane.showMessageDialog(null, "반납 되었습니다.");
				} else if(returnCheckedList(checkedList) == 2) {
					JOptionPane.showMessageDialog(null, "반납 완료된 도서가 있습니다. 해당 도서를 제외하고 선택해 주세요.");
				} else {
					JOptionPane.showMessageDialog(null, "반납에 실패하였습니다. 다시 시도해 주세요.");
					
				}
				checkedList.clear();
			} else {
				JOptionPane.showMessageDialog(null, "반납 취소 되었습니다.");
			}
			
		} else { /*row 선택하여 반납할 경우*/
			
			if(borrowTbl.getSelectedRow()> -1) {  //선택한 row가 없음.
				BorrowVO bwvo = this.bwModel.getData(borrowTbl.getSelectedRow());
				BookVO bkvo = manager.selectBookOne(bwvo.getBookIsbn());
				
				int rslt = JOptionPane.showConfirmDialog(null, bwvo.getBookNm()+" 을(를) 반납 하시겠습니까??", bwvo.getBookNm(),JOptionPane.YES_NO_OPTION);
				if (rslt == JOptionPane.YES_OPTION) { 
					returnBook(bwvo);
					JOptionPane.showMessageDialog(null, bwvo.getBookNm()+" 이(가) 반납 되었습니다.");
					
				} else { 
					JOptionPane.showMessageDialog(null," '" + bwvo.getBookNm() + "' "+" 반납을 취소합니다.", bwvo.getBookNm(), JOptionPane.INFORMATION_MESSAGE);
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "반납할 도서를 선택하세요.","선택된 행 없음", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}	
	}

	/**
	 * 대출 기록을 검색하는 메서드
	 */
	private void getSearchBorrowList() {
		
		String option = (String) cbbSearch.getSelectedItem();
		if (option.equals("") || option.isEmpty() ) {
			option = "전체" ;
		}
			
		if	(option.equals("전체")) option = "all";
		else if(option.equals("도서명")) option = "bookNm";
		else if(option.equals("저자")) option = "bookWtr";
		ChildCategoryVO childVO = (ChildCategoryVO) cbbCategoryC.getSelectedItem();
	
		BorrowVO vo = new BorrowVO();
		vo.setOption(option);
		vo.setCCtgPnt(cbbCategoryP.getSelectedIndex());
		if (childVO != null) {
			vo.setCCtgIdx(childVO.getCCtgIdx());
		} else {
			vo.setCCtgIdx(0);
		}
		vo.setQuery(schFd.getText().toString());
		
		this.bwModel.removeAll();
		this.bwModel.addDataList((ArrayList) manager.searchBorrowList(vo));
		this.borrowTbl.setModel(this.bwModel);
	}
	
	
	/** 반납, 삭제 버튼 비활성화 or 활성화 시키는 메서드
	 *  선택된 대출기록의 반납일 여부에 따라 버튼을 활성화/비활성화 시킨다.
	 */
	private void changeButtonDisable(BorrowVO vo) {
		if (checkedList.size() == 0) {
			/*선택한 열이 반납일이 없음 --> 반납 활성화*/
			if (vo.getReturnDate() == null) {
				returnBtn.setEnabled(true);
				deleteBtn.setEnabled(false);
			/*선택한 열이 반납일이 있음 --> 삭제 활성화*/
			} else if (vo.getReturnDate() != null){
				returnBtn.setEnabled(false);
				deleteBtn.setEnabled(true);

			}
		} else {
			int cnt = 0;
			/*체크박스 안 값 중 반납일이 null인 기록이 하나라도 있을 경우 count*/
			for (BorrowVO borrowVO : checkedList) {
				if(borrowVO.getReturnDate() == null) {
					cnt++;
				} 
			}
			
			if (cnt == 0) {
				returnBtn.setEnabled(false);
				deleteBtn.setEnabled(true);	
			} 
			if (cnt >= 1) {
				returnBtn.setEnabled(true);
				deleteBtn.setEnabled(false);
			}
			
		}
	}
	
	
	
	/**
	 * 대출 기록을 삭제하는 메서드
	 */
	private void deleteBorrowVO(BorrowVO vo) {
		if (vo.getReturnDate()==null) {
			System.out.println(vo.getBookNm() + " 은(는) 대출 중인 도서입니다.");
		} else {

			try {
				manager.borrowDeleted(vo.getBorrowNo());
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(deleteBtn, e);
			}
			manager.deletedBorrow(vo.getBorrowNo());
		}
		
	}
	
	
	/**
	 * 체크박스로 선택된 대출 데이터를 삭제하는 메서드.
	 * @param List<BorrowVO> checkedList
	 */
	private void deleteCheckedList(List<BorrowVO> checkedList) {
		for (BorrowVO borrowVO : checkedList) {
			deleteBorrowVO(borrowVO);
		}
	}
	
	

	/**
	 * 대출한 도서를 반납하는 메서드. 
	 * 반납일, 연체일 구하여 update 한다.
	 */
	private void returnBook(BorrowVO bwvo) {
		if (bwvo.getReturnDate()==null) {
			
			long gap = new Date().getTime() - bwvo.getEndDate().getTime();
		    int overDay = (int) (gap / (1000*60*60*24));
		    
		    // 대출상태, 반납일 update
		    bwvo.setReturnDate(new Date());	
		    if (overDay < 0) {
		    	bwvo.setOverdue(0);
		    } else {
		    	bwvo.setOverdue(overDay);
		    }		     
		   
			try {
				manager.borrowUpdated(bwvo);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(returnBtn, e);
			}
			manager.updatedBorrow(bwvo);
		} else {
			JOptionPane.showMessageDialog(null, "이미 반납된 도서입니다.");
		}
	}
	
	/**
	 * 체크박스로 선택된 대출 데이터를 반납하는 메서드.
	 * @param List<BorrowVO> checkedList
	 */
	private int returnCheckedList(List<BorrowVO> checkedList) {
		
		int cnt = 0;
		for (BorrowVO borrowVO : checkedList) {
			if (borrowVO.getReturnDate() != null) {
				cnt++;
			}
		}
			
		// 이미 반납된 기록 개수
		if (cnt == 0) {
			for (BorrowVO borrowVO : checkedList) {
				returnBook(borrowVO);
			}
			return 1;
		} else {
			return 2;
		}
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
	
	private void repaintBorrowTable() {
		this.bwModel.removeAll();
		initialize();
	}
	
	private void resetSearchField() {
		cbbCategoryP.removeAllItems();
		cbbCategoryC.removeAllItems();
		setParentValues();
		schFd.setText("");
	}
	


}

