package testing;

import static org.junit.Assert.*;
import enums.CardSeason;
import enums.CardType;
import game.Game;
import game.Player;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cards.RoyalCard;

public class TestDetermineWinner {

	Game g;
	ArrayList<Player> p4;
	ArrayList<Player> p5;
	ArrayList<Player> p8;

	@Before
	public void setUp() throws Exception {
		g = new Game();
		p4 = new ArrayList<Player>();
		for (int i = 0; i < 4; i++) {
			p4.add(new Player("Player " + i));
		}
		p5 = (ArrayList<Player>) p4.clone();
		p5.add(new Player("Player 5"));
		p8 = (ArrayList<Player>) p5.clone();
		for (int i = 0; i < 3; i++) {
			p8.add(new Player("Player " + (i + 6)));
		}
	}

	/*
	 * 
	 * p[0].setCard(new RoyalCard(CardType., CardSeason.)); p[1].setCard(new
	 * RoyalCard(CardType., CardSeason.)); p[2].setCard(new RoyalCard(CardType.,
	 * CardSeason.)); p[3].setCard(new RoyalCard(CardType., CardSeason.));
	 * p[4].setCard(new RoyalCard(CardType., CardSeason.)); p[5].setCard(new
	 * RoyalCard(CardType., CardSeason.)); p[6].setCard(new RoyalCard(CardType.,
	 * CardSeason.)); p[7].setCard(new RoyalCard(CardType., CardSeason.));
	 */
	
