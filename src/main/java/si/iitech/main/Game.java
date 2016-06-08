package si.iitech.main;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.github.axet.lookup.OCR;

public class Game {
	
	public static final int GAME_ID_X = 700;
//	public static final int GAME_ID_Y = 238;       with login
	public static final int GAME_ID_Y = 230;       

	public static final int CARD_WIDTH = 20;
	public static final int CARD_HEIGHT = 16;
	public static final int GAME_ID_WIDTH = 70;
	public static final int GAME_ID_HEIGHT = 16;
	
	public static final int CARD_DISTANCE = 32;

	public static final String IMAGE = "IMAGE.png";
	
	public static final int DEALER_CARD_X = GAME_ID_X - 62;
	public static final int DEALER_CARD_Y = GAME_ID_Y + 94;

	
	public static final int PLAYER_1_CARD_X = GAME_ID_X - 223;
	public static final int PLAYER_1_CARD_Y = GAME_ID_Y + 185;
	
	public static final int PLAYER_2_CARD_X = GAME_ID_X - 163;
	public static final int PLAYER_2_CARD_Y = GAME_ID_Y + 268;

	public static final int PLAYER_3_CARD_X = GAME_ID_X + 53;
	public static final int PLAYER_3_CARD_Y = GAME_ID_Y + 268;

	public static final int PLAYER_4_CARD_X = GAME_ID_X + 109;
	public static final int PLAYER_4_CARD_Y = GAME_ID_Y + 185;
	
	public static final int LAY_DEALER_WINS_OR_TIES_ALL_ODD_X = GAME_ID_X + 111;
	public static final int LAY_DEALER_WINS_OR_TIES_ALL_ODD_Y = GAME_ID_Y + 523;
	public static final int LAY_DEALER_WINS_OR_TIES_ALL_ODD_WIDTH = 55;
	public static final int LAY_DEALER_WINS_OR_TIES_ALL_ODD_HEIGHT = 14;
	
	public static final int LAY_DEALER_WINS_OR_TIES_ALL_RESULT_X = GAME_ID_X - 41;
	public static final int LAY_DEALER_WINS_OR_TIES_ALL_RESULT_Y = GAME_ID_Y + 523;
	public static final int LAY_DEALER_WINS_OR_TIES_ALL_RESULT_WIDTH = 280;
	public static final int LAY_DEALER_WINS_OR_TIES_ALL_RESULT_HEIGHT = 24;
	

	public final Rectangle GAME_ID = new Rectangle(GAME_ID_X, GAME_ID_Y, GAME_ID_WIDTH, GAME_ID_HEIGHT);
	public final Rectangle DEALER_CARD = new Rectangle(DEALER_CARD_X, DEALER_CARD_Y, CARD_WIDTH, CARD_HEIGHT);

	public final Rectangle PLAYER_1_CARD_1 = new Rectangle(PLAYER_1_CARD_X, PLAYER_1_CARD_Y, CARD_WIDTH, CARD_HEIGHT);
	public final Rectangle PLAYER_1_CARD_2 = new Rectangle(PLAYER_1_CARD_X + CARD_DISTANCE, PLAYER_1_CARD_Y, CARD_WIDTH, CARD_HEIGHT);

	public final Rectangle PLAYER_2_CARD_1 = new Rectangle(PLAYER_2_CARD_X, PLAYER_2_CARD_Y, CARD_WIDTH, CARD_HEIGHT);
	public final Rectangle PLAYER_2_CARD_2 = new Rectangle(PLAYER_2_CARD_X + 31, PLAYER_2_CARD_Y, CARD_WIDTH, CARD_HEIGHT);  //31

	public final Rectangle PLAYER_3_CARD_1 = new Rectangle(PLAYER_3_CARD_X, PLAYER_3_CARD_Y, CARD_WIDTH, CARD_HEIGHT);
	public final Rectangle PLAYER_3_CARD_2 = new Rectangle(PLAYER_3_CARD_X + 28, PLAYER_3_CARD_Y, CARD_WIDTH, CARD_HEIGHT);  //28

	public final Rectangle PLAYER_4_CARD_1 = new Rectangle(PLAYER_4_CARD_X, PLAYER_4_CARD_Y, CARD_WIDTH, CARD_HEIGHT);
	public final Rectangle PLAYER_4_CARD_2 = new Rectangle(PLAYER_4_CARD_X + 31, PLAYER_4_CARD_Y, CARD_WIDTH, CARD_HEIGHT);  //31

	public final Rectangle LAY_DEALER_WINS_OR_TIES_ALL_ODD = new Rectangle(LAY_DEALER_WINS_OR_TIES_ALL_ODD_X, LAY_DEALER_WINS_OR_TIES_ALL_ODD_Y, LAY_DEALER_WINS_OR_TIES_ALL_ODD_WIDTH, LAY_DEALER_WINS_OR_TIES_ALL_ODD_HEIGHT);
	public final Rectangle LAY_DEALER_WINS_OR_TIES_ALL_RESULT = new Rectangle(LAY_DEALER_WINS_OR_TIES_ALL_RESULT_X, LAY_DEALER_WINS_OR_TIES_ALL_RESULT_Y, LAY_DEALER_WINS_OR_TIES_ALL_RESULT_WIDTH, LAY_DEALER_WINS_OR_TIES_ALL_RESULT_HEIGHT);

