import java.io.File;
import java.io.IOException;

import model.Trie;

public class APL {

	public static void main(String[] args) throws IOException {
		Trie trie = new Trie(new File("document.txt"));

		// System.out.println("searching for ball");
		//
		// TrieData ball = trie.search("ball");
		//
		// System.out.println("found: " + ball.getWord());
		//
		// System.out.println("searching for asdfasd fa45sdf");
		//
		// TrieData notFound = trie.search("asdfasd fa45sdf");
		//
		// System.out.println("this should return null: " + notFound);
		//
		// System.out.println("deleting ball");
		//
		// trie.delete("ball");
		//
		// System.out
		// .println("no ball should be found and result should be null: "
		// + trie.search("ball"));

		System.out.println(trie);
	}
}