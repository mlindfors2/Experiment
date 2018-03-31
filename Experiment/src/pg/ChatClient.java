package pg;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

public class ChatClient {
	private Socket socket;
	private String ip;
	private int port;
	private Connection connection;
	boolean isConnected = false;
	private UserList users;

	public ChatClient(String ip, int port, String clientName) {
		this.ip = ip;
		this.port = port;
		this.connect(clientName);

	}

	public void connect(String clientName) {
		if (connection == null) {
			try {
				socket = new Socket(ip, port);
				connection = new Connection(socket, clientName);
				connection.start();
				
			} catch (IOException e) {
				System.out.println("ChatClient/connect() #4 " + e.getMessage());
			}
		}
	}

//	public void startStressTest() {
//		try {
////			connection.testSendAndReceiveMessage();
//		} catch (ClassNotFoundException e) {
//
//		} catch (IOException e) {
//		}
//	}
//
	// public boolean isConnected() {
	// return isConnected;
	// }

	private class Connection extends Thread {
		private ObjectOutputStream output;
		private ObjectInputStream input;
		private Socket socket;
		private User user;

		public Connection(Socket socket, String clientName) throws IOException {
			this.socket = socket;
			output = new ObjectOutputStream(socket.getOutputStream());
			output.flush();
			input = new ObjectInputStream(socket.getInputStream());
			this.user = new User(clientName, (int) Math.random() * 1000);
		}

	

		public void run() {
			if (!isConnected) {
				try {
					System.out.println("F�rs�ker skicka anv�ndare");
					output.writeObject(user);
					output.flush();
				} catch (IOException e) {
					System.out.println("Connection/run() #5 " + e.getMessage());
				}

				try {
					System.out.println("Klient v�ntar p� svar fr�n server");
					Object response = input.readObject();
					if (response instanceof UserList) {
						users = (UserList) response;
						System.out.println("Anv�ndarlista ankommit fr�n server ");
						// for (int i=0;i<users.getUserList().numberOfUsers();i++)
						// System.out.println("#"+i + " " + users.getUserList().getUser(i).getName());

						isConnected = true;
					}
				} catch (Exception e) {
					System.out.println("Connection/run() #6 " + e.getMessage());
				}
			}
			try {
				socket.close();
			} catch (IOException e) {
				System.out.println("Connection/run() #7 " + e.getMessage());
			}

		}

	}

}
