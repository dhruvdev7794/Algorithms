import java.util.ArrayList;
import java.util.Scanner;

/*
	Task: Given an undirected graph and two distinct vertices u and v, check if there is a path between u and v.
	Input Format: An undirected graph with n vertices and m edges. The next line contains two vertices u and v of the  graph.
	Output Format: Output 1 if there is a path between u and v and 0 otherwise.
	
 */

public class Reachability {
    private static int reach(ArrayList<Integer>[] adj, int x, int y) {
    		ArrayList<Integer> visited = new ArrayList<Integer>();
    		ArrayList<Integer> queue = new ArrayList<Integer>();
    		
    		while(true) {
    			// check if we can find y
    			if(adj[x].contains(y))
    				return 1;
    			// if not:-
    			else {
    				// add x to the visited list
    				visited.add(x);
    				for(Integer xs : adj[x]) {
    					// if not visited, add to the queue
    					if (!visited.contains(xs))
    						queue.add(xs);
    				}
    				// If the queue is empty, return 0
    				if(queue.isEmpty())
    					return 0;
    				// make a new x. If there will be elements in the queue, assign a new x
    				x = queue.get(0);
    				queue.remove(0);
    			}
    		}
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(reach(adj, x, y));
    }
}

