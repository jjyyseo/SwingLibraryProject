package net.mbiz.library.ui.common;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import net.mbiz.edt.barcode.ag.ui.common.table.BeanTableModel;
import net.mbiz.edt.barcode.ag.ui.common.table.CommonTableRenderer;
import net.mbiz.library.data.AddBookList;
import net.mbiz.library.data.AddBorrowList;
import net.mbiz.library.data.BookVO;
import net.mbiz.library.data.BorrowVO;
import net.mbiz.library.ui.common.renderer.BookTableRenderer;
import net.mbiz.library.ui.common.renderer.BorrowTableRenderer;
import net.mbiz.library.ui.common.renderer.CheckBoxRenderer;

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
	
	/*폰트 색상*/
	public static final Color COLOR_MENU_FONT = new Color(216,216,230);
	public static final Color COLOR_MENU_LINE = new Color(113,113,164);
	public static final Color COLOR_MENU_FONT2 = new Color(60,60,130);
	
	/*폰트*/
	public static final Font FONT_BASE_18 = new Font("나눔고딕", Font.PLAIN, 18);
	public static final Font FONT_BASE_17 = new Font("나눔고딕", Font.PLAIN, 17);
	public static final Font FONT_BASE_15 = new Font("나눔고딕", Font.PLAIN, 15);
	public static final Font FONT_BOLD_15 = new Font("나눔고딕", Font.PLAIN, 15);
	public static final Font FONT_BASE_12 = new Font("나눔고딕", Font.PLAIN, 12);
	public static final Font FONT_TITLE_25 = new Font("나눔고딕", Font.BOLD, 25);
	public static final Font FONT_TITLE_22 = new Font("나눔고딕", Font.BOLD, 22);
	
	/*테이블*/ //뺄거임
	public static BeanTableModel<BookVO> bkModel;
	public static BeanTableModel<BorrowVO> bwModel;
	
	
	/*대출 테이블 다시 그리는 메서드*/
	public static void repaintBorrowTable() {
		CommonConstants.bwModel.removeAll();
		Collections.sort(AddBorrowList.borrowList, Collections.reverseOrder());
		System.err.println("여기는 repaintBorrowTable");
		CommonConstants.bwModel.addDataList((ArrayList) AddBorrowList.borrowList);
		CommonConstants.bwModel.fireTableDataChanged();	// 테이블에 변경된 데이터 반영
		
	}
	/*전체 도서 테이블 다시 그리는 메서드*/
	public static void repaintBookTable() {
		CommonConstants.bkModel.removeAll();
		Collections.sort(AddBookList.bookList, Collections.reverseOrder());
		CommonConstants.bkModel.addDataList((ArrayList) AddBookList.bookList);
		CommonConstants.bkModel.fireTableDataChanged();	// 테이블에 변경된 데이터 반영
	}
	
	
	/**
	 * <테이블에 모델을 적용하고 랜더러 설정 및 테이블의 열을 설정한다.>
	 * JTable과 BeanTableModel을 파라미터로 받아 
	 * 테이블에 모델을 셋팅,
	 * 각 컬럼의 열 너비, 랜더러 셋팅.
	 * @param <T>
	 * @param table
	 * @param model
	 */
	public static <T> void setTableModelColumnWithCommonTableRenderer(JTable table, BeanTableModel<T> model) {
		
		model.setSorting(false);
		setDefaultTable(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		if (model.equals(bkModel)) {
			BookTableRenderer renderer = new BookTableRenderer();
			for (int k = 0; k < model.getColumnCount(); k++) {
			    TableColumn column = new TableColumn(k, model.getColumnWidth(k), renderer, null);
			    table.addColumn(column);
			}	
		} else if(model.equals(bwModel)) {
			BorrowTableRenderer renderer = new BorrowTableRenderer();
			for (int k = 0; k < model.getColumnCount(); k++) {
			    TableColumn column = new TableColumn(k, model.getColumnWidth(k), renderer, null);
			    table.addColumn(column);
			}
		}
		
		setTableCheckBox(table, model);
		table.setModel(model);
	}

	
	/**
	 * 기본 테이블 설정.
	 * @param table
	 */
	public static void setDefaultTable(JTable table) {
	    table.setAutoCreateColumnsFromModel(false);
	    table.setBackground(Color.white);
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    table.getTableHeader().setReorderingAllowed(false);
	    table.setRowHeight(31);
	    table.setFillsViewportHeight(true);
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    setDefaultTableHeader(table.getTableHeader());
	      
	}
	   
	   
	/**
	 * 기본 테이블의 헤더 설정.
	 * @param header
	 */
	public static void setDefaultTableHeader(JTableHeader header) {
	    header.setReorderingAllowed(false);
	    header.setUpdateTableInRealTime(true);
	    header.setBackground(new Color(175, 175, 175));
	    header.setForeground(Color.BLACK);
	    header.setFont(CommonConstants.FONT_BASE_17);
	    header.setBorder(BorderFactory.createEmptyBorder());
	    header.setForeground(CommonConstants.COLOR_WHITE_BACKGROUND);

	}
	
	/**
	 * 테이블의 체크박스 설정.
	 * @param <T>
	 * @param table
	 * @param model
	 */
	public static <T> void setTableCheckBox(JTable table, BeanTableModel<T> model) {
		model.setEditColumn(0); 
		JCheckBox ckBox = new JCheckBox();
		ckBox.setOpaque(true);
		ckBox.setHorizontalAlignment(0);
		
		CheckBoxRenderer checkRdr = new CheckBoxRenderer();
		table.getColumnModel().getColumn(0).setCellRenderer(checkRdr);
		table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(ckBox));
		
	}
	
}


//CellEdito란 무엇인가...?
