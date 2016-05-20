package decks;

import java.util.ArrayList;
import java.util.Random;

import cards.BaseCard;

public class BaseDeck {

	protected ArrayList<BaseCard> cards;

	public void shuffle(int shuffleAmount) {
		Random r = new Random();
		int x;
		BaseCard temp;
		for (int a = 0; a < shuffleAmount; a++) {
			for (int i = cards.size() - 1; i > 0; i--) {
				x = r.nextInt(i);
				temp = cards.get(x);
				cards.set(x, cards.get(i));
				cards.set(i, temp);
			}
		}
	}

	public String getPrintText() {
		String ret = "";
		for (int i = 0; i < cards.size(); i++) {
			ret += cards.get(i).toString();
		}
		return ret;
	}

	public void printDeck() {
		System.out.println(getPrintText());
	}

	/*
	 * Returns first (index 0) card and removes it from the 'cards'-list
	 */
	public BaseCard drawCard() {
		if (cards.size() > 0) {
			BaseCard ret = cards.get(0);
			cards.remove(0);
			return ret;
		} else {
			return null;
		}
	}
	
	//Needed in child-class
	public void reset(){
		
	}

	public ArrayList<BaseCard> getCards() {
		return cards;
	}

	public void setCards(ArrayList<BaseCard> cards) {
		this.cards = cards;
	}
	
}
