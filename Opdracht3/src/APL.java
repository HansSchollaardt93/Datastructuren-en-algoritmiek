import java.io.IOException;

public class APL {

	public static void main(String[] args) throws IOException {
		Trie trie = new Trie();
		trie.insert("ball", new Data(0, 0));
		trie.insert("bat", 2);
		trie.insert("doll", 3);
		trie.insert("dork", 4);
		trie.insert("do", 5);
		trie.insert("dorm", 6);
		trie.insert("send", 7);
		trie.insert("sense", 8);
	}

}