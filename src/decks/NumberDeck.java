package decks;

import java.util.ArrayList;

import cards.BaseCard;
import cards.NumberCard;
import enums.CardSeason;

public class NumberDeck extends BaseDeck{

	public NumberDeck(){
		cards = new ArrayList<BaseCard>();
		this.reset();
	}
	
	public void reset(){
		for (int i = 6; i <= 10; i++) {
			cards.add(new NumberCard(i, CardSeason.FRUEHLING));
			cards.add(new NumberCard(i, CardSeason.HERBST));
			cards.add(new NumberCard(i, CardSeason.SOMMER));
			cards.add(new NumberCard(i, CardSeason.WINTER));
		}	
	}
	
	public NumberCard draw(){
		return (NumberCard) this.drawCard();
	}
	
}
