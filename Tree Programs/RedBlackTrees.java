
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
		while(root!=null) {
			if(root.key> key) {
				root = root.left;
			}
			else if(root.key < key) {
				root = root.right;
			}
			else {
				return root;
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
		while (root!=null && root.right != null) {
			root = root.right;
		}
		return root;
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
		while(root != null && root.left != null) {
			root = root.left;
		}
		return root;
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
		displayInOrder(root);
	}
	void displayInOrder(Node root) {
		if(root != null) {
			displayInOrder(root.left);
			System.out.println(root.key + " has parent " + ((root.p == null) ? null : root.p.key) + " with color "+ root.color);
			displayInOrder(root.right);
		}
	}
	
	public void insert_items_initial() {
		insert(new Node(11));
		insert(new Node(2));
		insert(new Node(14));
		insert(new Node(1));
		insert(new Node(7));
		insert(new Node(15));
		insert(new Node(5));
		insert(new Node(8));
		inOrder();
	}
	public void insert_items_final() {
		insert(new Node(11));
		insert(new Node(2));
		insert(new Node(14));
		insert(new Node(1));
		insert(new Node(7));
		insert(new Node(15));
		insert(new Node(5));
		insert(new Node(8));
		insert(new Node(4));
		inOrder();
	}
	public void insert_items_pred() {
		insert(new Node(10));
		insert(new Node(5));
		insert(new Node(15));
		insert(new Node(3));
		insert(new Node(7));
		insert(new Node(25));
		insert(new Node(9));
		inOrder();
	}
	
	
	
	
	public static void main(String [] args) {
		System.out.println("--------------------- INITIAL TREE -------------------");
		RedBlackTrees rbt_initial = new RedBlackTrees();
		
		rbt_initial.insert_items_initial();
		System.out.println("-------------------- INSERT 4 ---------------------");
		RedBlackTrees rbt_final = new RedBlackTrees();
		rbt_final.insert_items_final();
		
		System.out.println("--------------------- FIND MIN ---------------------");
		RedBlackTrees rbt_findMin = new RedBlackTrees();
		rbt_findMin.insert_items_final();
		Node min = rbt_findMin.findMin();
		if(min == null)
			System.out.println("No minimum");
		else
			System.out.println(min.key);
		
		System.out.println("--------------------- FIND MAX ---------------------");
		RedBlackTrees rbt_findMax = new RedBlackTrees();
		rbt_findMax.insert_items_final();
		Node max = rbt_findMax.findMax();
		if(max == null)
			System.out.println("No max");
		else
			System.out.println(max.key);
		
		
		System.out.println("--------------------- SEARCH ---------------------");
		RedBlackTrees rbt_search = new RedBlackTrees();
		rbt_search.insert_items_final();
		Node node = rbt_search.search(14);
		if(node == null)
			System.out.println("Item not found");
		else
			System.out.println(node);
		
		System.out.println("--------------------- Successor ---------------------");
		RedBlackTrees rbt_successor = new RedBlackTrees();
		rbt_successor.insert_items_pred();
		Node successor = rbt_successor.successor(9);
		if(successor == null)
			System.out.println("No successor");
		else
			System.out.println(successor.key);
		
		System.out.println("--------------------- Predecessor ---------------------");
		RedBlackTrees rbt_predecessor = new RedBlackTrees();
		rbt_predecessor.insert_items_final();
		Node predecessor = rbt_predecessor.predecessor(5);
		if(predecessor == null)
			System.out.println("No predecessor");
		else
			System.out.println(predecessor.key);
	}
	
}

