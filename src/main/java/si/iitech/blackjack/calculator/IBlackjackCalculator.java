package si.iitech.blackjack.calculator;

import si.iitech.blackjack.Round;

public interface IBlackjackCalculator {
	
	public static final int POTENCIAL_CARD_VALUE = 10;

	public boolean placeBet(Round round);
}
