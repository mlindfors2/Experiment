package p2granskning;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//import p1.IconManager;

public class P2Viewer extends JPanel{
	private JLabel lblIcon = new JLabel();

	public P2Viewer(int width, int height) {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		lblIcon.setOpaque(true);
		add(lblIcon);
		setPreferredSize(new Dimension(width,height));
		JPanel jp = new JPanel();		
	}

	public P2Viewer(IconClient iconClient, int width, int height) {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		lblIcon.setOpaque(true);
		add(lblIcon);
		setPreferredSize(new Dimension(width,height));
		iconClient.addIconClientInterface(new CallbackImpl());
	}
	
	private class CallbackImpl implements IconClientInterface{
		public void showIcon(Icon icon) {
			lblIcon.setIcon(icon);
		}
	}
}
