package lifeGame.gameObjects;

import java.awt.Color;

import lifeGame.edible.IOmnivoreFood;
import lifeGame.ui.GameObject;

public class Omnivore extends GameObject {

	/** Represents the maximum lives. **/
	private static final int MAX_LIVES = 2;
	
	public Omnivore() {
		super(MAX_LIVES);
		init();
	}
	
	@Override
	public void init() {
		color = Color.blue;	
	}

	@Override
	protected GameObject createLife() {
		Omnivore newOmnivore = new Omnivore();
		
		return newOmnivore;
	}

	@Override
	protected boolean canEat(GameObject lifeForm) {
		return lifeForm instanceof IOmnivoreFood;
	}

	@Override
	protected boolean isSameLifeForm(GameObject lifeForm) {
		return lifeForm instanceof Omnivore;
	}

	@Override
	protected boolean canGiveBirth(int countFoodCell, int countMatingCell,
			int countEmptyCell) {
		return countMatingCell >= 1 && countEmptyCell >= 3 && countFoodCell == 3;
	}

}
