import java.io.IOException;

import model.Trie;
import model.TrieData;

public class APL {

	public static void main(String[] args) throws IOException {
		Trie trie = new Trie();
		trie.insertIntoTree("ball", new TrieData(5, 8));
		trie.insertIntoTree("bat", new TrieData(2,4));
		trie.insertIntoTree("doll", new TrieData(30,33));
		trie.insertIntoTree("dork", new TrieData(56,59));
		trie.insertIntoTree("do", new TrieData(62,63));
		trie.insertIntoTree("dorm", new TrieData(34,37));
		trie.insertIntoTree("send", new TrieData(45,49));
		trie.insertIntoTree("sense", new TrieData(68,72));
		
		System.out.println(trie.printTrie());
	}

}