
public class LinkedListImplement {
	Node head;
	class Node{
		Node next;
		int key;
		Node prev;
		
		Node(int key){
			this.key = key;
		}
	}
	
	public void add_beginning(int insert_val) {
		Node node = new Node(insert_val);
		
		node.next = head;
		node.prev = null;
		
		if(head != null) {
			head.prev = node;
		}
		head = node;
	}
	
	public void add_end(int insert_val) {
		Node node = new Node(insert_val);
		node.next = null;
		Node last = head;
		
		while(last !=null && last.next!=null) {
			last = last.next;
		}
		if(last!=null) {
			last.next = node;
			node.prev = last;
		}
		else {
			head = node;
		}
		
	}
	
	public void add_after(Node node, int insert_val) {
		Node new_node = new Node(insert_val);
		
		new_node.next = node.next;
		new_node.prev = node;
		
		node.next = new_node;
	}
	
	public void print() {
		Node node = head;
		while(node!=null) {
			System.out.println(node.key);
			node = node.next;
		}
	}
	
	public Node search(int key) {
		Node look = head;
		while(look != null) {
			if(look.key==key)
				return look;
			look = look.next;
		}
		return null;
	}
	
	public static void main(String[] args) {
		LinkedListImplement ll = new LinkedListImplement();
		ll.add_beginning(2);
		ll.add_beginning(4);
		ll.add_after(ll.search(4), 6);
		ll.print();
		
	}
}
