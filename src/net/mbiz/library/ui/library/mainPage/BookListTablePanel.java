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

import org.apache.ibatis.session.RowBounds;

import net.mbiz.edt.barcode.ag.ui.common.table.BeanTableModel;
import net.mbiz.library.data.BookVO;
import net.mbiz.library.data.BorrowVO;
import net.mbiz.library.data.ChildCategoryVO;
import net.mbiz.library.data.ParentCategoryVO;
import net.mbiz.library.listener.BookEventListener;
import net.mbiz.library.manager.HandlerManager;
import net.mbiz.library.ui.common.CommonConstants;
import net.mbiz.library.ui.common.ParentCategoryCombobox;
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
	private JComboBox<ParentCategoryVO> cbbCategoryP;
	private JComboBox<ChildCategoryVO> cbbCategoryC;
	private List<BookVO> checkedList = new ArrayList<>(); 
	
	private BeanTableModel<BookVO> bkModel;
	private HandlerManager manager = HandlerManager.getInstance();

	
	public BookListTablePanel(MainPanel pn) {
		jbInit();
		initTable();	// ????????? ??????
		initialize();   // ????????? ?????????
	}

	private void initialize() {
		this.bkModel.addDataList((ArrayList) manager.selectBookList()); // ???????????? ???????????? ???????????? ??????
		this.bkModel.fireTableDataChanged();	// ???????????? ????????? ????????? ??????
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

		// CENTER - ?????????.
		this.pnTbl = new JPanel();
		pnTbl.setLayout(new BorderLayout());
		pnTbl.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		
		this.bookTbl = new JTable(); // ???????????? ???????????? ?????????
		bookTbl.setRowHeight(32);
		bookTbl.setFont(CommonConstants.FONT_BASE_17);

		// ????????? ?????????
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
		this.title = new JLabel("?????? ??????");
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
		
		this.schBtn = new JButton("??????");
		schBtn.setPreferredSize(new Dimension(70, 30));
		schBtn.setFont(CommonConstants.FONT_BASE_12);

		this.pnCbb = new JPanel();
		pnCbb.setLayout(new BorderLayout());
		pnCbb.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		pnCbb.add(Box.createVerticalStrut(1),BorderLayout.SOUTH);
		pnCbb.add(Box.createHorizontalStrut(10),BorderLayout.EAST);
		
		this.cbbSearch = new JComboBox<>();
		cbbSearch.setModel(new DefaultComboBoxModel<>(new String[] {"??????","?????????","??????","?????????"}));
		cbbSearch.setFont(CommonConstants.FONT_BASE_15);
		cbbSearch.setPreferredSize(new Dimension(100,60));
		
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
		
	
		// WEST
		this.pnWest = new JPanel();
		pnWest.setLayout(new BorderLayout());
		pnWest.setBorder(BorderFactory.createEmptyBorder(33, 0, 0, 0));
		pnWest.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
	
		this.pvsBtn = new JButton("????????????");
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
		
		
		this.registBtn = new JButton("??????");
		registBtn.setPreferredSize(new Dimension(70, 50));
		registBtn.setFont(CommonConstants.FONT_BASE_15);
		this.deleteBtn = new JButton("??????");
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

		pnTitle.add(title, BorderLayout.WEST);

		pnHeader.add(pnTitle, BorderLayout.NORTH);
		pnHeader.add(pnEast, BorderLayout.EAST);
		pnHeader.add(pnWest, BorderLayout.WEST);
		
		//center
		pnBody.add(pnFooter, BorderLayout.SOUTH);
		pnBody.add(pnHeader, BorderLayout.NORTH);

		this.add(pnBody, BorderLayout.CENTER);
		
		
		
		schBtn.addActionListener(this);      /*?????? ??????*/ 
		pvsBtn.addActionListener(this);      /*?????? ??????*/  
		registBtn.addActionListener(this);   /*?????? ????????????*/  
		deleteBtn.addActionListener(this);   /*?????? ????????????*/  
		cbbCategoryP.addActionListener(this);
		bookTbl.addMouseListener(this);		 /*?????? ???????????? ??? ?????????*/
		manager.addBookEventListener(this);
	}
	
	
	


	/**
	 * ????????? ?????? ?????????
	 */
	private void initTable() {
		/* ???????????? ?????? setting */
		
		String topHeader[] = {"check","?????????", "??????", "?????????", "?????????", "????????????", "????????????" };	// ?????? setting
		int[] col = {67, 600, 304, 200, 200, 200, 200 };								// ??? ??????
		
		this.bkModel = new BeanTableModel<BookVO>(topHeader, col) {
			// ????????? ?????? ??? ???????????? ???????????? ???????????? ????????????.
			@Override 
			public Object getValueByColumIndex(int row, int col) {
				int i = 1;
				
				BookVO vo = getRowAt(row);
				return vo;
			}
			
			@Override
			public void setValueByColumIndex(int row, int col, Object obj) { 
				// ???????????? ?????? ??? --> row, 0, true(?????? ??????)
				
				
				
				if ((Boolean)obj == true) {
					System.out.println("????????? ???????? ===> " + row);
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
			resetSearchField();
		} else if(e.getSource().equals(pvsBtn)) { // ???????????? : ????????? ??????.
			repaintBookTable();
		} else if(e.getSource().equals(registBtn)) {
			getRegistBookDialog();
		} else if(e.getSource().equals(deleteBtn)) {
			getDeleteMessege();
		} else if(e.getSource().equals(cbbCategoryP)) {
			initParentCbbIdx();
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
	 * ?????? ?????? ?????????
	 */
	private void getSearchBookList() {
		
		String option = (String) cbbSearch.getSelectedItem();
		if (option.equals("") || option.isEmpty() ) {
			option = "??????" ;
		}
			
		if	(option.equals("??????")) option = "all";
		else if(option.equals("?????????")) option = "bookNm";
		else if(option.equals("??????")) option = "bookWtr";
		else if(option.equals("?????????")) option = "publisher";
		ChildCategoryVO childVO = (ChildCategoryVO) cbbCategoryC.getSelectedItem();
		
		BookVO vo = new BookVO();
		vo.setOption(option);
		vo.setCCtgPnt(cbbCategoryP.getSelectedIndex());
		System.out.println(vo);
		System.out.println("childVO"+ childVO);
		
		if ( childVO != null) {
			vo.setCCtgIdx(childVO.getCCtgIdx());
		} else {
			vo.setCCtgIdx(0);
		}
		vo.setQuery(schFd.getText().toString());
			
		this.bkModel.removeAll();
		
		this.bkModel.addDataList((ArrayList) manager.searchBookList(vo));
		this.bookTbl.setModel(this.bkModel);

	}
	

	/**
	 * ?????? ????????? ???????????? Dialog??? ????????? ???????????? ?????????????????? ?????????.
	 */
	private void getRegistBookDialog() {
		BookRegistDialog registDialog = new BookRegistDialog();
		registDialog.setLocationCenter();
		
	}

	
	/**
	 * ??????????????? ????????? ?????? ???????????? ???????????? ?????????.
	 * @param vo BookVO
	 * @return ?????? ?????? ??? = 1, ?????? ??? = 0
	 */
	private int deleteCheckedList(List<BookVO> checkedList) {
		
		List<BookVO> delList = new ArrayList<>();
		
		// cnt = ???????????? ????????? ??????
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
	 * ?????? ???????????? ???????????? ?????????.
	 * @param vo BookVO
	 * @return ?????? ?????? ??? = 1, ?????? ??? = 0 
	 */
	private void deleteBookVO(BookVO vo) {
		//?????? ?????? ?????? 
		if (vo.getIsBorrowed() == 1) {
			JOptionPane.showMessageDialog(null, "?????? ?????? ????????? ????????????. ????????? ?????? ???????????????.");
		} else {
			
			try {
				manager.bookDeleted(vo.getBookIsbn());
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(deleteBtn, e);
			}
			manager.deletedBook(vo.getBookIsbn());
			JOptionPane.showMessageDialog(null, vo.getBookNm()+" ???(???) ?????? ???????????????.");
		}
	}
	
	

	/**
	 * ?????? ????????? ????????? ???????????? messege??? ????????? ?????????.
	 */
	private void getDeleteMessege() {
		//??????????????? ???????????? ????????? ??????
		if (checkedList.size()> 0) { 
			int rslt = JOptionPane.showConfirmDialog(null, "????????? ????????? ?????? ???????????????????", "?????? ????????? ???????????????.", JOptionPane.YES_NO_OPTION);
			if (rslt == JOptionPane.YES_OPTION) {
				if (deleteCheckedList(checkedList) == 1) {
					JOptionPane.showMessageDialog(null, "?????? ?????? ???????????????.");
				} else {
					
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "?????? ?????? ???????????????.");
			}
			
		} else {
			
			if(bookTbl.getSelectedRow()>-1) { 			//????????? ?????? ?????? ??????
				
				BookVO delVO = this.bkModel.getRowAt(bookTbl.getSelectedRow());
				int rslt = JOptionPane.showConfirmDialog(null, " '" + delVO.getBookNm()+ "' " +" ???(???) ?????? ???????????????????", delVO.getBookNm(), JOptionPane.YES_NO_OPTION);
				
				if (rslt == JOptionPane.YES_OPTION) { 
					deleteBookVO(delVO);
				} else { 
					JOptionPane.showMessageDialog(null," '" + delVO.getBookNm() + "' "+" ????????? ???????????????.", delVO.getBookNm(), JOptionPane.INFORMATION_MESSAGE);
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "????????? ????????? ???????????????.","????????? ??? ??????", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		
		
		
	}
	

	/**
	 * ?????? ?????? ?????? Dialog??? ????????? ?????????.
	 * Dialog??? ???????????? ???????????? repaint??????.
	 */
	private void getBookDetailDialog() {
		BookVO vo = this.bkModel.getRowAt(bookTbl.getSelectedRow());
		
		BookUpdateDialog uptDialog = new BookUpdateDialog();
		uptDialog.initializeBookOne(vo);
		uptDialog.setLocationCenter();
		
		
	}
	
	



	private void setParentValues() {
		List<ParentCategoryVO> list= manager.selectParentCategoryList();
		ParentCategoryVO vo = new ParentCategoryVO();
		vo.setPCtgNm("?????? ????????????");
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
	
	
	private void resetSearchField() {
		cbbCategoryP.removeAllItems();
		cbbCategoryC.removeAllItems();
		setParentValues();
		schFd.setText("");
	}
	
	
	/**
	 * ?????? ???????????? ?????? ????????? ?????????.
	 * model??? ???????????? ?????? ?????? ??? ???????????? ?????? ????????? ??????.
	 */
	private void repaintBookTable() {
		this.bkModel.removeAll();
		initialize();
		
	}

}
