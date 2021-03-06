package farm;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Sheep extends Animal{
	private Image sheepLeft;
	private Image sheepRight;

	public Sheep(int x, int y) {
		super(x, y);
		loadImages();
	}

	public Sheep() {
		super();
		loadImages();
	}

	public void loadImages() {
		sheepLeft = new ImageIcon("images/sheepleft.png").getImage();
		sheepRight = new ImageIcon("images/sheepright.png").getImage();
	}

	public Image getNextAnimation() {
		Image animation = null;
		if (getX_direction() < 0) {
			if (getAnimation() == 0) {
				animation = sheepLeft;
			} else if (getAnimation() == 1) {
				animation = sheepLeft;
			} else if (getAnimation() == 2) {
				animation = sheepLeft;
			}
		} else if (getX_direction() > 0) {
			if (getAnimation() == 0) {
				animation = sheepRight;
			} else if (getAnimation() == 1) {
				animation = sheepRight;
			} else if (getAnimation() == 2) {
				animation = sheepRight;
			}
		}
		nextAnimation();
		return animation;
	}
}
