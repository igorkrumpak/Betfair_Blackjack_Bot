package si.iitech.blackjack.game;

public class Bet {

	private boolean placed;
	private Boolean won;
	private double odds;
	
	public static Bet newBet(double odds, Boolean won) {
		return new Bet(odds, won);
	}
	
	private Bet(double odds, Boolean won) {
		this.odds = odds;
		this.won = won;
	}
	public boolean isPlaced() {
		return placed;
	}
	public void setPlaced(boolean placed) {
		this.placed = placed;
	}
	public Boolean getWon() {
		return won;
	}
	public void setWon(Boolean won) {
		this.won = won;
	}
	public double getOdds() {
		return odds;
	}
	public void setOdds(double odds) {
		this.odds = odds;
	}

	//If bet is not yet placed, we update odds
	public void update(Bet bet) {
		if(placed) {
			this.won = bet.getWon();
			return;
		}
		this.odds = bet.getOdds();
	}

	@Override
	public String toString() {
		return "Bet [placed=" + placed + ", won=" + won + ", odds=" + odds + "]";
	}
	
	
}
