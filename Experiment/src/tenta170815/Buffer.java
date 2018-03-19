package tenta170815;

import java.util.LinkedList;

public class Buffer<T> {
	private LinkedList<T> buffer = new LinkedList<T>();

	public synchronized void put(T elem) {
		buffer.addLast(elem);
		notifyAll();
	}

	public synchronized T get() throws InterruptedException {
		while (buffer.size() == 0) {
			wait();
		}
		return buffer.removeFirst();
	}
}