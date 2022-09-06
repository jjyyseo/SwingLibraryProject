package net.mbiz.library.ui.common.renderer;

import java.awt.Component;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import net.mbiz.library.data.BorrowVO;
import net.mbiz.library.ui.common.CommonConstants;

public class BorrowTableRenderer extends JLabel implements TableCellRenderer{
	
	
	public BorrowTableRenderer() {
		setHorizontalAlignment(JLabel.CENTER);
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int col) {
		setOpaque(true); // 불투명
		setFont(CommonConstants.FONT_BASE_17);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		BorrowVO vo = (BorrowVO) value;
		String str = "";
		
		switch (col) {

		case 1:
			str = String.valueOf(vo.getBorrowNo());
		case 2:
			str = vo.getBookNm();
		case 3:
			str = vo.getBookWtr();
		case 4:
			str = sdf.format(vo.getStartDate());
		case 5:
			str = sdf.format(vo.getEndDate());
		case 6:
			if (vo.getReturnDate()!=null) {
				str = sdf.format(vo.getReturnDate());
			} else {
				str = "-";
			}
		case 7:
			str = vo.getOverdue() + "(일)";

		}
		
		return null;
	}
	
}
