package si.iitech.blackjack.entites;

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
	
	public int getHandCount() {
		int count = 0;
		if(card1 != null) count = count + card1.getValue();
		if(card2 != null) count = count + card2.getValue();
		return count;
	}

	public boolean compareCard1(Card card) {
		if (this.card1 != null && this.card1 == card)
			return true;
		return false;
	}
	
	public boolean compareCard2(Card card) {
		if (this.card2 != null && this.card2 == card)
			return true;
		return false;
	}
	
	public boolean hasAcePresent() {
		if(this.card2 != null && this.card1 == Card.ACE) return true;
		if(this.card2 != null && this.card1 == Card.ACE) return true;
		return false;
	}
	
	//based on Optimal Player Strategy Table
	public boolean shouldStand(Card dealersCard) {
		int count = getHandCount();
		if(count == 16 && (dealersCard == Card.FOUR || dealersCard == Card.FIVE || dealersCard == Card.SIX)) {
			return true;
		}
		if(count > 16 && count < 22) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Hand [card1=" + card1 + ", card2=" + card2 + "]";
	}

}