	private OCR api;
	private Robot robot;

	private static final String FONT_CARDS = "font_cards";
	private static final String FONT_ODDS = "font_odds";

	private Round round;
	private long counter;
	private double bank = 0;

	Logger logger = Logger.getLogger("Results");
	FileHandler fileHandler;

	public Game() throws AWTException, SecurityException, IOException {
		robot = new Robot();
		fileHandler = new FileHandler("results.log", true);
		logger.addHandler(fileHandler);
		SimpleFormatter formatter = new SimpleFormatter();
		fileHandler.setFormatter(formatter);
		logger.info("-----------------------------------------------------------");
	}

	public void play() throws IOException, InterruptedException {
		round = new Round();
		while (true) {
			try {
				api = new OCR(0.70f);
				api.loadFontsDirectory(Main.class, new File("fonts"));
				int gameId = readIntValueFromLocation(GAME_ID, FONT_CARDS);
				if (round.getGameId() != gameId) {
					round = null;
					round = new Round();
					round.setGameId(gameId);
					logger.info("NEW GAME");
				}
				
				if (!round.isBetPlaced()) {
					int dealerSum = readIntValueFromLocation(DEALER_CARD, FONT_CARDS);
					round.setDealerSum(dealerSum);
					int player1Sum = readIntValueFromLocation(PLAYER_1_CARD_1, FONT_CARDS)
							+ readIntValueFromLocation(PLAYER_1_CARD_2, FONT_CARDS);
					round.setPlayer1Sum(player1Sum);
					int player2Sum = readIntValueFromLocation(PLAYER_2_CARD_1, FONT_CARDS)
							+ readIntValueFromLocation(PLAYER_2_CARD_2, FONT_CARDS);
					round.setPlayer2Sum(player2Sum);
					int player3Sum = readIntValueFromLocation(PLAYER_3_CARD_1, FONT_CARDS)
							+ readIntValueFromLocation(PLAYER_3_CARD_2, FONT_CARDS);
					round.setPlayer3Sum(player3Sum);
					int player4Sum = readIntValueFromLocation(PLAYER_4_CARD_1, FONT_CARDS)
							+ readIntValueFromLocation(PLAYER_4_CARD_2, FONT_CARDS);
					round.setPlayer4Sum(player4Sum);
				 	double layOdds = readDoubleOrIntValueFromLocation(LAY_DEALER_WINS_OR_TIES_ALL_ODD, FONT_ODDS);
					round.setLayOdds(layOdds);
					
//					if(layOdds >= 120) {
//						placeBet(round);
//					}
					

					if (	layOdds > 0 && 
							(layOdds <= 6.0) &&
							dealerSum > 3 && 
							player1Sum > 0 && 
							player2Sum > 0 && 
							player3Sum > 0  &&
							player4Sum > 0  &&
							player1Sum < 22 &&
							player2Sum < 22 &&
							player3Sum < 22 &&
							player4Sum < 22) {

						int potentialDealerSum = dealerSum + 10;
						if (
//							atLeastOneHasBlackjack(player1Sum, player2Sum, player3Sum, player4Sum, potentialDealerSum) 
							potencialDealerSumIsLowerThenAtLeastOnePlayersSum(player1Sum, player2Sum, player3Sum, player4Sum, potentialDealerSum) ||
							potencialDealerSumIsLowerThenAtLeastOnePlayersPotencialSum(player1Sum, player2Sum, player3Sum, player4Sum, potentialDealerSum)
							) {
							placeBet(round);
						}
					}
				}

				String result = readValueFromLocationOnScreen(LAY_DEALER_WINS_OR_TIES_ALL_RESULT, FONT_ODDS);
				if (!round.isEnded()) {
					if (result.contains("ELIMINATED")) {
						round.setWon(true);
						round.setEnded(true);

						if (round.isBetPlaced()) {
							logger.info("BET WON");
							bank = bank + 1;
							logger.info("BANK: " + bank);
//							if(bank >= 25 || bank < -20) {
//								System.exit(0);
//							}
						}

					} else if (result.contains("WINNER")) {
						round.setEnded(true);

						if (round.isBetPlaced()) {
							logger.info("BET LOST");
							bank = bank - (round.getLayOdds() - 1);
							logger.info("BANK: " + bank);
//							if(bank >= 30 || bank < -20) {
//								System.exit(0);
//							}
						}
					}
				}
				
				counter++;
				if(counter % 150 == 0) {
					restartBrowser();
				}
				
				
				Thread.sleep(4000);
			} catch(Exception e) {
				
			}
		}
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

	private boolean potencialDealerSumIsLowerThenAtLeastOnePlayersPotencialSum(int player1Sum, int player2Sum,
			int player3Sum, int player4Sum, int potentialDealerSum) {
		int potentialPlayer1Sum = player1Sum + 10;
		int potentialPlayer2Sum = player2Sum + 10;
		int potentialPlayer3Sum = player3Sum + 10;
		int potentialPlayer4Sum = player4Sum + 10;
		
		if((potentialPlayer1Sum > potentialDealerSum && potentialPlayer1Sum <= 21) ||
			(potentialPlayer2Sum > potentialDealerSum && potentialPlayer2Sum <= 21) ||
			(potentialPlayer3Sum > potentialDealerSum && potentialPlayer3Sum <= 21) ||
			(potentialPlayer4Sum > potentialDealerSum && potentialPlayer4Sum <= 21))
			return true;
		return false;
	}

	private boolean potencialDealerSumIsLowerThenAtLeastOnePlayersSum(int player1Sum, int player2Sum, int player3Sum,
			int player4Sum, int potentialDealerSum) {
		return potentialDealerSum < player1Sum || 
				potentialDealerSum < player2Sum || 
				potentialDealerSum < player3Sum || 
				potentialDealerSum < player4Sum;
	}
	
	public final static int PLACE_BET_X = 1035;
	public final static int PLACE_BET_Y = 243;
	
	public final static int LAY_ALL_X = 1100;
	public final static int LAY_ALL_Y = 286;

	public final static int DEALER_WINS_OR_TIES_ALL_X = 1336;
	public final static int DEALER_WINS_OR_TIES_ALL_Y = 494;
	
	public final static int SUBMIT_1_X = 1416;
	public final static int SUBMIT_1_Y = 959;
	
	public final static int SUBMIT_2_X = 1420;
	public final static int SUBMIT_2_Y = 966;

	private void placeBet(Round round) throws InterruptedException {
		round.setBetPlaced(true);
//		Random random = new Random();
//		Thread.sleep(random.nextInt(1000) + 800);
//		robot.mouseMove(PLACE_BET_X, PLACE_BET_Y);
//		robot.mousePress(InputEvent.BUTTON1_MASK);
//		robot.mouseRelease(InputEvent.BUTTON1_MASK);
//		Thread.sleep(random.nextInt(1000) + 800);
//		robot.mouseMove(LAY_ALL_X, LAY_ALL_Y);
//		robot.mousePress(InputEvent.BUTTON1_MASK);
//		robot.mouseRelease(InputEvent.BUTTON1_MASK);
//		Thread.sleep(random.nextInt(1000) + 1200);
//		robot.mouseMove(DEALER_WINS_OR_TIES_ALL_X, DEALER_WINS_OR_TIES_ALL_Y);
//		robot.mousePress(InputEvent.BUTTON1_MASK);
//		robot.mouseRelease(InputEvent.BUTTON1_MASK);
//		robot.keyPress(KeyEvent.VK_1);
//		robot.keyRelease(KeyEvent.VK_1);
//		Thread.sleep(random.nextInt(1000) + 1200);
//		robot.mouseMove(SUBMIT_1_X, SUBMIT_1_Y);
//		robot.mousePress(InputEvent.BUTTON1_MASK);
//		robot.mouseRelease(InputEvent.BUTTON1_MASK);
//		Thread.sleep(random.nextInt(1000) + 800);
//		robot.mouseMove(SUBMIT_2_X, SUBMIT_2_Y);
//		robot.mousePress(InputEvent.BUTTON1_MASK);
//		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		logger.info(round.toString());
		
	}

	private void restartBrowser() {
		robot.mouseMove(73, 41);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	private String readValueFromLocationOnScreen(Rectangle cardPosition, String font) throws IOException {
		BufferedImage bufferedImage = robot.createScreenCapture(cardPosition);
		String finalText = api.recognize(bufferedImage, font);
		
//		File outputfile = new File(counter++ + "image.jpg");
//		ImageIO.write(bufferedImage, "jpg", outputfile);

		return finalText;

	}

	private int readIntValueFromLocation(Rectangle cardPosition, String font) throws IOException {
		try {
			String card = readValueFromLocationOnScreen(cardPosition, font).trim();
			
			if (card.contains("A")) {
				return 11;
			}

			if (card.contains("K") || card.contains("Q") || card.contains("J")) {
				return 10;
			}

			return Integer.parseInt(card);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	private double readDoubleOrIntValueFromLocation(Rectangle cardPosition, String font) throws IOException {
		try {
			String doubleOrIntStringValue = readValueFromLocationOnScreen(cardPosition, font);
			
			if(doubleOrIntStringValue.contains(".")) {
				return Double.parseDouble(doubleOrIntStringValue);
			}
			return Integer.parseInt(doubleOrIntStringValue);
		} catch (NumberFormatException e) {
			return 0.0;
		}
	}
}
