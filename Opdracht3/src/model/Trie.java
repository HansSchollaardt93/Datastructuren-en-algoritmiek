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
	private final static int ROOTDEPTH = 1;
	private Node root;
	private int lastPosition = -1;

	public Trie() {
		root = new Node("", null, root);
	}

	public void insertIntoTree(String name, int wordPosition) {
		lastPosition++;
		root.insert(name, lastPosition, ROOTDEPTH);
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
		private int nodeId;
		private Node parentnode;
		private TrieData data;

		/**
		 * Constructor voor initialisatie van de root-node van de trie
		 * 
		 * @param parentnode
		 */
		public Node(Node parentnode) {
			childnodes = new ArrayList<>();
			this.nodeId = ID++;
			this.parentnode = parentnode;
		}

		/**
		 * Constructor voor een woord dat in de trie opgeslagen moet worden
		 * 
		 * @param name
		 * @param data
		 * @param parentnode
		 */
		public Node(String name, TrieData data, Node parentnode) {
			this(parentnode);
			this.data = data;
			this.name = name;
		}

		/**
		 * Method to insert data with a given key ?
		 * 
		 * @param s
		 *            the key of the data?
		 * @param data
		 *            the 'index' of the String s
		 */
		public void insert(String word, int position, int depth) {
			assert (!word.startsWith(" ")) : "String cannot consist of whitespace";
			assert (!word.equals("")) : "No empty string allowed!";

			// base case
			if ((data != null && data.getWord().equals(word))
					|| word.length() == depth - 1) {
				if (data == null) {
					data = new TrieData(word, position);
					return;
				} else {
					if (data.getWord().length() > word.length()) {
						expandTrie();
						data = new TrieData(word, position);
						return;
					} else {
						// I am this word
						data.addPosition(position);
						return;
					}
				}

			}
			// Childnodes doorzoeken
			Node tempNode = null;
			char letter = word.charAt(depth - 1);
			for (Node node : childnodes) {
				if (node.isLetter(letter)) {
					// de node is gevonden
					tempNode = node;
					break;
				}
			}

			// Childnode which has no direct children. Also not the rootnode
			if (isLeaf() && depth > 1) {
				if (data != null
						&& word.substring(0, depth - 1).equals(data.getWord())) {

				} else if (data != null
						&& word.charAt(depth - 1) == data.getWord().charAt(
								depth - 1)) {
					// The start of the word to be added is equal to the nodes
					// first character
					if (name.length() > 1) {
						String rest = name.substring(1, name.length());
						name = name.charAt(0) + "";
						TrieData d = data;
						data = null;
						Node node = new Node(rest, d, this);
						childnodes.add(node);
						node.insert(word, position, depth + 1);
						return;
					}
				} else {
					expandTrie();
				}
			}

			if (tempNode != null) {
				tempNode.insert(word, position, depth + 1);
			} else {
				TrieData data = new TrieData(word, position);
				childnodes.add(new Node(
						word.substring(depth - 1, word.length()), data, this));
			}
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
		 * @param s
		 *            String to check
		 * @return the Node which consists of the first character of the string,
		 *         or null of none does
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

		private boolean isLetter(char letter) {
			return getName().charAt(0) == letter;
		}

		private String getName() {
			return this.name;
		}

		private void expandTrie() {
			String toSplit = name.substring(1, name.length());
			name = name.charAt(0) + "";
			TrieData dataToAdd = data;
			data = null;
			Node node = new Node(toSplit, dataToAdd, this);
			childnodes.add(node);
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
			String data = "";
			if (this.data != null) {
				data = this.data.toString();
			}
			String res = "n" + nodeId + " [label=\"" + name + " data: " + data
					+ "\"]\n";
			for (Node d : childnodes) {
				res += d.toDot();
				res += "n" + nodeId + "-> n" + d.nodeId + ";\n";
			}
			return res;
		}

	}
}
