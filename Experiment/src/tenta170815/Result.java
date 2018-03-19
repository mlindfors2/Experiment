package tenta170815;

public class Result {
	private String title;
	private int sum;
	private double average;

	public Result(String title, int sum, double average) {
		this.title = title;
		this.sum = sum;
		this.average = average;
	}

	public String getTitle() {
		return title;
	}

	public int getSum() {
		return sum;
	}

	public double getAverage() {
		return average;
	}

	public String toString() {
		return "(title=" + title + ", sum=" + sum + ", average=" + average + ")";
	}
}