package net.mbiz.library.ui.common.renderer;

import java.awt.Color;
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
		
		/*데이터  쪼개기*/
		switch (col) {

		case 1:
			str = String.valueOf(vo.getBorrowNo());
			break;
		case 2:
			str = vo.getBookNm();
			break;
		case 3:
			str = vo.getBookWtr();
			break;
		case 4:
			str = sdf.format(vo.getStartDate());
			break;
		case 5:
			str = sdf.format(vo.getEndDate());
			break;
		case 6:
			if (vo.getReturnDate()!=null) {
				str = sdf.format(vo.getReturnDate());
			} else {
				str = "-";
			}
			break;
		case 7:
			str = vo.getOverdue() + "(일)";
			break;

		}

		if (row % 2 == 1) {
			this.setBackground(fgColor);
		} else {
			this.setBackground(bgColor);
		}
		
		this.setText(str); // 문자열로 데이터 뿌리기.
		
		return this;
	}
	
	private Color fgColor = new Color(243, 243, 243);
	private Color bgColor = new Color(225, 227, 252);
	
}
