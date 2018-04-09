package farm;

import java.util.Random;
import javax.swing.JOptionPane;

public class Controller {
	private EventHandler handler;
	private UIMain main;
	private Board board;

	private int day = 1;
	private int cash = 15000000;
	
	private Random rand = new Random();
	
	public Controller () {
		new EventFileReader("files/testevent.txt");
		handler = EventHandler.getInstance();
		handler.setEffectMap(new EffectFileReader("files/testeffect.txt").getEffectMap());
		board = new Board();
		main = new UIMain(this, board);
		setCommodityStart();
		
	}
	/**
	 * Handles the exit phase.
	 */
	public void exit () {
		if(JOptionPane.showConfirmDialog(null, "Abandon farm??",  null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
		
	}
	/**
	 * Handles the end of turn phase. Randomizes the sell/buy price
	 */
	public void endTurn() {
		main.editCommodity("Cow", 500+(rand.nextInt(100)-50), -1);
		main.editCommodity("Pig", 300+(rand.nextInt(50)-25), -1);
		main.editCommodity("Horse", 2000+(rand.nextInt(500)-250), -1);
		main.editCommodity("Sheep", 200+(rand.nextInt(50)-25), -1);
		main.editCommodity("Chicken", 50+(rand.nextInt(20)- 10), -1);
	}
	/**
	 * Adds Commoditys and sets their prices. Should be called when application starts
	 */
	public void setCommodityStart() {
		main.addCommodity("Cow", 500, 0);
		main.addCommodity("Pig", 300, 0);
		main.addCommodity("Sheep", 200, 0);
		main.addCommodity("Chicken", 50, 0);
	}
    /**
     * Returns the current day
     * @return day
     */
	public int getDay() {
		return day;
	}

    /**
     * When called increases the day by one
     */
	public void setDay() {
		this.day ++;
	}

    /**
     * Returns how much cash the player has
     * @return cash
     */
	public int getCash() {
		return cash;
	}

    /**
     * Method that checks if it should increase or decrease cash with amount
     * @param arithmetic Can either be + or -
     * @param amount the amount cash will be changed with
     */
	public void setCash(char arithmetic, int amount) {
		if (arithmetic == '-'){
			cash = cash - amount;
		}
		if (arithmetic == '+'){
			cash = cash + amount;
		}
	}
	
	public static void main (String[] args) {
		new Controller();
	}

}
