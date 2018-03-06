package pg;

import java.util.LinkedList;

public class ServerController {
	private ChatServer chatServer;
	private UserList userList;

	public ServerController(int port, int numOfWorkers) {
		this.chatServer = new ChatServer(port, numOfWorkers, this);
		userList = new UserList();
	}
	public UserList getUserList() {
		return userList;
	}
	
}
