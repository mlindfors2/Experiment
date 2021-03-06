package farm;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Chicken extends Animal{
	private Image chickenLeft, chickenRight;

	public Chicken(int x, int y) {
		super(x, y);
		loadImages();
	}

	public Chicken() {
		super();
		loadImages();
	}

	public void loadImages() {
		chickenLeft = new ImageIcon("images/chickenleft.png").getImage();
		chickenRight = new ImageIcon("images/chickenright.png").getImage();
	}

	public Image getNextAnimation() {
		Image animation = null;
		if (getX_direction() < 0) {
			if (getAnimation() == 0) {
				animation = chickenLeft;
			} else if (getAnimation() == 1) {
				animation = chickenLeft;
			} else if (getAnimation() == 2) {
				animation = chickenLeft;
			}
		} else if (getX_direction() > 0) {
			if (getAnimation() == 0) {
				animation = chickenRight;
			} else if (getAnimation() == 1) {
				animation = chickenRight;
			} else if (getAnimation() == 2) {
				animation = chickenRight;
			}
		}
		nextAnimation();
		return animation;
	}
}
