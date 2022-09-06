package net.mbiz.library.ui.library.mainPage;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import net.mbiz.library.main.LibraryMain;
import net.mbiz.library.ui.common.CommonConstants;



public class MainPanel extends JPanel implements ActionListener{ 
	
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
		setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		
		/*NORTH : printTap*/
		this.pnTap = new JPanel();
		pnTap.setLayout(new BorderLayout());
		pnTap.setPreferredSize(new Dimension(60,50));
		pnTap.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
			
		this.btnSet = new JPanel();
		btnSet.setLayout(new BorderLayout());
		btnSet.setPreferredSize(new Dimension(140,50));
	
		this.cardBtn = new JButton("card");
		cardBtn.setPreferredSize(new Dimension(70, 0));
		cardBtn.setFont(CommonConstants.FONT_BASE_15);
		cardBtn.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		cardBtn.setForeground(CommonConstants.COLOR_MENU_FONT2);
		
		this.tblBtn = new JButton("table");
		tblBtn.setPreferredSize(new Dimension(70, 0));
		tblBtn.setFont(CommonConstants.FONT_BASE_15);
		tblBtn.setBackground(CommonConstants.COLOR_MENUBAR_BACKGROUND);
		tblBtn.setForeground(CommonConstants.COLOR_MENU_FONT);
		
		btnSet.add(tblBtn, BorderLayout.WEST);
		btnSet.add(cardBtn, BorderLayout.CENTER);
		pnTap.add(btnSet, BorderLayout.WEST);
		this.add(pnTap, BorderLayout.SOUTH);


		this.pnCard = new JPanel();
		pnCard.setBackground(CommonConstants.COLOR_BASE_BACKGROUND);
		pnCard.setLayout(cards);
		
		BookListTablePanel pnBody = new BookListTablePanel(this);
		pnCard.add(pnBody);
		
		pnCard.add("card", new BookListCardPanel(this));
		pnCard.add("table", new BookListTablePanel(this));
		this.add(pnCard, BorderLayout.CENTER);
		
		
		
		cardBtn.addActionListener(this);
		tblBtn.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(cardBtn)) {
			changeMenuCard(cardBtn);
		} else if (e.getSource().equals(tblBtn)) {
			changeMenuCard(tblBtn);
		}
		
	}

	private void changeMenuCard(JButton btn) {
		if (btn.equals(cardBtn)) {
			cardBtn.setBackground(CommonConstants.COLOR_MENUBAR_BACKGROUND);
			cardBtn.setForeground(CommonConstants.COLOR_MENU_FONT);
			tblBtn.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
			tblBtn.setForeground(CommonConstants.COLOR_MENU_FONT2);
			cards.show(pnCard, "card");
			
		} else if (btn.equals(tblBtn)) {
			tblBtn.setBackground(CommonConstants.COLOR_MENUBAR_BACKGROUND);
			tblBtn.setForeground(CommonConstants.COLOR_MENU_FONT);
			cardBtn.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
			cardBtn.setForeground(CommonConstants.COLOR_MENU_FONT2);
			cards.show(pnCard, "table");
			
		}
		
	}
	
}
