//package com.zetcode;
package farm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel {// implements ActionListener {

	private Dimension d;
	private final Font smallFont = new Font("Helvetica", Font.BOLD, 14);
	private JButton knapp = new JButton("Start");
	public int x = 800;
	public int y = 600;
	LinkedList<Animal> animalList = new LinkedList<Animal>();
	
	
	
	
	
	private int choice = 0;
	private Image ii;
	private final Color dotColor = new Color(192, 192, 0);
	private Color mazeColor;

	private boolean inGame = false;
	private boolean dying = false;

	private final int BLOCK_SIZE = 24;
	private final int N_BLOCKS = 15;
	private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
	private final int PAC_ANIM_DELAY = 2;
	private final int PACMAN_ANIM_COUNT = 4;
	private final int MAX_GHOSTS = 12;
	private final int PACMAN_SPEED = 6;

	private int pacAnimCount = PAC_ANIM_DELAY;
	private int pacAnimDir = 1;
	private int pacmanAnimPos = 0;
	private int N_GHOSTS = 6;
	private int pacsLeft, score;
	private int[] dx, dy;
	private int[] ghost_x, ghost_y, ghost_dx, ghost_dy, ghostSpeed;

	private Graphics gg;
	private Image cowleft1, cowleft2, cowleft3;
	private Image pinkCow;
	private int pacman_x, pacman_y, pacmand_x, pacmand_y;
	private int req_dx, req_dy, view_dx, view_dy;

	private final short levelData[] = { 19, 26, 26, 26, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22, 21, 0, 0, 0, 17, 16,
			16, 16, 16, 16, 16, 16, 16, 16, 20, 21, 0, 0, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20, 21, 0, 0, 0,
			17, 16, 16, 24, 16, 16, 16, 16, 16, 16, 20, 17, 18, 18, 18, 16, 16, 20, 0, 17, 16, 16, 16, 16, 16, 20, 17,
			16, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 16, 24, 20, 25, 16, 16, 16, 24, 24, 28, 0, 25, 24, 24, 16, 20, 0,
			21, 1, 17, 16, 20, 0, 0, 0, 0, 0, 0, 0, 17, 20, 0, 21, 1, 17, 16, 16, 18, 18, 22, 0, 19, 18, 18, 16, 20, 0,
			21, 1, 17, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 20, 0, 21, 1, 17, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16,
			20, 0, 21, 1, 17, 16, 16, 16, 16, 16, 18, 16, 16, 16, 16, 20, 0, 21, 1, 17, 16, 16, 16, 16, 16, 16, 16, 16,
			16, 16, 20, 0, 21, 1, 25, 24, 24, 24, 24, 24, 24, 24, 24, 16, 16, 16, 18, 20, 9, 8, 8, 8, 8, 8, 8, 8, 8, 8,
			25, 24, 24, 24, 28 };

	private final int validSpeeds[] = { 1, 2, 3, 4, 6, 8 };
	private final int maxSpeed = 6;

	private int currentSpeed = 3;
	private short[] screenData;
	private Timer timer;

	public Board() {

		loadImages();

		initBoard();
		for (int i=0;i<100;i++) {
			animalList.add(new Cow(Math.random()*, Math.random(550)));
		}
 	}

	private void initBoard() {
		// addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.black);
		setDoubleBuffered(true);
		this.add(knapp);
		knapp.addActionListener(new ButtonListener());
	}

	public void paintComponent(Graphics gg) {
		super.paintComponents(gg);
		gg.setColor(Color.BLACK);
		Graphics2D g = (Graphics2D) gg;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(Color.GREEN);
		g.fillRect(100, 100, 800, 800);
		g.setColor(Color.WHITE);
		drawBuilding(g);
		drawCow(g);

		
	}

	private void loadImages() {
		cowleft1 = new ImageIcon("images/ko1.png").getImage();
		cowleft2 = new ImageIcon("images/ko2.png").getImage();
		cowleft3 = new ImageIcon("images/ko3.png").getImage();
		pinkCow = new ImageIcon("images/cowpink.jpg").getImage();
		// pinkCow = new ImageIcon(pinkCow.getImage().getScaledInstance(80, 80,
		// Image.SCALE_DEFAULT));
	}

	public void drawBuilding(Graphics2D g2d) {

		// g2d.setColor(Color.GREEN);
		//
		// g2d.fillRect(100, 100, 800, 800);
		// g2d.setColor(Color.WHITE);
		//
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				g2d.drawImage(pinkCow, col * 100, row * 100, col * 100 + 80, row * 100 + 80, 0, 0, 318, 159, null);
			}
		}
		// if (choice == 0) {
		// g2d.drawImage(cowleft1, x, y, x + 80, y + 80, 0, 0, 2120, 1800, null);
		// System.out.println("left 2 " + x + " " + y);
		// } else if (choice == 1) {
		// g2d.drawImage(cowleft2, x, y, x + 80, y + 80, 0, 0, 2120, 1800, null);
		// } else if (choice == 2) {
		// g2d.drawImage(cowleft3, x, y, x + 80, y + 80, 0, 0, 2120, 1800, null);
		// System.out.println("left 1 " + x);
		// }
		// if (choice < 2) {
		// choice++;
		// } else {
		// choice = 0;
		// }
	}

	public void drawCow(Graphics2D g2d) {

//		g2d.setColor(Color.GREEN);
//
//		g2d.fillRect(100, 100, 800, 800);
//		g2d.setColor(Color.WHITE);
		if (choice == 0) {
			g2d.drawImage(cowleft1, x, y, x + 80, y + 80, 0, 0, 2120, 1800, null);
			System.out.println("left 2 " + x);
		} else if (choice == 1) {
			g2d.drawImage(cowleft2, x, y, x + 80, y + 80, 0, 0, 2120, 1800, null);
		} else if (choice == 2) {
			g2d.drawImage(cowleft3, x, y, x + 80, y + 80, 0, 0, 2120, 1800, null);
			System.out.println("left 1 " + x);
		}
		if (choice < 2) {
			choice++;
		} else {
			choice = 0;
		}
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == knapp) {
				// System.out.println("kommer jag hit?");
				new Timer(100, (ActionEvent ex) -> {
					x = x - 2;
					y = y - 2;
					// System.out.println("listener" + x);
					if (x == 1) {
						x = 800;
					}
					if (y == 1) {
						y =600;
					}
					repaint();
				}).start();
			}
		}
	}
}