package net.mbiz.library.ui.library.mainPage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.FileHandler;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.handler.DBSqlHandler;
import net.mbiz.library.manager.HandlerManager;
import net.mbiz.library.ui.common.CommonConstants;
import net.mbiz.library.ui.library.book.panel.BookPanel;

public class BookListCardPanel extends JPanel implements ActionListener{
	
	private JPanel pnHeader;
	private JPanel pnBody;
	private JPanel pnCnt;
	private JPanel pnSch;
	private JPanel pnSchSet;
	private JPanel pnPadding;
	private JPanel pnTitle;
	private JLabel title;
	private JScrollPane sclPn;
	private JTextField schFd;
	
	private JButton schBtn;
	
	private HandlerManager manager =  HandlerManager.getInstance();
	
	public BookListCardPanel(MainPanel pn){
		jbInit();
	}

	private void jbInit() {
		this.setLayout(new BorderLayout());
		this.setBackground(CommonConstants.COLOR_BASE_BACKGROUND);
		
// -----------------------pnBody - pnHeader(NORTH), sclPn-pnCnt(CENTER)---------------------------		
		this.pnBody = new JPanel();
		pnBody.setLayout(new BorderLayout());
		pnBody.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		
		this.pnCnt = new JPanel();
		pnCnt.setLayout(new FlowLayout());
		pnCnt.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		
		this.pnHeader = new JPanel();
		pnHeader.setLayout(new BorderLayout());
		pnHeader.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		pnHeader.setPreferredSize(new Dimension(0,400));

// ------------------pnHeader - pnBtnSet(NORTH), pnTitle(CENTER), pnSch(SOUTH)--------------------
		
		
		//CENTER
		this.pnTitle = new JPanel();
		pnTitle.setPreferredSize(new Dimension(0,150));
		pnTitle.setLayout(new BorderLayout());
		pnTitle.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		
		this.title = new JLabel("?????? ??????");
		title.setFont(CommonConstants.FONT_TITLE_25);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		
		//SOUTH
		this.pnSch = new JPanel();
		pnSch.setLayout(new BorderLayout());
		pnSch.setPreferredSize(new Dimension(0,200));
		pnSch.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		pnSch.setBorder(BorderFactory.createEmptyBorder(0, 0, 90, 0));
		
		this.pnSchSet = new JPanel();
		pnSchSet.setLayout(new BorderLayout());
		pnSchSet.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		pnSchSet.setBorder(BorderFactory.createEmptyBorder(0,600,60,600));
		/*????????? ?????????*/
		this.schFd = new JTextField();
		schFd.setPreferredSize(new Dimension(500,47));
		schFd.setFont(CommonConstants.FONT_BASE_17);
		
		

		this.schBtn = new JButton("??????");
		schBtn.setPreferredSize(new Dimension(70,47));
		
		this.pnPadding = new JPanel();
		pnPadding.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		
		/*book data ???????????? book Panel ?????????*/
		for (BookVO bv : manager.selectBookList()) {	
			BookPanel bookPanel = new BookPanel(bv);
			pnCnt.add(bookPanel);
		}
		
		
		pnTitle.add(title, BorderLayout.CENTER);

		pnSchSet.add(schFd, BorderLayout.WEST);
		pnSchSet.add(schBtn, BorderLayout.EAST);
		pnSchSet.add(pnPadding, BorderLayout.CENTER);
		pnSch.add(pnSchSet, BorderLayout.CENTER);
		pnHeader.add(pnTitle, BorderLayout.CENTER);
		pnHeader.add(pnSch, BorderLayout.SOUTH);
		
		pnBody.add(pnHeader, BorderLayout.NORTH);
		this.add(pnBody, BorderLayout.CENTER);
		
		/*????????? ?????????*/
		this.sclPn = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS
										   , ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.sclPn.setViewportView(pnCnt);
		pnBody.add(sclPn, BorderLayout.CENTER);
		

		
		schBtn.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(schBtn)) {
			getSearhBookCardList();
		}

	}

	private void getSearhBookCardList() {
		// ???????????? ???????????? ?????? ??????
		if (!schFd.getText().isEmpty() && !schFd.getText().equals("")) {
			for (BookVO bv : manager.selectBookList()) {
				System.err.println("????????? BookListCardPanel. ?????? ?????? ?????? ????????? ??????. ");

				if (schFd.getText().contains(bv.getBookNm())) {
					
					BookPanel pnBook = new BookPanel(bv);
					pnBook.printBookPanel(bv);
					
					pnCnt.add(pnBook);
				}
			}
			
		} else {
			JOptionPane.showMessageDialog(pnBody, "???????????? ??????????????????");
		}
	}
	
	

}
