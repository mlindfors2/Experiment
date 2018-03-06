package pg;

import java.util.LinkedList;

public class UserList implements java.io.Serializable {
	private LinkedList<User> users;
	public UserList() {
		users = new LinkedList<User>();
	}
	public  synchronized int numberOfUsers() {
		return users.size();
	}
	public synchronized  void addUser(User user) {
		users.add(user);
	}

	public synchronized  void removeUser(User user) {
		users.remove(user);
	}
	public synchronized UserList getUserList() {
		return this;
	}
	public synchronized User getUser(int i) {
		return users.get(i);
	}
	
}
