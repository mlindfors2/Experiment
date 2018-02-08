package l5games;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Controller implements ResultController {
	private GamesUI resultUI;
	private GameResults result;
	
	public Controller() {
		try {
		    result = new GameResults("files/games.txt");
		    resultUI = new GamesUI(this);
			showFrame(resultUI);
		} catch(IOException e) {}
	}

	private void showFrame(JPanel panel) {
		JFrame frame = new JFrame("Game results");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	@Override
	public void start() {
		if(result!=null) {
		    result.startSimulation();
		}
	}
	
	@Override
	public void stop() {
		if(result!=null) {
			result.stopSimulation();
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Controller controller = new Controller();
			}
		});		
	}
}
