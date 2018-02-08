package l5alarm;

import java.util.Observable;

public class AlarmThreadA extends Observable{
	private Thread thread;
	private long ms;
	
	public AlarmThreadA(long ms) {
		this.ms = ms;
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
			setChanged();
			notifyObservers();
//			System.out.println("Nu är det dags för alarm!");
			thread = null;
		}
	}
}

