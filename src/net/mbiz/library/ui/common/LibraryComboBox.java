package net.mbiz.library.ui.common;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

public class LibraryComboBox<E> extends JComboBox<E>{
	
	public LibraryComboBox() {
		super();
		init();
	}
	public LibraryComboBox(ComboBoxModel<E> comboModel) {
		super(comboModel);
		init();
	}
	
	private void init() {
		this.setFont(CommonConstants.FONT_BASE_17);
	}
	
	

}
