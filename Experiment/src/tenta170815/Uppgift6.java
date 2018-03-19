package tenta170815;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Uppgift6 {

	
	
	
	public static Person[] readPersons(String filename) {
		
		int nbrPersons = 0;
		String[] id ;
		String[] name; 
		int[] totalIncome; 
		Person[] p = null;
		try(DataInputStream dis = new DataInputStream(new FileInputStream(filename))) {
			nbrPersons = dis.readInt();
			p = new Person[nbrPersons];
			
			for (int i=0;i<nbrPersons;i++) {
				p[i] = new Person(dis.readUTF(),dis.readUTF(), dis.readInt());
			}
			
			
		}catch (IOException e) { System.out.println(e.getMessage());}
		
		
		return p;
		
	}
	public void doSomeShit() {
		
	}
	
	public static void main(String []args) {
		Uppgift6 u6 = new Uppgift6();
		u6.doSomeShit();
	}
}
