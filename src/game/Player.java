package game;

import cards.BaseCard;
import enums.DuelState;

public class Player {
	private String name;
	private int currency;
	private BaseCard card;
	private DuelState currentRoundState;
	private static int idCtr = 0;
	private final int UID;
	
	public Player(String name){
		this.name = name;
		currency = 0;
		UID = idCtr;
	}
	
	public void setCard(BaseCard card){
		this.card = card;
	}

	public BaseCard getCard() {
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

	public DuelState getCurrentRoundState() {
		return currentRoundState;
	}

	public void setCurrentRoundState(DuelState currentRoundState) {
		this.currentRoundState = currentRoundState;
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
