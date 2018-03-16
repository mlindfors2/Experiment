package pg;

import java.util.ArrayList;


public class UserList implements java.io.Serializable {
	private ArrayList<User> users;
	public UserList() {
		users = new ArrayList<User>();
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
