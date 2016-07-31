package lifeGame.ui;

/**
 * Support class for the getAdjacent method.
 * @author Eddy Lau
 *
 */
public class Position {
	/** Represents position of x. **/
	private int posX;
	/** Represents position of y.**/
	private int posY;
	
	/**
	 * Constructor of a position.
	 * @param x X-coordinates
	 * @param y Y_coordinates
	 */
	public Position(int x, int y) {
		posX = x;
		posY = y;
	}
	/**
	 * Gets the position of x.
	 * @return posX X
	 */
	public int getX() {
		return posX;
	}
	/**
	 * Gets the position of y.
	 * @return posY Y
	 */
	public int getY() {
		return posY;
	}
}
