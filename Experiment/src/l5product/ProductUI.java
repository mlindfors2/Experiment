package l5product;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ProductUI extends JPanel {
	private JTextArea taResult = new JTextArea();
	private Controller controller;
	private JButton btnStart = new JButton("Start");
	private JButton btnStop = new JButton("Stop");
	private JTextField tfProduct = new JTextField();
	
	public ProductUI(Controller controller) {
		this.controller = controller;
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(400,600));

		JScrollPane scroll = new JScrollPane(taResult);
		add(scroll,BorderLayout.CENTER);
		add(controlPanel(),BorderLayout.SOUTH);
	}
	
	private JPanel controlPanel() {
		JPanel panel = new JPanel(new GridLayout(2,1));
		ActionListener al = new ButtonListener();
		
		JPanel pnlProduct = new JPanel(new BorderLayout());
		pnlProduct.add(new JLabel("Produkt (1-10000):"),BorderLayout.WEST);
		pnlProduct.add(tfProduct,BorderLayout.CENTER);
		
		JPanel pnlButtons = new JPanel(new GridLayout(1,3));
		pnlButtons.add(btnStart);
		pnlButtons.add(btnStop);
		btnStart.addActionListener(al);
		btnStop.addActionListener(al);
		
		panel.add(pnlProduct);
		panel.add(pnlButtons);
		return panel;
	}
	
	public void clear() {
		taResult.setText("");
	}
	
	public void append(String text) {
		taResult.append(text + "\n");
	}
	
	public String getProduct() {
		return tfProduct.getText();
	}
	
	private class ButtonListener implements ActionListener {		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnStart) {
				controller.start();
			} else if(e.getSource()==btnStop) {
				controller.stop();
			}			
		}
	}
	
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, new ProductUI(null));
	}
}
