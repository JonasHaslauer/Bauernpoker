package cards;

import java.lang.Character.UnicodeScript;

import enums.CardSeason;
import enums.CardType;
import enums.DuelState;

public class BaseCard {

	private final CardType type;
	private final CardSeason season;

	public BaseCard(CardType type, CardSeason season) {
		this.type = type;
		this.season = season;
	}

	public DuelState isStronger(BaseCard other, CardSeason season) {
		boolean isMyHomeSeason = false, isOtherHomeSeason = false;

		if (this.season == season) {
			isMyHomeSeason = true;
		}
		if (other.season == season) {
			isOtherHomeSeason = true;
		}

		/************* WELIHANDLING - Weli in Besitz *************/
		if (this.type == CardType.WELI) {
			// Im Herbst ist Weli wertlos
			if (season == CardSeason.HERBST) {
				return DuelState.WERTLOSER_WELI;
			}
			// Unter mit Heimvorteil schlägt Weli
			else if (other.type == CardType.UNTER && isOtherHomeSeason) {
				return DuelState.WERTLOSER_WELI;
			}
			// Weli wurde nicht gebrochen
			else {
				return DuelState.GEWINNER;
			}
		}

		/************* WELIHANDLING - Weli in Gegnerbesitz *************/
		if (other.type == CardType.WELI) {
			// Im Herbst ist Weli wertlos
			if (season == CardSeason.HERBST) {
				return DuelState.GEWINNER;
			}
			// Unter mit Heimvorteil schlägt Weli
			else if (this.type == CardType.UNTER && isMyHomeSeason) {
				return DuelState.WELIBRECHER;
			}
			// Weli wurde nicht gebrochen
			else {
				return DuelState.VERLIERER;
			}
		}

		/************* KENIGSHANDLING *************/
		if (this.type == CardType.KOENIG) {
			if (isMyHomeSeason) {
				return DuelState.GEWINNER;
			} else if (other.type == CardType.KOENIG) {
				if (isOtherHomeSeason) {
					return DuelState.VERLIERER;
				} else {
					return DuelState.GLEICHWERTIG;
				}
			}
		}

		/************* OBERHANDLING *************/
		if (this.type == CardType.OBER) {
			if (isMyHomeSeason) {
				if (isOtherHomeSeason) {
					if (other.type == CardType.KOENIG) {
						return DuelState.VERLIERER;
					}
				} else {
					return DuelState.GEWINNER;
				}
			}else{
				if(other.type == CardType.KOENIG){
					return DuelState.VERLIERER;
				}else if(other.type == CardType.OBER){
					if(isOtherHomeSeason){
						return DuelState.VERLIERER;
					}else{
						return DuelState.GLEICHWERTIG;
					}
				}else{
					return DuelState.GEWINNER;
				}
			}
		}

		/************* UNTERHANDLING *************/
		if (this.type == CardType.OBER) {
			if (!(other.type == CardType.UNTER)) {
				return DuelState.VERLIERER;
			} else if (isMyHomeSeason) {
				return DuelState.GEWINNER;
			} else {
				return DuelState.GLEICHWERTIG;
			}
		}
		return DuelState.VERLIERER;
	}

	public void printCard(){
		switch (this.getSeason()) {
		case FRUEHLING:
			System.out.print("Frühling - ");
			break;
		case HERBST:
			System.out.print("Herbst - ");
			break;
		case SOMMER:
			System.out.print("Sommer - ");
			break;
		case WINTER:
			System.out.print("Winter - ");
			break;
		case WELI:
			System.out.print("");
			break;
		}

		switch (this.getType()) {
		case UNTER:
			System.out.println("Unter");
			break;
		case OBER:
			System.out.println("Ober");
			break;
		case KOENIG:
			System.out.println("Kenig");
			break;
		case WELI:
			System.out.println("WELI");
			break;
		}
	}

	public CardType getType() {
		return type;
	}

	public CardSeason getSeason() {
		return season;
	}

}
