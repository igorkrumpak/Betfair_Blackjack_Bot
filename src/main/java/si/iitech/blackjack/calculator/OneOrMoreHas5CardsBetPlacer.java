package si.iitech.blackjack.calculator;

import java.util.List;

import si.iitech.blackjack.entites.Card;
import si.iitech.blackjack.entites.Hand;
import si.iitech.blackjack.entites.Round;
import si.iitech.blackjack.game.Bet;

public class OneOrMoreHas5CardsBetPlacer {

	private Round round;
	private List<Hand> allParticipants;
	private List<Hand> allPlayers;
	private Hand dealer;
	
	public OneOrMoreHas5CardsBetPlacer(Round round) {
		this.round = round;
		this.dealer = round.getDealer();
		this.allParticipants = round.getAllParticipants();
		this.allPlayers = round.getAllPlayers();
	}

	public void process() {
		// Rule 3 - Don't use this method when an Ace is involved in the active
		// players hand or the dealer's hand,
		// Unless player with ACE will stand
		if (isAnyAcePresent())
			return;

		// Rule 1, 2 Only bet when 1 player has a chance of getting 5 cards or
		// more in round
		// TODO: HOW TO COUNT ROUND??
		// For someone to have a chance of getting 5 cards or more in round, he
		// should have a combined card values between 7 and 9
		if (!onePlayerHasAChanceOfGetting5CardsOrMore())
			return;

		placeBet();
		
		//round.getBackOneOrMoreHas5CardsBet();
		round.getLayOneOrMoreHas5CardsBet();
	}
	
	private void placeBet() {
		//First we lay bet
		Bet layOneOrMoreHas5CardsBet = round.getLayOneOrMoreHas5CardsBet();
		if(!layOneOrMoreHas5CardsBet.isPlaced()) {
			layOneOrMoreHas5CardsBet.setPlaced(true);
			return;
		}
		
		//If bet is layed and odds are 2times higher as were at layed bet, then we need to back bet to create a sure bet
		Bet backOneOrMoreHas5CardsBet = round.getBackOneOrMoreHas5CardsBet();
		if(!backOneOrMoreHas5CardsBet.isPlaced() && backOneOrMoreHas5CardsBet.getOdds() >= (layOneOrMoreHas5CardsBet.getOdds() * 2)) {
			backOneOrMoreHas5CardsBet.setPlaced(true);
			return;
		}
	}
	

	private boolean onePlayerHasAChanceOfGetting5CardsOrMore() {
		int count = 0;
		for (Hand player : allParticipants) {
			int handCount = player.getHandCount();
			if (handCount >= 7 && handCount <= 9)
				count++;
		}
		return count == 1;
	}

	private boolean isAnyAcePresent() {
		Card dealerCard = dealer.getCard1();
		if(dealerCard == Card.ACE) return true;
		for (Hand player : allPlayers) {
			if (player.hasAcePresent() && !player.shouldStand(dealerCard))
				return true;
		}
		return false;
	}

}
