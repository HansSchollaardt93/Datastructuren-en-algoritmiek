import java.io.IOException;

import model.Trie;

public class APL {

	public static void main(String[] args) throws IOException {
		Trie trie = new Trie();
		
		trie.insertIntoTree("ball", 2);
		trie.insertIntoTree("bat", 10);
		trie.insertIntoTree("doll", 12);
		trie.insertIntoTree("dork",16);
		trie.insertIntoTree("do",26);
		trie.insertIntoTree("dorm", 34);
		trie.insertIntoTree("send", 43);
		trie.insertIntoTree("sense", 50);
		
		System.out.println(trie.printTrie());
	}
}