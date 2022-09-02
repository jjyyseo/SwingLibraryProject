package net.mbiz.library.ui.panel.mainPage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import net.mbiz.library.data.AddBookList;
import net.mbiz.library.data.BookVO;
import net.mbiz.library.ui.common.CommonConstants;
import net.mbiz.library.ui.panel.BookPanel;

// MainPanel의 pnBody. - card 형식으로 리스트를 출력하는 패널(첫번쨰 카드)
public class BookListCardPanel extends JPanel{
	
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
		
		this.title = new JLabel("전체 도서");
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
		pnSchSet.setBorder(BorderFactory.createEmptyBorder(0,610,60,610));
		/*검색어 입력란*/
		this.schFd = new JTextField();
		schFd.setPreferredSize(new Dimension(500,47));
		schFd.setFont(CommonConstants.FONT_BASE_17);
		
		
		ImageIcon calImg = new ImageIcon("C:\\Work\\03.Workspace\\Test\\SwingLibrary\\src\\net\\mbiz\\library\\ui\\img\\sch_icon.png");
		Image img = calImg.getImage();
		Image changeImg = img.getScaledInstance(17,17, Image.SCALE_SMOOTH);
		ImageIcon chImg = new ImageIcon(changeImg);
		JLabel lbl = new JLabel(chImg);
		this.schBtn = new JButton();
		schBtn.setPreferredSize(new Dimension(50,47));
		schBtn.add(lbl);
		this.pnPadding = new JPanel();
		pnPadding.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		
		/*book data 가져와서 book Panel 만들기*/
		for (BookVO bv : AddBookList.bookList) {	
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
		
		
		/*스크롤 만들기*/
		this.sclPn = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS
										   , ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.sclPn.setViewportView(pnCnt);
		pnBody.add(sclPn, BorderLayout.CENTER);
		
		
		// 검색 이벤트
		schBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.err.println("여기는 schBtn ActionListener. 검색어는?-->" + schFd.getText());
				// 검색어가 비어있지 않은 경우
				if (!schFd.getText().isEmpty() && !schFd.getText().equals("")) {
					for (BookVO bv : AddBookList.bookList) {
						System.err.println("야기는 BookListCardPanel. 전체 도서 검색 결과가 있음. ");

						if (schFd.getText().contains(bv.getBookNm())) {
							
							BookPanel pnBook = new BookPanel(bv);
							pnBook.printBookPanel(bv);
							
							pnCnt.add(pnBook);
						}
					}
					
				} else {
					JOptionPane.showMessageDialog(pnBody, "검색어를 입력해주세요");
				}
				
			}
		});
		
		
		
	}

}
