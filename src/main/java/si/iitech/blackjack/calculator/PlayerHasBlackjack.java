package si.iitech.blackjack.calculator;

import si.iitech.blackjack.Round;

public class PlayerHasBlackjack implements IBlackjackCalculator {

	public boolean placeBet(Round round) {
//		int potentialDealerSum = round.getDealerSum() + POTENCIAL_CARD_VALUE;
//		return (round.getPlayer1Sum() == 21 || 
//				round.getPlayer2Sum() == 21 ||
//				round.getPlayer3Sum() == 21 ||
//				round.getPlayer4Sum() == 21) &&
//				(potentialDealerSum < 20);
		return true;
	}

}
