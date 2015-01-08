import java.io.IOException;

import model.Trie;

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

		System.out.println(trie.printTrie());
	}
}