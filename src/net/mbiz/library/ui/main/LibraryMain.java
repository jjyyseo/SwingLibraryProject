package net.mbiz.library.ui.main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import net.mbiz.library.ui.common.CommonConstants;
import net.mbiz.library.ui.mypage.MyPagePanel;
import net.mbiz.library.ui.panel.MainPanel;

public class LibraryMain extends JFrame {

	private JPanel pnCard;
	private JPanel pnMenu;
	private JPanel btnSet;
	private JButton mainBtn;
	private JButton myPageBtn;
	
	private CardLayout cards = new CardLayout();


	
	public LibraryMain() {
		this.setTitle("도서 관리 프로그램");
		jbInit();
	}

	/*
	 * 기본 UI Init
	 */
	private void jbInit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(1920, 1080)); // 화면 사이즈
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); // 풀스크린모드
		this.setResizable(false); // 리사이즈 X
//		this.setUndecorated(true);					    // 타이틀바 없어짐

//		this.addComponentListener(this);

		this.add(Box.createHorizontalStrut(50), BorderLayout.EAST);
		this.add(Box.createHorizontalStrut(50), BorderLayout.WEST);
		this.add(Box.createVerticalStrut(50), BorderLayout.NORTH);
		this.add(Box.createVerticalStrut(50), BorderLayout.SOUTH);

		this.pnCard = new JPanel();
		pnCard.setBackground(CommonConstants.COLOR_BASE_BACKGROUND);
		pnCard.setLayout(cards);

		MainPanel pnMain = new MainPanel(this);
		pnMain.printTap();
		pnCard.add(pnMain);

		printMenu(this);

		pnCard.add("main", new MainPanel(this));
		pnCard.add("mypage", new MyPagePanel(this));

		getContentPane().add(pnCard);
	}

	public void printMenu(LibraryMain f) {
		/* 메뉴 바 패널 */
		this.pnMenu = new JPanel();
		pnMenu.setPreferredSize(new Dimension(0, 100));
		pnMenu.setBackground(CommonConstants.COLOR_MENUBAR_BACKGROUND);
		pnMenu.setLayout(new BorderLayout());

		/* 메뉴버튼 영역 패널 */
		this.btnSet = new JPanel();
		btnSet.setLayout(new BorderLayout());
		btnSet.setBackground(CommonConstants.COLOR_MENUBAR_BACKGROUND);
		btnSet.setBorder(BorderFactory.createEmptyBorder(10, 50, 0, 0));

		// Main 버튼
		this.mainBtn = new JButton("MAIN");
		mainBtn.setPreferredSize(new Dimension(180, 0));
		mainBtn.setFont(CommonConstants.FONT_BASE_18);
		mainBtn.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		mainBtn.setForeground(CommonConstants.COLOR_MENU_FONT2);
		mainBtn.setBorder(new LineBorder(CommonConstants.COLOR_MENU_LINE, 1));
		
		// MyPage 버튼
		this.myPageBtn = new JButton("MY PAGE");
		myPageBtn.setPreferredSize(new Dimension(180, 0));
		myPageBtn.setFont(CommonConstants.FONT_BASE_18);
		myPageBtn.setBackground(CommonConstants.COLOR_MENUBAR_BACKGROUND);
		myPageBtn.setForeground(CommonConstants.COLOR_MENU_FONT);
		myPageBtn.setBorder(new LineBorder(CommonConstants.COLOR_MENU_LINE, 1));

		btnSet.add(mainBtn, BorderLayout.WEST);
		btnSet.add(myPageBtn, BorderLayout.EAST);

		pnMenu.add(btnSet, BorderLayout.WEST);
		this.add(pnMenu, BorderLayout.NORTH);

		/* Main 버튼 클릭 이벤트 */
		mainBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myPageBtn.setBackground(CommonConstants.COLOR_MENUBAR_BACKGROUND);
				myPageBtn.setForeground(CommonConstants.COLOR_MENU_FONT);
				mainBtn.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
				mainBtn.setForeground(CommonConstants.COLOR_MENU_FONT2);
				cards.show(pnCard, "main");
			}
		});

		/* MyPage 버튼 클릭 이벤트 */
		myPageBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myPageBtn.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
				myPageBtn.setForeground(CommonConstants.COLOR_MENU_FONT2);
				mainBtn.setBackground(CommonConstants.COLOR_MENUBAR_BACKGROUND);
				mainBtn.setForeground(CommonConstants.COLOR_MENU_FONT);
				cards.show(pnCard, "mypage");
			}
		});

	}

	public void setLocationCenter() {
		Dimension d = this.getToolkit().getScreenSize();
		this.setLocation((int) d.getWidth() / 2 - this.getWidth() / 2, (int) d.getHeight() / 2 - this.getHeight() / 2);
		this.setVisible(true);
	}

}
