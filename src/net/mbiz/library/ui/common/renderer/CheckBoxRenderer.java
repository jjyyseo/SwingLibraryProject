package net.mbiz.library.ui.common.renderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

/**
 * 체크박스만 뽑아내는 Renderer. 
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
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		if (isSelected) {
			this.setForeground(Color.MAGENTA);
			this.setBackground(Color.LIGHT_GRAY);
		} else {
			this.setForeground(Color.BLACK);
			this.setBackground(Color.WHITE);
			this.setOpaque(true);
		}
		
        if (row % 2 == 1) {
            this.setBackground(fgColor);
        }
        else {
            this.setBackground(bgColor);
        }
		System.out.println(row); 
		System.out.println(value); // null 이네요..?

		
		setSelected(value != null && ((Boolean) value).booleanValue()); // 선택 값이 비었을 때 선택 해제?
		
		return this;
	}
	
    private Color fgColor = new Color(243, 243, 243);
    private Color bgColor = new Color(225, 227, 252);
	
	


}




/**
 * btn.setSelected(true);  // 버튼의 선택 활성화
 * btn.setSelected(false); // 버튼의 선택 해제
 * 
 */
