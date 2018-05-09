import java.util.Iterator;
import java.util.LinkedList;

public class DFS {
	
	private int V;
	LinkedList<Integer> adj[];
	
	DFS(int v){
		V=v;
		adj = new LinkedList[v];
		for(int i =0 ; i< v; i++) {
			adj[i] = new LinkedList();
		}
	}
	
	void addEdge(int m, int n) {
		adj[m].add(n);
	}
	
	void depthFirstSearch(int s){
		boolean[] visited = new boolean[V];
		dfsUtil(s, visited);
	}
	void dfsUtil(int v, boolean visited[]) {
		visited[v] = true;
		System.out.print(v+ "   ");
		Iterator<Integer> itr = adj[v].listIterator();
		while(itr.hasNext()) {
			int n = itr.next();
			if(!visited[n]) {
				dfsUtil(n, visited);
			}
		}
	}
	
	public static void main(String[] args) {
		DFS dfs = new DFS(4);
		dfs.addEdge(0, 1);
		dfs.addEdge(0, 2);
		dfs.addEdge(1, 2);
		dfs.addEdge(2, 0);
		dfs.addEdge(2, 3);
		dfs.addEdge(3, 3);
		
		dfs.depthFirstSearch(2);
 
	}
	

}
