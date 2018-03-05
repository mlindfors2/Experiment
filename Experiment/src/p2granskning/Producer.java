package p2granskning;

import javax.swing.Icon;

/**
 * The producer class is used for taking an prodBuffers information and using it in the ToDo thread.
 * The ToDo thread will also put the Icons from IconProducer and put them in the iconBuffer buffer.
 * @author Daniel Rosdahl
 *
 */
public class Producer{
	private Buffer<IconProducer> prodBuffer;
	private Buffer<Icon> iconBuffer;
	private Thread thread;
	
	/**
	 * Constructor that initializes the 2 types of buffers
	 * @param prodBuffer
	 * @param iconBuffer
	 */

	public Producer(Buffer<IconProducer> prodBuffer, Buffer<Icon> iconBuffer){
		this.prodBuffer = prodBuffer;
		this.iconBuffer = iconBuffer;
	}
	
	/**
	 * Method used for starting the inner class thread ToDo
	 */

	public void start() {
		if(thread==null) {
			thread = new ToDo();
			thread.start();
		}
	}
	
	/**
	 * Inner class that extends Thread
	 * @author Daniel
	 *
	 */

	private class ToDo extends Thread {
		/**
		 * The method run() takes the information from the prodBuffer and puts the sequence of Icons that it got
		 * from prodBuffer into iconBuffer. it also sleeps for a set amount of time given by prodBuffer
		 */
		public void run() {
			while(!Thread.interrupted()) {
				try {
					IconProducer iconProd = prodBuffer.get();
					for(int i = 0; i < iconProd.times(); i++) {
						for(int j = 0; j < iconProd.size(); j++) {
							iconBuffer.put(iconProd.nextIcon());
							Thread.sleep(iconProd.delay());
						}
					}
					break;

				}catch(InterruptedException e) {
					break;
				}
			}
			thread = null;
		}
	}
}
