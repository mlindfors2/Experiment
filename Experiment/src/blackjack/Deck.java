package blackjack;

import java.util.ArrayList;
import java.util.Collections;


public class Deck {
	ArrayList<Card> list = new <Card>ArrayList();

	
	
	public Deck() {
		makeDeck();
	}


	public Card drawCard() {
		if(!list.isEmpty()){
			return list.remove(0);
		}
		System.out.println("Slut på kort");
		return null;
	}

	public void makeDeck() {
		for (int i=1;i<=13;i++) {
			for (int color=0;color<4;color++) {
				list.add(makeCard(i,color));
			}
		}
		Collections.shuffle(list);
	}
	
	public Card makeCard(int cardnbr, int color) {
		Card card = new Card(cardnbr,color);
		return card.getCard();
	}
}
