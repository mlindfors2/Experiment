package tenta170815;

import java.util.Observable;
import java.util.Observer;

public class MessageWriter implements Observer {
	
	public MessageWriter(Notifier notify) {
		notify.addObserver(this);
	}

	public void update(Observable arg0, Object arg1) {
		System.out.println(arg1);
		
	}
	
}
