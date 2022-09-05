package net.mbiz.library.ui.common;

import java.awt.Color;
import java.awt.Component;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import net.mbiz.edt.barcode.ag.ui.common.table.BeanTableModel;

public class CommonTableRenderer extends JLabel implements TableCellRenderer{

	public CommonTableRenderer() {
	}
	
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		setOpaque(true);
		setFont(CommonConstants.FONT_BASE_17);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		String str = "";
		
//		System.out.println(value.getClass().getName());
		
		if (value instanceof Date) {
			str = sdf.format(value);
			
		} else {
			str = (value == null) ? "" : value.toString(); 
		}
		
		
		if (isSelected) { // 테이블 선택 시,
			setForeground(CommonConstants.COLOR_MENUBAR_BACKGROUND);
		} else {
			setForeground(Color.black);
		}
		
		this.setText(str);
		
		return this;
	}
	
	

}
