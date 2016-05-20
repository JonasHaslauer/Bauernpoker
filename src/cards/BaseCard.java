package cards;

import enums.CardSeason;
import enums.CardType;

public class BaseCard {

	protected final CardType type;
	protected final CardSeason season;

	public BaseCard(CardType type, CardSeason season) {
		this.type = type;
		this.season = season;
	}
	
	public String toString(){
		return this.getSeasonText() + " - " + this.getTypeText();
	}
	
	public void print(){
		System.out.println(this.toString());
	}

	public String getSeasonText(){
		switch (this.getSeason()) {
		case FRUEHLING:
			return "Frühling";
		case HERBST:
			return"Herbst";
		case SOMMER:
			return "Sommer";
		case WINTER:
			return "Winter";
		}
		return "";
	}
	
	public String getTypeText(){
		switch (this.getType()) {
		case UNTER:
			return "Unter";
		case OBER:
			return "Ober";
		case KOENIG:
			return "Kenig";
		case WELI:
			return "WELI";
		}
		return "";
	}
	
	public CardSeason getSeason() {
		return season;
	}

	public CardType getType() {
		return type;
	}
	
	
	
}
