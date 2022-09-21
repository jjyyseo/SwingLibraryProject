package net.mbiz.library.ui.common.renderer;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.Renderer;

public class CategoryComboboxRenderer extends JComboBox implements Renderer{
	
	// 표시하는 값(aValue)을 설정함과 함께, 그 값이 「현재의 선택」(isSelected)으로서 렌더링 될지 어떨지를 설정합니다.
	@Override
	public void setValue(Object aValue, boolean isSelected) {
		// CategoryChildVO, CategoryParentVO
	}

	@Override
	public Component getComponent() { 	// 값을 렌더링 하기 위해서 사용되는 컴퍼넌트를 리턴합니다.
		// 값 뿌려주는 곳
		
		
		return this; // 콤보박스 리턴
	}
	
}
