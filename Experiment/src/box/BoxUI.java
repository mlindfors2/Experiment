package box;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

public class BoxUI extends JPanel {

	public JPanel contentPane = new JPanel();
	private BoxController controller = new BoxController();
	private JPanel mainPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	private JButton btnLeft = new JButton("Left");
	private JButton btnRight = new JButton("Right");
	private final JLabel label = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					createAndShowUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BoxUI() {
		this.setPreferredSize(new Dimension(600, 600));

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		contentPane.add(mainPanel, BorderLayout.CENTER);
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout());

		this.add(btnLeft);
		this.add(btnRight);

		btnLeft.addActionListener(new ButtonListener());
		btnRight.addActionListener(new ButtonListener());

		controller.scale(100);

		controller.rotate(Math.PI / 4, Math.atan(Math.sqrt(2)));

	}

	private static void createAndShowUI() {
		JFrame frame = new JFrame("BoxUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new BoxUI());
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);

	}

	public void paintComponent(Graphics gg) {
		super.paintComponents(gg);
		gg.setColor(Color.BLACK);
		Graphics2D g = (Graphics2D) gg;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		drawLines(g);
	}

	public void drawLines(Graphics g) {
		g.clearRect(0, 50, 800, 800);
		g.translate(getWidth() / 2, getHeight() / 2);
		double[][] nodes = controller.getNodes();
		int[][] edges = controller.getEdges();
		for (int i = 0; i < 12; i++) {
			double[] xy1 = nodes[edges[i][0]];
			double[] xy2 = nodes[edges[i][1]];
			g.drawLine((int) Math.round(xy1[0]), (int) Math.round(xy1[1]), (int) Math.round(xy2[0]),
					(int) Math.round(xy2[1]));
		}
		for (int i = 0; i < 8; i++) {
			g.fillRect((int) Math.round(nodes[i][0]) - 4, (int) Math.round(nodes[i][1]) - 4, 8, 8);
		}
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnLeft) {
				new Timer(16, (ActionEvent ex) -> {
					controller.rotate(Math.PI / 90, 0);
					repaint();
				}).start();

			} else if (e.getSource() == btnRight) {
				new Timer(16, (ActionEvent ex) -> {
					controller.rotate(Math.PI / -90, 0);
					repaint();
				}).start();

			}
		}

	}

}
