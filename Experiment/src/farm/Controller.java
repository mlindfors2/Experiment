package farm;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Controller for the application. Connects the different classes with UIMain
 * 
 * @author Mikael Lindfors, Max Rudander, Elin Olsson, Malin Zederfeldt,
 *         Matthias Svensson Falk
 */
public class Controller {
	private EventHandler handler;
	private UIMain main;
	private Board board;
	private UIStartMenu startMenu;

	private int day = 1;
	private int cash = 15000000;
	private int x;
	private int y;

	private Random rand = new Random();

	public Controller() {
		new EventFileReader("files/testevent.txt");
		handler = EventHandler.getInstance();
		handler.setEffectMap(new EffectFileReader("files/testeffect.txt").getEffectMap());
		newGame(); // TA BORT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//		startMenu = new UIStartMenu(this);
	}

	/**
	 * Starts up an new game
	 */
	public void newGame() {
		board = new Board();
		main = new UIMain(this, board);
		setCommodityStart();
		setPropertyStart();
	}

	/**
	 * Handles the exit phase.
	 */
	public void exit() {
		if (JOptionPane.showConfirmDialog(null, "Abandon farm??", null,
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			System.exit(0);
		}

	}

	/**
	 * Handles the end of turn phase. Randomizes the sell/buy price
	 */
	public void endTurn() {
		main.editCommodity("Cow", 500 + (rand.nextInt(100) - 50), -1);
		main.editCommodity("Pig", 300 + (rand.nextInt(50) - 25), -1);
		main.editCommodity("Sheep", 200 + (rand.nextInt(50) - 25), -1);
		main.editCommodity("Chicken", 50 + (rand.nextInt(20) - 10), -1);
	}

	/**
	 * Adds Commodities and sets their prices. Should be called when application
	 * starts
	 */
	public void setCommodityStart() {
		main.addCommodity("Cow", 500, 0, new ImageIcon("images/ko1SMALL.png"));
		main.addCommodity("Pig", 300, 0, new ImageIcon("images/pigSMALL.jpg"));
		main.addCommodity("Sheep", 200, 0, new ImageIcon("images/sheepSMALL.jpg"));
		main.addCommodity("Chicken", 50, 0, new ImageIcon("images/chickenSMALL.jpg"));

	}

	public void setPropertyStart() {
		main.addProperty("Barn", 1000, 0, new ImageIcon("images/barnIcon.png"));
		main.addProperty("Pigsty", 1200, 0, new ImageIcon("images/pigstyIcon.png"));
		main.addProperty("Hen house", 700, 0, new ImageIcon("images/henhouseIcon.png"));
		main.addProperty("Stable", 1000, 0, new ImageIcon("images/StableIcon.png"));
	}

	/**
	 * Adds the selected animal to the board
	 * 
	 * @param name
	 *            name of animal that will be added
	 */
	public void buyCommodity(String name) {
		if (name == "Cow") {
			board.addAnimal(new Cow());
		}
		if (name == "Pig") {
			board.addAnimal(new Pig());
		}
		if (name == "Chicken") {
			board.addAnimal(new Chicken());
		}
		if (name == "Sheep") {
			board.addAnimal(new Sheep());
		}
	}

	/**
	 * Sells the selected animal and removes it from the board
	 * 
	 * @param name
	 *            name of animal that will be sold
	 */
	public void sellCommodity(String name) {
		if (name == "Cow") {
			board.removeAnimal(new Cow());
		}
		if (name == "Pig") {
			board.removeAnimal(new Pig());
		}
		if (name == "Chicken") {
			board.removeAnimal(new Chicken());
		}
		if (name == "Sheep") {
			board.removeAnimal(new Sheep());
		}
	}
	/**
	 * Adds selected building to board through x and y coordinate provided by player
	 * 
	 * @param name, x, y
	 */

	public void buyProperty(String name) {
		board.grid(true);
		x = Integer.parseInt(JOptionPane.showInputDialog(null, "x coordinate (0-800)"));
		y = Integer.parseInt(JOptionPane.showInputDialog(null, "y coordinate (0-800)"));
		if (name == "Barn") {
			if (x <= 800 && y <= 800 && x >= 0 && y >= 0) {
				board.addBuilding(new Barn(x, y));
				board.grid(false);
			} else {
				JOptionPane.showMessageDialog(null, "Imvalid input, try again!");
			}
		}
		if (name == "Pigsty") {

			if (x <= 800 && y <= 800 && x >= 0 && y >= 0) {
				board.addBuilding(new Pigsty(x, y));
				board.grid(false);
			} else {
				JOptionPane.showMessageDialog(null, "Imvalid input, try again!");
			}
		}
		if (name == "Hen house") {
			if (x <= 800 && y <= 800 && x >= 0 && y >= 0) {
				board.addBuilding(new HenHouse(x, y));
				board.grid(false);

			} else {
				JOptionPane.showMessageDialog(null, "Imvalid input, try again!");
			}
		}
		if (name == "Stable") {

			if (x <= 800 && y <= 800 && x >= 0 && y >= 0) {
				board.addBuilding(new Stable(x, y));
				board.grid(false);
			} else {
				JOptionPane.showMessageDialog(null, "Imvalid input, try again!");  

			}
		}
	}

	public void sellProperty(String name) {
		if (name == "Barn") {
			board.removeBuilding(new Barn());
		}
		if (name == "Pigsty") {
			board.removeBuilding(new Pigsty());
		}
		if (name == "Hen house") {
			board.removeBuilding(new HenHouse());
		}
		if (name == "Stable") {
			board.removeBuilding(new Stable());
		}
	}

	/**
	 * Returns the current day
	 * 
	 * @return day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * When called increases the day by one
	 */
	public void setDay() {
		this.day++;
	}

	/**
	 * Returns how much cash the player has
	 * 
	 * @return cash
	 */
	public int getCash() {
		return cash;
	}

	/**
	 * Method that checks if it should increase or decrease cash with amount
	 * 
	 * @param arithmetic
	 *            Can either be + or -
	 * @param amount
	 *            the amount cash will be changed with
	 */
	public void setCash(int amount) {
			cash = cash + amount;
	}

	public static void main(String[] args) {
		new Controller();
	}

}
