package p2granskning;

import javax.swing.Icon;

public interface IconProducer {
	public int delay();
	public int times();
	public int size();
	public Icon nextIcon();
}
