package lifeGame.ui;

import java.awt.Color;
import java.util.ArrayList;

import lifeGame.edible.IHerbivoreFood;
import lifeGame.gameObjects.Hervibore;
import lifeGame.gameObjects.Plant;
import lifeGame.main.RandomGenerator;

/**
 * Represents a game object.
 * @author Eddy Lau
 *
 */
public abstract class GameObject implements Renderable{
	/** Represents the color white. **/
	public Color color = Color.white;
	/** Represents a cell **/
	protected Cell cell;
	
	private int max_lives;
	private int lives;
	
	public GameObject(int maxLives) {
		max_lives = maxLives;
		lives = max_lives;
	}
	
	
	/** 
	 * Sets the cell.
	 * @param c Cell
	 */
	public void setCell(Cell c) {
		cell = c;
	}
	
	protected abstract GameObject createLife();
	protected abstract boolean canEat(GameObject lifeForm);
	protected abstract boolean isSameLifeForm(GameObject lifeForm);
	
	public boolean updateEaten() {
		if (lives <= 0) {	// determines if the life form is out of lives (dead).
			cell.setObject(null);
			setCell(null);
			return false;
		}
		return true;
	}
	
	public Cell choosePosition(ArrayList<Cell> cellList) {
		int cellCount = cellList.size(); // how many adjacent cells are available before moving.
		int rand = RandomGenerator.nextNumber(cellCount); 
		Cell nextCell = cellList.get(rand);	// pick random available index to move.
		
		return nextCell;
	}
	
	public void eat(Cell nextCell) {
		if (canEat(nextCell.getGameObj())) { // If the herbivore moves to a plant, it is eaten
			resetLives();					  // and regains 4 moves.
		} else {
			addLives(-1);
		}
	}

	public ArrayList<Cell> getNeighboursForMove() {
		ArrayList<Cell> adjCells = cell.getAdjacentCells();
		ArrayList<Cell> emptyCellList = new ArrayList<Cell>();
		ArrayList<Cell> foodCellList = new ArrayList<Cell>();
		
		for(Cell tmpCell : adjCells) {
			if(tmpCell.getGameObj() == null) {
				emptyCellList.add(tmpCell);
				continue;
			}
			
			if (canEat(tmpCell.getGameObj())) {
				foodCellList.add(tmpCell); // adds plants to a list if there's any
			}
		}
		
		if (foodCellList.isEmpty()) {
			return emptyCellList;
		} 
		
		return foodCellList;
	}
	
	protected void resetLives() {
		lives = max_lives;
	}
	
	protected void addLives(int count) {
		lives += count;
	}
	
	protected GameObject giveBirth() {
		ArrayList<Cell> adjCells = cell.getAdjacentCells();
		ArrayList<Cell> emptyCells = new ArrayList<Cell>();
		int countFoodCell = 0;
		int countMatingCell = 0;
		
		
		for(Cell tmpCell : adjCells) {
			GameObject lifeForm = tmpCell.getGameObj();
			if(isSameLifeForm(lifeForm)) {
				countMatingCell++;
			}
			if(canEat(lifeForm)) {
				countFoodCell++;
			}
			if(lifeForm == null) {
				emptyCells.add(tmpCell);
			}
		}
		
		if(canGiveBirth(countFoodCell, countMatingCell, emptyCells.size())) {
			GameObject baby = createLife();
			Cell birthCell = choosePosition(emptyCells);
			baby.setCell(birthCell);
			birthCell.setObject(baby);
			
			return baby;
		}
		
		return null;
	}
	
	protected abstract boolean canGiveBirth
					(int countFoodCell, int countMatingCell, int countEmptyCell);
	
	public boolean move() {
		if(updateEaten() == false) {
			return false;
		}
		
		Cell nextCell = choosePosition(getNeighboursForMove());
		eat(nextCell);
		
		nextCell.setObject(this); 	// sets the selected object, herbivore, to the next cell.
		cell.setObject(null); 	// the herbivore where it was originally placed becomes null, since it moved.
		setCell(nextCell); 	// assigns the current cell to the object, herbivore.
		return true;	
	}
	
}
