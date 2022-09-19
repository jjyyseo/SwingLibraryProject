package net.mbiz.library.listener;

import javax.swing.event.EventListenerList;

public abstract class BookEvent {
    
    protected EventListenerList listenerList = new EventListenerList();
    
    public void addBookListener(BookEventListener l) {
        listenerList.add(BookEventListener.class, l);
    }
}
