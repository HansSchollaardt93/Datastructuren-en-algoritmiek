import java.io.IOException;

import model.Trie;
import model.TrieData;

public class APL {

	public static void main(String[] args) throws IOException {
		Trie trie = new Trie();
		trie.insert("ball", new TrieData(5, 8));
		trie.insert("bat", new TrieData(2,4));
		trie.insert("doll", new TrieData(30,33));
		trie.insert("dork", new TrieData(56,59));
		trie.insert("do", new TrieData(62,63));
		trie.insert("dorm", new TrieData(34,37));
		trie.insert("send", new TrieData(45,49));
		trie.insert("sense", new TrieData(68,72));
	}

}