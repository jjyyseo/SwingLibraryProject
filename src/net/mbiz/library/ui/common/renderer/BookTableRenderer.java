package net.mbiz.library.ui.common.renderer;

import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.ui.common.CommonConstants;

/**
 * 테이블 데이터를 그려주는 렌더러.
 * 
 * @author metabiz
 *
 */
public class BookTableRenderer extends JLabel implements TableCellRenderer {

	public BookTableRenderer() {
		setHorizontalAlignment(JLabel.CENTER);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int col) {
		setOpaque(true); // 불투명
		setFont(CommonConstants.FONT_BASE_17);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		BookVO vo = (BookVO) value;
		String str = "";

		/*데이터  쪼개기*/
		switch (col) {
		case 1:
			str = String.valueOf(vo.getBookNo());
			break;
		case 2:
			str = vo.getBookNm();
			break;
		case 3:
			str = vo.getBookWtr();
			break;
		case 4:
			str = vo.getPublisher();
			break;
		case 5:
			str = sdf.format(vo.getReleaseDate());
			break;
		case 6:
			str = vo.getCategory();
			break;
		case 7:
			if (vo.getIsBorrowed() == 0) {
				str = "대출가능";
			} else if (vo.getIsBorrowed() == 1) {
				str = "대출중";
			} else {
				str = "알수없음";
			}
			break;
		}
		

//		str = (str == null) ? "" : value.toString();

		
		if (row % 2 == 1) {
			this.setBackground(fgColor);
		} else {
			this.setBackground(bgColor);
		}
		this.setText(str);
		
		
		if (this.getText().equals("대출중")) {
			if (col==7) {
				this.setForeground(Color.red);
			}
		} else {
			this.setForeground(Color.black);
		}
		

		return this;
	}

	private Color fgColor = new Color(243, 243, 243);
	private Color bgColor = new Color(225, 227, 252);

}
