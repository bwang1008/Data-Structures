import java.util.LinkedList;
import java.util.Queue;
public class MaxFlow {
	//Thanks to GeeksForGeeks

	public static void main(String[] args) {
		int[][] weights = new int[][] { {0, 16, 13, 0, 0, 0}, 
            {0, 0, 10, 12, 0, 0}, 
            {0, 4, 0, 0, 14, 0}, 
            {0, 0, 9, 0, 0, 20}, 
            {0, 0, 0, 7, 0, 4}, 
            {0, 0, 0, 0, 0, 0}};
            
        System.out.println(maxFlow(0, 5, weights, 6)); //should get 23
	}
	
	public static int maxFlow(int s, int t, int[][] weights, int N) {
		int maxFlow = 0;
		int[] parents = new int[N];
		while(bfs(s, t, weights, parents, N)) {
			int flow = Integer.MAX_VALUE;
			for(int v = t; v != s; v = parents[v]) {
				int u = parents[v];
				flow = Math.min(flow, weights[u][v]);
			}
			for(int v = t; v != s; v = parents[v]) {
				int u = parents[v];
				weights[u][v] -= flow;
				weights[v][u] += flow;
			}
			
			maxFlow += flow;
		}
		
		return maxFlow;
	}
	
	public static boolean bfs(int s, int t, int[][] weights, int[] parents, int N) {
		boolean[] visited = new boolean[N];
		Queue<Integer> q = new LinkedList<>();
		q.add(s);
		visited[s] = true;
		parents[s] = -1;
		
		while(!q.isEmpty()) {
			int node = q.poll();
			for(int v = 0; v < weights[node].length; v++) {
				if(!visited[v] && weights[node][v] > 0) {
					parents[v] = node;
					visited[v] = true;
					q.add(v);
				}
			}
		}
		
		return visited[t];
	}
}
