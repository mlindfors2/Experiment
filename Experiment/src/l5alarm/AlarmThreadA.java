package l5alarm;

public class AlarmThreadA {
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
			System.out.println("Nu är det dags för alarm!");
			thread = null;
		}
	}
}

