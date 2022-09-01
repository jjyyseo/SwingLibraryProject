package net.mbiz.library.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import net.mbiz.library.data.AddBookList;
import net.mbiz.library.data.BookVO;
import net.mbiz.library.ui.common.CommonConstants;
import net.mbiz.library.ui.common.MyCalenderDialog;

public class BookRegistDialog extends JDialog {

	
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
	private JButton registBtn;	  // in pnFooter
	
	private JTextArea txtArea;    // in pnIntro
	
	// 도서 정보 입력란
	private JTextField tfBookNm;
	private JTextField tfBookWtr;
	private JTextField tfPublisher;
	private JComboBox<String> cbbCategory;
	private JTextField tfIsbn;
	private JTextField tfDate;
	private JPanel pnReleaseDate;
	private JButton calenderBtn;

	private JLabel lblBookNm;
	private JLabel lblBookWtr;
	private JLabel lblPublisher;
	private JLabel lblCategory;
	private JLabel lblReleaseDate;
	private JLabel lblIsbn;
	
	private String category = "";
	
	
	public BookRegistDialog(){
		setTitle("도서 정보 추가하기");
		jbInit();
	}

	/**
	 * 기본 UI Init
	 */
	private void jbInit() {
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		setSize((new Dimension(600,700)));
		setModal(true); // 배경 클릭 ㄴㄴ
		
		getContentPane().add(Box.createHorizontalStrut(20), BorderLayout.WEST);
		getContentPane().add(Box.createHorizontalStrut(20), BorderLayout.EAST);
		getContentPane().add(Box.createVerticalStrut(20), BorderLayout.NORTH);
		getContentPane().add(Box.createVerticalStrut(20), BorderLayout.SOUTH);
		
		/*pnMain - pnTop(NORTH),pnBottom(SOUTH)*/
		this.pnMain = new JPanel();
		pnMain.setLayout(new BorderLayout());
		pnMain.setPreferredSize(new Dimension(550,660));

		//NORTH
		this.pnTop = new JPanel();
		pnTop.setLayout(new BorderLayout());
		pnTop.setPreferredSize(new Dimension(0,318));
		pnTop.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
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
		pnWest.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		//EAST
		this.pnEast = new JPanel();
		pnEast.setLayout(new BorderLayout());
		pnEast.setPreferredSize(new Dimension(367,0));
		pnEast.setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
		

		
		/*pnWest - pnImg(CENTER), pnBorrow(SOUTH)*/
		this.pnImg = new JPanel();
		pnImg.setPreferredSize(new Dimension(185,260));
		pnImg.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		pnWest.add(pnImg, BorderLayout.CENTER);
		this.pnAttach = new JPanel();
		pnAttach.setLayout(new BorderLayout());
		pnAttach.setPreferredSize(new Dimension(185,70));
		pnAttach.add(Box.createVerticalStrut(10), BorderLayout.NORTH);
		pnAttach.add(Box.createVerticalStrut(10), BorderLayout.SOUTH);		
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
		pnCntWest.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		this.pnCntEast = new JPanel();
		pnCntEast.setLayout(new GridLayout(6,1, 5 ,10));
		pnCntEast.setPreferredSize(new Dimension(283, 0));
		pnCntEast.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

		pnCnt.add(pnCntEast, BorderLayout.EAST);
		pnCnt.add(pnCntWest, BorderLayout.WEST);
		
		
		
		this.cbbCategory = new JComboBox<>();
		cbbCategory.setModel(new DefaultComboBoxModel<>(new String[] {"소설","어린이","경제경영","자기계발","자연과락"}));
		cbbCategory.setFont(CommonConstants.FONT_BASE_15);
		
		this.tfBookNm = new JTextField();
		this.tfBookWtr = new JTextField();
		this.tfPublisher = new JTextField();
		this.tfIsbn = new JTextField();
		this.pnReleaseDate= new JPanel();
		pnReleaseDate.setLayout(new BorderLayout());;
		
		this.calenderBtn = new JButton();
		calenderBtn.setPreferredSize(new Dimension(45,35));
		
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
		
		this.tfDate = new JTextField();
		tfDate.setPreferredSize(new Dimension(215,0));

		pnReleaseDate.add(calenderBtn, BorderLayout.EAST);
		pnReleaseDate.add(tfDate, BorderLayout.WEST);
		
		pnCntEast.add(tfBookNm);
		pnCntEast.add(tfBookWtr);
		pnCntEast.add(tfPublisher);
		pnCntEast.add(tfIsbn);
		pnCntEast.add(cbbCategory);
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
		pnIntro.setPreferredSize(new Dimension(0,240));
		pnIntro.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		this.txtArea = new JTextArea();
		txtArea.setLineWrap(true); 
		txtArea.setPreferredSize(new Dimension(0,230));
		txtArea.setFont(CommonConstants.FONT_BASE_17);
		
		JScrollPane srlPn = new JScrollPane(txtArea);
		pnIntro.add(srlPn, BorderLayout.CENTER);
		
		//SOUTH		
		this.pnFooter = new JPanel();
		pnFooter.setLayout(new FlowLayout());
		pnFooter.setPreferredSize(new Dimension(0,50));
		pnFooter.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
		this.registBtn = new JButton("등록");
		registBtn.setPreferredSize(new Dimension(100,50));
		registBtn.setFont(CommonConstants.FONT_BASE_17);
		pnFooter.add(registBtn);
		
		
		
		
		pnEast.add(pnCnt, BorderLayout.CENTER);

		pnTop.add(pnWest, BorderLayout.WEST);
		pnTop.add(pnEast, BorderLayout.EAST);
		pnBottom.add(pnIntro, BorderLayout.NORTH);
		pnBottom.add(pnFooter, BorderLayout.SOUTH);
		
		pnMain.add(pnTop, BorderLayout.NORTH);
		pnMain.add(pnBottom, BorderLayout.SOUTH);
		getContentPane().add(pnMain, BorderLayout.CENTER);
		
		
		
		
		
		cbbCategory.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				category = cbbCategory.getSelectedItem().toString();
				System.out.println("콤보박스 선택!!! " + category);
			}
		});
		
		
		/*도서 정보 insert*/
		registBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertBookVO();
			}
		});
		
		calenderBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MyCalenderDialog().setLocationCenter();
				
				// 다이어로그 종료 후 
				
			}
		});
		
	}
	
	public void setLocationCenter() {
		Dimension d = this.getToolkit().getScreenSize(); 
		this.setLocation((int) d.getWidth() / 2 - this.getWidth() / 2, (int) d.getHeight() / 2 - this.getHeight() / 2);
		this.setVisible(true);
	}
	
	private void insertBookVO(){
		if (category.equals("") || category.isEmpty() ) {
			category = "소설" ;
		}
		System.out.println("여기는 등록 " + category);
		
		
		// 어느 하나 빈칸이 있는 경우
		if( tfBookNm.getText().isEmpty() || tfBookWtr.getText().isEmpty()
				|| tfPublisher.getText().isEmpty() || category.equals("") || category.isEmpty()
//				|| pnReleaseDate.getText().isEmpty() || tfIsbn.getText().isEmpty()
				||txtArea.getText().isEmpty() ) {
			
			JOptionPane.showMessageDialog(null, "정보가 모두 입력되지 않았습니다. 모두 입력 해주세요.", "도서 추가 실패", JOptionPane.INFORMATION_MESSAGE);

		} else {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
			BookVO vo = new BookVO();
			
			int bkNo = AddBookList.bookList.size() + 1;
			String bkNm = tfBookNm.getText();
			String bkWtr = tfBookWtr.getText();
			String publisher = tfPublisher.getText();
			String bookIsbn = tfIsbn.getText();
//			String releaseDate = tfReleaseDate.getText();
			String booksub = txtArea.getText();
			
			vo.setBookNo(bkNo);
			vo.setBookNm(bkNm);
			vo.setBookWtr(bkWtr);
			vo.setPublisher(publisher);
			vo.setBookIsbn(Long.parseLong(bookIsbn));
//			try {
//				vo.setReleaseDate(sdf.parse(releaseDate));
//			} catch (ParseException e1) {
//				e1.printStackTrace();
//				System.err.println("package net.mbiz.library.ui.dialog.BookRegistDialog : 도서 등록 중 출간일 파싱에러 발생!");
//			}
			vo.setCategory(category);
			vo.setBooksub(booksub);
			vo.setRegistDate(new Date());
			
			AddBookList.bookList.add(vo);
			
			
			
			if (AddBookList.bookList.size() == bkNo) {
				JOptionPane.showMessageDialog(null, bkNm + "(이)가 등록되었습니다.", bkNm, JOptionPane.INFORMATION_MESSAGE);
				dispose();
				System.out.println("package net.mbiz.library.ui.dialog.BookRegistDialog : 도서 정보가 등록되었습니다. /n 등록된 도서 정보 ----> " + AddBookList.bookList.get(AddBookList.bookList.size()-1));
			} else {
				JOptionPane.showMessageDialog(null, "도서 추가 실패", "도서 추가 실패", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
	};

}
