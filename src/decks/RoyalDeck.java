package decks;

import java.util.ArrayList;

import cards.BaseCard;
import cards.RoyalCard;
import cards.SeasonCard;
import enums.CardSeason;
import enums.CardType;

public class RoyalDeck extends BaseDeck {

	public RoyalDeck() {
		cards = new ArrayList<BaseCard>();
		this.reset();
	}

	public void reset() {
		cards.clear();
		cards.add(new RoyalCard(CardType.UNTER, CardSeason.FRUEHLING));
		cards.add(new RoyalCard(CardType.UNTER, CardSeason.HERBST));
		cards.add(new RoyalCard(CardType.UNTER, CardSeason.SOMMER));
		cards.add(new RoyalCard(CardType.UNTER, CardSeason.WINTER));
		cards.add(new RoyalCard(CardType.OBER, CardSeason.FRUEHLING));
		cards.add(new RoyalCard(CardType.OBER, CardSeason.HERBST));
		cards.add(new RoyalCard(CardType.OBER, CardSeason.SOMMER));
		cards.add(new RoyalCard(CardType.OBER, CardSeason.WINTER));
		cards.add(new RoyalCard(CardType.KOENIG, CardSeason.FRUEHLING));
		cards.add(new RoyalCard(CardType.KOENIG, CardSeason.HERBST));
		cards.add(new RoyalCard(CardType.KOENIG, CardSeason.SOMMER));
		cards.add(new RoyalCard(CardType.KOENIG, CardSeason.WINTER));
		cards.add(new RoyalCard(CardType.WELI, CardSeason.WELI));
	}

	public RoyalCard draw(){
		return (RoyalCard) this.drawCard();
	}

}