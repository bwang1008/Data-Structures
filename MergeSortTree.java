import java.util.ArrayList;

public class MergeSortTree {
	
	int N;
	int[] base;
	ArrayList<int[]> lists;
	int[][] all;
	
	public MergeSortTree(int[] arr) {
		N = arr.length;
		base = new int[N];
		
		int[] temp = new int[N];
		for(int i = 0; i < N; i++)
			base[i] = temp[i] = arr[i];
		
		lists = new ArrayList<>();
		lists.add(temp);
		
		sort(base);
		
		all = new int[lists.size()][N];
		for(int i = 0; i < lists.size(); i++)
			for(int j = 0; j < N; j++)
				all[i][j] = lists.get(i)[j];
		
	}
	
	public void sort(int[] a) {
		for(int size = 1; size < a.length; size <<= 1) {
			for(int start = 0; start < a.length; start += 2*size)
				merge(a, start, Math.min(a.length, start + size), Math.min(a.length, start + 2 * size));
			
			int[] temp = new int[N];
			for(int i = 0; i < N; i++)
				temp[i] = a[i];
			lists.add(temp);
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < all.length; i++) {
			for(int j = 0; j < N; j++)
				sb.append(String.format("%3d", all[i][j]));
			sb.append('\n');
		}
		return sb.toString();
	}
	
	public static void merge(int[] a, int start, int mid, int end) {
		int index = 0;
		int[] temp = new int[end - start];
		for(int i = start, j = mid; i < mid || j < end; index++) {
			if(i == mid) temp[index] = a[j++];
			else if(j == end) temp[index] = a[i++];
			else if(a[i] < a[j]) temp[index] = a[i++];
			else temp[index] = a[j++];
		}
		for(int i = start; i < end; i++)
			a[i] = temp[i-start];
	}
	
	public int query(int left, int right) {
		
		
		return 0;
	}
	
	public static void main(String[] args) {
		int[] a = {3, 5, 2, 1, 4, 6};
		MergeSortTree tree = new MergeSortTree(a);
		System.out.println(tree);
	}

}
