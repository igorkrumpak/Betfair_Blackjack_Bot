package si.iitech.blackjack;

import java.awt.AWTException;
import java.io.IOException;

import si.iitech.blackjack.entites.Game;

public class Main {

	public static void main(String[] args) throws AWTException, IOException, InterruptedException {
		Game game = new Game();
		game.play();
	}

}