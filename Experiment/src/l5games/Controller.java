package l5games;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

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
			result.addObserver(new Innerclass());
			result.addObserver(new Innerclass2());
		} catch (IOException e) {
		}
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
		if (result != null) {
			result.startSimulation();
		}
	}

	@Override
	public void stop() {
		if (result != null) {
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

	private class Innerclass implements Observer {

		@Override
		public void update(Observable o, Object gameobject) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					Game game = (Game) gameobject;
					resultUI.newResult(game.toString());
				}
			});
		}
	}

	private class Innerclass2 implements Observer {

		@Override
		public void update(Observable o, Object gameobject) {
			Game game = (Game) gameobject;
			System.out.println(game.toString());
		}

	}
}
