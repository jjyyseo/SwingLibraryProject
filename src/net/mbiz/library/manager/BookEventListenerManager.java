package net.mbiz.library.manager;

import java.util.ArrayList;
import java.util.List;

import net.mbiz.library.data.BookVO;
import net.mbiz.library.listener.BookEventListener;

public class BookEventListenerManager {
	private static BookEventListenerManager bookEventManager;

	private BookEventListenerManager() {

	}
	public static BookEventListenerManager getInstance() {
		if (bookEventManager == null) {
			bookEventManager = new BookEventListenerManager();
		}
		return bookEventManager;
	}
	
	private List<BookEventListener> listenerList = new ArrayList<>();
	
	// 이벤트리스너를 impl한 핸들러를 handlerList에 추가한다.
	public void addBookEventListener(BookEventListener listener) {
		listenerList.add(listener);
	}

	
	public void addedBook(BookVO vo) {
		for (BookEventListener listener : listenerList) {
			listener.bookAdded(vo);
		}
	}
}
