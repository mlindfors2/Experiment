package farm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Class that handles the animations in the mainpanel.
 * 
 * @author Mikael Lindfors, Max Rudander, Elin Olsson, Malin Zederfeldt,
 *         Matthias Svensson Falk
 *
 */
public class Board extends JPanel implements ActionListener {

	private int max_x = 800; // Original value 665
	private int max_y = 800; // OriginalValue 535
	private final int animalsize = 40;
	private final int buildingsize = 80;
	int choice = 0;
	private boolean grid = false;
	private Graphics2D g;
	private LinkedList<Animal> animalList = new LinkedList<Animal>();
	private LinkedList<Building> buildingList = new LinkedList<Building>();

	private Timer timer;
	private Animation animation;

	public Board() {
		initBoard();
	}

	/**
	 * Method that initilize the board.
	 */
	private void initBoard() {
		animation = new Animation(animalList, buildingList);
//		animation = new Animation();
		this.setBackground(new Color(130, 202, 112));
		setPreferredSize(new Dimension(800, 600));
		setDoubleBuffered(true);
		animation.loadImages();
		// addBarn(new Barn(100, 100));
		// addBarn(new Barn(100, 380));
		// addBarn(new Barn(380, 100));
		// addBarn(new Barn(380, 380));
		// // timer = new Timer(40, this);
		timer = new Timer(15, this);
		timer.start();
	}

	/**
	 * Adds an animal to the list with animals.
	 * 
	 * @param animal
	 *            - An object to be added
	 */
	public void addFence(Fence fence) {

		fence.setRestrictedArea(fence.getX1(), fence.getY1(), fence.getX2(), fence.getX1());
		for (int i = 0; i < buildingList.size(); i++) {
			int restrictedX = buildingList.get(i).getX();
			int restrictedY = buildingList.get(i).getY();
			fence.setRestrictedArea(restrictedX, restrictedY, restrictedX + buildingsize, restrictedY + buildingsize);
		}
		buildingList.add(fence);
	}

	public void addAnimal(Animal animal) {

		for (int i = 0; i < buildingList.size(); i++) {
			int x1 = buildingList.get(i).getX();
			int y1 = buildingList.get(i).getY();
			animal.setRestrictedAreaOutBound(x1, y1, x1 + buildingsize, y1 + buildingsize);
			animal.setRestrictedAreaInbound(0, 0, max_x, max_y);
		}
		animalList.add(animal);
	}

	public void addBarn(Barn barn) {
		barn.setRestrictedArea(barn.getX(), barn.getY(), barn.getX() + buildingsize, barn.getY() + buildingsize);
		buildingList.add(barn);
		for (int i = 0; i < animalList.size(); i++) {
			animalList.get(i).setRestrictedAreaOutBound(barn.getX(), barn.getY(), barn.getX() + buildingsize,
					barn.getY() + buildingsize);
		}
	}

	/**
	 * Remove an animal from the list with animals.
	 * 
	 * @param animal
	 *            - An object to be removed.
	 */
	public void removeAnimal(Animal animal) {
		if (animal instanceof Cow) {
			for (int i = 0; i < animalList.size(); i++) {
				if (animalList.get(i) instanceof Cow) {
					animalList.remove(i);
					break;
				}
			}

		}
		if (animal instanceof Pig) {
			for (int i = 0; i < animalList.size(); i++) {
				if (animalList.get(i) instanceof Pig) {
					animalList.remove(i);
					break;
				}
			}
		}
	}

	/**
	 * Method that draws the animations in the mainpanel.
	 * 
	 * @param gg
	 *            - Graphics where animations will be drawn.
	 */
	public void paintComponent(Graphics gg) {
		super.paintComponent(gg);
		
		g = (Graphics2D) gg;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHints(rh);

		animation.drawEdges(g);
		animation.drawAnimals(g);
		animation.drawBuildings(g);
		if (grid) {
			animation.drawGrid(g);
		}

	}

	public void grid(boolean status) {
		this.grid = status;
	}

	/**
	 * ActionListener that moves the animals every 40ms (25fps) and repaints the
	 * mainpanel.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {

		for (int i = 0; i < animalList.size(); i++) {
			animalList.get(i).move();
		}
		repaint();

	}
}
