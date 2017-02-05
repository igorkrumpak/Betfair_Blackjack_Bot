package si.iitech.blackjack.entites;

import java.util.ArrayList;
import java.util.List;

import si.iitech.blackjack.game.Bet;

public class Round {

	private Integer gameId;
	private Hand dealer;
	private Hand player1;
	private Hand player2;
	private Hand player3;
	private Hand player4;

	private Bet backDealerWinsOrTiesAllBet;
	private Bet layDealerWinsOrTiesAllBet;
	private Bet backOneOrMoreHas5CardsBet;
	private Bet layOneOrMoreHas5CardsBet;

	private boolean roundEnded;

	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public Hand getDealer() {
		return dealer;
	}

	public void setDealer(Hand dealer) {
		this.dealer = dealer;
	}

	public Hand getPlayer1() {
		return player1;
	}

	public void setPlayer1(Hand player1) {
		this.player1 = player1;
	}

	public Hand getPlayer2() {
		return player2;
	}

	public void setPlayer2(Hand player2) {
		this.player2 = player2;
	}

	public Hand getPlayer3() {
		return player3;
	}

	public void setPlayer3(Hand player3) {
		this.player3 = player3;
	}

	public Hand getPlayer4() {
		return player4;
	}

	public void setPlayer4(Hand player4) {
		this.player4 = player4;
	}

	public List<Hand> getAllParticipants() {
		List<Hand> allPlayers = getAllPlayers();
		allPlayers.add(dealer);
		return allPlayers;
	}

	public List<Hand> getAllPlayers() {
		List<Hand> players = new ArrayList<Hand>();
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		return players;
	}

	public boolean isRoundEnded() {
		return roundEnded;
	}

	public void setRoundEnded(boolean roundEnded) {
		this.roundEnded = roundEnded;
	}

	public Bet getBackDealerWinsOrTiesAllBet() {
		return backDealerWinsOrTiesAllBet;
	}

	public void setBackDealerWinsOrTiesAllBet(Bet backDealerWinsOrTiesAllBet) {
		this.backDealerWinsOrTiesAllBet = backDealerWinsOrTiesAllBet;
	}

	public Bet getLayDealerWinsOrTiesAllBet() {
		return layDealerWinsOrTiesAllBet;
	}

	public void setLayDealerWinsOrTiesAllBet(Bet layDealerWinsOrTiesAllBet) {
		this.layDealerWinsOrTiesAllBet = layDealerWinsOrTiesAllBet;
	}

	public Bet getBackOneOrMoreHas5CardsBet() {
		return backOneOrMoreHas5CardsBet;
	}

	public void setBackOneOrMoreHas5CardsBet(Bet backOneOrMoreHas5CardsBet) {
		this.backOneOrMoreHas5CardsBet = backOneOrMoreHas5CardsBet;
	}

	public Bet getLayOneOrMoreHas5CardsBet() {
		return layOneOrMoreHas5CardsBet;
	}

	public void setLayOneOrMoreHas5CardsBet(Bet layOneOrMoreHas5CardsBet) {
		this.layOneOrMoreHas5CardsBet = layOneOrMoreHas5CardsBet;
	}

	public boolean isSameRound(Round round) {
		return this.gameId == round.getGameId();
	}

	public void update(Round round) {
		this.backDealerWinsOrTiesAllBet.update(round.getBackDealerWinsOrTiesAllBet());
		this.layDealerWinsOrTiesAllBet.update(round.getLayDealerWinsOrTiesAllBet());
		this.backOneOrMoreHas5CardsBet.update(round.getBackOneOrMoreHas5CardsBet());
		this.layOneOrMoreHas5CardsBet.update(round.getLayOneOrMoreHas5CardsBet());
	}

	@Override
	public String toString() {
		return "Round [gameId=" + gameId + ", dealer=" + dealer + ", player1=" + player1 + ", player2=" + player2
				+ ", player3=" + player3 + ", player4=" + player4 + ", backDealerWinsOrTiesAllBet="
				+ backDealerWinsOrTiesAllBet + ", layDealerWinsOrTiesAllBet=" + layDealerWinsOrTiesAllBet
				+ ", backOneOrMoreHas5CardsBet=" + backOneOrMoreHas5CardsBet + ", layOneOrMoreHas5CardsBet="
				+ layOneOrMoreHas5CardsBet + ", roundEnded=" + roundEnded + "]";
	}
}
