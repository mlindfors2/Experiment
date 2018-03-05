package p1granskning;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * 
 * @author Oscar Andersson
 *Läser in ett textdokument där första raden är antalet gånger det ska köras
 * och andra raden är mellanrummet det ska läsas in.
 * Resterande rader är sökvägar till bilder. */
public class FileProducer implements IconProducer {

	private int delay;
	private int times;
	private Icon[] icons;
	private int currentIndex = -1;

	/**
	 * Läser in ett textdokument där första raden är antalet gånger det ska köras
	 * och andra raden är mellanrummet det ska läsas in.
	 * Resterande rader är sökvägar till bilder.
	 * @param filename sökvägen till filen
	 */
	public FileProducer(String filename) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			Object[] lines = br.lines().toArray();
			//Första raden är antalet gånger det ska köras
			times = Integer.parseInt(lines[0].toString());
			//Andra raden är delayen
			delay = Integer.parseInt(lines[1].toString());
			//Resten av raderna är ikonadresser
			icons = new Icon[lines.length - 2];

			for(int i = 2; i < lines.length; i++) {
				icons[i-2] = new ImageIcon(lines[i].toString());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * @return delayen mellan ikonerna
	 */
	public int delay() {
		return delay;
	}

	/**
	 * @return antalet gånger det ska köras
	 */
	@Override
	public int times() {
		return times;
	}

	/**
	 * @return antalet ikoner
	 */
	@Override
	public int size() {
		return (icons == null) ? 0 : icons.length;
	}

	/**
	 * @return nästa ikon i listan, om tom returneras null
	 */
	@Override
	public Icon nextIcon() {
		if(icons==null || icons.length==0) {
			System.out.println("no icons");
		    return null;
		}
		currentIndex = (currentIndex+1) % icons.length;
		return icons[currentIndex];	
	}
}
