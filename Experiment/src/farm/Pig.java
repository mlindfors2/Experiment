package farm;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Pig extends Animal {

	private Image pigLeft;
	private Image pigRight;

	public Pig(int x, int y) {
		super(x, y);
		loadImages();
	}

	public Pig() {
		super();
		loadImages();
	}

	public void loadImages() {
		pigLeft = new ImageIcon("images/pigleft.png").getImage();
		pigRight = new ImageIcon("images/pigright.png").getImage(); 
	}

	public Image getNextAnimation() {
		Image animation = null;
		if (getX_direction() < 0) {
			if (getAnimation() == 0) {
				animation = pigLeft;
			} else if (getAnimation() == 1) {
				animation = pigLeft;
			} else if (getAnimation() == 2) {
				animation = pigLeft;
			}
		} else if (getX_direction() > 0) {
			if (getAnimation() == 0) {
				animation = pigRight;
			} else if (getAnimation() == 1) {
				animation = pigRight;
			} else if (getAnimation() == 2) {
				animation = pigRight;
			}
		}
		nextAnimation();
		return animation;
	}
}
