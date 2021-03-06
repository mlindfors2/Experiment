package farm;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Cow extends Animal{
	
	Image cowLeft1; 
	Image cowLeft2; 
	Image cowLeft3;
	Image cowRight1;
	Image cowRight2;
	Image cowRight3;
	
	public Cow( int x , int y) {
		super(x,y);
		loadImages();
	}
	public Cow() {
		super();
		loadImages();
	}
	public void loadImages() {
		cowLeft1 = new ImageIcon("images/ko1.png").getImage();
		cowLeft2 = new ImageIcon("images/ko2.png").getImage();
		cowLeft3 = new ImageIcon("images/ko3.png").getImage();

		cowRight1 = new ImageIcon("images/ko1right.png").getImage();
		cowRight2 = new ImageIcon("images/ko2right.png").getImage();
		cowRight3 = new ImageIcon("images/ko3right.png").getImage();
	}
	
	public Image getNextAnimation() {
		Image animation = null;
		if (getX_direction() < 0) {
			if (getAnimation() == 0) {
				animation = cowLeft1;
			} else if (getAnimation() == 1) {
				animation = cowLeft2;
			} else if (getAnimation() == 2) {
				animation = cowLeft3;
			}
		} else if (getX_direction() > 0) {
			if (getAnimation() == 0) {
				animation = cowRight1;
			} else if (getAnimation() == 1) {
				animation = cowRight2;
			} else if (getAnimation() == 2) {
				animation = cowRight3;
			}
		}
		nextAnimation();
		return animation;
	
	
	
	}
	
	
}
