package pg;

import java.util.LinkedList;

public class Mainpg {
	
	static LinkedList<ChatClient> list = new LinkedList<ChatClient>();//Används för stresstester med meddelande
	
	public static void main(String[] args) {
//		ChatServer cs = new ChatServer(5000,10);
		ServerController sc = new ServerController(5000,50);
		
		for (int i = 0; i<500 ; i ++) {
			String name = "Pelle" + i;
			list.add(new ChatClient("127.0.0.1",5000,name));
		}
		for(int i=0;i<sc.getUserList().numberOfUsers();i++) {
			System.out.println(sc.getUserList().getUser(i).getName()); //Printar ut alla användare inloggad på servern
		
		}
		System.out.println("Alla klienter inloggade");
		for(int i=0;i<sc.getUserList().numberOfUsers();i++) {
			list.get(i).startStressTest();
		}
		
	}
}
  