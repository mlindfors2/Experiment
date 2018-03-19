package tenta170815;

public class Person {
	private String id;
	private String name;
	private int totalIncome;

	public Person(String id, String name, int totalIncome) {
		this.id = id;
		this.name = name;
		this.totalIncome = totalIncome;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getTotalIncome() {
		return totalIncome;
	}

	public String toString() {
		return "(id=" + id + ", name=" + name + ", totalIncome=" + totalIncome + ")";
	}
}