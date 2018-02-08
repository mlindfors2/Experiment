package p1granskning;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Viewer extends JPanel implements Observer {
	private JLabel lblIcon = new JLabel();
	private IconManager im;

	public Viewer(int width, int height) {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		lblIcon.setOpaque(true);
		add(lblIcon);
		setPreferredSize(new Dimension(width, height));
	}

	public Viewer(IconManager im, int width, int height) {
		this(width, height);
		this.im = im;
		this.im.addObserver(this);
	}

	public void setIcon(Icon icon) {
		lblIcon.setIcon(icon);
	}

	@Override
	public void update(Observable o, Object arg) {
		setIcon(((Icon) arg));
	}
}
