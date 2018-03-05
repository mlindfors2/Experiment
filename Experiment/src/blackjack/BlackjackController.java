package blackjack;

import javax.swing.JOptionPane;

public class BlackjackController {
	
	
	private Deck deck;
	private int cardsindeck;
	private int playerCards;
	private int computerCards;
	private Player humanPlayer;
	private Player computerPlayer;
	
	public BlackjackController () {
		initilizeGame();
		startGame();
		
		
		//Spelet startar!
		System.out.println("Välkommen till JackBlack!");
	}
	private void initilizeGame() {
		deck = new Deck();
		humanPlayer = new Player("Mikael");
		computerPlayer = new Player("Robo-jackblack");
		cardsindeck = 52;
		playerCards = 0;
		computerCards = 0 ;
		
	}
	
	private void PrintPlayerCards(boolean dealer) {
		if(dealer) {
			System.out.println(computerPlayer.getName() +" visar: " + computerPlayer.getCard(0));
		}
		else {
			System.out.println(humanPlayer.getName()+ " visar: " + humanPlayer.getCard(0) + " och " + humanPlayer.getCard(1));
		}
	}
	
	public void startGame() {
		while ( humanPlayer.size() < 2) {
			humanPlayer.receiveCard(deck.drawCard());
			computerPlayer.receiveCard(deck.drawCard());
		}
		PrintPlayerCards(false);
		PrintPlayerCards(true);
		int choice; 
		if ( humanPlayer.getCard(0).getValue() == humanPlayer.getCard(1).getValue()) {
			choice = Integer.parseInt(JOptionPane.showInputDialog("Tryck : \n 1. Dra kort \n 3. Split \n 2. Stanna"));
		}
		else if ( (humanPlayer.getCard(0).getValue() + humanPlayer.getCard(1).getValue()) >7 && (humanPlayer.getCard(0).getValue() + humanPlayer.getCard(1).getValue()<13 )) {
			choice = Integer.parseInt(JOptionPane.showInputDialog("Tryck : \n 1. Dra kort \n 4. Dubbla \n 2. Stanna"));
		}
		else {
			choice = Integer.parseInt(JOptionPane.showInputDialog("Tryck : \n 1. Dra kort \n 2.Stanna"));
		}
		
		if (choice == 1) {
			humanPlayer.receiveCard(deck.drawCard());
		
		}
		if (choice == 2) {
			System.out.println(humanPlayer.getName()+ " visar: " + humanPlayer.getCard(0) + " och " + humanPlayer.getCard(1) + " och blir totalt: "+ humanPlayer.getTotalValue());
			System.out.println(computerPlayer.getName()+  " visar: " + computerPlayer.getCard(0) + " och " + computerPlayer.getCard(1) + " och blir totalt: "+ computerPlayer.getTotalValue());
		}
		if (choice == 3) {
			humanPlayer.receiveCard(deck.drawCard());
			humanPlayer.receiveCard(deck.drawCard());
			System.out.println(humanPlayer.getName()+ " visar: " +humanPlayer.getCard(2) +" och " + humanPlayer.getCard(3));
		}
	}
	
	public static void main (String[] args) {
		BlackjackController bj = new BlackjackController();
	}
}
