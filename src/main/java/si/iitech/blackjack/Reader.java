package si.iitech.blackjack;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.axet.lookup.OCR;

import si.iitech.main.Main;

public class Reader {
	
	private long counter;
	private boolean printPictures = true;

	private static final int GAME_ID_X = 700;
	private static final int GAME_ID_Y = 243;

	//Components dimensions
	private static final int CARD_WIDTH = 20;
	private static final int CARD_HEIGHT = 16;
	
	private static final int GAME_ID_WIDTH = 70;
	private static final int GAME_ID_HEIGHT = 16;
	
	private static final int BACK_LAY_WIDTH = 55;
	private static final int BACK_LAY_HEIGHT = 14;
	
	private static final int RESULT_WIDTH = 280;
	private static final int RESULT_HEIGHT = 24;
	
	private static final int CARD_DISTANCE = 32;

	private static final String IMAGE = "IMAGE.png";

	private static final int DEALER_CARD_X = GAME_ID_X - 62;
	private static final int DEALER_CARD_Y = GAME_ID_Y + 94;

	private static final int PLAYER_1_CARD_X = GAME_ID_X - 224;
	private static final int PLAYER_1_CARD_Y = GAME_ID_Y + 185;

	private static final int PLAYER_2_CARD_X = GAME_ID_X - 166;
	private static final int PLAYER_2_CARD_Y = GAME_ID_Y + 268;

	private static final int PLAYER_3_CARD_X = GAME_ID_X + 51;
	private static final int PLAYER_3_CARD_Y = GAME_ID_Y + 269;

	private static final int PLAYER_4_CARD_X = GAME_ID_X + 108;
	private static final int PLAYER_4_CARD_Y = GAME_ID_Y + 185;

	private static final int LAY_DEALER_WINS_OR_TIES_ALL_ODDS_X = GAME_ID_X + 111;
	private static final int LAY_DEALER_WINS_OR_TIES_ALL_ODDS_Y = GAME_ID_Y + 523;

	private static final int BACK_DEALER_WINS_OR_TIES_ALL_ODDS_X = GAME_ID_X + 40;
	private static final int BACK_DEALER_WINS_OR_TIES_ALL_ODDS_Y = GAME_ID_Y + 523;
	
	private static final int LAY_ONE_OR_MORE_HAS_5_CARDS_ODDS_X = GAME_ID_X + 111;
	private static final int LAY_ONE_OR_MORE_HAS_5_CARDS_ODDS_Y = GAME_ID_Y + 557;

	private static final int BACK_ONE_OR_MORE_HAS_5_CARDS_ODDS_X = GAME_ID_X + 40;
	private static final int BACK_ONE_OR_MORE_HAS_5_CARDS_ODDS_Y = GAME_ID_Y + 557;

	private static final int BACK_OR_LAY_DEALER_WINS_OR_TIES_ALL_RESULT_X = GAME_ID_X - 41;
	private static final int BACK_OR_LAY_DEALER_WINS_OR_TIES_ALL_RESULT_Y = GAME_ID_Y + 523;
	
	private static final int BACK_OR_LAY_ONE_OR_MORE_HAS_5_CARDS_RESULT_X = GAME_ID_X - 41;
	private static final int BACK_OR_LAY_ONE_OR_MORE_HAS_5_CARDS_RESULT_Y = GAME_ID_Y + 557;
	
	private final Rectangle GAME_ID = new Rectangle(GAME_ID_X, GAME_ID_Y, GAME_ID_WIDTH, GAME_ID_HEIGHT);
	private final Rectangle DEALER_CARD = new Rectangle(DEALER_CARD_X, DEALER_CARD_Y, CARD_WIDTH, CARD_HEIGHT);

	private final Rectangle PLAYER_1_CARD_1 = new Rectangle(PLAYER_1_CARD_X, PLAYER_1_CARD_Y, CARD_WIDTH, CARD_HEIGHT);
	private final Rectangle PLAYER_1_CARD_2 = new Rectangle(PLAYER_1_CARD_X + CARD_DISTANCE, PLAYER_1_CARD_Y, CARD_WIDTH,
			CARD_HEIGHT);

	private final Rectangle PLAYER_2_CARD_1 = new Rectangle(PLAYER_2_CARD_X, PLAYER_2_CARD_Y, CARD_WIDTH, CARD_HEIGHT);
	private final Rectangle PLAYER_2_CARD_2 = new Rectangle(PLAYER_2_CARD_X + CARD_DISTANCE, PLAYER_2_CARD_Y, CARD_WIDTH,
			CARD_HEIGHT); // 31

