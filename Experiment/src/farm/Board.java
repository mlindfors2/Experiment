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

	// public int x = 800; // Original value 800
	// public int y = 800; // Original value 600
	private int animalsize = 40;
	private int buildingsize = 80;

	private int max_x = 800; // Original value 665
	private int max_y = 800; // OriginalValue 535
	int choice = 0;
	private boolean grid = false;
	private LinkedList<Animal> animalList = new LinkedList<Animal>();
	private LinkedList<Building> buildingList = new LinkedList<Building>();
	private Image cowleft1, cowleft2, cowleft3, pinkcow, barnpic, cowright1, cowright2, cowright3;
	private Timer timer;

	public Board() {
		initBoard();
	}

	/**
	 * Method that loads all images used for animations.
	 */
	private void loadImages() {
		cowleft1 = new ImageIcon("images/ko1.png").getImage();
		cowleft2 = new ImageIcon("images/ko2.png").getImage();
		cowleft3 = new ImageIcon("images/ko3.png").getImage();

		cowright1 = new ImageIcon("images/ko1right.png").getImage();
		cowright2 = new ImageIcon("images/ko2right.png").getImage();
		cowright3 = new ImageIcon("images/ko3right.png").getImage();
		barnpic = new ImageIcon("images/Barn.png").getImage();
	}

	/**
	 * Method that initilize the board.
	 */
	private void initBoard() {

		this.setBackground(new Color(130, 202, 112));
		setPreferredSize(new Dimension(800, 600));
		setDoubleBuffered(true);
		loadImages();
		addBarn(new Barn(100, 100));
		addBarn(new Barn(100, 380));
		addBarn(new Barn(380, 100));
		addBarn(new Barn(380, 380));
		// timer = new Timer(40, this);
		timer = new Timer(15, this);
		timer.start();
	}

	/**
	 * Adds an animal to the list with animals.
	 * 
	 * @param animal
	 *            - An object to be added
	 */
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
		Graphics2D g = (Graphics2D) gg;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHints(rh);

		drawEdges(g);
		drawAnimals(g);
		drawBuildings(g);
		if (grid) {
			drawGrid(g);
		}

	}

	public void grid(boolean status) {
		this.grid = status;
	}

	public void drawGrid(Graphics2D g) {
		int gridSize = max_x / 20;
		boolean onBuilding = false;
		for (int x = 0; x < max_x; x = x + gridSize) {
			ArrayList list = new ArrayList();
			for (int buildingIndex = 0; buildingIndex < buildingList.size(); buildingIndex++) {
				RestrictedArea area = buildingList.get(buildingIndex).getRestricedArea();
				for (int y = 0; y < max_y; y++) {
					if (!area.check(x, y) && !onBuilding) {
						list.add(y);
						onBuilding = true;
					} else if (area.check(x, y) && onBuilding) {
						list.add(y);
						onBuilding = false;
					}
				}
			}
			if (!list.isEmpty()) {
				g.drawLine(x, 0, x, (int) list.get(0));
				for (int i = 1; i < list.size() - 2; i++) {
					g.drawLine(x, (int) list.get(i), x, (int) list.get(i + 1));
				}
				g.drawLine(x, (int) list.get(list.size() - 1), x, max_y);
			} else {
				g.drawLine(x, 0, x, max_y);
			}
		}
		for (int y = 0; y < max_y; y = y + gridSize) {
			ArrayList list = new ArrayList();
			for (int buildingIndex = 0; buildingIndex < buildingList.size(); buildingIndex++) {
				RestrictedArea area = buildingList.get(buildingIndex).getRestricedArea();
				for (int x = 0; x < max_x; x++) {
					if (!area.check(x, y) && !onBuilding) {
						list.add(x);
						onBuilding = true;
					} else if (area.check(x, y) && onBuilding) {
						list.add(x);
						onBuilding = false;
					}
				}
			}
			if (!list.isEmpty()) {
				g.drawLine(0, y, (int) list.get(0), y);
				for (int i = 1; i < list.size() - 2; i++) {
					g.drawLine((int) list.get(i), y, (int) list.get(i + 1), y);
				}
				g.drawLine((int) list.get(list.size() - 1), y, max_x, y);
			} else {
				g.drawLine(0, y, max_x, y);
			}
		}
	}

	/**
	 * Method that draws buildings in the mainpanel
	 * 
	 * @param g
	 *            - Graphics g where animations will be drawn.
	 */

	public void drawBuildings(Graphics2D g) {
		for (int i = 0; i < buildingList.size(); i++) {
			if (buildingList.get(i) instanceof Barn) {
				Barn barn = (Barn) buildingList.get(i);
				g.drawImage(barnpic, barn.getX(), barn.getY(), barn.getX() + buildingsize, barn.getY() + buildingsize,
						0, 0, 856, 638, this);
			}
		}
	}

	public void drawAnimals(Graphics2D g) {
		for (int i = 0; i < animalList.size(); i++) {
			if (animalList.get(i) instanceof Cow) {
				Cow cow = (Cow) animalList.get(i);
				Image cowAnimation = null;
				if (cow.getX_direction() < 0) {
					if (cow.getAnimation() == 0) {
						cowAnimation = cowleft1;
					} else if (cow.getAnimation() == 1) {
						cowAnimation = cowleft2;
					} else if (cow.getAnimation() == 2) {
						cowAnimation = cowleft3;
					}
					g.drawImage(cowAnimation, cow.getX(), cow.getY(), cow.getX() + animalsize, cow.getY() + animalsize,
							0, 0, 2120, 1800, this);
				} else if (cow.getX_direction() > 0) {
					if (cow.getAnimation() == 0) {
						cowAnimation = cowright1;
					} else if (cow.getAnimation() == 1) {
						cowAnimation = cowright2;
					} else if (cow.getAnimation() == 2) {
						cowAnimation = cowright3;
					}
					g.drawImage(cowAnimation, cow.getX(), cow.getY(), cow.getX() + animalsize, cow.getY() + animalsize,
							0, 0, 2120, 1800, this);
				}
				cow.nextAnimation();
			} else if (animalList.get(i) instanceof Pig) {

			}
		}

	}

	/**
	 * Method that draws a border where the animals can wander around.
	 * 
	 * @param g
	 *            - Graphics2D where the rectangle will be drawn.
	 */
	public void drawEdges(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, max_x, max_y);
	}

	public void drawBorder(Graphics2D g, int x1, int y1, int x2, int y2) {
		g.setColor(Color.BLACK);
		g.drawRect(x1, y1, x2 - x1, y2 - y1);
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
