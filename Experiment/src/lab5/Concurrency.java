package f6;

import java.awt.Color;
import java.util.Random;

import javax.swing.JLabel;

public class Concurrency {
	private Random random = new Random();
	private Thread t1 = new Thread(new Change());
	private Thread t2 = new Thread(new Compare());
	private Color c;
	private JLabel lbl = new JLabel();
	private Color[] colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.WHITE, Color.GREEN};
	
	public Concurrency() {
		changeColor();
		t1.start();
		t2.start();
	}
	
	public Color getColor1() { 
		return c;
	}
	
	public Color getColor2() {
		return lbl.getBackground();
	}
	
//	public synchronized void changeColor() {
	public void changeColor() {
		c = colors[random.nextInt(colors.length)];
		lbl.setBackground(c);
	}
	
	private class Compare implements Runnable {
		public void run() {
			int count = 0, notSame=0;
			Color color1;
			Color color2;
			while(notSame<10 && count<100000) {
				count++;
//				synchronized(Concurrency.this) { // Kan man ersätta synchronized-satsen genom att synkronisera  
					color1 = getColor1();        // getColor1 och getColor2 i 
					color2 = getColor2();
//				}
				if(color1!=color2) {
					notSame++;
					System.out.println("count=" + count +": C1=" + color1 + "  C2=" + color2);
				}
			}
			System.out.println(this + " stopped, count="+count);
		}
	}
	
	private class Change implements Runnable {
		public void run() {
			int count = 0;
			while(t2.isAlive()) {
				count++;
			    changeColor();
			}
			System.out.println(this+" stopped, count="+count);
		}
	}
	
	public static void main(String[] args) {
		new Concurrency();
	}
}
