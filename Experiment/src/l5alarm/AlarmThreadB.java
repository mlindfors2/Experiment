package l5alarm;

import java.util.LinkedList;

public class AlarmThreadB {
	private Thread thread;
	private long ms;
	AlarmListener al;
	private LinkedList <AlarmListener>list = new LinkedList<AlarmListener>();
	
	public AlarmThreadB(long ms) {
		this.ms = ms;
		
	}
	public void addAlarmListener(AlarmListener listener) {
		list.add(listener);
	}
	public void startAlarm() {
		if(thread==null) {
			thread = new AT();
			thread.start();
		}
	}
	
	private class AT extends Thread {
		public void run() {
			try {
				Thread.sleep(ms);
			}catch(InterruptedException e) {
				
			}
			for (int i=0;i<list.size();i++)
				list.get(i).alarm();
			thread = null;
		}
	}
}

