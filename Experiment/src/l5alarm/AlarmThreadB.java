package l5alarm;

import java.util.LinkedList;

public class AlarmThreadB {
	private Thread thread;
	private long ms;
<<<<<<< HEAD
	AlarmListener al;
	private LinkedList <AlarmListener>list = new LinkedList<AlarmListener>();
	
=======
	private AlarmListener al;
	private LinkedList<AlarmListener>list = new LinkedList<AlarmListener>();
>>>>>>> branch 'master' of https://github.com/mlindfors2/Experiment
	public AlarmThreadB(long ms) {
		this.ms = ms;
		
	}
<<<<<<< HEAD
	public void addAlarmListener(AlarmListener listener) {
		list.add(listener);
=======
	
	public void addAlarmListener(AlarmListener al) {
		list.add(al);
>>>>>>> branch 'master' of https://github.com/mlindfors2/Experiment
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
<<<<<<< HEAD
			for (int i=0;i<list.size();i++)
				list.get(i).alarm();
=======
			for (AlarmListener alarms : list) {
				alarms.alarm();
			}
			
>>>>>>> branch 'master' of https://github.com/mlindfors2/Experiment
			thread = null;
		}
	}
}

