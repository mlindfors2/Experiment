package tenta170815;

import java.util.ArrayList;
import java.util.LinkedList;

public class Uppgift5a {

	
	public LinkedList<Person> getList( ArrayList<Person> persons, int totalIncome ) {
		LinkedList<Person> newList = new LinkedList<Person>();
		for (int i=0;i<persons.size();i++) {
			if (persons.get(i).getTotalIncome() >= totalIncome) {
				newList.add(persons.get(i));
			}
		}
		return newList;
		
	
	}
	public void testCode() {
		ArrayList<Person> pList = new ArrayList<Person>();
		LinkedList<Person> list;
		pList.add(new Person("871023-1111","Adam",98700));
		pList.add(new Person("890304-2222","Eva",123100));
		pList.add(new Person("840110-3333","Emil",28300));
		pList.add(new Person("921210-4444","Ida",145200));
		pList.add(new Person("910504-5555","Nils",62100));
		list = getList(pList,100000);
		
	}
	public static void main (String [] args) {
		Uppgift5a u5 = new Uppgift5a();
		u5.testCode();
	}
}
