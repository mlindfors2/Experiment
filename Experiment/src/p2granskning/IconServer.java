package p2granskning;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

//import p1.*;

import javax.swing.Icon;
import javax.swing.SwingUtilities;

/**
 * The IconServer class is a server used for sending Icon objects to all connected clients.
 * @author Daniel
 *
 */
public class IconServer {
	private Icon icon;
	private LinkedList<ClientHandler> list = new LinkedList<ClientHandler>();

	/** 
	 * Constructor that starts the server using the Connection class
	 * @param iconMan an instance of the IconManager class
	 * @param port the port that the server will use
	 */
	public IconServer(IconManager iconMan, int port){
		iconMan.addObserver(new IconManObserver());
		new Connection(port).start();
	}

	/**
	 * 
	 * A thread that handles the clients connecting to the server
	 *
	 */
	private class Connection extends Thread {
		private int port;
		public Connection(int port){
			this.port = port;
		}
		public void run() {
			Socket socket = null;
			System.out.println("Server started");
			try (ServerSocket serverSocket = new ServerSocket(port)){
				while(true){
					try{
						socket = serverSocket.accept();
						new ClientHandler(socket);
					} catch(IOException e){
						System.err.println(e);
						if(socket!=null){
							socket.close();
						}
					}
				}
			} catch(IOException e){
				System.err.println(e);
			}
			System.out.println("server stopped");
		}
	}
	
	/**
	 * 
	 * A private class that handles the different clients and what is sent to them.
	 * The clients are stored in a LinkedList
	 *
	 */
	private class ClientHandler{
		private Socket socket;
		private ObjectOutputStream oos;

		/**
		 * Constructor that creates the outputStreams to the clients and adds the class to the IconServers LinkedList
		 * @param socket
		 * @throws IOException
		 */
		public ClientHandler(Socket socket) throws IOException{
			this.socket = socket;
			oos = new ObjectOutputStream(socket.getOutputStream());
			list.add(this);
		}

		/**
		 * sends the Icon object to the client
		 * @param icon
		 */
		public void send(Icon icon) {
			try{
				oos.writeObject(icon);
				oos.flush();
			}catch(IOException e){}
		}
	}
	
	/**
	 * sends the icon from the parameter to every client in the list
	 * @param icon
	 */
	public void ToSend(Icon icon){
		for(ClientHandler clr: list){
			clr.send(icon);
		}
	}

	/**
	 * class that implements Observer and uses the ToSend method each time an icon is received from IconManager
	 * @author Daniel
	 *
	 */
	private class IconManObserver implements Observer {
		public void update(Observable o, Object arg) {
			Icon icon = (Icon) arg;
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					ToSend(icon);
				}
			});
		}
	}
}


