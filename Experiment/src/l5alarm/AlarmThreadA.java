package l5alarm;

import java.util.Observable;

<<<<<<< HEAD
public class AlarmThreadA extends Observable{
=======
public class AlarmThreadA extends Observable {
>>>>>>> branch 'master' of https://github.com/mlindfors2/Experiment
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
<<<<<<< HEAD
//			System.out.println("Nu är det dags för alarm!");
=======
			//System.out.println("Nu är det dags för alarm!");
>>>>>>> branch 'master' of https://github.com/mlindfors2/Experiment
			thread = null;
		}
	}
}

