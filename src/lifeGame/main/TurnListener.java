package lifeGame.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import lifeGame.ui.GameFrame;

/**
 * Mouse listener that calls takeTurn method when mouse
 * is clicked.
 * @author Eddy Lau
 *
 */
public class TurnListener extends MouseAdapter {
	/** Represents the game frame. **/
	GameFrame gFrame;
	/**
	 * Constructor for the turn listener.
	 * @param gameFrame GameFrame
	 */
	public TurnListener(GameFrame gameFrame) {
		gFrame = gameFrame;
	}
	/**
	 * Calls the take turn method when mouse is pressed.
	 * @param e Mouse event
	 */
	public void mousePressed(MouseEvent e) {

		gFrame.takeTurn();
	}
}
