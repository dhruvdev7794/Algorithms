
public class BinarySearchTree {
	
	// declare class Node
	class Node{
		int key;
		Node left, right;
		public Node(int item) {
			key = item;
			left = right = null;
		}
	}
	
	// initializations.
	Node root;
	BinarySearchTree(){
		root = null;
	}
	
	// insert element in tree
	void insert(int key) {
		root = insertKey(root, key);
	}
	
	Node insertKey(Node root, int key) {
		if (root==null) {
			root = new Node(key);
			return root;
		}
		
		if(key<root.key) {
			root.left = insertKey(root.left, key);
		}
		else if (key>root.key) {
			root.right = insertKey(root.right, key);
		}
		return root;
	}
	
	
	// Read tree elements : inorder
	void inorder() {
		inorderRec(root);
	}
	
	void inorderRec(Node root) {
		if(root!=null) {
			inorderRec(root.left);
			System.out.println(root.key);
			inorderRec(root.right);
		}
	}
	
	// Perform Search in BST
	Node search( int key){
		return searchItem(root, key);
	}
	Node searchItem(Node bst, int key) {
		if(bst== null || bst.key == key) {
			return bst;
		}
		if(bst.key>key)
			return searchItem(bst.left, key);
		else
			return searchItem(bst.right, key);
	}
	
	// delete item methods
	void deleteKey(int key) {
		root = deleteRec(root, key);
	}
	
	Node deleteRec(Node root, int key) {
		if(root == null)
			return root;
		if(key<root.key) 
			root.left = deleteRec(root.left, key);
		else if(key>root.key)
			root.right = deleteRec(root.right, key);
		else {
			if(root.left == null)
				return root.right;
			else if(root.right == null)
				return root.left;
			
			root.key = minValue(root.right);
			root.right = deleteRec(root.right, root.key);
		}
		
		
		return root;
	}
	
	int minValue(Node root) {
		int minv = root.key;
		while(root.left!=null) {
			minv = root.left.key;
			root = root.left;
		}
		return minv;
	}
	
	// main method.
	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(10);
		bst.insert(20);
		bst.insert(15);
		bst.insert(25);
		bst.inorder();
		System.out.println();
		bst.deleteKey(25);
		bst.inorder();
	}
	
}
