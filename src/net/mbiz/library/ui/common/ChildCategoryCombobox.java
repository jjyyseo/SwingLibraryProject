package net.mbiz.library.ui.common;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import net.mbiz.library.data.ChildCategoryVO;
import net.mbiz.library.data.ParentCategoryVO;
import net.mbiz.library.manager.HandlerManager;

public class ChildCategoryCombobox extends JComboBox {
	
	private HandlerManager manager = HandlerManager.getInstance();
	private String[] arr;
	
	public ChildCategoryCombobox(int pCtgIdx) {
		initialize(pCtgIdx);
		this.setModel(new DefaultComboBoxModel<>(arr));
		this.setFont(CommonConstants.FONT_BASE_15);
	}
	
	private void initialize(int pCtgIdx) {
		List<ChildCategoryVO> list= manager.selectChildCategoryList(pCtgIdx);
		arr = list.toArray(new String[list.size()]);
		
	}
	
	
}
