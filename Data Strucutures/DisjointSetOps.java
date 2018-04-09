import java.util.HashMap;
import java.util.Map;

public class DisjointSetOps {
	
	Map<Integer, Node> node_map = new HashMap<>();
	class Node{
		Node parent;
		int data;
		int depth;
	}
	
	public void makeSet(int data) {
		Node node = new Node();
		node.data = data;
		node.parent = node;
		node.depth = 0;
		node_map.put(data, node);
	}
	
	public void union(int val1, int val2) {
		Node parent1 = findSet(val1);
		Node parent2 = findSet(val1);
		
		if(parent1.data == parent2.data)
			return;
		if(parent1.depth > parent2.depth) {
			parent2.parent = parent1;
			parent2.depth++;
		}
			
		else {
			parent1.parent = parent2;
			parent1.depth++;
		}
			
	}
	
	public Node findSet(int node_val) {
		Node node = node_map.get(node_val);
		while(node.parent != node) {
			node = node.parent;
		}
		return node;
	}
	
}
