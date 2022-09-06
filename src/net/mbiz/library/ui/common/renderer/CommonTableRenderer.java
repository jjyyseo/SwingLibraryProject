package net.mbiz.library.ui.common.renderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import net.mbiz.library.ui.common.CommonConstants;

public class CommonTableRenderer extends JLabel implements TableCellRenderer{

	public CommonTableRenderer() {
		setHorizontalAlignment(JLabel.CENTER);
	}
	
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		setOpaque(true);
		setFont(CommonConstants.FONT_BASE_17);
		String str = (value == null) ? "" : value.toString(); 
	
//		if (isSelected) { // 테이블 선택 시,
//			setForeground(CommonConstants.COLOR_MENUBAR_BACKGROUND);
//		} else {
//			setForeground(Color.black);
//		}
		
        if (row % 2 == 1) {
            this.setBackground(fgColor);
        }
        else {
            this.setBackground(bgColor);
        }
		this.setText(str);
		return this;
	}
	
    private Color fgColor = new Color(243, 243, 243);
    private Color bgColor = new Color(225, 227, 252);

}
