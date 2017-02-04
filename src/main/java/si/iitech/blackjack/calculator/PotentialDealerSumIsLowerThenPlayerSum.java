package si.iitech.blackjack.calculator;

import si.iitech.blackjack.Round;

public class PotentialDealerSumIsLowerThenPlayerSum implements IBlackjackCalculator {

	public boolean placeBet(Round round) {
//		int potentialDealerSum = round.getDealerSum() + POTENCIAL_CARD_VALUE;
//		return potentialDealerSum < round.getPlayer1Sum() || 
//				potentialDealerSum < round.getPlayer2Sum() || 
//				potentialDealerSum < round.getPlayer3Sum() || 
//				potentialDealerSum < round.getPlayer4Sum();
		return true;
	}

}
