package net.mbiz.library.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.EventListenerList;

/**
 * 도서 관련 Event가 일어날 경우 정해진 동작을 실행한다.
 * @author metabiz
 *
 */
public abstract class BookEventListener implements ActionListener{
	
	protected EventListenerList listenerList = new EventListenerList();
	
    public void addBookListener(BookEventListener bookListener) {
        listenerList.add(BookEventListener.class, bookListener);
    }
    
    public void reloadPerformed(ActionEvent e) {
    	
    }
    

}
