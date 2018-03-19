package tenta170815;

public class Uppgift7main {

	public static void main(String[] args) {
		int[] data1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		int[] data2 = { 4, 7, 6, 2 };
		Uppgift7 client = new Uppgift7();
		Result r1 = client.writeToServer("193.227.29.23", 34343, new Data("Test1", data1));
		System.out.println(r1);
		Result r2 = client.writeToServer("193.227.29.23", 34343, new Data("Test2", data2));
		System.out.println(r2);
	}
}
