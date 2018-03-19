package tenta170815;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Uppgift7 {

	private int sum = 0;
	private double average = 0;

	public Result writeToServer(String ip, int port, Data dataItem) {
		try {
			Socket socket = new Socket(ip, port);

			DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			int[] myArray = dataItem.getData();
			dos.writeInt(myArray.length);
			for (int i = 0; i < myArray.length; i++) {
				dos.writeInt(myArray[i]);
			}
			dos.flush();
			sum = dis.readInt();
			average = dis.readDouble();
			socket.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return null;
		}
		Result result = new Result(dataItem.getTitle(), sum, average);
		return result;

	}

	
}
