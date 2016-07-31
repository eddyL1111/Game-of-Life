package lifeGame.ui;
import java.awt.GridLayout;

import javax.swing.JFrame;

import lifeGame.main.TurnListener;

/**
 * Draws the frame of the game and adds the panels
 * of the cells.
 * @author Eddy Lau
 *
 */
public class GameFrame
    extends JFrame
{
	/**
	 * Serial version of the UID.
	 */
	private static final long serialVersionUID = 1L;
	/** Represents the world. **/
    private final World world;
    /**
     * Constructor for the GameFrame.
     * @param w World
     */
    public GameFrame(final World w)
    {
        world = w;
    }
    /**
     * Initializes the world by drawing a grid consisting
     * of cells.
     */
    public void init()
    {
        setTitle("Assignment 2a");
        setLayout(new GridLayout(world.getRowCount(),
                                 world.getColumnCount()));

        for(int row = 0; row < world.getRowCount(); row++)
        {
            for(int col = 0; col < world.getColumnCount(); col++)
            {
                add(world.getCellAt(row,
                                    col));
            }
        }

        addMouseListener(new TurnListener(this));
    }
    /**
     * Each game object will do their own functions
     * after each turn and then be repainted in the 
     * game frame.
     */
	public void takeTurn()
    {
        world.takeTurn();
        repaint();
    }
}
