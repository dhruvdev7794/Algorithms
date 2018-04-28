import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class ConnectedComponents {
    private static int numberOfComponents(ArrayList<Integer>[] adj) {
        int result = 0;
        
        ArrayList<Integer> queue = new ArrayList<Integer>();
        int count = 0;
        for (int i = 0 ; i < adj.length; i++) {
        		queue.add(count);
        		count++;
        }
        ArrayList<Integer> visited = new ArrayList<Integer>();
        for(Integer x : queue) {
        		if(!visited.contains(x)) {
        			recurse_dfs(adj, visited, x);
        			result++;
        		}
        }
        return result;
    }
    
    public static void recurse_dfs(ArrayList<Integer>[] adj, ArrayList<Integer> visited, int x) {
    		visited.add(x);
    		for(Integer vals: adj[x]) {
    			if(!visited.contains(vals)) {
    				visited.add(vals);
        			recurse_dfs(adj, visited, vals);
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
        System.out.println(numberOfComponents(adj));
    }
}

