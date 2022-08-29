package net.mbiz.library.ui.main.panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import net.mbiz.library.ui.common.CommonConstants;
import net.mbiz.library.ui.main.LibraryMain;


/*
 * 메인화면 패널.
 * 검색 패널, 스크롤페인 추가예정@!!
 */
public class MainPanel extends JPanel{
	
	private JPanel pnCard;
	private JPanel pnTap;

	// tap
	private JPanel btnSet;
	private JButton cardBtn;
	private JButton tblBtn;
	
	private CardLayout cards = new CardLayout();
	
	
	public MainPanel(LibraryMain f){
		jbInit();
	}
	
	private void jbInit() {
		this.setLayout(new BorderLayout());
		this.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		setBorder(BorderFactory.createEmptyBorder(20,14,20,14));
		
		printTap();		//NORTH

		this.pnCard = new JPanel();
		pnCard.setBackground(CommonConstants.COLOR_BASE_BACKGROUND);
		pnCard.setLayout(cards);
		
		BookListCardPanel pnBody = new BookListCardPanel(this);
		pnCard.add(pnBody);

		pnCard.add("card", new BookListCardPanel(this));
		pnCard.add("table", new BookListTablePanel(this));
		
		this.add(pnCard, BorderLayout.CENTER);
		
		
	}

	/*
	 * MainPanel의 tap을 print하는 메서드
	 */
	public void printTap() {

		this.pnTap = new JPanel();
		pnTap.setLayout(new BorderLayout());
		pnTap.setPreferredSize(new Dimension(70,50));
		pnTap.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		
		// 카드로 보기, 테이블로 보기
		this.btnSet = new JPanel();
		btnSet.setLayout(new BorderLayout());
		btnSet.setPreferredSize(new Dimension(140,50));
		this.cardBtn = new JButton("card");
		cardBtn.setPreferredSize(new Dimension(70, 0));
		cardBtn.setFont(CommonConstants.FONT_BASE_15);
		this.tblBtn = new JButton("table");
		tblBtn.setPreferredSize(new Dimension(70, 0));
		tblBtn.setFont(CommonConstants.FONT_BASE_15);
		
		
		btnSet.add(cardBtn, BorderLayout.WEST);
		btnSet.add(tblBtn, BorderLayout.EAST);
		pnTap.add(btnSet, BorderLayout.EAST);
		

		this.add(pnTap, BorderLayout.NORTH);
		
		/* card 버튼 클릭 이벤트 */
		cardBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardBtn.setBackground(CommonConstants.COLOR_MENUBAR_BACKGROUND);
				cardBtn.setForeground(CommonConstants.COLOR_MENU_FONT);
				tblBtn.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
				tblBtn.setForeground(CommonConstants.COLOR_MENU_FONT2);
				cards.show(pnCard, "card");
			}
		});
		/* table 버튼 클릭 이벤트 */
		tblBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tblBtn.setBackground(CommonConstants.COLOR_MENUBAR_BACKGROUND);
				tblBtn.setForeground(CommonConstants.COLOR_MENU_FONT);
				cardBtn.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
				cardBtn.setForeground(CommonConstants.COLOR_MENU_FONT2);
				cards.show(pnCard, "table");
			}
		});



	}
	
}
