package lifeGame.ui;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import lifeGame.gameObjects.Hervibore;
import lifeGame.gameObjects.Plant;

/**
 * Represents a cell in the world.
 * 
 * @author Eddy Lau
 *
 */
public class Cell extends JPanel {
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1708246504520192881L;
	/** Represents the world. **/
	private World world;
	/** Represents the game object. **/
	private GameObject gameObject;
	/** Represents a position in the world. **/
	private Position pos;
	
	/** 
	 * Constructor of a cell.
	 * 
	 * @param x X-Coordinates
	 * @param y Y-Coordinates
	 * @param w World
	 */
	public Cell(int x, int y, World w) {
		init();
		world = w;
		pos = new Position(x,y);
	}
	
	/**
	 * Gets adjacent cells around the herbivore excluding
	 * cells that have herbivores.
	 * If there are adjacent cells with plant, return list of adjacent cells with plants only,
	 * otherwise, return empty adjacent cells.
	 * 
	 * @return the list of adjacent cells where the hervibore can move to.
	 */
	public ArrayList<Cell> getAdjacentCells() {
		Position tmpPos = new Position(pos.getX()-1, pos.getY()-1); // position, northwest, from the herbivore where for-loop starts.
		ArrayList<Cell> cellList = new ArrayList<Cell>();

		for (int i = tmpPos.getX(); i < tmpPos.getX() + 3; i++) {
			for (int j = tmpPos.getY(); j < tmpPos.getY() + 3; j++) {	
				if (i == pos.getX() && j == pos.getY()) { // main position of animal
					continue;
				}
				if (i < 0 || i >= world.getRowCount()) { // rows out of bounds
					continue;
				}
				if (j < 0 || j >= world.getColumnCount()) { // columns out of bounds
					continue;
				}	
				
				Cell tmpCell = world.getCellAt(i, j);
				cellList.add(tmpCell);
			}	
		}

		return cellList;
	}
	/**
	 * Gets the location of a cell.
	 * @return pos Position
	 */
	public Position locate() {
		return pos;
	}
	/**
	 * Initializes a cell with its color.
	 */
	public void init() {
		setColor();
	}
	/**
	 * Sets an object in the cell.
	 * @param obj GameObject
	 */
	public void setObject(GameObject obj) {
		gameObject = obj;
		setColor();
	}
	/**
	 * Sets the color of an empty cell to white.
	 */
	public void setColor() {
		Color color = Color.white;
		if (gameObject != null) {
			color = this.gameObject.color;
		}
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(color);
	}
	/**
	 * Gets a game object
	 * @return gameObject
	 */
	public GameObject getGameObj() {
		return gameObject;
	}
}
