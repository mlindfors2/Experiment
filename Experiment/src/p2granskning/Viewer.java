package p2granskning;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Viewer extends JPanel {
	private JLabel lblIcon = new JLabel();

	public Viewer(int width, int height) {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		lblIcon.setOpaque(true);
		add(lblIcon);
		setPreferredSize(new Dimension(width,height));
	}

	public Viewer(IconManager iconMan, int width, int height) {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		lblIcon.setOpaque(true);
		add(lblIcon);
		setPreferredSize(new Dimension(width,height));
		iconMan.addObserver(new IconManObserver());
	}

	public void setIcon(Icon icon) {
		lblIcon.setIcon(icon);
	}

	private class IconManObserver implements Observer {
		public void update(Observable o, Object arg) {
			Icon icon = (Icon) arg;
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					setIcon(icon);
				}
			});
		}
	}
}