	private final Rectangle PLAYER_3_CARD_1 = new Rectangle(PLAYER_3_CARD_X, PLAYER_3_CARD_Y, CARD_WIDTH, CARD_HEIGHT);
	private final Rectangle PLAYER_3_CARD_2 = new Rectangle(PLAYER_3_CARD_X + CARD_DISTANCE, PLAYER_3_CARD_Y, CARD_WIDTH,
			CARD_HEIGHT); // 28

	private final Rectangle PLAYER_4_CARD_1 = new Rectangle(PLAYER_4_CARD_X, PLAYER_4_CARD_Y, CARD_WIDTH, CARD_HEIGHT);
	private final Rectangle PLAYER_4_CARD_2 = new Rectangle(PLAYER_4_CARD_X + CARD_DISTANCE, PLAYER_4_CARD_Y, CARD_WIDTH,
			CARD_HEIGHT); // 31
	
	private final Rectangle LAY_DEALER_WINS_OR_TIES_ALL_ODDS = new Rectangle(LAY_DEALER_WINS_OR_TIES_ALL_ODDS_X,
			LAY_DEALER_WINS_OR_TIES_ALL_ODDS_Y, BACK_LAY_WIDTH, BACK_LAY_HEIGHT);
	
	private final Rectangle BACK_DEALER_WINS_OR_TIES_ALL_ODDS = new Rectangle(BACK_DEALER_WINS_OR_TIES_ALL_ODDS_X,
			BACK_DEALER_WINS_OR_TIES_ALL_ODDS_Y, BACK_LAY_WIDTH, BACK_LAY_HEIGHT);
	
	private final Rectangle LAY_ONE_OR_MORE_HAS_5_CARDS = new Rectangle(LAY_ONE_OR_MORE_HAS_5_CARDS_ODDS_X,
			LAY_ONE_OR_MORE_HAS_5_CARDS_ODDS_Y, BACK_LAY_WIDTH, BACK_LAY_HEIGHT);
	
	private final Rectangle BACK_ONE_OR_MORE_HAS_5_CARDS = new Rectangle(BACK_ONE_OR_MORE_HAS_5_CARDS_ODDS_X,
			BACK_ONE_OR_MORE_HAS_5_CARDS_ODDS_Y, BACK_LAY_WIDTH, BACK_LAY_HEIGHT);
	
	
	private final Rectangle BACK_OR_LAY_DEALER_WINS_OR_TIES_ALL_RESULT = new Rectangle(BACK_OR_LAY_DEALER_WINS_OR_TIES_ALL_RESULT_X,
			BACK_OR_LAY_DEALER_WINS_OR_TIES_ALL_RESULT_Y, RESULT_WIDTH,
			RESULT_HEIGHT);
	
	private final Rectangle BACK_OR_LAY_ONE_OR_MORE_HAS_5_CARDS_RESULT = new Rectangle(BACK_OR_LAY_ONE_OR_MORE_HAS_5_CARDS_RESULT_X,
			BACK_OR_LAY_ONE_OR_MORE_HAS_5_CARDS_RESULT_Y, RESULT_WIDTH,
			RESULT_HEIGHT);

	private Robot robot;

	private static final String FONT_CARDS = "font_cards";
	private static final String FONT_ODDS = "font_odds";

