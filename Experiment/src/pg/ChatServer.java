package pg;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.LinkedList;

public class ChatServer {
	ServerController sc;
	// private LinkedList<ClientHandler> clients;
	private int numberOfWorkers;
	private Buffer<Runnable> buffer = new Buffer<Runnable>();
	private LinkedList<Worker> workers;
	

	public ChatServer(int port, int numberOfWorkers, ServerController sc) {
		this.numberOfWorkers = numberOfWorkers;
		this.sc = sc;
		new Connection(port).start();
		start();
	}

	public void start() {
		Worker worker;
		if (workers == null) {
			workers = new LinkedList();
			for (int i = 0; i < numberOfWorkers; i++) {
				worker = new Worker();
				worker.start();
				workers.add(worker);
			}
		}
	}

	public synchronized void execute(Runnable runnable) {
		buffer.put(runnable);
	}

	public synchronized void stop() {
		if (workers != null) {
			buffer.clear();
			for (int i = 0; i < workers.size(); i++) {
				workers.get(i).interrupt();
			}
			workers = null;
		}
	}

	private class Connection extends Thread {
		private int port;
		

		public Connection(int port) {
			this.port = port;
		}

		public void run() {
			Socket socket = null;
			System.out.println("Server startar upp");
			try (ServerSocket serversocket = new ServerSocket(port)) {
				System.out.println("Server startad på port " + serversocket.getLocalPort());
				while (true) {
					socket = serversocket.accept();
					// clients.add(new ClientHandler(socket));
					buffer.put(new ClientHandler(socket));
				}
			} catch (IOException e) {
				System.out.println("Connection/Run() #1" + e.getMessage());
			}
		}
	}

	private class ClientHandler implements Runnable {
		private Socket socket;
		private Object response;
		private User user;
		private Message message;
		// private ObjectOutputStream output;
		// private ObjectInputStream input;

		public ClientHandler(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try (ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
					ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
				try {
					output.flush();
					System.out.println("Server väntar på information från klienter");
					response = input.readObject();
					System.out.println("Server mottagit ett objekt");
				} catch (ClassNotFoundException e) {
				}
				// inloggning av ny användare
				if (response instanceof User) {
					user = (User) response;
					System.out.println(user.getName() + " loggar in på servern ");
					sc.getUserList().addUser(user);
					
					System.out.println("Antal klienter anslutna till servern: " + sc.getUserList().numberOfUsers());
					
					System.out.println("Server försöker skicka tillbaka användarlista till klienten");
					output.writeObject(sc.getUserList());
					output.flush();
				} else if (response instanceof Message) {
					message = (Message) response;
				} else {
					// Något gick helt åt helvete.
				}

			} catch (IOException e) {
				System.out.println("ClientHandler/run() #2" + e.getMessage());
			}

			try {
				socket.close();
			} catch (IOException e) {
				System.out.println("ClientHandler/run() #3 " + e.getMessage());
			}

		}
	}

	private class Worker extends Thread {
		public void run() {
			while (!Thread.interrupted()) {
				try {
					buffer.get().run();
				} catch (Exception e) {
					System.out.println("Worker/run() #8 " + e.getMessage());
					break;
				}
			}
		}
	}

	private class Buffer<T> {
		private LinkedList<T> buffer = new LinkedList<T>();

		public synchronized void put(T obj) {
			buffer.addLast(obj);
			notifyAll();
		}

		public synchronized T get() throws InterruptedException {
			while (buffer.isEmpty()) {
				wait();
			}
			return buffer.removeFirst();
		}

		public synchronized void clear() {
			buffer.clear();
		}

		public synchronized int size() {
			return buffer.size();
		}
	}

}
