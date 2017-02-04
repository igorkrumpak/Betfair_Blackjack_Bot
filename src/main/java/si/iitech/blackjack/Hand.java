package si.iitech.blackjack;

public class Hand {

	private Card card1;
	private Card card2;

	public static Hand newHand(Card card1) {
		return new Hand(card1, null);
	}
	
	public static Hand newHand(Card card1, Card card2) {
		return new Hand(card1, card2);
	}

	private Hand(Card card1, Card card2) {
		this.card1 = card1;
		this.card2 = card2;
	}
	
	public Card getCard1() {
		return card1;
	}
	
	public Card getCard2() {
		return card2;
	}

	@Override
	public String toString() {
		return "Hand [card1=" + card1 + ", card2=" + card2 + "]";
	}
	
	
}
