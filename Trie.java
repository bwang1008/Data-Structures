import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class Trie {
	
	TrieNode root;
	HashSet<String> contains;
	
	private int index;
	private String[] result;
	
	public Trie() {
		root = new TrieNode();
		contains = new HashSet<>();
	}
	
	public Trie(String[] list) {
		root = new TrieNode();
		contains = new HashSet<>();
		for(String g : list)
			insert(g);
	}
	
	public boolean contains(String g) {
		return contains.contains(g);
	}
	
	public boolean insert(String g) {
		if(g == null) return false;
		TrieNode node = root;
		
		for(int i = 0; i < g.length(); i++) {
			char c = g.charAt(i);
			if(!node.map.containsKey(c)) node.map.put(c, new TrieNode());
			node = node.map.get(c);
		}
		node.isWord = true;
		node.s = g;
		contains.add(g);
		return true;
	}
	
	public String[] getSortedList() {
		result = new String[contains.size()];
		fillSortedStringArray(root);
		return result;
	}
	
	private void fillSortedStringArray(TrieNode node) {
		Set<Character> set = node.map.keySet();
		if(node.isWord) result[index++] = node.s;
		for(char c : set)
			fillSortedStringArray(node.map.get(c));
	}
	
	static class TrieNode {
		TreeMap<Character, TrieNode> map;
		String s;
		boolean isWord;
		
		public TrieNode() {
			map = new TreeMap<>();
		}
	}
	
	public static void main(String[] args) {
		String[] list = new String[]{"there", "their", "answer", "any", "bye"};
		Trie tree = new Trie(list);
		
		
		String[] result = tree.getSortedList();
		for(String g : result)
			System.out.println(g);
		//answer any bye their there

	}

}
