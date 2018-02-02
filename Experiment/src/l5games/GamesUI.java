package l5games;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GamesUI extends JPanel {
	private JTextArea taResult = new JTextArea();
	private ResultController controller;
	private JButton btnStart = new JButton("Start");
	private JButton btnStop = new JButton("Stop");
	
	public GamesUI(ResultController controller) {
		ActionListener al = new Listener();
		this.controller = controller;
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(400,600));

		JPanel pnlSouth = new JPanel(new GridLayout(1,3));
		JScrollPane scroll = new JScrollPane(taResult);
		pnlSouth.add(btnStart);
		pnlSouth.add(btnStop);
		add(scroll,BorderLayout.CENTER);
		add(pnlSouth,BorderLayout.SOUTH);
		btnStart.addActionListener(al);
		btnStop.addActionListener(al);
	}
	
	public void clear() {
		taResult.setText("");
	}
	
	public void newResult(String text) {
		taResult.insert(text+"\n", 0);
	}
	
	private class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnStart) {
				controller.start();
			} else if(e.getSource()==btnStop) {
				controller.stop();
			}
			
		}
	}
}
