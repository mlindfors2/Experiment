package l5alarm;

public class AlarmThreadB {
	private Thread thread;
	private long ms;
	
	public AlarmThreadB(long ms) {
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
			System.out.println("Nu är det dags för alarm!");
			thread = null;
		}
	}
}

