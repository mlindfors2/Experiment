package choice;

import java.util.Random;

public class Choice {

	private Random rand = new Random();
	private int answer;
	
	public void randomChoice() {
		answer = rand.nextInt(3)+1;
		
		int test = 10 %10;
		
		if (answer ==  0) {
			System.out.println("Emporia");
		}
		else if (answer == 1) {
			System.out.println("Plugga");
		}
		else if (answer == 2) {
			System.out.println("Badkar");
		}
		
		
		else if (answer == 3) {
			System.out.println("Säng");
		}
		
	}
	

	public static void main (String[] args) {
		Choice c = new Choice();
		c.randomChoice();
	}

}
