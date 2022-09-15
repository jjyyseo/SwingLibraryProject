package net.mbiz.library.ui.common.renderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.data.BorrowVO;

/**
 * 체크박스를 테이블에 그려주는 Renderer. 
 * 체크된 상태에 따라서 그림을 그려줌.
 * @author metabiz
 *
 */
public class CheckBoxRenderer extends JCheckBox implements TableCellRenderer{
	
	private final Border noFocusBorder = new EmptyBorder(5,5,5,5);
    
	public CheckBoxRenderer() {
		setHorizontalAlignment(JLabel.CENTER);
		setBorder(noFocusBorder);
		setBorderPainted(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			boolean hasFocus, int row, int column) {
		
		if (row % 2 == 1) {
        	this.setBackground(fgColor);
        }
        else {
        	this.setBackground(bgColor);
        }
		
		
		
		boolean isChecked = false;
		if (value instanceof BookVO) {
			
			isChecked =  ((BookVO) value).isSelect();
			if (isChecked) {
				this.setSelected(true);
			} else {
				this.setSelected(false);
			}
			
		} else if (value instanceof BorrowVO) {
			
			isChecked =  ((BorrowVO) value).isSelect();
			if (isChecked) {
				this.setSelected(true);
			} else {
				this.setSelected(false);
			}
			
		}
		
		return this;
	}
	
	Color fgColor = new Color(243, 243, 243);
    Color bgColor = new Color(225, 227, 252);
}


/**
 * btn.setSelected(true);  // 버튼의 선택 활성화
 * btn.setSelected(false); // 버튼의 선택 해제
 * 
 */
