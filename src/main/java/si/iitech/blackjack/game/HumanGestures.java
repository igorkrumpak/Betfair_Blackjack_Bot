package si.iitech.blackjack.game;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class HumanGestures {

	private static final int RELOAD_CHROME_X = 73;
	private static final int RELOAD_CHROME_Y = 41;
	private Robot robot;
	
	private static HumanGestures instance;
	
	public static HumanGestures getInstance() {
		if(instance == null) instance = new HumanGestures();
		return instance;
	}
	
	private HumanGestures() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public void restartBrowser() {
		robot.mouseMove(RELOAD_CHROME_X, RELOAD_CHROME_Y);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
}
