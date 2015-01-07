package model;

import java.util.ArrayList;

/**
 * Implements a Reduced Trie
 * 
 * @author Benjamin & Hans Schollaardt
 * 
 * @param <T>
 *            the Type of the data to store
 */
public class Trie {

	private Trie root;
	private ArrayList<Trie> childs;
	private ArrayList<TrieData> triedata;
	private String name;

	public Trie() {
		root = new Trie("root", null);
		childs = new ArrayList<>();
		triedata = new ArrayList<>();
	}


	public Trie(String name, TrieData data) {
		childs = new ArrayList<>();
		triedata = new ArrayList<>();

		this.name = name;
		triedata.add(data);
	}

	public void addData(TrieData data) {
		triedata.add(data);
	}

	/**
	 * Method to add a child to this Trie.
	 * 
	 * @return the 'next' of the
	 */
	public void addChild(Trie child) {
		childs.add(child);
	}

	/**
	 * Removes the child Trie of this Trie by setting it to null.
	 * 
	 * @return whether the child has been removed or not.
	 */
	public boolean removeChild(Trie child) {
		return childs.remove(child);
	}

	/**
	 * A Trie is a leave when it has not further children.
	 * 
	 * @return whether this Trie is a leave or not.
	 */
	public boolean isLeaf() {
		return childs.size() == 0;
	}

	public Trie getTrieWithValue(String s) {
		for (Trie child : childs) {
			if (child.getName().equals(s)) {
				return child;
			}
		}
		return null;
	}

	private String getName() {
		return this.name;
	}

	/**
	 * Method to insert data with a given key ?
	 * 
	 * @param s
	 *            the key of the data?
	 * @param data
	 *            the 'index' of the String s
	 */
	public void insert(String s, TrieData data) {
		assert (data != null) : "Dataobject cannot be null";
		assert (!s.startsWith(" ")) : "String cannot consist of whitespace";
		assert (s.equals("")) : "No empty string allowed!";
		Trie trie = null;
		// IF already has Trie with full word
		if (root.getTrieWithValue(s) != null) {
			trie = root.getTrieWithValue(s);
			// only add data
			trie.addData(data);
			// ELSE IF has word with first character of string
			// insert with substring of word
		} else if (root.getTrieWithValue(s.charAt(0) + "") != null) {
			trie = root.getTrieWithValue(s.charAt(0) + "");
			trie.insert(s.substring(1, s.length()), data);
		} 
		// ELSE add whole word as new child + set data
		else {
			root.addChild(new Trie(s, data));
		}
	}

	/**
	 * Method to search for data given a String s
	 * 
	 * @param s
	 *            the String to search for.
	 * 
	 * @return an Array of T containing the elements whether results where found
	 *         or not.
	 */
	public TrieData[] search(String s) {
		// IF has whole word as Trie-object

		// ELSE has first letter of word as Trie

		return null;
	}

	/**
	 * Deletes the item which matches the argument
	 * 
	 * @param s
	 *            the String to delete
	 */
	public void delete(String s) {
	}

	/**
	 * Inner-class which will hold the data and the child of a Trie of the
	 * RedTrie.
	 * 
	 * @author Benjamin & Hans Schollaardt.
	 * 
	 */

	// @Override
	// public String toString() {
	// String result = "digraph heap {\n";
	// return result + toDot();
	// }
	//
	// /**
	// * Create a string in dot format
	// *
	// * @return
	// */
	// private String toDot() {
	// String res = "n" + id + " [label=\"" + text + " data" + data + "\"]\n";
	// for (Trie<D> s : nodes) {
	// res += s.toDot();
	// res += "n" + id + "-> n" + s.id + ";\n";
	// }
	// return res;
	// }

}
