package game;

import cards.RoyalCard;

public class Player {
	private String name;
	private int currency;
	private RoyalCard card;
	private static int idCtr = 0;
	private final int UID;
	
	public Player(String name){
		this.name = name;
		currency = 0;
		UID = idCtr;
		idCtr++;
	}
	
	public void setCard(RoyalCard card){
		this.card = card;
	}

	public RoyalCard getCard() {
		return card;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCurrency() {
		return currency;
	}

	public void setCurrency(int currency) {
		this.currency = currency;
	}

	public static int getIdCtr() {
		return idCtr;
	}

	public static void setIdCtr(int idCtr) {
		Player.idCtr = idCtr;
	}

	public int getUID() {
		return UID;
	}
	
	
}
