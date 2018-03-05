package blackjack;

import java.util.ArrayList;

public class Player {

	private String name;
	private Card card;
	private int size;
	private ArrayList<Card> hand = new <Card>ArrayList();

	public Player(String name) {
		this.name = name;
		size = 0;
	}

	public void receiveCard(Card card) {
		hand.add(card);
		size++;
	}

	public int size() {
		return size;
	}

	public Card getCard(int index) {
		return hand.get(index);
	}

	public String getName() {
		return name;
	}

	public int getTotalValue() {
		int sum=0;
		for (int i = 0; i < hand.size(); i++) {
			sum = sum + hand.get(i).getValue();
		}
		return sum;
	}
}
