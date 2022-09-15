package net.mbiz.library.manager;

import java.util.ArrayList;
import java.util.List;

import net.mbiz.library.handler.DataHandler;

// DBHandler이냐 FileHandler이냐 구분하여 동작.

public class HandlerManager {

	private List<DataHandler> handlerList = new ArrayList<>();
	
	private void checkHandlerType() {
		
	}

}
