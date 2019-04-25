import java.util.Arrays;
import java.util.LinkedList;

public class CentroidTree {

	int N;
	int[] values;
	int[] parent;
	boolean[] filled;
	boolean[] isCentroid;
	LinkedList<Integer>[] edges;
	
	
	static int count = 0;
	static int count2 = 0;
	
	@SuppressWarnings("unchecked")
	public CentroidTree(int n, int[][] q) {
		N = n;
		values = new int[N];
		edges = new LinkedList[N];
		parent = new int[N];
		isCentroid = new boolean[N];
		
		filled = new boolean[N];
		
		Arrays.fill(parent, -1);
		for(int i = 0; i < N; i++)
			edges[i] = new LinkedList<>();
		
		for(int[] t : q) {
			int u = t[0];
			int v = t[1];
			edges[u].add(v);
			edges[v].add(u);
		}
		
	}
	
	public void decompose(int node) {
		getValue(node, filled);
		
		boolean small = false;
		while(!small) {
			small = true;
			int index = 0;
			for(int child : edges[node]) {
				if(!isCentroid[child]) {
					if(getValue(child, filled) > N/2) {
						small = false;
						index = child;
						break;
					}
				}
			}
			
			if(!small) {
				values[node] = 1;
				for(int child : edges[node])
					if(!isCentroid[child] && child != index)
						values[node] += getValue(child, filled);
				
				node = index;
				values[node] = N;
				
			}
			else {
				isCentroid[node] = true;
				for(int child : edges[node]) { 
					if(!isCentroid[child]) {
						parent[child] = node;
						decompose(child);
					}
				}
			}
			
		}
		
	}
	
	public int getValue(int node, boolean[] filled) {
		if(filled[node]) {
			count2++;
			return values[node];
		}
		
		count++;
		
		filled[node] = true;
		values[node] = 1;
		
		for(int child : edges[node])
			if(!filled[child])
				values[node] += getValue(child, filled);
		
		return values[node];
	}
	
	public int LeastCommonAncestor(int u, int v) {
		
		return 0;
	}
	
	public static void main(String[] args) {
		int[][] edge = new int[14][2];
		edge[0] = new int[]{0, 1};
		edge[1] = new int[]{2, 1};
		edge[2] = new int[]{3, 1};
		edge[3] = new int[]{4, 1};
		edge[4] = new int[]{5, 6};
		edge[5] = new int[]{4, 5};
		edge[6] = new int[]{4, 7};
		edge[7] = new int[]{7, 8};
		edge[8] = new int[]{0, 1};
		edge[8] = new int[]{10, 9};
		edge[9] = new int[]{11, 9};
		edge[10] = new int[]{13, 12};
		edge[11] = new int[]{14, 12};
		edge[12] = new int[]{9, 8};
		edge[13] = new int[]{12, 8};
		
		CentroidTree tree = new CentroidTree(15, edge);
		tree.decompose(0);
		
		for(int i = 0; i < tree.parent.length; i++)
			System.out.println("parent of " + i + " = " + tree.parent[i]);
		
		System.out.println(count + " " + count2);
	}

}
