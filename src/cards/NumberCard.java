package cards;

import enums.CardSeason;
import enums.CardType;

public class NumberCard extends BaseCard{

	private final int VALUE;
	
	public NumberCard(int wert, CardSeason season) {
		super(CardType.ZAHL, season);
		VALUE = wert;
	}

	
	
}
