package lifeGame.gameObjects;

import java.awt.Color;

import lifeGame.edible.ICarnivoreFood;
import lifeGame.edible.IHerbivoreFood;
import lifeGame.edible.IOmnivoreFood;
import lifeGame.ui.GameObject;

/**
 * Represents a herbivore in the world.
 * @author Eddy Lau
 *
 */
public class Hervibore extends GameObject 
					   implements ICarnivoreFood, IOmnivoreFood {
	
	/** Represents the maximum lives. **/
	private static final int MAX_LIVES = 10;
	/** Represents the times that a herbivore can move. **/
	
	/**
	 * Constructor of the herbivore.
	 */
	public Hervibore() {
		super(MAX_LIVES);
		init();
	}
	/**
	 * Initializes the cell color of the herbivore.
	 */
	@Override
	public void init() {
		color = Color.yellow;
	}
	
	@Override
	protected boolean canEat(GameObject lifeForm) {
		return lifeForm instanceof IHerbivoreFood;
	}
	
	@Override
	protected boolean isSameLifeForm(GameObject lifeForm) {
		return lifeForm instanceof Hervibore;
	}
	
	@Override
	protected GameObject createLife() {
		GameObject newHerbivore = new Hervibore();
		
		return newHerbivore;
	}
	@Override
	protected boolean canGiveBirth(int countFoodCell, int countMatingCell,
			int countEmptyCell) {
		
		return countMatingCell >= 1 && countEmptyCell >= 1 && countFoodCell == 2;
	}
	
	
	
}
