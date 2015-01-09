package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Implements a Trie
 * 
 * @author Benjamin & Hans Schollaardt
 * 
 */
public class Trie<T extends TrieData> {
	// for identifying the Nodes so they can be visualized
	private static int ID = 0;
	// the default depth of the Trie
	private final static int DEFAULT_DEPTH = 1;
	// the root Node of this Trie
	private Node root;

	/**
	 * Constructs a default empty Trie.
	 */
	public Trie() {
		root = new Node("", null, root);
	}

	/**
	 * Constructs a Trie with a File that has to be red. Example contents of
	 * File:
	 * 
	 * ball bat doll dork do dorm send sense ball.<br>
	 * The words have to be seperated by spaces or new lines.
	 * 
	 * @throws FileNotFoundException
	 *             thrown when the file is not found.
	 */
	public Trie(File file) throws FileNotFoundException {
		this();
		Scanner scanner = new Scanner(file);
		int position = 0;
		while (scanner.hasNext()) {
			String word = scanner.next();
			insert(word, position++);
		}
		scanner.close();
	}

	/**
	 * Method to insert a String into the Trie.
	 * 
	 * @param name
	 *            the word self
	 * @param wordPosition
	 *            the position to insert into the Trie
	 */
	public void insert(String name, int wordPosition) {
		root.insert(name, wordPosition, DEFAULT_DEPTH);
	}

	/**
	 * Search in this Trie for a specific String.
	 * 
	 * @param s
	 *            the String to look for
	 * @return the TrieData object if found
	 */
	public TrieData search(String s) {
		return root.search(s, DEFAULT_DEPTH);
	}

	/**
	 * Deletes the Node with the specified String, and restores the Trie
	 * afterwards.
	 * 
	 * @param s
	 *            the String to search for and delete
	 */
	public void delete(String s) {
		root.delete(s, DEFAULT_DEPTH);
	}

	/**
	 * Overriden so we can print the whole Trie
	 * 
	 * @return the digraph String compatible with:
	 *         http://graphviz-dev.appspot.com/
	 */
	@Override
	public String toString() {
		return root.toString();
	}

	/**
	 * Inner-class which will hold the data and the child of a Trie.
	 * 
	 * @author Benjamin & Hans Schollaardt.
	 * 
	 */
	private class Node {
		private String name;
		private ArrayList<Node> childNodes;
		private int id;
		private Node parentNode;
		private TrieData data;

		/**
		 * Constructor for initialization of the main Trie with a default empty
		 * root Node.
		 * 
		 * @param parentNode
		 */
		private Node(Node parentNode) {
			childNodes = new ArrayList<Node>();
			this.id = ID++;
			this.parentNode = parentNode;
		}

		/**
		 * Constructor for a word that has to be saved in the Trie
		 * 
		 * @param name
		 * @param data
		 * @param parentNode
		 */
		private Node(String name, TrieData data, Node parentNode) {
			this(parentNode);
			assert name != null : "Name cannot be null";
			this.data = data;
			this.name = name;
		}

		/**
		 * Method to insert a String into the Trie.
		 * 
		 * @param word
		 *            the word self.
		 * @param position
		 *            the position to insert into the Trie.
		 * @param depth
		 *            the 'index' of the String word
		 */
		private void insert(String word, int position, int depth) {
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
			// Iterating over all child Nodes
			Node tempNode = null;
			char letter = word.charAt(depth - 1);
			for (Node node : childNodes) {
				if (node.isLetter(letter)) {
					// The Node is found
					tempNode = node;
					break;
				}
			}

			// Childnode which has no direct children. Also not the rootnode
			if (isLeaf() && depth > DEFAULT_DEPTH) {
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
						System.out.println("class; " + d.getClass().getName());
						data = null;
						Node node = new Node(rest, d, this);
						childNodes.add(node);
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
				childNodes.add(new Node(
						word.substring(depth - 1, word.length()), data, this));
			}
		}

		/**
		 * Search the whole Trie.
		 * 
		 * @param s
		 *            the String to search for.
		 * @param depth
		 *            the depth of the tree to start looking from.
		 * @return a TrieData object if its found or null if not found.
		 */
		private TrieData search(String s, int depth) {
			Node temp = searchNode(s, depth);
			if (temp == null) {
				return null;
			}
			return temp.data;
		}

