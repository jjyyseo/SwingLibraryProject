package net.mbiz.library.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.mbiz.library.ui.common.CommonConstants;

public class BookRegistDialog extends JDialog {

	
	private JPanel pnMain;        
	private JPanel pnTop;		  // in pnMain
	private JPanel pnBottom;      // in pnMain
	private JPanel pnWest;        // in pnTop
	private JPanel pnEast;        // in pnTop
	private JPanel pnImg;         // in pnWest
	private JPanel pnAttach;      // in pnWest
	private JPanel pnTitle;       // in pnEast
	private JPanel pnCnt;         // in pnEast
	private JPanel pnFooter;      // in pnEast
	private JPanel pnIntro;       // in pnBottom
	
	private JTextArea txtArea;    // in pnIntro
	private JButton attachBtn;	  // in pnAttach
	
	public BookRegistDialog(){
		setTitle("도서 정보 추가하기");
		jbInit();
	}

	/**
	 * 기본 UI Init
	 */
	private void jbInit() {
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setSize((new Dimension(600,700)));
		setModal(true); // 배경 클릭 ㄴㄴ
		
		this.add(Box.createHorizontalStrut(20), BorderLayout.WEST);
		this.add(Box.createHorizontalStrut(20), BorderLayout.EAST);
		this.add(Box.createVerticalStrut(20), BorderLayout.NORTH);
		this.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);
		
		/*pnMain - pnTop(NORTH),pnBottom(SOUTH)*/
		this.pnMain = new JPanel();
		pnMain.setLayout(new BorderLayout());
		pnMain.setPreferredSize(new Dimension(560,660));
		pnMain.setBackground(Color.blue);

		//NORTH
		this.pnTop = new JPanel();
		pnTop.setLayout(new BorderLayout());
		pnTop.setPreferredSize(new Dimension(0,363));
		pnTop.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		pnTop.setBackground(Color.green);
		//SOUTH
		this.pnBottom = new JPanel();
		pnBottom.setLayout(new BorderLayout());
		pnBottom.setPreferredSize(new Dimension(560,260));
		pnBottom.setBackground(Color.magenta);
		pnBottom.add(Box.createHorizontalStrut(10), BorderLayout.WEST);
		pnBottom.add(Box.createHorizontalStrut(10), BorderLayout.EAST);
		pnBottom.add(Box.createVerticalStrut(10), BorderLayout.NORTH);
		pnBottom.add(Box.createVerticalStrut(10), BorderLayout.SOUTH);
		
		
		
		/*pnTop - pnWest(WEST), pnEast(EAST)*/
		//WEST
		this.pnWest = new JPanel();
		pnWest.setLayout(new BorderLayout());
		pnWest.setPreferredSize(new Dimension(215,0));
		pnWest.setBackground(CommonConstants.COLOR_WHITE_BACKGROUND);
		//EAST
		this.pnEast = new JPanel();
		pnEast.setLayout(new BorderLayout());
		pnEast.setPreferredSize(new Dimension(332,0));
		pnEast.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		pnEast.setBackground(CommonConstants.COLOR_WHITE_BACKGROUND);
		
		/*pnWest - pnImg(CENTER), pnBorrow(SOUTH)*/
		this.pnImg = new JPanel();
		pnImg.setPreferredSize(new Dimension(205,230));
		pnImg.setBackground(Color.magenta);
		pnWest.add(pnImg, BorderLayout.CENTER);
		
		this.pnAttach = new JPanel();
		pnAttach.setLayout(new BorderLayout());
		pnAttach.setPreferredSize(new Dimension(205,100));
		pnAttach.setBackground(CommonConstants.COLOR_WHITE_BACKGROUND);
		pnAttach.add(Box.createVerticalStrut(10), BorderLayout.NORTH);
		pnAttach.add(Box.createVerticalStrut(10), BorderLayout.SOUTH);		
		
		this.attachBtn = new JButton("사진 첨부");
		pnAttach.add(attachBtn, BorderLayout.CENTER);
		pnWest.add(pnAttach, BorderLayout.SOUTH);
		
		
		
		/*pnEast - pnTitle(NORTH), pnCnt(CENTER), pnFooter(SOUTH)*/
		//NORTH
		this.pnTitle = new JPanel();
		pnTitle.setLayout(new BorderLayout());
		pnTitle.setPreferredSize(new Dimension(0, 130));
		pnTitle.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		pnTitle.setBackground(CommonConstants.COLOR_WHITE_BACKGROUND);
		//CENTER
		this.pnCnt = new JPanel();
		pnCnt.setLayout(new GridLayout(2,2, 10,10));
		pnCnt.setPreferredSize(new Dimension(0, 173));
		pnCnt.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		pnCnt.setBackground(CommonConstants.COLOR_MENUBAR_BACKGROUND);

		
		
		/*pnBottom - pnIntro(CENTER)*/
		this.pnIntro = new JPanel();
		pnIntro.setLayout(new BorderLayout());
		pnIntro.setBackground(Color.GREEN);
		
		this.txtArea = new JTextArea();
		txtArea.setBackground(CommonConstants.COLOR_WHITE_BACKGROUND);
		txtArea.setEditable(false);
		txtArea.setLineWrap(true); 
		txtArea.setFont(CommonConstants.FONT_BASE_17);
		JScrollPane srlPn = new JScrollPane(txtArea);
		pnIntro.add(srlPn, BorderLayout.CENTER);
		
		
		pnEast.add(pnTitle, BorderLayout.NORTH);
		pnEast.add(pnCnt, BorderLayout.CENTER);

		pnTop.add(pnWest, BorderLayout.WEST);
		pnTop.add(pnEast, BorderLayout.EAST);
		pnBottom.add(pnIntro, BorderLayout.CENTER);
		
		pnMain.add(pnTop, BorderLayout.NORTH);
		pnMain.add(pnBottom, BorderLayout.SOUTH);
		this.add(pnMain, BorderLayout.CENTER);
		
	}
	
	public void setLocationCenter() {
		Dimension d = this.getToolkit().getScreenSize(); 
		this.setLocation((int) d.getWidth() / 2 - this.getWidth() / 2, (int) d.getHeight() / 2 - this.getHeight() / 2);
		this.setVisible(true);
	}

}
