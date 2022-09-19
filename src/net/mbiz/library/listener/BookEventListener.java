package net.mbiz.library.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.event.EventListenerList;

import net.mbiz.library.manager.HandlerManager;

/**
 * 도서 관련 Event가 일어날 경우 정해진 동작을 실행한다.
 * @author metabiz
 *
 */
public interface BookEventListener extends EventListener{

	public void reloadPerformed(ActionEvent e);
    

}
