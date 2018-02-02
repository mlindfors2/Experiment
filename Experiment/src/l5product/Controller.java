package l5product;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Controller {
	private ProductUI productUI=new ProductUI(this);
	private ProductGenerator productGenerator = new ProductGenerator(10000);
	
	public Controller() {
		JFrame frame = new JFrame("Game results");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(productUI);
		frame.pack();
		frame.setVisible(true);
	}
		
	public void start() {
		int product;
		boolean ok = false;
		try {
			product = Integer.parseInt(productUI.getProduct());
			if(product>=1 && product<=10000) {
				ok = true;
				productGenerator.start(product);
			}
		} catch(NumberFormatException e) {}
		if(!ok) {
			System.out.println("Bad input: " + productUI.getProduct());
		}
	}

	public void stop() {
		productGenerator.stop();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Controller controller = new Controller();
			}
		});		
	}
}
