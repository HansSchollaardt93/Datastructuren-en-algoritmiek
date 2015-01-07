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
	static int ID = 0;
	private Node root;

	public Trie() {
		root = new Node("", null);
	}

	public void insertIntoTree(String name, TrieData data) {
		root.insert(name, data);
	}

	public String printTrie() {
		return root.toString();
	}

	/**
	 * Inner-class which will hold the data and the child of a Trie of the
	 * RedTrie.
	 * 
	 * @author Benjamin & Hans Schollaardt.
	 * 
	 */
	public class Node {
		private String name;
		private ArrayList<Node> childnodes;
		private ArrayList<TrieData> triedata;
		private int nodeId;

		public Node(String name, TrieData data) {
			childnodes = new ArrayList<>();
			triedata = new ArrayList<>();
			this.name = name;
			triedata.add(data);
			this.nodeId = ID++;
		}

		/**
		 * Method to add a child to this Trie.
		 * 
		 * @return the 'next' of the
		 */
		public void addChild(Node child) {
			childnodes.add(child);
		}

		/**
		 * Removes the child Trie of this Trie by setting it to null.
		 * 
		 * @return whether the child has been removed or not.
		 */
		public boolean removeChild(Node child) {
			return childnodes.remove(child);
		}

		/**
		 * A Trie is a leave when it has not further children.
		 * 
		 * @return whether this Trie is a leave or not.
		 */
		public boolean isLeaf() {
			return childnodes.size() == 0;
		}

		/**
		 * 
		 * @param s String to check
		 * @return	the Node which consists of the first character of the string, or null of none does
		 */
		public Node hasValue(String s) {
			for (Node child : childnodes) {
				String childCompare = child.getName().charAt(0) + "";
				String stringCompare = s.charAt(0) + "";
				if (childCompare.equals(stringCompare)) {
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
			assert (!s.equals("")) : "No empty string allowed!";
			Node child;

			if (!isLeaf()) {
				// Kijk naar childs' waarde
				child = hasValue(s);
				if (child != null) {
					// als childstring.equals(s); append data
					if (child.getName().equals(s)) {
						// Same word
						child.triedata.add(data);
					} else {
						// als childstring NIET gelijk is, splits de trie
						expandTrie(child);
						// of this.?
						child.insert(s, data);
					}
				} else {
					this.addChild(new Node(s, data));
				}
				// Leaf, so add whole new Node
			} else {
				this.addChild(new Node(s, data));
			}
		}

		private void expandTrie(Node node) {
			String toSplit = node.getName();
			node.name = toSplit.charAt(0) + "";
			Node newChild = new Node(toSplit.substring(1, toSplit.length()),
					null);
			newChild.triedata = node.triedata;
			node.triedata = new ArrayList<>();
			node.addChild(newChild);
		}

		/**
		 * Method to search for data given a String s
		 * 
		 * @param s
		 *            the String to search for.
		 * 
		 * @return an Array of T containing the elements whether results where
		 *         found or not.
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

		@Override
		public String toString() {
			String result = "digraph heap {\n";
			return result + toDot();
		}

		/**
		 * Create a string in dot format
		 * 
		 * @return
		 */
		private String toDot() {
			String res = "n" + nodeId + " [label=\"" + name + " data"
					+ triedata + "\"]\n";
			for (Node s : childnodes) {
				res += s.toDot();
				res += "n" + nodeId + "-> n" + s.nodeId + ";\n";
			}
			return res;
		}

	}
}
