package farm;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Barn extends Building {

	private Image barn;
	
	
	public Barn(int x, int y) {
		super(x, y);
		loadImages();
	}

	public Barn() {
		super();
		loadImages();
	}
	public void loadImages() {
		barn = new ImageIcon("images/Barn.png").getImage();
	}
	public Image getImage() {
		return barn;
	}
}
