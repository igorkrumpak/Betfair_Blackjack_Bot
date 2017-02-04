package si.iitech.blackjack.calculator;

import java.util.ArrayList;
import java.util.List;

import si.iitech.blackjack.Round;

public class Calculator {

	private List<IBlackjackCalculator> calculators = new ArrayList<IBlackjackCalculator>(); 
	
	public Calculator() {
		calculators.add(new PlayerHasBlackjack());
		calculators.add(new PotentialDealerSumIsLowerThenPlayerSum());
		calculators.add(new PotentialDealerSumIsLowerThenPotentialPlayerSum());
	}
	
	public void calculateIfPlaceBet(Round round) {
//		if(round.getLayOdds() == 0) return;
//		if(round.getLayOdds() > 8) return;
////		if(round.getDealerSum() < 5) return;
//		for (IBlackjackCalculator calculator : calculators) {
//			boolean placeBet = calculator.placeBet(round);
//			if(placeBet) {
//				round.setBetPlaced(true);
//				System.out.println(round.toString());
//				break;
//			}
//		}
	}
}
