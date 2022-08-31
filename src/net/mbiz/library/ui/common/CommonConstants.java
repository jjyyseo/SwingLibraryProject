package net.mbiz.library.ui.common;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTable;

import net.mbiz.edt.barcode.ag.ui.common.table.BeanTableModel;
import net.mbiz.library.data.BookVO;
import net.mbiz.library.data.BorrowVO;

/*
 * 공통 상수를 정의하는 클래스
 */
public class CommonConstants {
	
	/*배경 색상*/ 
	public static final Color COLOR_BASE_BACKGROUND = new Color(237,237,237);
	public static final Color COLOR_WHITE_BACKGROUND = new Color(247,250,255);
	public static final Color COLOR_CONTENT_BACKGROUND = new Color(200,200,200);
	public static final Color COLOR_MENUBAR_BACKGROUND = new Color(051,051,120);
	public static final Color COLOR_LABEL_BACKGROUND = new Color(255,255,154);
//	public static final Color COLOR_STATE_lINE = new Color(152,163,230);
	
	/*폰트 색상*/
	public static final Color COLOR_MENU_FONT = new Color(216,216,230);
	public static final Color COLOR_MENU_LINE = new Color(113,113,164);
	public static final Color COLOR_MENU_FONT2 = new Color(60,60,130);
	
	/*폰트*/
	public static final Font FONT_BASE_18 = new Font("나눔스퀘어", Font.PLAIN, 18);
	public static final Font FONT_BASE_17 = new Font("나눔스퀘어", Font.PLAIN, 17);
	public static final Font FONT_BASE_15 = new Font("나눔스퀘어", Font.PLAIN, 15);
	public static final Font FONT_TITLE_25 = new Font("예스 고딕 레귤러", Font.BOLD, 25);
	public static final Font FONT_TITLE_22 = new Font("예스 고딕 레귤러", Font.BOLD, 22);
	
}
