package lifeGame.gameObjects;

import java.awt.Color;

import lifeGame.edible.ICarnivoreFood;
import lifeGame.edible.IOmnivoreFood;
import lifeGame.ui.GameObject;

public class Carnivore extends GameObject implements IOmnivoreFood {

	/** Represents the maximum lives. **/
	private static final int MAX_LIVES = 3;
	
	public Carnivore() {
		super(MAX_LIVES);
		init();
	}

	@Override
	public void init() {
		color = Color.magenta;
		
	}

	@Override
	protected GameObject createLife() {
		GameObject newCarnivore = new Carnivore();
		return newCarnivore;
	}

	@Override
	protected boolean canEat(GameObject lifeForm) {
		return lifeForm instanceof ICarnivoreFood;
	}

	@Override
	protected boolean isSameLifeForm(GameObject lifeForm) {
		return lifeForm instanceof Carnivore;
	}

	@Override
	protected boolean canGiveBirth(int countFoodCell, int countMatingCell,
			int countEmptyCell) {
		return countMatingCell >= 1 && countEmptyCell >= 3 && countFoodCell == 3;
	}

}