		/**
		 * Helper method to search for a Node.
		 * 
		 * @param s
		 *            the String to search for.
		 * @param depth
		 *            the depth in the tree.
		 * @return the found Node or null if not found.
		 */
		private Node searchNode(String s, int depth) {
			if (data != null && data.getWord().equals(s)) {
				return this;
			}

			// itearate over all Nodes to check which Node has the character and
			// stores it in temp
			Node temp = null;
			char letter = s.charAt(depth - 1);
			for (Node node : childNodes) {
				if (node.isLetter(letter)) {
					// the Node is found here
					temp = node;
					break;
				}
			}

			if (temp != null) {
				// the Node is found here
				return temp.searchNode(s, depth + 1);
			} else {
				// No Node found, returning null here
				return null;
			}
		}

		/**
		 * A Trie is a leave when it has not further children.
		 * 
		 * @return whether this Trie is a leave or not.
		 */
		public boolean isLeaf() {
			return childNodes.size() == 0;
		}

		/**
		 * Helper method to check if a char is a letter or not.
		 * 
		 * @param letter
		 *            the char to check for
		 * @return true or false if its a letter.
		 */
		private boolean isLetter(char letter) {
			return getName().charAt(0) == letter;
		}

		/**
		 * 
		 * @return the String representation of this Node.
		 */
		private String getName() {
			return name;
		}

		/**
		 * This method will expand the Trie.
		 */
		private void expandTrie() {
			String toSplit = name.substring(1, name.length());
			name = name.charAt(0) + "";
			TrieData dataToAdd = data;
			data = null;
			Node node = new Node(toSplit, dataToAdd, this);
			childNodes.add(node);
		}

		/**
		 * Deletes a Node of the children.
		 * 
		 * @param n
		 *            the Node to delete.
		 */
		private void delete(Node n) {
			if (parentNode != null && data == null) {
				if (size() == 1) {
					// delete 1 child
					childNodes.remove(n);
					// This Node has no data left so we delete it
					parentNode.delete(this);
				} else if (size() == 2) {
					// it needs to be merged
					childNodes.remove(n);
					setToNode(childNodes.get(0));
					parentNode.collapse();
				}
			} else if (parentNode != null) {
				childNodes.remove(n);
				if (size() == 0) {
					parentNode.collapse();
				}
			}
		}

		/**
		 * Helper method to clean this Node when there is 1 child and no Data
		 * exists.
		 */
		private void collapse() {
			if (size() == 1 && data == null) {
				setToNode(childNodes.get(0));
				// sets this value to the childs values
				if (parentNode.parentNode != null) {
					parentNode.collapse();
				}
			}
		}

		/**
		 * Helper method which transfers the TrieData and String to the
		 * specified Node
		 * 
		 * @param node
		 *            the Node to transfer to
		 */
		private void setToNode(Node node) {
			data = node.data;
			name += node.name;
			childNodes.remove(node);
		}

		/**
		 * Deletes the item which matches the argument
		 * 
		 * @param s
		 *            the String to delete
		 * @param depth
		 *            the depth of the Trie
		 */
		protected void delete(String s, int depth) {
			Node temp = searchNode(s, depth);
			if (temp == null) {
				// The Node that needs to be deleted does not exist.
				return;
			} else if (temp.size() > 1) {
				// There are more nodes so deleting data is enough
				temp.data = null;
			} else if (temp.size() == 0 && temp.parentNode != null) {
				temp.parentNode.delete(temp);
			} else {
				temp.data = null;
				temp.collapse();
			}
		}

		/**
		 * Method to get the amount of children of this Node.
		 * 
		 * @return the amount of children Nodes
		 */
		private int size() {
			return childNodes.size();
		}

		@Override
		public String toString() {
			String result = "digraph heap {\n";
			return result + toDot();
		}

		/**
		 * Create a string in dot format for visualization at:
		 * http://graphviz-dev.appspot.com/
		 * 
		 * @return the String representation for this Node
		 */
		private String toDot() {
			String data = "";
			if (this.data != null) {
				data = this.data.toString();
			}
			String res = "n" + id + " [label=\"" + name + " data: " + data
					+ "\"]\n";
			for (Node d : childNodes) {
				res += d.toDot();
				res += "n" + id + "-> n" + d.id + ";\n";
			}
			return res;
		}
	}

}
