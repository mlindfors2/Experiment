package p2granskning;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * FileProducer implements the IconProducer interface in a way that allows it to read from files 
 * @author Daniel Rosdahl
 *
 */
public class FileProducer implements IconProducer {
	private Icon[] icons = new Icon[10];
	private int delay;
	private int times;
	private int currentIndex = -1;
	
	/**
	 * Constructor that uses streams to read the information from filename
	 * @param filename
	 */
	public FileProducer(String filename){
		try (BufferedReader bw = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"UTF-8")) ) {
			String line = bw.readLine();
			this.times = Integer.parseInt(line);
			line = bw.readLine();
			this.delay = Integer.parseInt(line);
			line = bw.readLine();
			
			int i = 0;
			String iconText;
			while(line!=null) {
				iconText = line;
				icons[i] = new ImageIcon(iconText);
				line = bw.readLine();
				
				i++;
			}
		} catch (IOException e) {}
	}
	
	/**
	 * @return delay the time pauses will have 
	 */
	@Override
	public int delay() {
		return this.delay;
	}

	/**
	 * @return times the amount of times icons should be repeated
	 */
	@Override
	public int times() {
		return this.times;
	}
	
	/**
	 * checks to see if the size is null, if so: returns 0, if not: returns the size of the icons array 
	 * @return the size of icons array
	 */
	@Override
	public int size() {
		return (icons==null) ? 0 : icons.length;
	}
	
	/**
	 * returns the next icon in line from the icons array
	 * @return next icon in line 
	 */
	@Override
	public Icon nextIcon() {
		if(icons==null || icons.length==0)
		    return null;
		currentIndex = (currentIndex+1) % icons.length;
		return icons[currentIndex];
	}

}