	/*
	 * Tests all seasons with 4 UNTER/OBER/KOENIG
	 */
	@Test
	public void testEquals() {
		int winnerIndex;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				CardType cd = intToType(i);
				p4.get(0).setCard(new RoyalCard(cd, CardSeason.FRUEHLING));
				p4.get(1).setCard(new RoyalCard(cd, CardSeason.HERBST));
				p4.get(2).setCard(new RoyalCard(cd, CardSeason.SOMMER));
				p4.get(3).setCard(new RoyalCard(cd, CardSeason.WINTER));
				g.setPlayers(p4);

				g.setCurrentSeason(intToSeason(j));
				winnerIndex = uidToIndex(p4, g.determineRoundWinner());
				assertEquals(j, winnerIndex);
			}
		}

	}
	
	@Test
	public void testWeliWithEquals(){
		int winnerIndex;
		
		p5.get(0).setCard(new RoyalCard(CardType.WELI, CardSeason.WELI));
		p5.get(1).setCard(new RoyalCard(CardType.KOENIG, CardSeason.HERBST));
		p5.get(2).setCard(new RoyalCard(CardType.KOENIG, CardSeason.SOMMER));
		p5.get(3).setCard(new RoyalCard(CardType.KOENIG, CardSeason.WINTER));
		p5.get(4).setCard(new RoyalCard(CardType.KOENIG, CardSeason.FRUEHLING));
		g.setPlayers(p5);
		
		g.setCurrentSeason(CardSeason.HERBST);
		winnerIndex = uidToIndex(p5, g.determineRoundWinner());
		assertEquals(1, winnerIndex);
		
		g.setCurrentSeason(CardSeason.SOMMER);
		winnerIndex = uidToIndex(p5, g.determineRoundWinner());
		assertEquals(0, winnerIndex);
		
		g.setCurrentSeason(CardSeason.WINTER);
		winnerIndex = uidToIndex(p5, g.determineRoundWinner());
		assertEquals(0, winnerIndex);
		
		g.setCurrentSeason(CardSeason.FRUEHLING);
		winnerIndex = uidToIndex(p5, g.determineRoundWinner());
		assertEquals(0, winnerIndex);
		
		p5.get(1).setCard(new RoyalCard(CardType.OBER, CardSeason.HERBST));
		p5.get(2).setCard(new RoyalCard(CardType.OBER, CardSeason.SOMMER));
		p5.get(3).setCard(new RoyalCard(CardType.OBER, CardSeason.WINTER));
		p5.get(4).setCard(new RoyalCard(CardType.OBER, CardSeason.FRUEHLING));
		g.setPlayers(p5);
		
		g.setCurrentSeason(CardSeason.HERBST);
		winnerIndex = uidToIndex(p5, g.determineRoundWinner());
		assertEquals(1, winnerIndex);

		g.setCurrentSeason(CardSeason.SOMMER);
		winnerIndex = uidToIndex(p5, g.determineRoundWinner());
		assertEquals(0, winnerIndex);
		
		g.setCurrentSeason(CardSeason.WINTER);
		winnerIndex = uidToIndex(p5, g.determineRoundWinner());
		assertEquals(0, winnerIndex);
		
		g.setCurrentSeason(CardSeason.FRUEHLING);
		winnerIndex = uidToIndex(p5, g.determineRoundWinner());
		assertEquals(0, winnerIndex);
		
		//p5.get(0).setCard(new RoyalCard(CardType.WELI, CardSeason.WELI));
		p5.get(1).setCard(new RoyalCard(CardType.UNTER, CardSeason.HERBST));
		p5.get(2).setCard(new RoyalCard(CardType.UNTER, CardSeason.SOMMER));
		p5.get(3).setCard(new RoyalCard(CardType.UNTER, CardSeason.WINTER));
		p5.get(4).setCard(new RoyalCard(CardType.UNTER, CardSeason.FRUEHLING));
		g.setPlayers(p5);
		
		g.setCurrentSeason(CardSeason.HERBST);
		winnerIndex = uidToIndex(p5, g.determineRoundWinner());
		assertEquals(1, winnerIndex);

		g.setCurrentSeason(CardSeason.SOMMER);
		winnerIndex = uidToIndex(p5, g.determineRoundWinner());
		assertEquals(2, winnerIndex);
	
		g.setCurrentSeason(CardSeason.WINTER);
		winnerIndex = uidToIndex(p5, g.determineRoundWinner());
		assertEquals(3, winnerIndex);
	
		g.setCurrentSeason(CardSeason.FRUEHLING);
		winnerIndex = uidToIndex(p5, g.determineRoundWinner());
		assertEquals(4, winnerIndex);	
	}

	@Test
	public void testWeli(){
		int winnerIndex;
		
		p5.get(0).setCard(new RoyalCard(CardType.WELI, CardSeason.WELI));
		p5.get(1).setCard(new RoyalCard(CardType.KOENIG, CardSeason.HERBST));
		p5.get(2).setCard(new RoyalCard(CardType.KOENIG, CardSeason.SOMMER));
		p5.get(3).setCard(new RoyalCard(CardType.KOENIG, CardSeason.WINTER));
		p5.get(4).setCard(new RoyalCard(CardType.KOENIG, CardSeason.FRUEHLING));
		g.setPlayers(p5);
		
		p8.get(0).setCard(new RoyalCard(CardType.WELI, CardSeason.WELI));
		p8.get(1).setCard(new RoyalCard(CardType.UNTER, CardSeason.HERBST));
		p8.get(2).setCard(new RoyalCard(CardType.UNTER, CardSeason.FRUEHLING));
		p8.get(3).setCard(new RoyalCard(CardType.KOENIG, CardSeason.WINTER));
		p8.get(4).setCard(new RoyalCard(CardType.UNTER, CardSeason.SOMMER));
		p8.get(5).setCard(new RoyalCard(CardType.KOENIG, CardSeason.SOMMER));
		p8.get(6).setCard(new RoyalCard(CardType.UNTER, CardSeason.WINTER));
		p8.get(7).setCard(new RoyalCard(CardType.OBER, CardSeason.SOMMER));
		g.setCurrentSeason(CardSeason.HERBST);
		g.setPlayers(p8);
		
		winnerIndex = uidToIndex(p8, g.determineRoundWinner());
		assertEquals(-1, winnerIndex);
	}
	
	private int uidToIndex(ArrayList<Player> list, int uid){
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getUID() == uid){
				return i;
			}
		}
		return -1;
	}
	
	/*
	 * x -> 0 = FFRUEHLING, 1 = HERBST, 2 = SOMMER, 3 = WINTER, other = null
	 * FRUEHLING
	 */
	private CardSeason intToSeason(int x) {
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
			return null;
		}
	}

	/*
	 * x -> 0 = UNTER, 1 = OBER, 2 = KOENIG, 3 = WELI, other = null
	 */
	private CardType intToType(int x) {
		switch (x) {
		case 0:
			return CardType.UNTER;
		case 1:
			return CardType.OBER;
		case 2:
			return CardType.KOENIG;
		case 3:
			return CardType.WELI;
		default:
			return null;
		}
	}

}
