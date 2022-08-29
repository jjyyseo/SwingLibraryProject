package net.mbiz.library.ui.mypage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import net.mbiz.library.data.BorrowVO;
import net.mbiz.library.data.MakeBorrowList;
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
	
	private JTable borrowTbl;
	
	private JLabel title;
	private JLabel pvsLbl;
	
	private JTextField schFd;
	private JButton schBtn;
	private JButton pvsBtn;
	
	private List<BorrowVO> bwList;
	private BeanTableModel<BorrowVO> dModel;
	
	public MyPagePanel(LibraryMain f) {
		jbInit();
		initTable();	// 테이블 생성 먼저.
		initialize();	// 데이터 초기화
	}

	private void initialize() {
		bwList = MakeBorrowList.getInstance().makeBorrowList();
		dModel.addDataList((ArrayList) bwList);
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
		this.borrowTbl  = new JTable(dModel); // 데이터가 들어가는 테이블 
		borrowTbl.setRowHeight(32);
		borrowTbl.setFont(CommonConstants.FONT_BASE_17);
		// 테이블 스크롤
		JScrollPane sclpn = new JScrollPane(borrowTbl);
		sclpn.setCorner(JScrollPane.UPPER_LEFT_CORNER, borrowTbl.getTableHeader());
		
		
		//SOUTH		
		this.pnFooter = new JPanel();
		pnFooter.setPreferredSize(new Dimension(0,50));
		pnFooter.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);

		
		
		
		/* pnHeander - pnTitle(NORTH), pnSch(EAST), pnPvs(WEST) */
		// NORTH
		this.pnTitle = new JPanel();
		pnTitle.setLayout(new BorderLayout());
		pnTitle.setPreferredSize(new Dimension(0,55));
		pnTitle.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		this.title = new JLabel("나의 대출리스트");
		title.setFont(CommonConstants.FONT_TITLE_30);
		title.setHorizontalAlignment(JLabel.LEFT);
		title.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

		// EAST
		this.pnSch = new JPanel();
		pnSch.setLayout(new BorderLayout());
		pnSch.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		pnSch.setBorder(BorderFactory.createEmptyBorder(33, 0, 0, 0));
		
		//EAST
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

		pnTbl.add(sclpn, BorderLayout.CENTER);
		
		pnBody.add(pnFooter, BorderLayout.SOUTH);
		pnBody.add(pnTbl, BorderLayout.CENTER);
		pnBody.add(pnHeader, BorderLayout.NORTH);
		
		this.add(pnBody, BorderLayout.CENTER);
		
		// 검색 이벤트
		schBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.err.println("검색버튼 클릭");
				searchBorrow();
			}
		});
		
		/*전체리스트로 이동*/
		pvsBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dModel.addDataList((ArrayList) bwList);
			}
		});

	}
	
	/*테이블을 출력하는 메서드*/
	private void initTable() {
		
		String topHeader[] = {"도서명", "대출일", "반납예정일", "반납일", "연체일"};	
		int col[] = {100 ,100 ,100 ,100 ,100};
		
		this.dModel = new BeanTableModel<BorrowVO>(topHeader, col) {
			
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
        dModel.setNumbering(true);
        this.borrowTbl.setModel(dModel);
	}
	
	private void searchBorrow() {
		
		if (!schFd.getText().isEmpty() && !schFd.getText().equals("")) {
			dModel.removeAll();
			for (BorrowVO bv : bwList) {
				if (schFd.getText().contains(bv.getBookNm())) {
					dModel.addData(bv);
					System.err.println("여기는 searchBorrow. for문 안. 검색결과 bv는? --->" + bv);
				}
			}
			this.borrowTbl.setModel(dModel);

		} else {
			JOptionPane.showMessageDialog(pnBody, "검색어를 입력해주세요.");
		}
		
	}


}

//private void delete() {
//    int index = table.getSelectedRow();
//    if(index < 0){
//        showMessage("삭제할 행을 선택해 주세요.");
//    }else{
//        model.removeRow(index);
//    }
//}
