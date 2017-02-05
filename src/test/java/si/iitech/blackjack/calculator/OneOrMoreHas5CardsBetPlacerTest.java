package si.iitech.blackjack.calculator;

import org.junit.Assert;
import org.junit.Test;

import si.iitech.blackjack.entites.Card;
import si.iitech.blackjack.entites.Hand;
import si.iitech.blackjack.entites.Round;
import si.iitech.blackjack.game.Bet;

public class OneOrMoreHas5CardsBetPlacerTest {

	@Test
	public void testOneOrMoreHas5CardsBet() {
		Round round = new Round();
		round.setDealer(Hand.newHand(Card.TEN));
		round.setPlayer1(Hand.newHand(Card.SEVEN, Card.NINE));
		round.setPlayer2(Hand.newHand(Card.ACE, Card.NINE));
		round.setPlayer3(Hand.newHand(Card.FOUR, Card.THREE));
		round.setPlayer4(Hand.newHand(Card.TEN, Card.TEN));

		Bet backOneOrMoreHas5CardsBet = Bet.newBet(7, false);
		Bet layOneOrMoreHas5CardsBet = Bet.newBet(7, false);

		round.setBackOneOrMoreHas5CardsBet(backOneOrMoreHas5CardsBet);
		round.setLayOneOrMoreHas5CardsBet(layOneOrMoreHas5CardsBet);

		OneOrMoreHas5CardsBetPlacer placer = new OneOrMoreHas5CardsBetPlacer(round);
		placer.process();

		// Bet is layed at 7, we lay 2 euro, possible gain is 2 euro, possible
		// lose is 12 euro 
		//+2 -12
		Assert.assertTrue(round.getLayOneOrMoreHas5CardsBet().isPlaced());

		// Bet is backed at 14, we back 1 euro, possible gain is 14 euro,
		// possible lose is 1 euro 
		//-1 +14
		backOneOrMoreHas5CardsBet = Bet.newBet(14, false);
		round.getBackOneOrMoreHas5CardsBet().update(backOneOrMoreHas5CardsBet);

		placer = new OneOrMoreHas5CardsBetPlacer(round);
		placer.process();

		Assert.assertTrue(round.getBackOneOrMoreHas5CardsBet().isPlaced());
	}
}
