package p2granskning;


public class IconProducerManager{
	private Buffer<IconProducer> buffer;
	
	public IconProducerManager(Buffer<IconProducer> buffer) {
		this.buffer = buffer;
	}
	
	public void addIconProducer(IconProducer iconProd) {
		buffer.put(iconProd);
	}
}
