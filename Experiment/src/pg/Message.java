package pg;

import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.ImageIcon;

public class Message implements java.io.Serializable {
	private User sender;
	private ArrayList<User> receivers = new ArrayList<User>();
	private String text;
	private ImageIcon image;
	private String delivered;
	private String received;

	private Calendar cal = Calendar.getInstance();
	private int day = cal.get(Calendar.DAY_OF_MONTH);
	private int year = cal.get(Calendar.YEAR);
	private int month = cal.get(Calendar.MONTH);
	private int hour = cal.get(Calendar.HOUR_OF_DAY);
	private int minute = cal.get(Calendar.MINUTE);
	private int second = cal.get(Calendar.SECOND);
	private int millisec = cal.get(Calendar.MILLISECOND);

	//Dummy constructor with only text for testing
	public Message(String text) {
		this.text = text;
	}
	public Message(String text, User sender, ArrayList<User> receivers, String delivered, String received) {
		this.text = text;
		this.sender = sender;
		this.receivers = receivers;
		this.delivered = DeliveredDate();
		this.received = ReceivedDate();
	}

	public Message(String text, User sender, ArrayList<User> receivers, String delivered, String received,
			ImageIcon image) {
		this.text = text;
		this.sender = sender;
		this.receivers = receivers;
		this.delivered = DeliveredDate();
		this.received = ReceivedDate();
		this.image = image;

	}

	public Message(User sender, ArrayList<User> receivers, String delivered, String received, ImageIcon image) {
		this.sender = sender;
		this.receivers = receivers;
		this.delivered = DeliveredDate();
		this.received = ReceivedDate();
		this.image = image;

	}

	public String ReceivedDate() {

		received = year + ":" + (month + 1) + ":" + day + " " + hour + ":" + minute + ":" + second + ":" + millisec;
		return received;
	}

	public String DeliveredDate() {
		delivered = year + ":" + (month + 1) + ":" + day + " " + hour + ":" + minute + ":" + second + ":" + millisec;
		return delivered;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	}

	public User getSender() {
		return sender;
	}

	public ArrayList<User> getReceivers() {
		return receivers;
	}

	public String getText() {
		return text;
	}

	public ImageIcon getImage() {
		return image;
	}

	public String getDelivered() {
		return delivered;
	}

	public String getReceived() {
		return received;
	}
}