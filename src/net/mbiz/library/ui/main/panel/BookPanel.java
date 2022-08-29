package net.mbiz.library.ui.main.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import net.mbiz.library.ui.common.CommonConstants;

public class BookPanel extends JPanel {

	private JPanel bodyPn;
	private JPanel footerPn;
	private JLabel lblBookNm;
	private JLabel lblState;
	private JPanel pnState;
	private JPanel stateLine;

	public BookPanel() {
		jbInit();
	}

	private void jbInit() {
		setPreferredSize(new Dimension(200, 390));
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(20, 14, 20, 14));
		this.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);

		printBookPanel("테스트 북", ABORT);
		;
	}

	public void printBookPanel(String bookNm, int isBorrowed) {

		/* body 패널 */
		this.bodyPn = new JPanel();
		bodyPn.setPreferredSize(new Dimension(200, 240));
		bodyPn.setBackground(Color.DARK_GRAY);

		/* footer 패널 */
		this.footerPn = new JPanel();
		footerPn.setLayout(new GridLayout(2, 1));
		footerPn.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		footerPn.setPreferredSize(new Dimension(200, 120));

		// footerPn - 도서명 라벨
		this.lblBookNm = new JLabel(bookNm);
		lblBookNm.setPreferredSize(new Dimension(50, 10));
		lblBookNm.setFont(CommonConstants.FONT_BASE_18);
		lblBookNm.setHorizontalAlignment(JLabel.CENTER);
		// 대출 상태 표시 영역
		this.pnState = new JPanel();
		pnState.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		pnState.setLayout(new BorderLayout());
		pnState.add(Box.createHorizontalStrut(34), BorderLayout.EAST);
		pnState.add(Box.createHorizontalStrut(34), BorderLayout.WEST);
		pnState.add(Box.createVerticalStrut(11), BorderLayout.NORTH);
		pnState.add(Box.createVerticalStrut(11), BorderLayout.SOUTH);

		// lblState의 테두리 패널
		this.stateLine = new JPanel();
		stateLine.setBorder(new LineBorder(CommonConstants.COLOR_MENUBAR_BACKGROUND, 1));
		stateLine.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		stateLine.setLayout(new BorderLayout());

		pnState.add(stateLine, BorderLayout.CENTER);

		// footerPn - 대출 상태 라벨
		this.lblState = new JLabel();
		if (isBorrowed == 0) {
			this.lblState = new JLabel("대출가능");
		} else if (isBorrowed == 1) {
			this.lblState = new JLabel("대출중");
		}
		lblState.setPreferredSize(new Dimension(50, 10));
		lblState.setFont(CommonConstants.FONT_BASE_18);
		lblState.setHorizontalAlignment(JLabel.CENTER);

		stateLine.add(lblState, BorderLayout.CENTER);

		footerPn.add(lblBookNm);
		footerPn.add(pnState);

		this.add(bodyPn, BorderLayout.NORTH);
		this.add(footerPn, BorderLayout.SOUTH);
	}

}
