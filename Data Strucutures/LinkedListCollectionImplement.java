import java.util.LinkedList;

public class LinkedListCollectionImplement {
	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<String>();
		
		// Add some elements
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		// display
		System.out.println(list);
		
		// add to first position
		list.addFirst("E");
		list.add("F");
		System.out.println(list);
		
		// remove the last element
		list.removeLast();
		System.out.println(list);
		
		// remove the first element
		list.removeFirst();
		System.out.println(list);
		
		// check if value exists in linked list
		System.out.println(list.contains("E"));
		System.out.println(list.contains("A"));
		
		// get the size of the linked list
		System.out.println(list.size());
		
		// get the 2nd element (starts from 0)
		System.out.println(list.get(2));
	}
	
}
