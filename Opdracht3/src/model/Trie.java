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

	/**
	 * Search in this Trie for a specific String.
	 * 
	 * @param s
	 *            the String to look for
	 * @return the TrieData object if found
	 */
	public TrieData search(String s) {
		return root.search(s, ROOTDEPTH);
	}

	/**
	 * Deletes the Node with the specified String, and restores the Trie
	 * afterwards.
	 * 
	 * @param s
	 *            the String to search for and delete
	 */
	public void delete(String s) {
		root.delete(s, 1);
	}

	public String printTrie() {
		return root.toString();
	}

	/**
	 * Inner-class which will hold the data and the child of a Trie.
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
		 * Search
		 * 
		 * @param s
		 * @param depth
		 * @return
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
			for (Node node : childnodes) {
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

		private void delete(Node n) {
			if (parentnode != null && data == null) {
				if (size() == 1) {
					// ik heb één child die verwijdert moet worden
					childnodes.remove(n);
					// mijn data is leeg dus verwijder mij ook
					parentnode.delete(this);
				} else if (size() == 2) {
					// ik moet samengevoegd worden
					childnodes.remove(n);
					setToNode(childnodes.get(0));
					// controleer of ik nog verder samengevoegd kan worden
					parentnode.collapse();
				}
			} else if (parentnode != null) {
				childnodes.remove(n);
				if (size() == 0) {
					parentnode.collapse();
				}
			}
		}

		/**
		 * Helper method to clean this Node when there is 1 child and no Data
		 * exists.
		 */
		private void collapse() {
			if (size() == 1 && data == null) {
				// ik kan ingeklapt worden
				setToNode(childnodes.get(0));
				// zet mijn waardes naar mijn childs waardes
				if (parentnode.parentnode != null) {
					// kan ik nog ingeklapt worden?
					parentnode.collapse();
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
			childnodes.remove(node);
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
				// de string die je wilt verwijderen bestaat niet
				return;
			} else if (temp.size() > 1) {
				// heeft meerdere children onder zich dus alleen de data
				// verwijderen is genoeg
				temp.data = null;
			} else if (temp.size() == 0 && temp.parentnode != null) {
				// er moet nodes verwijdert gaan worden
				temp.parentnode.delete(temp);
			} else {
				// de node die verwijdert gaat worden heeft 1 child
				temp.data = null;
				temp.collapse();
			}
		}

		/**
		 * 
		 * @return the amount of children Nodes.
		 */
		private int size() {
			return childnodes.size();
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
