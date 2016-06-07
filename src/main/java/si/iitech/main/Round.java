package si.iitech.main;

public class Round {

	private int dealerSum = 0;
	private int player1Sum = 0;
	private int player2Sum = 0;
	private int player3Sum = 0;
	private int player4Sum = 0;
	private int gameId = 0;
	private double layOdds;
	private boolean won = false;
	private boolean betPlaced = false;
	private boolean ended = false;

	public int getDealerSum() {
		return dealerSum;
	}

	public void setDealerSum(int dealerSum) {
		this.dealerSum = dealerSum;
	}

	public int getPlayer1Sum() {
		return player1Sum;
	}

	public void setPlayer1Sum(int player1Sum) {
		this.player1Sum = player1Sum;
	}

	public int getPlayer2Sum() {
		return player2Sum;
	}

	public void setPlayer2Sum(int player2Sum) {
		this.player2Sum = player2Sum;
	}

	public int getPlayer3Sum() {
		return player3Sum;
	}

	public void setPlayer3Sum(int player3Sum) {
		this.player3Sum = player3Sum;
	}

	public int getPlayer4Sum() {
		return player4Sum;
	}

	public void setPlayer4Sum(int player4Sum) {
		this.player4Sum = player4Sum;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public double getLayOdds() {
		return layOdds;
	}

	public void setLayOdds(double layOdds) {
		this.layOdds = layOdds;
	}

	public boolean isWon() {
		return won;
	}

	public void setWon(boolean won) {
		this.won = won;
	}

	public boolean isBetPlaced() {
		return betPlaced;
	}

	public void setBetPlaced(boolean betPlaced) {
		this.betPlaced = betPlaced;
	}
	
	public boolean isEnded() {
		return ended;
	}
	
	public void setEnded(boolean ended) {
		this.ended = ended;
	}

	@Override
	public String toString() {
		return "Round [dealerSum=" + dealerSum + ", player1Sum=" + player1Sum + ", player2Sum=" + player2Sum
				+ ", player3Sum=" + player3Sum + ", player4Sum=" + player4Sum + ", gameId=" + gameId + ", layOdds="
				+ layOdds + "]";
	}
	
	
	
}
