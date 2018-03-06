package pg;

public class Mainpg {
	public static void main(String[] args) {
//		ChatServer cs = new ChatServer(5000,10);
		ServerController sc = new ServerController(5000,500);
		
		for (int i = 0; i<5000 ; i ++) {
			String name = "Pelle" + i;
			new ChatClient("127.0.0.1",5000,name);
		}
		for(int i=0;i<sc.getUserList().numberOfUsers();i++) {
		
			System.out.println(sc.getUserList().getUser(i).getName());
		
		}
	}
}
  