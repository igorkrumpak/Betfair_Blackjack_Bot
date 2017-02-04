package si.iitech.blackjack.calculator;

import si.iitech.blackjack.Round;

public class PotentialDealerSumIsLowerThenPotentialPlayerSum implements IBlackjackCalculator {

	public boolean placeBet(Round round) {
//		int potentialDealerSum = round.getDealerSum() + POTENCIAL_CARD_VALUE;
//		int potentialPlayer1Sum = round.getPlayer1Sum() + POTENCIAL_CARD_VALUE;
//		int potentialPlayer2Sum = round.getPlayer2Sum() + POTENCIAL_CARD_VALUE;
//		int potentialPlayer3Sum = round.getPlayer3Sum() + POTENCIAL_CARD_VALUE;
//		int potentialPlayer4Sum = round.getPlayer4Sum() + POTENCIAL_CARD_VALUE;
//		
//		return ((potentialPlayer1Sum > potentialDealerSum && potentialPlayer1Sum <= 21) ||
//			(potentialPlayer2Sum > potentialDealerSum && potentialPlayer2Sum <= 21) ||
//			(potentialPlayer3Sum > potentialDealerSum && potentialPlayer3Sum <= 21) ||
//			(potentialPlayer4Sum > potentialDealerSum && potentialPlayer4Sum <= 21));
		return true;
	}

}
