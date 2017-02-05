package si.iitech.blackjack.entites;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import si.iitech.blackjack.game.Reader;

public class Game {
	
	public static final String LOGGER = "Game";
	public static final String LOGGER_FILE = "game.log";
	
	private Logger logger;
	
	private FileHandler fileHandler;
	
	private Round activeRound;
	private Reader reader;
	
	private long counter;
	double bank = 0.0;
	
	public Game() throws SecurityException, IOException {
		initLogger();
		initReader();
	}

	private void initLogger() throws IOException {
		logger = Logger.getLogger(LOGGER);	
		fileHandler = new FileHandler(LOGGER_FILE, true);
		logger.addHandler(fileHandler);
		SimpleFormatter formatter = new SimpleFormatter();
		fileHandler.setFormatter(formatter);
	}
	
	private void initReader() {
		reader = new Reader();
	}
	
	public void play() throws InterruptedException {
		
		while(true) {
			Round round = reader.readRound();
			//We save current round for later use
			if(activeRound == null || !activeRound.isSameRound(round)) {
				activeRound = round;
			} else {
				activeRound.update(round);
			}

			
			
			
			
			Thread.sleep(4000);
		}
		
		
//		round = new Round();
//		while (true) {
//			try {
//				api = new OCR(0.70f);
//				api.loadFontsDirectory(Main.class, new File("fonts"));
//				int gameId = readIntValueFromLocation(GAME_ID, FONT_CARDS);
//				if (round.getGameId() != gameId) {
//					round = null;
//					round = new Round();
//					round.setGameId(gameId);
//					logger.info("NEW GAME " + gameId);
//				}
//				
//				if(!round.isBackAndLayPlaced()) {
//					double layOdds = readDoubleOrIntValueFromLocation(LAY_DEALER_WINS_OR_TIES_ALL_ODD, FONT_ODDS);
//					double backOdds = readDoubleOrIntValueFromLocation(BACK_DEALER_WINS_OR_TIES_ALL_ODD, FONT_ODDS);
//					
//					if(round.getLayOdds() == 0 && layOdds == 3.8) {
//						round.setLayOdds(layOdds);
//						round.setLayOddsAmount(2);
//						logger.info("LAYED 2$ at " + layOdds);
//					}
//					
//					if(round.getLayOdds() > 0) {
//						if(backOdds > 0 && backOdds < 3.2) {
//							round.setBackOdds(backOdds);
//							round.setBackOddsAmount(2);
//							logger.info("BACKED 2$ at " + backOdds);
//						} else if(backOdds > 5.2 && backOdds < 7) {
//							round.setBackOdds(backOdds);
//							round.setBackOddsAmount(1.5);
//							logger.info("BACKED 1,5$ at " + backOdds);
//						} else if(backOdds > 7) {
//							round.setBackOdds(backOdds);
//							round.setBackOddsAmount(1);
//							logger.info("BACKED 1$ at " + backOdds);
//						}
//					}
//				}
//					
//				
////				if (!round.isBetPlaced()) {
////					int dealerSum = readIntValueFromLocation(DEALER_CARD, FONT_CARDS);
////					round.setDealerSum(dealerSum);
////					int player1Sum = readIntValueFromLocation(PLAYER_1_CARD_1, FONT_CARDS)
////							+ readIntValueFromLocation(PLAYER_1_CARD_2, FONT_CARDS);
////					round.setPlayer1Sum(player1Sum);
////					int player2Sum = readIntValueFromLocation(PLAYER_2_CARD_1, FONT_CARDS)
////							+ readIntValueFromLocation(PLAYER_2_CARD_2, FONT_CARDS);
////					round.setPlayer2Sum(player2Sum);
////					int player3Sum = readIntValueFromLocation(PLAYER_3_CARD_1, FONT_CARDS)
////							+ readIntValueFromLocation(PLAYER_3_CARD_2, FONT_CARDS);
////					round.setPlayer3Sum(player3Sum);
////					int player4Sum = readIntValueFromLocation(PLAYER_4_CARD_1, FONT_CARDS)
////							+ readIntValueFromLocation(PLAYER_4_CARD_2, FONT_CARDS);
////					round.setPlayer4Sum(player4Sum);
////				 	double layOdds = readDoubleOrIntValueFromLocation(LAY_DEALER_WINS_OR_TIES_ALL_ODD, FONT_ODDS);
////					round.setLayOdds(layOdds);
//					
////					if(layOdds >= 120) {
////						placeBet(round);
////					}
//					
//
////					if (	layOdds > 0 && 
////							(layOdds <= 6.0) &&
//////							dealerSum > 3 && 
////							player1Sum > 0 && 
////							player2Sum > 0 && 
////							player3Sum > 0  &&
////							player4Sum > 0  &&
////							player1Sum < 22 &&
////							player2Sum < 22 &&
////							player3Sum < 22 &&
////							player4Sum < 22) {
////
////						int potentialDealerSum = dealerSum + 10;
////						if (
//////							atLeastOneHasBlackjack(player1Sum, player2Sum, player3Sum, player4Sum, potentialDealerSum) 
////							potencialDealerSumIsLowerThenAtLeastOnePlayersSum(player1Sum, player2Sum, player3Sum, player4Sum, potentialDealerSum) ||
////							potencialDealerSumIsLowerThenAtLeastOnePlayersPotencialSum(player1Sum, player2Sum, player3Sum, player4Sum, potentialDealerSum)
////							) {
////							placeBet(round);
////						}
////					}
////				}
//
//				String result = readValueFromLocationOnScreen(LAY_DEALER_WINS_OR_TIES_ALL_RESULT, FONT_ODDS);
//				if (!round.isEnded()) {
//					if (result.contains("ELIMINATED")) {
////						round.setWon(true);
//						round.setEnded(true);
//						double winning = round.getLayOddsAmount();
//						winning = winning - winning * 0.04;
//						double lossing = round.getBackOddsAmount();
//						bank = bank + winning - lossing;
//						logger.info("LAY WON");
//						logger.info("BANK: " + bank);
////						if (round.isBetPlaced()) {
////							logger.info("BET WON");
////							bank = bank + 1;
////							logger.info("BANK: " + bank);
//////							if(bank >= 25 || bank < -20) {
//////								System.exit(0);
//////							}
////						}
//
//					} else if (result.contains("WINNER")) {
//						round.setEnded(true);
//						double winning = ((round.getBackOddsAmount() * round.getBackOdds()) - round.getBackOddsAmount());
//						winning = winning - winning * 0.04;
//						double losing = ((round.getLayOddsAmount() * round.getLayOdds()) - round.getLayOddsAmount());
//						bank = bank + winning - losing;
//						logger.info("BACK WON");
//						logger.info("BANK: " + bank);
//
////						if (round.isBetPlaced()) {
////							logger.info("BET LOST");
////							bank = bank - (round.getLayOdds() - 1);
////							logger.info("BANK: " + bank);
//////							if(bank >= 30 || bank < -20) {
//////								System.exit(0);
//////							}
////						}
//					}
//				}
//				
//				counter++;
//				if(counter % 150 == 0) {
//					restartBrowser();
//				}
//				
//				
//				Thread.sleep(4000);
//			} catch(Exception e) {
//				
//			}
//		}
	}

//	private boolean atLeastOneHasBlackjack(int player1Sum, int player2Sum, int player3Sum, int player4Sum,
//			int potentialDealerSum) {
//		return 
//				(player1Sum == 21 || 
//				player2Sum == 21 ||
//				player3Sum == 21 ||
//				player4Sum == 21) &&
//				(potentialDealerSum < 20);
//	}
	
//	public final static int PLACE_BET_X = 1035;
//	public final static int PLACE_BET_Y = 243;
//	
//	public final static int LAY_ALL_X = 1100;
//	public final static int LAY_ALL_Y = 286;
//
//	public final static int DEALER_WINS_OR_TIES_ALL_X = 1336;
//	public final static int DEALER_WINS_OR_TIES_ALL_Y = 494;
//	
//	public final static int SUBMIT_1_X = 1416;
//	public final static int SUBMIT_1_Y = 959;
//	
//	public final static int SUBMIT_2_X = 1420;
//	public final static int SUBMIT_2_Y = 966;

//	private void placeBet(Round round) throws InterruptedException {
////		round.setBetPlaced(true);
////		Random random = new Random();
////		Thread.sleep(random.nextInt(800) + 700);
////		robot.mouseMove(PLACE_BET_X, PLACE_BET_Y);
////		robot.mousePress(InputEvent.BUTTON1_MASK);
////		robot.mouseRelease(InputEvent.BUTTON1_MASK);
////		Thread.sleep(random.nextInt(800) + 700);
////		robot.mouseMove(LAY_ALL_X, LAY_ALL_Y);
////		robot.mousePress(InputEvent.BUTTON1_MASK);
////		robot.mouseRelease(InputEvent.BUTTON1_MASK);
////		Thread.sleep(random.nextInt(800) + 1000);
////		robot.mouseMove(DEALER_WINS_OR_TIES_ALL_X, DEALER_WINS_OR_TIES_ALL_Y);
////		robot.mousePress(InputEvent.BUTTON1_MASK);
////		robot.mouseRelease(InputEvent.BUTTON1_MASK);
////		robot.keyPress(KeyEvent.VK_1);
////		robot.keyRelease(KeyEvent.VK_1);
////		Thread.sleep(random.nextInt(800) + 1000);
////		robot.mouseMove(SUBMIT_1_X, SUBMIT_1_Y);
////		robot.mousePress(InputEvent.BUTTON1_MASK);
////		robot.mouseRelease(InputEvent.BUTTON1_MASK);
////		Thread.sleep(random.nextInt(800) + 700);
////		robot.mouseMove(SUBMIT_2_X, SUBMIT_2_Y);
////		robot.mousePress(InputEvent.BUTTON1_MASK);
////		robot.mouseRelease(InputEvent.BUTTON1_MASK);
//		logger.info(round.toString());
//		
//	}
//
//	private void restartBrowser() {
//		robot.mouseMove(73, 41);
//		robot.mousePress(InputEvent.BUTTON1_MASK);
//		robot.mouseRelease(InputEvent.BUTTON1_MASK);
//	}

}
