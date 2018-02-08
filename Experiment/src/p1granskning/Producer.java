package p1granskning;

import javax.swing.Icon;

/**
 * Hämtar ut ikoner ur IconProducerklasser i ett Buffer och sätter dem i en ikonbuffer med en viss delay mellan varje ikon.
 * @author Oscar Andersson
 *
 */
public class Producer extends Thread {
	private Buffer<IconProducer> prodBuffer;
	private Buffer<Icon> iconBuffer;

	/**
	 * 
	 * @param prodBuffer Buffer med IconProducers som ska läsas av
	 * @param iconBuffer Buffer med Icons som ska fyllas
	 */
	public Producer(Buffer<IconProducer> prodBuffer, Buffer<Icon> iconBuffer) {
		this.prodBuffer = prodBuffer;
		this.iconBuffer = iconBuffer;
	}

	/**
	 * Kör tråden och går genom varje IconProducers ikon och sätter in i Icon-buffret. Efter varje ikoninsättning vilar tråden en viss stund.
	 */
	public void run() {
		try {
			boolean hasLeft = true;
			while(hasLeft) {
				IconProducer ip = prodBuffer.get();
				for (int j = 0; j < ip.times(); j++) {
					for (int i = 0; i < ip.size(); i++) {
						iconBuffer.put(ip.nextIcon());
						sleep(ip.delay());
					}
				}
				if(prodBuffer.size() == 0) 
					hasLeft = false;
			}
			System.out.println("Program slut");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}