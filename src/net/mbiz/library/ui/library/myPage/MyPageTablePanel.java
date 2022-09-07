package net.mbiz.library.ui.library.myPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import net.mbiz.library.data.memory.AddBookList;
import net.mbiz.library.data.memory.AddBorrowList;
import net.mbiz.library.main.LibraryMain;
import net.mbiz.library.ui.common.CommonConstants;

public class MyPageTablePanel extends JPanel implements ActionListener, MouseListener{
	
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
	private List<BorrowVO> checkedList = new ArrayList<>(); 
	
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
		//CENTER
		this.pnTbl = new JPanel(); 
		pnTbl.setLayout(new BorderLayout());
		pnTbl.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		this.borrowTbl  = new JTable(CommonConstants.bwModel); // 데이터가 들어가는 테이블 
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
		cbbSearch.setModel(new DefaultComboBoxModel<>(new String[] {"도서명","저자"}));
		cbbSearch.setFont(CommonConstants.FONT_BASE_15);
		cbbSearch.setPreferredSize(new Dimension(100,50));

		pnCbb.add(cbbSearch);
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
	}
	
	
	/**
	 * 대출 기록 테이블을 출력하는 메서드.
	 * */
	private void initTable() {
		String topHeader[] = {"check", "no", "도서명", "저자", "대출일", "반납예정일", "반납일", "연체일"};	
		int[] col = {60, 60, 600, 296, 200, 180, 180, 180 };
		
		CommonConstants.bwModel = new BeanTableModel<BorrowVO>(topHeader, col) {
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
					}
					else {
						vo.setSelect(true);
					}
					
					checkedList.add(vo);
				}
				else {
					BorrowVO vo = getRowAt(row);
					vo.setSelect(false);
				}
			}
		};
		CommonConstants.setTableModelColumnWithCommonTableRenderer(borrowTbl, CommonConstants.bwModel);
		
		
		
		//EVENT
		borrowTbl.addMouseListener(this);
	
	}
	
	
	
	//---------------------------ActionListener, MouseListener------------------------------------	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(pvsBtn)) {
			CommonConstants.repaintBorrowTable();
		} else if (e.getSource().equals(schBtn)) {
			getSearchBorrowList();
		} else if (e.getSource().equals(returnBtn)) {
			getReturnMessege();
		} else if (e.getSource().equals(deleteBtn)) {
			getDeleteMessege();
		}
		
	}
	


	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		// 테이블 row 선택 시 해당 도서의 상태에 따라, 반납/삭제 버튼 -> 활성화/비활성화
		if (arg0.getSource().equals(borrowTbl)) {
			
			BorrowVO seletedVO = AddBorrowList.borrowList.get(borrowTbl.getSelectedRow());
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
		
		if (checkedList.size() > 0) {
			int rslt = JOptionPane.showConfirmDialog(null,  checkedList.size() + "개의" + " 대출 기록을 삭제 하시겠습니까?", "대출 기록을 삭제합니다.", JOptionPane.YES_NO_OPTION);
			if (rslt == JOptionPane.YES_OPTION) { // '예' 선택
				deleteCheckedList(checkedList);
				JOptionPane.showMessageDialog(null,"삭제 완료 되었습니다.");
			} else { 
				JOptionPane.showMessageDialog(null, "삭제 취소 되었습니다.");
			}	
			
		} else  {
			if (borrowTbl.getSelectedRow()> -1) { // row가 선택되어 있을 때
				BorrowVO seletedVO = AddBorrowList.borrowList.get(borrowTbl.getSelectedRow());
				
				int rslt = JOptionPane.showConfirmDialog(null, " '" + seletedVO.getBookNm()+ "' " + " 대출 기록을 삭제 하시겠습니까?", seletedVO.getBookNm(), JOptionPane.YES_NO_OPTION);
				if (rslt == JOptionPane.YES_OPTION) { // '예' 선택
					deleteBorrowVO(seletedVO);
					JOptionPane.showMessageDialog(null,"대출 기록이 삭제 되었습니다.");
				} else { 
					System.out.println(seletedVO.getBookNm() + " 대출 기록 삭제를 취소합니다.");
					JOptionPane.showMessageDialog(null, "삭제가 취소 되었습니다.", seletedVO.getBookNm(), JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "삭제할 기록을 선택하세요.","선택된 행 없음", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		

	}

	/**
	 * 대출한 도서를 반납하는 메서드. 
	 * 반납일, 연체일을 update 한다.
	 */
	private void returnBook() {
		int row = borrowTbl.getSelectedRow();
		BorrowVO seletedVO = CommonConstants.bwModel.getData(row);
			
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
	
	
	private void getReturnMessege() {
		if (checkedList.size()> 0) {
			
			int rslt = JOptionPane.showConfirmDialog(null, "선택한 도서를 반납 하시겠습니까?", "도서 반납", JOptionPane.YES_NO_OPTION);
			if (rslt == JOptionPane.YES_OPTION) {
				if (deleteCheckedList(checkedList) == 1) {
					JOptionPane.showMessageDialog(null, "반납 되었습니다.");
				} else {
					JOptionPane.showMessageDialog(null, "반납에 실패하였습니다. 다시 시도해 주세요.");
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "반납 취소 되었습니다.");
			}
			
		} else {
			
			if(borrowTbl.getSelectedRow()>-1) { 
				BorrowVO returnVO = CommonConstants.bwModel.getData(borrowTbl.getSelectedRow());
				int rslt = JOptionPane.showConfirmDialog(null, returnVO.getBookNm()+" 을(를) 반납 하시겠습니까??"
						, returnVO.getBookNm(),JOptionPane.YES_NO_OPTION);
				if (rslt == JOptionPane.YES_OPTION) { // '예' 선택
					deleteBorrowVO(returnVO);
					JOptionPane.showMessageDialog(null, returnVO.getBookNm()+" 이(가) 반납 되었습니다.");
					
				} else { 
					JOptionPane.showMessageDialog(null," '" + returnVO.getBookNm() + "' "+" 반납을 취소합니다.", returnVO.getBookNm(), JOptionPane.INFORMATION_MESSAGE);
				}
				
			}else {
				JOptionPane.showMessageDialog(null, "반납할 도서를 선택하세요.","선택된 행 없음", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}	
	}

	/**
	 * 대출 기록을 검색하는 메서드
	 */
	private void getSearchBorrowList() {
		
		if (!schFd.getText().isEmpty() && !schFd.getText().equals("")) {
			CommonConstants.bwModel.removeAll();
			
			for (BorrowVO bv : AddBorrowList.borrowList) {
				String cbb = (String) cbbSearch.getSelectedItem();
				
				if (cbb.equals("도사명")) {
					if (bv.getBookNm().contains(schFd.getText())) {
						CommonConstants.bwModel.addData(bv);
					}	
				} else if (cbb.equals("저자")) {
					if (bv.getBookWtr().contains(schFd.getText())) {
						CommonConstants.bwModel.addData(bv);
					}
				}
			}
			this.borrowTbl.setModel(CommonConstants.bwModel);

		} else {
			CommonConstants.repaintBorrowTable();
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
	
	
	/**
	 *  체크박스로 선택된 대출 데이터를 삭제하는 메서드.
	 * @param vo BookVO
	 */
	private int deleteCheckedList(List<BorrowVO> checkedList) {
		
		List<BorrowVO> delList = new ArrayList<>();
		
		int cnt = 0;
		// 대출중이지 않은 것만 거르기.
		for (BorrowVO vo : checkedList) {
			if (vo.getIsBorrowed() == 1) {
				cnt++;
				break;
			} else if(vo.getIsBorrowed() == 0) {
				delList.add(vo);
			}
		}
		
		if (cnt == 0) {
			for (BorrowVO vo : delList) {
				deleteBorrowVO(vo);
			}	
		} else {
			return 0;	
		}
		
		CommonConstants.repaintBookTable();
		borrowTbl.removeAll();
		borrowTbl.setModel(CommonConstants.bwModel);
	
		return 1;
	}

}

