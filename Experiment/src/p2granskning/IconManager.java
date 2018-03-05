package p2granskning;
 
import java.util.Observable;
import javax.swing.Icon;

public class IconManager extends Observable{
	private Buffer<Icon> buffer;
	private ToDo thread;
	
	public IconManager(Buffer<Icon> buffer) {
		this.buffer = buffer;
	}
	
	public void start() {
		if(thread==null) {
			thread = new ToDo();
			thread.start();
		}
	}
	
	private class ToDo extends Thread{
		public void run(){
			while(!Thread.interrupted()){
				try{
					Icon icon = buffer.get();
					setChanged();
					notifyObservers(icon);
				} catch (InterruptedException e){
					break;
				}
			}
		}
	}
}
