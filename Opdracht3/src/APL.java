import java.io.IOException;

import model.Trie;
import model.TrieData;

public class APL {

	public static void main(String[] args) throws IOException {
		Trie trie = new Trie();

		trie.insertIntoTree("ball", 2);
		trie.insertIntoTree("bat", 10);
		trie.insertIntoTree("doll", 12);
		trie.insertIntoTree("dork", 16);
		trie.insertIntoTree("do", 26);
		trie.insertIntoTree("dorm", 34);
		trie.insertIntoTree("send", 43);
		trie.insertIntoTree("sense", 50);

		// trie.insertIntoTree("voetbal", 10);
		// trie.insertIntoTree("voetbaltafel", 15);
		//
		// trie.insertIntoTree("kapper", 20);
		// trie.insertIntoTree("kapsalon", 25);
		//
		// trie.insertIntoTree("bal", 30);
		// trie.insertIntoTree("ballenpomp", 35);
		//
		// trie.insertIntoTree("paneermeel", 40);
		// trie.insertIntoTree("paneer", 45);
		//
		// trie.insertIntoTree("handdoek", 50);
		// trie.insertIntoTree("doek", 55);

		System.out.println("searching for ball");

		TrieData ball = trie.search("ball");

		System.out.println("found: " + ball.getWord());

		System.out.println("searching for asdfasd fa45sdf");

		TrieData notFound = trie.search("asdfasd fa45sdf");

		System.out.println("this should return null: " + notFound);

		System.out.println("deleting ball");

		trie.delete("ball");

		System.out
				.println("no ball should be found and result should be null: "
						+ trie.search("ball"));

		System.out.println(trie.printTrie());
	}
}