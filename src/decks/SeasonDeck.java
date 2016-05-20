package decks;

import java.util.ArrayList;

import cards.BaseCard;
import cards.SeasonCard;
import enums.CardSeason;

public class SeasonDeck extends BaseDeck {

	public SeasonDeck() {
		cards = new ArrayList<BaseCard>();
		this.reset();
	}

	public void reset() {		
		cards.clear();
		cards.add(new SeasonCard(CardSeason.SOMMER));
		cards.add(new SeasonCard(CardSeason.FRUEHLING));
		cards.add(new SeasonCard(CardSeason.HERBST));
		cards.add(new SeasonCard(CardSeason.WINTER));
	}
	
	public SeasonCard draw(){
		return (SeasonCard) this.drawCard();
	}
}
