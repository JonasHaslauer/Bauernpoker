package game;

import java.util.ArrayList;

import cards.NumberCard;
import cards.SeasonCard;
import decks.FullDeck;
import enums.CardSeason;

public class Game {
	private final int MAXPLAYERS = 13;
	private int roundCounter, currentPot;
	private CardSeason currentSeason;
	private SeasonCard currentSeasonCard;
	private FullDeck deck;
	private ArrayList<Player> players;

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

		deck = new FullDeck();
	}

	public void startGame() {
		startRound();
	}

	private void startRound() {
		/************* RESETTING ROUND *************/
		// Reset cards
		deck.reset();
		deck.shuffle(100);

		// Reset pot
		currentPot = 0;

		// Give all players cards (removes them from the deck in draw functions)
		for (int i = 0; i < players.size(); i++) {
			players.get(i).setCard(deck.drawRoyal());
		}

		// Set Season for this round
		currentSeasonCard = deck.drawSeason();
		currentSeason = currentSeasonCard.getSeason();

		/*
		 * TODO: remove debugging
		 */
		this.debugDisplayCards();

		/*
		 * TODO: Let players set their bet-amount, add to pot
		 */

		/************* DECIDE ROUND *************/
		//stechen wird wenn nötig in determineRoundWinner aufgerufen
		int uid_winner = this.determineRoundWinner();
		for (int i = 0; i < players.size(); i++) {
			if (uid_winner == players.get(i).getUID()) {
				System.out.println("Es hat " + players.get(i).getName()
						+ " gewonnen!");
			}
		}
	}

	/*
	 * Returns UID of player that won the round Errorcodes: -1 = no winner
	 * detected
	 */
	/*
	 * private int determineRoundWinner() { ArrayList<Player> fightingPlayers =
	 * new ArrayList<Player>(); Player buffer; boolean kingBreakerIsWinner =
	 * false; int kingBreakerIndex = -1;
	 * 
	 * for (int i = 0; i < players.size(); i++) {
	 * fightingPlayers.add(players.get(i)); }
	 * 
	 * while (fightingPlayers.size() > 1) { for (int i = 0; i <
	 * fightingPlayers.size() - 1; i++) { kingBreakerIsWinner = true; for (int j
	 * = 0; j < fightingPlayers.size() - 1; j++) { if
	 * (fightingPlayers.get(i).getUID() != fightingPlayers.get( j + 1).getUID())
	 * { fightingPlayers .get(i) .getCard() .isStronger( fightingPlayers.get(j +
	 * 1).getCard(), currentSeason.getSeason()); switch
	 * (fightingPlayers.get(i).getCard().getDuelState()) { case WELIBRECHER:
	 * return fightingPlayers.get(i).getUID(); case KOENIGSBRECHER:
	 * kingBreakerIndex = i; for (int k = 0; k < fightingPlayers.size(); k++) {
	 * if (k != i) { RoyalCard temp; if ((temp = fightingPlayers.get(k)
	 * .getCard()).getType() == CardType.KOENIG && temp.getDuelState() ==
	 * DuelState.UNGEBROCHEN) { kingBreakerIsWinner = false; } } } case
	 * GEBROCHEN: fightingPlayers.remove(i); break; } } } if
	 * (kingBreakerIsWinner && kingBreakerIndex != -1) { return
	 * fightingPlayers.get(kingBreakerIndex).getUID(); } debugDisplayCards();
	 * System.out.print(fightingPlayers.get(i).getName() + " (UID " +
	 * fightingPlayers.get(i).getUID() + ") - " +
	 * fightingPlayers.get(i).getCard().toString()); switch
	 * (fightingPlayers.get(i).getCard().getDuelState()) { case UNGEBROCHEN:
	 * System.out.println(" - UNGEBROCHEN"); break; case GEBROCHEN:
	 * System.out.println(" - GEBROCHEN"); break; case WELIBRECHER:
	 * System.out.println(" - WELIBRECHER"); break; case KOENIGSBRECHER:
	 * System.out.println(" - KOENIGSBRECHER"); break; case GLEICHWERTIG:
	 * System.out.println(" - GLEICHWERTIG"); break; } } } return 0; }
	 */

	private int determineRoundWinner() {
		ArrayList<Player> koenige = new ArrayList<Player>(), ober = new ArrayList<Player>(), unter = new ArrayList<Player>();
		boolean weliIsInGame = false, koenigIsInGame = false, oberIsInGame = false, unterIsInGame = false;
		int index = -1, weliIndex = -1;

		for (int i = 0; i < players.size(); i++) {
			switch (players.get(i).getCard().getType()) {
			case WELI:
				weliIsInGame = true;
				weliIndex = i;
				break;
			case KOENIG:
				koenigIsInGame = true;
				koenige.add(players.get(i));
				break;
			case OBER:
				oberIsInGame = true;
				ober.add(players.get(i));
				break;
			case UNTER:
				unterIsInGame = true;
				unter.add(players.get(i));
				break;
			default:
				return -1;
			}
		}

		// Unter (Heimseason, ausg. Herbst) > Weli (ausg. Herbst) >
		// König (Heimseason) > Ober (Heimseason) > König >
		// Ober > Unter (Heimseason) > Unter > Weli (Herbst)

		if (weliIsInGame) {
			// Weli verliert im Herbst, daher muss bevor der Rest geprüft wird,
			// geprüft werden ob Herbst ist
			if (currentSeason == CardSeason.HERBST)
				;
			else if (unterIsInGame) {
				return unter.get(lookForHomeSeason(unter)).getUID();
			} else {
				return players.get(weliIndex).getUID(); // index = WeliIndex
			}
		} else if (koenigIsInGame) {
			// Kein Weli im Spiel -> König zu diesem Zeitpunkt unabhängig
			// stärkste Karte
			if ((index = lookForHomeSeason(koenige)) != -1) {
				return koenige.get(index).getUID();
			} else if (oberIsInGame) {
				if ((index = lookForHomeSeason(ober)) != -1) {
					return ober.get(index).getUID();
				}
			} else {
				if (koenige.size() > 1) {
					if ((index = lookForHomeSeason(koenige)) != -1) {
						return koenige.get(index).getUID();
					} else {
						/*
						 * TODO: Stechen
						 */
						System.out.println("UNENTSCHIEDEN");
					}
				} else {
					return koenige.get(0).getUID();
				}
			}
		} else if (oberIsInGame) {
			if (ober.size() > 1) {
				if ((index = lookForHomeSeason(ober)) != -1) {
					return ober.get(index).getUID();
				} else {
					/*
					 * TODO: Stechen
					 */
					System.out.println("UNENTSCHIEDEN");
				}
			} else {
				return ober.get(0).getUID();
			}
		} else if (unterIsInGame) {
			if (unter.size() > 1) {
				if ((index = lookForHomeSeason(unter)) != -1) {
					return unter.get(index).getUID();
				} else {
					/*
					 * TODO: Stechen
					 */
					System.out.println("UNENTSCHIEDEN");
				}
			} else {
				return unter.get(0).getUID();
			}
		}

		return -1;
	}

	/*
	 * TODO: stechen
	 */
	private int stechen(Player p1, Player p2){
		NumberCard nc_p1, nc_p2;
		
		nc_p1 = deck.drawNumber();
		nc_p2 = deck.drawNumber();
		
		return 0;
	}
	
	public void debugDisplayCards() {
		/*
		 * DEBUG Display Cards
		 */
		System.out.println("Season: " + currentSeasonCard.getSeasonText());
		for (int i = 0; i < players.size(); i++) {
			System.out.print(players.get(i).getName() + " - ");
			System.out.println(players.get(i).getCard().toString());
		}
	}

	/*
	 * Returns index of first card with the season 'currentSeason' Returns -1 if
	 * no card with 'currentSeason' is found
	 */
	private int lookForHomeSeason(ArrayList<Player> a) {
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i).getCard().getSeason() == currentSeason) {
				return i;
			}
		}
		return -1;
	}
}
