package cards;

import java.util.Random;

import enums.CardSeason;
import enums.CardType;

public class SeasonCard extends BaseCard {
	
	public SeasonCard(CardSeason season) {
		super(CardType.SEASON, season);
	}

	public void print() {
		switch (season) {
		case FRUEHLING:
			System.out.println("Frühling");
			break;
		case HERBST:
			System.out.println("Herbst");
			break;
		case SOMMER:
			System.out.println("Sommer");
			break;
		case WINTER:
			System.out.println("Winter");
			break;
		case WELI:
			System.out.println("Weli");
			break;
		}
	}

	public CardSeason roll() {
		Random r = new Random();
		int x = r.nextInt(4);

		switch (x) {
		case 0:
			return CardSeason.FRUEHLING;
		case 1:
			return CardSeason.HERBST;
		case 2:
			return CardSeason.SOMMER;
		case 3:
			return CardSeason.WINTER;
		default:
			return CardSeason.FRUEHLING;
		}
	}

}
