package blackjack;

public class Card {

	private int card;
	private int color;

	public Card(int card, int color) {
		this.card = card;
		this.color = color;
	}

	public Card getCard() {
		return this;
	}

	public int getValue() {
		return card;
	}

	public int getColor() {
		return color;
	}

	public String toString() {
		String res;
		if (color == 0) {
			res = "Spader"; 
		}
		else if (color == 1) {
			res = "Klöver"; 
		}
		else if (color == 2) {
			res = "Ruter"; 
		}
		else {
				res = "Hjärter";
		}
		return card + " " + res;
		
		
		 
	}
}
