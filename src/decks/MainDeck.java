package decks;

import java.util.ArrayList;
import java.util.Random;

import cards.BaseCard;
import enums.CardSeason;
import enums.CardType;

public class MainDeck {

	private ArrayList<BaseCard> cards;

	public MainDeck() {
		cards = new ArrayList<BaseCard>();
		this.resetDeck();
	}

	public void resetDeck() {
		cards.clear();
		cards.add(new BaseCard(CardType.UNTER, CardSeason.FRUEHLING));
		cards.add(new BaseCard(CardType.UNTER, CardSeason.HERBST));
		cards.add(new BaseCard(CardType.UNTER, CardSeason.SOMMER));
		cards.add(new BaseCard(CardType.UNTER, CardSeason.WINTER));
		cards.add(new BaseCard(CardType.OBER, CardSeason.FRUEHLING));
		cards.add(new BaseCard(CardType.OBER, CardSeason.HERBST));
		cards.add(new BaseCard(CardType.OBER, CardSeason.SOMMER));
		cards.add(new BaseCard(CardType.OBER, CardSeason.WINTER));
		cards.add(new BaseCard(CardType.KOENIG, CardSeason.FRUEHLING));
		cards.add(new BaseCard(CardType.KOENIG, CardSeason.HERBST));
		cards.add(new BaseCard(CardType.KOENIG, CardSeason.SOMMER));
		cards.add(new BaseCard(CardType.KOENIG, CardSeason.WINTER));
		cards.add(new BaseCard(CardType.WELI, CardSeason.WELI));
	}

	public void shuffle(int shuffleAmount) {
		Random r = new Random();
		int x;
		BaseCard temp;
		for (int a = 0; a < shuffleAmount; a++) {
			for (int i = cards.size() - 1; i > 0; i--) {
				x = r.nextInt(i);
				temp = cards.get(x);
				cards.set(x, cards.get(i));
				cards.set(i, temp);
			}
		}
	}

	public BaseCard drawCard() {
		Random r = new Random();
		int x = r.nextInt(cards.size());
		BaseCard ret = cards.get(x);
		cards.remove(x);
		return ret;
	}

	public void printDeck() {
		for (int i = 0; i < cards.size(); i++) {
			cards.get(i).printCard();
		}
	}

}