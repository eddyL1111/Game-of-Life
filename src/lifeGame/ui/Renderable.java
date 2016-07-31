package lifeGame.ui;



/**
 * Interface for all cell objects
 * 
 * @author Eddy Lau
 *
 */
public interface Renderable {
	/**
	 * Sets the cell in the world.
	 * 
	 * @param c Cell
	 */
	void setCell(Cell c);
	/**
	 * Initializes an object in the world.
	 */
	void init();
}
