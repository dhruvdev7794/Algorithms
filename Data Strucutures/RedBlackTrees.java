import java.util.Scanner;

public class RedBlackTrees {
	
	private final static String RED = "Red";
	private final static String BLACK = "Black";
	
	class Node{
		int key;
		Node left, right, p;
		String color;
		
		public Node(int item) {
			key = item;
			left = right = p = null;
			color = RED;
		}
	}
	
	// initialization and constructor
	Node root;
	public RedBlackTrees() {
		root = null;
	}
	
	/// INSERTION ///
	private void insert(Node node) {
		
		Node x = root;
		Node y = null;
		while(x != null) {
			y = x;
			if(node.key < x.key)
				x = x.left;
			else
				x = x.right;
		}
		node.p = y;
		if(y == null)
			root = node;
		else if (node.key < y.key)
			y.left = node;
		else
			y.right = node;
		node.left = null;
		node.right = null;
		node.color = RED;
		rbTreeFixUp(node);
	}
	
	private void rbTreeFixUp(Node node) {
		if(node.p != null) {  
			while(node.p != null && node.p.color == RED) {
//				if(node.p.p!=null) {
					if(node.p == node.p.p.left) {
						Node y = node.p.p.right;  
						if(y != null && y.color == RED) {
							
							node.p.color = BLACK; 
							y.color = BLACK; 
							node.p.p.color = RED; 
							node = node.p.p; 
						}
						else {
							if(node == node.p.right) {
								node = node.p;  
								left_rotate(node);  
							}
							node.p.color = BLACK;  
							node.p.p.color = RED;  
							right_rotate(node.p.p);  
							
						}
					}
					else {
						Node y = node.p.p.left; 
						
						if(y != null && y.color == RED) {
							node.p.color = BLACK; 
							y.color = BLACK; 
							node.p.p.color = RED; 
							node = node.p.p; 
						}
						else {
							
							if(node == node.p.left) {
								node = node.p;
								right_rotate(node);
							}
							node.p.color = BLACK; 
							node.p.p.color = RED;
							left_rotate(node.p.p);
						}
					}
//				}
			}
			root.color = BLACK;  
		}
		else{
			node.color = BLACK; 
			root = node; 
		}
	}
	
	// LEFT ROTATION //
	void left_rotate(Node x) {
		Node y = x.right;
		x.right = y.left;
		if(y.left != null) {
			y.left.p = x;
		}
		y.p = x.p;
		if(x.p == null)
			root = y;
		else if (x == x.p.left)
			x.p.left = y;
		else
			x.p.right = y;
		y.left = x;
		x.p = y;
	}
	
	// RIGHT ROTATION //
	void right_rotate(Node x) {
		Node y = x.left; 
		x.left = y.right; 
		if (y.right!=null) {
			y.right.p = x;
		}
		y.p = x.p; 
		if(x.p == null)
			root = y; 
		else if (x == x.p.left)
			x.p.left = y;
		else
			x.p.right = y;
		y.right = x; 
		x.p = y; 
	}
	
	///// SEARCH FOR NODE IN TREE //////
	public Node search(int key) {
		Node node = root;
		while(node!=null) {
			if(node.key> key) {
				node = node.left;
			}
			else if(node.key < key) {
				node = node.right;
			}
			else {
				return node;
			}
		}
		return null;
	}
	
	////// FIND SUCCESSOR //////
	public Node successor(int val) {
		Node node = search(val);
		if(node == null) {
			return null;
		}
		if(node.right != null) {
			return findMin(node.right);
		}
		Node y = node.p;
		while(y!=null && node==y.right) {
			node = y;
			y = y.p;
		}
		return y;
	}
	//////  FIND PREDECESSOR //////
	public Node predecessor(int val) {
		Node node = search(val);
		if(node == null) {
			return null;
		}
		
		if(node.left != null) {
			return findMin(node.left);
		}
		Node y = node.p;
		while(y!=null && node==y.left) {
			node = y;
			y = y.p;
		}
		return y;
	}
	
	////// FIND MAX IN NODE ///////
	public Node findMax() {
		Node node = root;
		while (node!=null && node.right != null) {
			node = node.right;
		}
		return node;
	}
	// overloaded
	public Node findMax(Node node) {
		while (node !=null && node.right != null) {
			node = node.right;
		}
		return node;
	}
	
	//////FIND MAX IN NODE ///////
	public Node findMin() {
		Node node = root;
		while(node != null && node.left != null) {
			node = node.left;
		}
		return node;
	}
	// overloaded
	public Node findMin(Node node) {
		while(node != null && node.left != null) {
			node = node.left;
		}
		return node;
	}
	
	// DISPLAY IN SORTED ORDER/INORDER //
	void inOrder() {
		Node node = root;
		displayInOrder(node);
	}
	void displayInOrder(Node node) {
		if(node != null) {
			displayInOrder(node.left);
			System.out.println(node.key + " has parent " + ((node.p == null) ? null : node.p.key) + " with color "+ node.color);
			displayInOrder(node.right);
		}
	}
	
	public void insert_items(int[] numberArr) {
		for(int num : numberArr) {
			insert(new Node(num));
		}
		inOrder();
	}
	
	public void insert_item(int number) {
		insert(new Node(number));
		inOrder();
	}
	
	public static void main(String [] args) {
		System.out.println("--------------------- INITIAL TREE -------------------");
		RedBlackTrees rbt_initial = new RedBlackTrees();
		rbt_initial.insert_items(new int[] {11,2,14,1,7,15,5,8});
		while(true) {
			System.out.println("Press 1: Insert");
			System.out.println("Press 2: Find Min");
			System.out.println("Press 3: Find Max");
			System.out.println("Press 4: Search");
			System.out.println("Press 5: Get Successor");
			System.out.println("Press 6: Get Predecessor");
			System.out.println("Press 7: Exit");
			
			Scanner sc = new Scanner(System.in);
			int n = sc.nextInt();
			if(n == 7) {
				System.out.println("Exited!!");
				break;
			}
			else {
				try {
					switch(n) {
					case 1:
						System.out.print("Enter the number to be inserted");
						Scanner insert =  new Scanner(System.in);
						rbt_initial.insert_item(insert.nextInt());	
						break;
					case 2:
						Node min = rbt_initial.findMin();
						if(min == null)
							System.out.println("No minimum");
						else
							System.out.println(min.key);
						break;
					case 3:
						Node max = rbt_initial.findMax();
						if(max == null)
							System.out.println("No max");
						else
							System.out.println(max.key);
						break;
					case 4:
						System.out.print("Enter the number to be searched");
						Scanner search = new Scanner(System.in);
						Node node = rbt_initial.search(search.nextInt());
						if(node == null)
							System.out.println("Item not found");
						else
							System.out.println(node.key);
						break;
					case 5:
						System.out.print("Enter the number whose successor you are looking for");
						Scanner successor = new Scanner(System.in);
						Node successorNode = rbt_initial.successor(successor.nextInt());
						if(successorNode == null)
							System.out.println("No successor");
						else
							System.out.println(successorNode.key);
						break;
					case 6:
						System.out.print("Enter the number whose predecessor you are looking for");
						Scanner predecessor = new Scanner(System.in);
						Node predecessorNode = rbt_initial.predecessor(predecessor.nextInt());
						if(predecessorNode == null)
							System.out.println("No predecessor");
						else
							System.out.println(predecessorNode.key);
						break;
					default:
						System.out.println("Invalid option");
					}
				}
				catch(Exception e) {
					System.out.println("Invalid input");
				}
			}
		}
	}
}

