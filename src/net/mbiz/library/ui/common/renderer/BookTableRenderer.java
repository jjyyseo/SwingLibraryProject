package net.mbiz.library.ui.common.renderer;

import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.manager.HandlerManager;
import net.mbiz.library.ui.common.CommonConstants;
import net.mbiz.library.util.DateFomatUtil;

/**
 * 테이블 데이터를 그려주는 렌더러.
 * 
 * @author metabiz
 *
 */
public class BookTableRenderer extends JLabel implements TableCellRenderer {

	private HandlerManager manager = HandlerManager.getInstance();
	
	public BookTableRenderer() {
		setHorizontalAlignment(JLabel.CENTER);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int col) {
		setOpaque(true); // 불투명
		setFont(CommonConstants.FONT_BASE_17);
		
		BookVO vo = (BookVO) value;
		String str = "";

		
		/*데이터  쪼개기*/
		switch (col) {
		case 1:

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
			str = DateFomatUtil.formatToString("releaseDate", vo.getReleaseDate());
			break;
		case 6:
			str = manager.selectChildCategoryNm(vo.getBkCtgC());
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
		

		if (isSelected) {
			this.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		} else {
			if (row % 2 == 1) {
				this.setBackground(fgColor);
			} else {
				this.setBackground(bgColor);
			}	
		}
		
//		if (col == 7) {
//			if (str.equals("대출중")) {
//				this.setForeground(Color.red);	
//			} else {
//				this.setForeground(Color.GREEN);
//			}
//		}
		this.setText(str);
		
		return this;
	}

	private Color fgColor = new Color(243, 243, 243);
	private Color bgColor = new Color(225, 227, 252);

}
