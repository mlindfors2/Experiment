package p1granskning;

public class IconProducerManager {
	private Buffer<IconProducer> buffer;
	
	public IconProducerManager(Buffer<IconProducer> buffer) {
		this.buffer = buffer;
	}
	
	public void addIconProducer(IconProducer icon) {
		buffer.put(icon);
	}
}
