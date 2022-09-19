package net.mbiz.library.ui.library.book.dialog;

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
import net.mbiz.library.ui.library.book.panel.BookDetailPanel;
import net.mbiz.library.ui.library.book.panel.BookUpdatePanel;
import net.mbiz.library.util.DateFomatUtil;

public class BookDetailDialog extends JDialog implements ActionListener{
	private JPanel pnCard;
	private JPanel pnUpdate;   
	private JButton updateBtn;

	private CardLayout cards = new CardLayout();
	private BookDetailPanel detail;
	private BookUpdatePanel update;
	
	// table list에서 넘겨받을 vo 객체
//	public static BookVO bkDatilVO;
	
	private BookVO vo = null;
	private String bkNm;
	
	public BookDetailDialog(BookVO vo) {
		this.vo = vo;
		this.bkNm = vo.getBookNm();
		setTitle(bkNm);
		jbInit();
		
	}
	
	public void initialize(BookVO vo) {
		System.out.println(vo);
	}
	
	/**
	 * 기본 UI Init()
	 */
	private void jbInit() {
		System.out.println("여기는 dialog--vo??>>" + vo);
		this.setTitle(bkNm);
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
		
		
		pnCard.add("datail", new BookDetailPanel(this, vo));
		pnCard.add("update", new BookUpdatePanel(this, vo));
		
		
		
		pnCard.add(new BookDetailPanel(this, vo));
		
		
		
		this.add(pnCard, BorderLayout.CENTER);
		this.add(pnUpdate, BorderLayout.SOUTH);
		
		
		updateBtn.addActionListener(this);
		
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(updateBtn)) {
			System.out.println("btn click");
			changeUpdateCard();
		}
	}
	
	public void changeDetailCard() {
		cards.show(pnCard, "detail");
	};
	public void changeUpdateCard() {
		cards.show(pnCard, "update");
	};

	public void setLocationCenter() {
		Dimension d = this.getToolkit().getScreenSize(); 
		this.setLocation((int) d.getWidth() / 2 - this.getWidth() / 2, (int) d.getHeight() / 2 - this.getHeight() / 2);
		this.setVisible(true);
	}
	
	
}
