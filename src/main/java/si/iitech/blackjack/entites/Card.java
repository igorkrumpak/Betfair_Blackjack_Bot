package si.iitech.blackjack.entites;

public enum Card {
	ONE("1", 1), TWO("2", 2), THREE("3", 3), FOUR("4", 4), FIVE("5", 5), SIX("6", 6), SEVEN("7", 7), EIGHT("8",
			8), NINE("9", 9), TEN("10", 10), JACK("J", 10), QUEEN("Q", 10), KING("K", 10), ACE("A", 10, 1);

	private String sign;
	private Integer value;
	private Integer secondaryValue;

	private Card(String sign, Integer value) {
		this(sign, value, null);
	}

	private Card(String sign, Integer value, Integer secondaryValue) {
		this.sign = sign;
		this.value = value;
		this.secondaryValue = secondaryValue;
	}

	public String getSign() {
		return sign;
	}

	public Integer getValue() {
		return value;
	}

	public Integer getSecondaryValue() {
		return secondaryValue;
	}

	public static Card fromSign(String sign) {
		if (sign != null) {
			for (Card card : Card.values()) {
				if (card.getSign().contentEquals(sign)) {
					return card;
				}
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return sign;
	}
}
