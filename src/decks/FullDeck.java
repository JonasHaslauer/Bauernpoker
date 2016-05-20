package decks;

import java.util.ArrayList;

import cards.BaseCard;
import cards.NumberCard;
import cards.RoyalCard;
import cards.SeasonCard;

public class FullDeck extends BaseDeck{
	
	private RoyalDeck rd;
	private SeasonDeck sd;
	private NumberDeck nd;
	
	public FullDeck(){
		cards = new ArrayList<BaseCard>();
		
		rd = new RoyalDeck();
		for (int i = 0; i < rd.getCards().size(); i++) {
			cards.add(rd.getCards().get(i));
		}
		
		sd = new SeasonDeck();
		for (int i = 0; i < sd.getCards().size(); i++) {
			cards.add(sd.getCards().get(i));
		}
		
		nd = new NumberDeck();
		for (int i = 0; i < nd.getCards().size(); i++) {
			cards.add(nd.getCards().get(i));
		}	
	}
	
	/*
	 * Returns the position of card 'other'
	 * Returns -1 if not found
	 */
	public int findCard(BaseCard other){
		for (int i = 0; i < cards.size(); i++) {
			if(cards.get(i).getSeason() == other.getSeason() && cards.get(i).getType() == other.getType())
				return i;
		}
		return -1;
	}
	
	public RoyalCard drawRoyal(){
		RoyalCard ret = rd.draw();
		cards.remove(findCard(ret));
		return ret;
	}
	
	public SeasonCard drawSeason(){
		SeasonCard ret = sd.draw();
		cards.remove(findCard(ret));
		return ret;
	}
	
	public NumberCard drawNumber(){
		NumberCard ret = nd.draw();
		cards.remove(findCard(ret));
		return ret;
	}
	
	public void shuffle(int shuffleAmount){
		super.shuffle(shuffleAmount);
		rd.shuffle(shuffleAmount);
		sd.shuffle(shuffleAmount);
		nd.shuffle(shuffleAmount);
	}
	
	
}
