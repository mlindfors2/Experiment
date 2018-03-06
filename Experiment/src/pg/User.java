package pg;

import javax.swing.ImageIcon;

public class User implements java.io.Serializable {
	private String name;
	private long id;
	private ImageIcon picture;

	public User(String name, long id) {
		this.name = name;
		this.id = id;
	}

	public User(String name, ImageIcon picture, long id) {
		this.name = name;
		this.picture = picture;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public long getid() {
		return id;
	}

	public void setPicture(ImageIcon picture) {
		this.picture = picture;
	}

	public ImageIcon getPicture() {
		return picture;
	}
}
