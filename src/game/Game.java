package game;

import java.util.ArrayList;
import java.util.Random;

import decks.MainDeck;
import enums.CardSeason;
import enums.CardType;
import enums.DuelState;

public class Game {
	final int MAXPLAYERS = 13;
	int roundCounter, currentPot;
	CardSeason currentSeason;
	MainDeck deck;
	ArrayList<Player> players;

	public Game() {
		players = new ArrayList<Player>();

		roundCounter = 0;

		players.add(new Player("Player 1"));
		players.add(new Player("Player 2"));
		players.add(new Player("Player 3"));
		players.add(new Player("Player 4"));
		players.add(new Player("Player 5"));
		players.add(new Player("Player 6"));
		players.add(new Player("Player 7"));
		players.add(new Player("Player 8"));

		deck = new MainDeck();
	}

	public void startGame() {
		startRound();
	}

	private void startRound() {
		/************* RESETTING ROUND *************/

		// Reset pot
		currentPot = 0;

		// Set Season for this round
		currentSeason = rollSeason();
		switch (currentSeason) {
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
		}

		// Give all players cards (removes them from the deck in drawCard
		// function)
		// Reset DuelStates of all Players
		for (int i = 0; i < players.size(); i++) {
			players.get(i).setCard(deck.drawCard());
			players.get(i).setCurrentRoundState(DuelState.GLEICHWERTIG);
		}

		/*
		 * DEBUG Display Cards
		 */
		for (int i = 0; i < players.size(); i++) {
			System.out.print(players.get(i).getName() + " - ");
			players.get(i).getCard().printCard();
		}

		/*
		 * TODO: Let players set their bet-amount, add to pot
		 */

		/************* DECIDE ROUND *************/
		System.out.println("Winner of the Round is: "
				+ players.get(determineRoundWinner()).getName());

	}

	/*
	 * Returns UID of player that won the round Errorcodes: -1 = no winner
	 * detected
	 */
	private int determineRoundWinner() {
		ArrayList<Player> fightingPlayers = new ArrayList<Player>();
		boolean done = false;

		for (int i = 0; i < players.size(); i++) {
			fightingPlayers.add(players.get(i));
		}

		// while(!done){
		for (int i = 0; i < fightingPlayers.size() - 1; i++) {
			/*
			 * fightingPlayers.get(i).setCurrentRoundState(fightingPlayers.get(i)
			 * .getCard().isStronger(fightingPlayers.get(i+1).getCard(),
			 * currentSeason)); if(fightingPlayers.get(i).getCurrentRoundState()
			 * == DuelState.WELIBRECHER){ return
			 * fightingPlayers.get(i).getUID(); }
			 */
			switch (fightingPlayers
					.get(i)
					.getCard()
					.isStronger(fightingPlayers.get(i + 1).getCard(),
							currentSeason)) {
			case WERTLOSER_WELI:
				break;
			case WELIBRECHER:
				break;
			case GEWINNER:
				break;
			case VERLIERER:
				break;
			case GLEICHWERTIG:
				break;
			}
		}
		// }

		return -1;
	}

	private CardSeason rollSeason() {
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
		}
		return CardSeason.FRUEHLING;
	}
}
