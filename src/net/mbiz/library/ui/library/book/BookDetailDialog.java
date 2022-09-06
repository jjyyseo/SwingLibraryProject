package net.mbiz.library.ui.library.book;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.ui.common.CommonConstants;

public class BookDetailDialog extends JDialog implements ActionListener{
	private JPanel pnCard;
	private JPanel pnUpdate;   
	private JButton updateBtn;
	
	private BookDetailPanel detail;
	private BookDetailPanel update;
	
	private CardLayout cards = new CardLayout();
	
	// table list에서 넘겨받을 vo 객체
	public static BookVO bkDatilVO;
	
	public BookDetailDialog() {
		jbInit();
	}

	/**
	 * 기본 UI Init()
	 */
	private void jbInit() {
		this.setTitle(bkDatilVO.getBookNm());
		setLocationRelativeTo(this); // 화면 중앙 설정
		setLayout(new BorderLayout());
		setSize((new Dimension(600,700)));
		setModal(true);
		// 여백
		this.add(Box.createHorizontalStrut(20), BorderLayout.WEST);
		this.add(Box.createHorizontalStrut(20), BorderLayout.EAST);
		this.add(Box.createVerticalStrut(20), BorderLayout.NORTH);
		this.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);
		
		
		this.pnCard = new JPanel();
		pnCard.setBackground(CommonConstants.COLOR_BASE_BACKGROUND);
		pnCard.setLayout(cards);
		pnCard.setPreferredSize(new Dimension(560,590));

		
		this.pnUpdate = new JPanel();
		pnUpdate.setLayout(new BorderLayout());
		pnUpdate.setBackground(Color.black);
		pnUpdate.setPreferredSize(new Dimension(205,80));
		pnUpdate.add(Box.createVerticalStrut(10), BorderLayout.NORTH);
		pnUpdate.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);
		pnUpdate.add(Box.createHorizontalStrut(240), BorderLayout.WEST);
		pnUpdate.add(Box.createHorizontalStrut(240), BorderLayout.EAST);
		
		this.updateBtn = new JButton("수정");
		updateBtn.setPreferredSize(new Dimension(70,0));
		updateBtn.setFont(CommonConstants.FONT_BASE_15);
		
		
		pnUpdate.add(updateBtn, BorderLayout.CENTER);
		
		pnCard.add("datail", new BookDetailPanel(this));
		pnCard.add("update", new BookUpdatePanel(this));
		
		detail = new BookDetailPanel(this);
		pnCard.add(detail);
		
		
		
		this.add(pnCard, BorderLayout.CENTER);
		this.add(pnUpdate, BorderLayout.SOUTH);
		
		
		updateBtn.addActionListener(this);
		
	}
	
	public void setLocationCenter() {
		Dimension d = this.getToolkit().getScreenSize(); 
		this.setLocation((int) d.getWidth() / 2 - this.getWidth() / 2, (int) d.getHeight() / 2 - this.getHeight() / 2);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(updateBtn)) {
			changeDetailCard();
		}
	}
	
	public void changeDetailCard() {
		cards.show(pnCard, "detail");
//		Detail.initializeBookInfo(bkDatilVO);
	};
	public void changeUpdateCard() {
		cards.show(pnUpdate, "update");
	};

}
