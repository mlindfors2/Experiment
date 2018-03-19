package tenta170815;

import java.util.Observable;

public class Notifier extends Observable {
	Buffer<String> myBuffer;

	public Notifier(Buffer<String> buff) {
		this.myBuffer = buff;
		start();
	}
	
	public void start() {
		new ThreadClass(myBuffer).start(); 
	}

	private class ThreadClass extends Thread  {
		private Buffer<String> myBuffer;

		public ThreadClass(Buffer<String> buff) {
			this.myBuffer = buff;
		}

		public void run() {
			while (!Thread.interrupted()) {
				try {
					String myString = myBuffer.get();
					setChanged();
					notifyObservers(myString);
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
