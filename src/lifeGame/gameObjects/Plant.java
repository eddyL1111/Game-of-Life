package lifeGame.gameObjects;

import java.awt.Color;
import java.util.ArrayList;

import lifeGame.edible.IHerbivoreFood;
import lifeGame.edible.IOmnivoreFood;
import lifeGame.ui.Cell;
import lifeGame.ui.GameObject;

/**
 * Represents a plant object in the world.
 * @author eddy
 *
 */
public class Plant extends GameObject 
				   implements IHerbivoreFood, IOmnivoreFood {
	
	private static final int MAX_LIVES = 10;

	
	/** 
	 * Constructor of plant.
	 */
	public Plant() {
		super(MAX_LIVES);
		init();
	}
	/**
	 * Initializes the plant object with color green.
	 */
	public void init() {
		color = Color.green;
	}
	
	@Override
	public boolean move() {
		if(updateEaten() == false) {
			return false;
		}
		addLives(-1);
		
		return true;
	}
	
	
	
	@Override
	public void eat(Cell nextCell) {		
	}
	
	@Override
	protected GameObject createLife() {
		GameObject newPlant = new Plant();
		
		return newPlant;
	}
	@Override
	protected boolean canEat(GameObject lifeForm) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	protected boolean isSameLifeForm(GameObject lifeForm) {
		return lifeForm instanceof Plant;
	}
	@Override
	protected boolean canGiveBirth(int countFoodCell, int countMatingCell,
			int countEmptyCell) {
		return countMatingCell >= 3 && countEmptyCell >= 2 && countFoodCell == 0;
	}
	

}
