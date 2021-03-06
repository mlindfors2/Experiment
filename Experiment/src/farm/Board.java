package farm;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.awt.image.DataBufferInt;
import java.awt.image.WritableRaster;

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
	private final int MAX_X = 800; // Original value 665
	private final int MAX_Y = 800; // OriginalValue 535
	private final int ANIMALSIZE = 40;
	private final int BUILDINGSIZE = 80;
	private final int fire_x = 80;
	private final int fire_y = 400;
	private boolean grid = false;
	private Graphics2D g;
	private LinkedList<Animal> animalList = new LinkedList<Animal>();
	private LinkedList<Building> buildingList = new LinkedList<Building>();
	private ArrayList<Fence> fenceList = new ArrayList<Fence>();
	private Timer timer;
	private int[] data;
	private int[] data2;
	private int[] palette = new int[256];

	private BufferedImage canvas;

	public Board() {
		initBoard();
	}

	/**
	 * Method that initilize the board.
	 */
	private void initBoard() {
		this.setBackground(new Color(130, 202, 112));
		setPreferredSize(new Dimension(800, 600));
		setDoubleBuffered(true);
		addFence(100,100,300,300); //#1
		addFence(400,100,600,300); //#2
		addFence(100,400,300,600); //#3
		addBuilding(new Barn(400, 400));
		
		for (int i = 0;i<1;i++) {
			addAnimal(new Cow(150,150));
		}
		for (int i = 0;i<1;i++) {
			addAnimal(new Pig(450,150));
		}
		for (int i = 0;i<1;i++) {
			addAnimal(new Sheep(150,450));
		}
		this.palette = generatePalette();
		canvas = new BufferedImage(fire_x, fire_y, BufferedImage.TYPE_INT_ARGB_PRE);
		data = ((DataBufferInt) canvas.getAlphaRaster().getDataBuffer()).getData();
		data2 = new int[fire_x * (fire_y + 6)];
		timer = new Timer(25, this);
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
			animal.setRestrictedAreaOutBound(x1, y1, x1 + BUILDINGSIZE, y1 + BUILDINGSIZE);
		}
		for (int i=0;i<fenceList.size();i++) {
			int x1 = fenceList.get(i).getX1();
			int y1 = fenceList.get(i).getY1();
			int x2 = fenceList.get(i).getX2();
			int y2 = fenceList.get(i).getY2();
			animal.setRestrictedAreaInbound(x1, y1, x2, y2);
			animal.setRestrictedAreaOutBound(x1, y1, x2, y2);
		}
		
		animal.setRestrictedAreaInbound(0, 0, MAX_X, MAX_Y);
		animalList.add(animal);
	}

	public void addBuilding(Building building) {
		building.setRestrictedArea(building.getX(), building.getY(), building.getX() + BUILDINGSIZE,
				building.getY() + BUILDINGSIZE);
		buildingList.add(building);
		for (int i = 0; i < animalList.size(); i++) {
			animalList.get(i).setRestrictedAreaOutBound(building.getX(), building.getY(),
					building.getX() + BUILDINGSIZE, building.getY() + BUILDINGSIZE);
		}
		//Adds fence to the building
//		addFence(building.getX()-100,building.getY()-100, building.getX()+buildingsize+100, building.getY()+buildingsize+100);
		
	}
	
	
	public void addFence(int x1, int y1, int x2, int y2) {
		Fence fence = new Fence(x1, y1, x2, y2);
		//Detta förstör tydligen inte
		fence.setRestrictedArea(x1, y1, x2, y2);
		fenceList.add(fence);
		for (int i=0;i<animalList.size();i++) {
			//Detta förstör tydligen inte
			animalList.get(i).setRestrictedAreaInbound(x1, y1, x2, y2);
		}
	}


	
	
	public int[] generatePalette() {
		int color;
		int[] palette = new int[256];
		for (int i = 1; i < 256; i++) {
			float saturation = i > 96 ? 0 : 1f - i / 128f;
			palette[i] = Color.HSBtoRGB(i / 576f, saturation, Math.min(1f, i / 48f));
			// palette[i] = Color.HSBtoRGB(i / 3, saturation, Math.min(1f, i / 48f));
		}
		return palette;
	}

	public void drawFire(Graphics2D g, int[] palette, int xpos, int ypos) {
		// width 80,height 80
		Random rand = new Random();
		for (int x = 0; x < fire_x; x++) {
			data2[x + fire_x * (fire_y - 2)] = Math.random() > 0.55 ? 0 : 255;
		}
		for (int y = 0; y < fire_y - 2; y++) {
			for (int x = 0; x < fire_x - 2; x++) {
				data2[x + fire_x * y] = (int) ((int) ((data2[x + fire_x * y] + data2[x + fire_x * (y + 1)]
						+ data2[(x - 1) + fire_x * (y + 1)] + data2[(x + 1) + fire_x * (y + 1)]
						+ data2[x + fire_x * (y + 2)]) / 5.045) * 1.01);
			}
		}
		for (int y = 0; y < data.length; y++) {
			data[y] = palette[data2[y]];
		}
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.5));
		g.drawImage(canvas, xpos, ypos, fire_x, fire_y, null);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1));
	}

		public void drawFence(Graphics2D g) {
		for (int i = 0; i < fenceList.size(); i++) {
			Fence fence = fenceList.get(i);
			g.setStroke(new BasicStroke(2));
			
			g.drawRect(fence.getX1(), fence.getY1(), fence.getX2() - fence.getX1(), fence.getY2() - fence.getY1());
		}

	}

	
	/**
	 * Method that draws buildings in the mainpanel
	 * 
	 * @param g
	 *            - Graphics g where animations will be drawn.
	 */
	public void drawBuildings(Graphics2D g) {
		Image animation = null;
		for (int i = 0; i < buildingList.size(); i++) {
			Building building = buildingList.get(i);
			animation = building.getImage();
			g.drawImage(animation, building.getX(), building.getY(), building.getX() + BUILDINGSIZE,
					building.getY() + BUILDINGSIZE, 0, 0, animation.getWidth(this), animation.getHeight(this), this);
		}
	}

	public void drawAnimals(Graphics2D g) {
		Image animation = null;
		for (int i = 0; i < animalList.size(); i++) {
			Animal animal = animalList.get(i);
			animation = animal.getNextAnimation();
			g.drawImage(animation, animal.getX(), animal.getY(), animal.getX() + ANIMALSIZE, animal.getY() + ANIMALSIZE,
					0, 0, animation.getWidth(this), animation.getHeight(this), this);
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
		g.drawRect(0, 0, MAX_X, MAX_Y);
	}

	public void drawBorder(Graphics2D g, int x1, int y1, int x2, int y2) {
		g.setColor(Color.BLACK);
		g.drawRect(x1, y1, x2 - x1, y2 - y1);
	}

	/**
	 * Method that loads all images used for animations.
	 */
	public void grid(boolean status) {
		this.grid = status;
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
		if (animal instanceof Sheep) {
			for (int i = 0; i < animalList.size(); i++) {
				if (animalList.get(i) instanceof Sheep) {
					animalList.remove(i);
					break;
				}
			}
		}
		if (animal instanceof Chicken) {
			for (int i = 0; i < animalList.size(); i++) {
				if (animalList.get(i) instanceof Chicken) {
					animalList.remove(i);
					break;
				}
			}
		}
	}
	public void drawGrid(Graphics2D g) {
		int gridSize = MAX_X / 20;
		boolean onBuilding = false;
		for (int x = 0; x < MAX_X; x = x + gridSize) {
			ArrayList list = new ArrayList();
			for (int buildingIndex = 0; buildingIndex < buildingList.size(); buildingIndex++) {
				RestrictedArea area = buildingList.get(buildingIndex).getRestrictedArea();
				for (int y = 0; y < MAX_Y; y++) {
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
				g.drawLine(x, (int) list.get(list.size() - 1), x, MAX_Y);
			} else {
				g.drawLine(x, 0, x, MAX_Y);
			}
		}
		for (int y = 0; y < MAX_Y; y = y + gridSize) {
			ArrayList list = new ArrayList();
			for (int buildingIndex = 0; buildingIndex < buildingList.size(); buildingIndex++) {
				RestrictedArea area = buildingList.get(buildingIndex).getRestrictedArea();
				for (int x = 0; x < MAX_X; x++) {
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
				g.drawLine((int) list.get(list.size() - 1), y, MAX_X, y);
			} else {
				g.drawLine(0, y, MAX_X, y);
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
		drawEdges(g);
		drawAnimals(g);
//		drawFire(g, palette, 300, 150);
		drawBuildings(g);
		drawFence(g);
		if (grid) {
			drawGrid(g);
		}

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

	public void removeBuilding(Building House) {
		// TODO Auto-generated method stub
		
	}

	
}