package p2granskning;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;

import javax.swing.Icon;

/**
 * IconClient is a client used for receiving Icon objects from the server
 * @author Daniel
 *
 */
public class IconClient{ 
	private Socket socket;
	private ObjectInputStream ois;
	private LinkedList<IconClientInterface> list = new LinkedList<IconClientInterface>();

	/**
	 * Constructor that establishes the connection to the server and starts the Listener thread
	 * @param ip the ip of the server to connect to
	 * @param port the port of the server to connect to
	 */
	public IconClient(String ip, int port){
		try{
			socket = new Socket(ip, port);
			ois = new ObjectInputStream(socket.getInputStream());
			new Listener().start();
		} catch(IOException e){}
	}

	/**
	 * used for callback to the P2Viewer
	 * @param listener
	 */
	public void addIconClientInterface(IconClientInterface listener){
		list.add(listener);
	}

	/**
	 * Thread used for reading the Icon objects the server sent and for callback with P2Viewer
	 * @author Daniel
	 *
	 */
	private class Listener extends Thread{
		public void run(){
			Icon icon;
			try{
				while(true){
					try{
						icon = (Icon)ois.readObject();
						for(IconClientInterface ici: list) {
							ici.showIcon(icon);
						}
					}catch (ClassNotFoundException e){}
				}
			} catch(Exception e){}
			try{
				socket.close();
			} catch(Exception e){}
		}
	}
}