	public Reader() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public Round readRound() {
		Round round = new Round();
		OCR api = getApi();
		int gameId = readNumber(GAME_ID, api);
		round.setGameId(gameId);
		
		Card dealerCard1 = readCard(DEALER_CARD, api);
		round.setDealer(Hand.newHand(dealerCard1));
		
		Card player1Card1 = readCard(PLAYER_1_CARD_1, api);
		Card player1Card2 = readCard(PLAYER_1_CARD_2, api);
		round.setPlayer1(Hand.newHand(player1Card1, player1Card2));
		
		Card player2Card1 = readCard(PLAYER_2_CARD_1, api);
		Card player2Card2 = readCard(PLAYER_2_CARD_2, api);
		round.setPlayer2(Hand.newHand(player2Card1, player2Card2));
		
		Card player3Card1 = readCard(PLAYER_3_CARD_1, api);
		Card player3Card2 = readCard(PLAYER_3_CARD_2, api);
		round.setPlayer3(Hand.newHand(player3Card1, player3Card2));
		
		Card player4Card1 = readCard(PLAYER_4_CARD_1, api);
		Card player4Card2 = readCard(PLAYER_4_CARD_2, api);
		round.setPlayer4(Hand.newHand(player4Card1, player4Card2));
		
		double layDealerWinsOrTiesAllOdds = readOdds(LAY_DEALER_WINS_OR_TIES_ALL_ODDS, api);
		Boolean layDealerWinsOrTiesAllWon = checkResult(BACK_OR_LAY_DEALER_WINS_OR_TIES_ALL_RESULT, true, api);
		Bet layDealerWinsOrTiesAllBet = Bet.newBet(layDealerWinsOrTiesAllOdds, layDealerWinsOrTiesAllWon);
		round.setLayDealerWinsOrTiesAllBet(layDealerWinsOrTiesAllBet);
		
		double backDealerWinsOrTiesAllOdds = readOdds(BACK_DEALER_WINS_OR_TIES_ALL_ODDS, api);
		Boolean backDealerWinsOrTiesAllWon = checkResult(BACK_OR_LAY_DEALER_WINS_OR_TIES_ALL_RESULT, false, api);
		Bet backDealerWinsOrTiesAllBet = Bet.newBet(backDealerWinsOrTiesAllOdds, backDealerWinsOrTiesAllWon);
		round.setBackDealerWinsOrTiesAllBet(backDealerWinsOrTiesAllBet);
		
		double layOneOrMoreHas5CardsOdds = readOdds(LAY_ONE_OR_MORE_HAS_5_CARDS, api);
		Boolean layOneOrMoreHas5CardsWon = checkResult(BACK_OR_LAY_ONE_OR_MORE_HAS_5_CARDS_RESULT, true, api);
		Bet layOneOrMoreHas5CardsBet = Bet.newBet(layOneOrMoreHas5CardsOdds, layOneOrMoreHas5CardsWon);
		round.setLayOneOrMoreHas5CardsBet(layOneOrMoreHas5CardsBet);

		double backOneOrMoreHas5CardsOdds = readOdds(BACK_ONE_OR_MORE_HAS_5_CARDS, api);
		Boolean backOneOrMoreHas5CardsWon = checkResult(BACK_OR_LAY_ONE_OR_MORE_HAS_5_CARDS_RESULT, false, api);
		Bet backOneOrMoreHas5CardsBet = Bet.newBet(backOneOrMoreHas5CardsOdds, backOneOrMoreHas5CardsWon);
		round.setBackOneOrMoreHas5CardsBet(backOneOrMoreHas5CardsBet);
		
		System.out.println(round);
		return round;
	}
	
	private Boolean checkResult(Rectangle resultPosition, boolean reverse, OCR api) {
		String result = readValueFromLocationOnScreen(resultPosition, FONT_ODDS, api);
		if (result.contains("ELIMINATED")) {
			if(reverse) return true;
			return false;
		}
		if (result.contains("WINNER")) {
			if(reverse) return false;
			return true;
		}
		return null;
	}
	 
	private String readValueFromLocationOnScreen(Rectangle cardPosition, String font, OCR api)  {
		BufferedImage bufferedImage = robot.createScreenCapture(cardPosition);
		String finalText = "";
		try {
			finalText = api.recognize(bufferedImage, font);
		} catch(Exception e) {
			
		}
		
		if(printPictures) {
			 File outputfile = new File(counter++ + "image.jpg");
			 try {
				ImageIO.write(bufferedImage, "jpg", outputfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return finalText;
	}
	
	private Card readCard(Rectangle position, OCR api) {
		String sign = readValueFromLocationOnScreen(position, FONT_CARDS, api).trim();
		System.out.println(sign);
		return Card.fromSign(sign);
	}

	private int readNumber(Rectangle cardPosition, OCR api) {
		try {
			String value = readValueFromLocationOnScreen(cardPosition, FONT_CARDS, api).trim();
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	private double readOdds(Rectangle cardPosition, OCR api) {
		try {
			String doubleOrIntStringValue = readValueFromLocationOnScreen(cardPosition, FONT_ODDS, api);
			if (doubleOrIntStringValue.contains(".")) {
				return Double.parseDouble(doubleOrIntStringValue);
			}
			return Integer.parseInt(doubleOrIntStringValue);
		} catch (NumberFormatException e) {
			return 0.0;
		}
	}
	
	private OCR getApi() {
		OCR api = new OCR(0.70f);
		api.loadFontsDirectory(Main.class, new File("fonts"));
		return api;
	}
}
