package p1granskning;

import java.util.Observable;

import javax.swing.Icon;

public class IconManager extends Observable {
	private Buffer<Icon> iconBuffer;
	private Thread thread;

	public IconManager(Buffer<Icon> iconBuffer) {
		this.iconBuffer = iconBuffer;
	}

	public void start() {
		if (thread == null) {
			thread = new Inner();
			thread.start();
		}
	}

	private class Inner extends Thread {
		public void run() {
			Icon icon;

			while (!Thread.interrupted()) {
				try {
					icon = iconBuffer.get();
					setChanged();
					notifyObservers(icon);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
			thread = null;
		}
	}
}