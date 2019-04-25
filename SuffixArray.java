import java.util.*;
public class SuffixArray {

	public static void main(String[] args) {
		String g = "lkgfkjgvkhjadshfcgvjhkbmniuoltyfgj";
		
		SuffixArray sa = new SuffixArray(g);
		int[] indexes = sa.getSA();
		
		System.out.println(Arrays.toString(indexes));
		
		for(int i = 0; i < indexes.length; i++)
			System.out.println(g.substring(indexes[i]));
	}
	
	private String g;
	private int len;
	
	public SuffixArray(String h) {
		h = h.toUpperCase();
		g = h;
		len = g.length();
	}
	
	public int[] getSA() { // O(N * log N * log N)
		
		int[] indexes = new int[len];
		int[] temp = new int[len];
		int[] rank = new int[len];
		int[] rank2 = new int[len];
		double[] combinedRank = new double[len];
		
		HashMap<Double, Integer> map = new HashMap<>();
		for(int i = 0; i < len; i++)
			indexes[i] = i;
		
		
		for(int k = 1; k < len; k <<= 1)
		{
			if(k == 1)
			{
				for(int i = 0; i < len; i++)
					rank[i] = g.charAt(i) - 'A';
				
				for(int i = 1; i < len; i++)
					rank2[i-1] = rank[i];
				
				rank2[len-1] = -1;
			}
			else
			{
				rank[0] = 0;
				
				for(int i = 1; i < len; i++)
					rank[i] = rank[i-1] + (((int) (combinedRank[i]/100) == (int) (combinedRank[i-1]/100)) ? 0 : 1);
				
				for(int i = 0; i < len; i++)
					rank2[i] = ((indexes[i] + k >= len) ? -1 : rank[temp[indexes[i] + k]]);
			}
			
			
			map = new HashMap<>();
			
			for(int i = 0; i < len; i++)
				map.put((combinedRank[i] = 10000 * rank[i] + 100 * rank2[i] + 100 + i / 1000.0), indexes[i]);
			
			Arrays.sort(combinedRank);
			
			for(int i = 0; i < len; i++)
				indexes[i] = map.get(combinedRank[i]);
			
			for(int i = 0; i < len; i++)
				temp[indexes[i]] = i;
			
		}
		return indexes;
	}
	

}
