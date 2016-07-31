package lifeGame.ui;

import java.util.ArrayList;

import javax.swing.JPanel;

import lifeGame.gameObjects.Carnivore;
import lifeGame.gameObjects.Hervibore;
import lifeGame.gameObjects.Omnivore;
import lifeGame.gameObjects.Plant;
import lifeGame.main.RandomGenerator;

/**
 * Represents the world where all game objects
 * are initialized.
 * @author Eddy Lau
 *
 */
public class World extends JPanel {
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -8301009340811568485L;
	/** Represents the number of rows of the world. **/
	private int numRows;
	/** Represents the number of columns of the world. **/
	private int numCols;
	/** 2d-Array that holds the cell and its position. **/
	private Cell[][] cell;
	/** Array list for the s. **/
	private ArrayList<GameObject> lifeForms;
	/** Maximum chance of object creation. **/
	private static final int MAX_CHANCE = 99;
	/** Chance of a  to be created. **/
	private static final int HERBIVORE_CHANCE = 80;
	/** Chance of a plant to be Created. **/
	private static final int PLANT_CHANCE = 60;
	
	private static final int CARNIVORE_CHANCE =  50;
	private static final int OMNIVORE_CHANCE =  40;
	
	
	/**
	 * Constructor of the world.
	 * @param nr Number of rows
	 * @param nc Number of columns
	 */
	public World (int nr, int nc) {
		numRows = nr;
		numCols = nc;
	}
	/**
	 * Gets the number of rows.
	 * @return Number of rows.
	 */
	public int getRowCount() {
		return numRows;
	}
	/**
	 * Gets the number of columns.
	 * @return Number of columns.
	 */
	public int getColumnCount() {
		return numCols;
	}
	/**
	 * Each unique objects does its functions when the mouse 
	 * is clicked.
	 */
	public void takeTurn() {
		update();
	}
	
	public void update() {
		ArrayList<GameObject> newLifeFormsList = new ArrayList<GameObject>();
		for(int i = 0; i < lifeForms.size(); i++) {
			GameObject lifeForm = lifeForms.get(i);
			if (lifeForm.move() == false) { // if the  can't move (dead)
				lifeForms.remove(i); // then it's removed.
				i--; // fixing the index so that when the object is removed, the for-loop doesn't skip
					 // to the next index.
			} else {
				GameObject newLifeForm = lifeForm.giveBirth();
				
				if(newLifeForm != null) {
					newLifeFormsList.add(newLifeForm);
				}	
			}
		}
		
		lifeForms.addAll(newLifeFormsList);

	}
	
	/**
	 * Initializes all objects in the world.
	 */
	public void init() {	
		int rnd;
		cell = new Cell[numRows][numCols];
		lifeForms = new ArrayList<GameObject>();
		
		RandomGenerator.reset();
		for(int i = 0; i < numRows; i++) {	
			for(int j = 0; j < numCols; j++) {
				
				GameObject lifeForm = null;
				cell[i][j] = new Cell(i,j, this);
				rnd = RandomGenerator.nextNumber(MAX_CHANCE);
				
				if(rnd >= HERBIVORE_CHANCE){ 
					lifeForm = new Hervibore();
				} else if(rnd >= PLANT_CHANCE){
					lifeForm = new Plant();	
				} else if(rnd >= CARNIVORE_CHANCE){
					lifeForm = new Carnivore();
				} else if(rnd >= OMNIVORE_CHANCE){
					lifeForm = new Omnivore();
				}
				
				addLifeForm(lifeForm, i, j);
			}
		}
		
	}
	/**
	 * Gets cell at a certain position.
	 * @param row Row index
	 * @param col Column index
	 * @return A cell
	 */
	public Cell getCellAt(int row, int col) {
		return cell[row][col];
	}
	
	private void addLifeForm(GameObject lifeForm, int row, int col) {
		if(lifeForm == null)
			return;
		lifeForm.setCell(cell[row][col]); //  gives a cell reference to the life form.
		cell[row][col].setObject(lifeForm); // sets the life form into the cell.
		lifeForms.add(lifeForm); // add the life form to the lists of life forms.
	}
	
}
