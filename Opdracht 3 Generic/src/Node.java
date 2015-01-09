import java.util.ArrayList;

/**
 * This class keeps track of all children with extra variables like characters
 * and if its a complete word.
 * 
 * @param <T>
 *            The type of the custom Data objects
 * @author Hans & Benjamin
 */
public class Node<T> {

	private Node<T> parent; // The Parent node
	private ArrayList<Node<T>> children; // The child nodes
	private boolean isLeaf; // Is it a leaf
	private boolean isWord; // Is it a word
	private String firstLetter; // The first character
	private String remainder; // The remaining characters
	private T data; // Data object to store extra information

	// Just for debugging and visualization
	private static int LAST_ID;
	private int id;

	/**
	 * Constructor for the root Node
	 */
	public Node() {
		children = new ArrayList<Node<T>>();
		isLeaf = true;
		isWord = false;
		remainder = "";
		id = LAST_ID++;
	}

	/**
	 * Constructor for child Nodes of the Trie
	 * 
	 * @param firstLetter
	 *            the first letter
	 * @param remainder
	 *            the remaining characters
	 * @param data
	 *            the extra data object stored in this Node
	 */
	public Node(String firstLetter, String remainder, T data) {
		this();
		this.firstLetter = firstLetter;
		this.remainder = remainder;
		this.data = data;
	}

	/**
	 * Helper method to look up a Node and return it
	 * 
	 * @param s
	 *            the first character that has to match
	 * @return child The child Node with the starting character
	 */
	public Node<T> findNode(String s) {
		for (Node<T> child : children) {
			if (s != null && child.firstLetter != null
					&& s.equals(child.firstLetter)) {
				return child;
			}
		}
		return null;
	}

	/**
	 * This method will ad a new word to the Node.
	 * 
	 * @param word
	 *            the word that needs to be added
	 * @param data
	 *            the data object that belongs to this word
	 */
	public void insert(String word, T data) {
		isLeaf = false;
		String s = word.substring(0, 1);

		// Find the child with this specific character
		Node<T> child = findNode(s);
		if (child == null) {
			// Character not found, so a new node will be created
			// All remaining characters will be stored in this as well
			child = new Node<T>(s, word.substring(1), null);
			child.parent = this;
			child.isWord = true;
			child.data = data;
			children.add(child);
		} else {

			String childWord = child.firstLetter + child.remainder;

			// If the word already exists, edit the stored Data-object and add
			// new position
			if (child.isWord && word.equals(childWord) && data instanceof Data) {
				Data chData = (Data) child.getData();
				Data nwData = (Data) data;
				chData.addPosition(nwData.getPosition().get(0));
			} else {

				// Split the characters in seperate nodes
				if (child.remainder.length() != 0) {
					child.insert(child.remainder, child.data);
					child.isWord = false;
					child.data = null;
					child.remainder = "";
				}

				// If it an existing character within the trie
				// Make it a word and store the data
				if (word.length() == 1) {
					child.isWord = true;
					child.data = data;
				} else {
					child.insert(word.substring(1), data);
				}
			}
		}
	}

	/**
	 * Deletes the Node with the specified String, and restores the Trie
	 * afterwards.
	 * 
	 * @param word
	 *            the String to search for and delete
	 */
	public void delete(String word) {
		// Searching for the to be deleted character
		if (children.size() > 0) {
			for (int i = 0; i < children.size(); i++) {
				Node<T> child = children.get(i);
				if (child != null
						&& word.equals((child.firstLetter + child.remainder))) {
					child = null;
					children.remove(i);
					isLeaf = children.size() == 0;
					break;
				}
			}
		}

		// If it is a leaf and not root-node, ask parent to delete itself
		if (firstLetter != null && isLeaf) {
			parent.delete(firstLetter + remainder);
		}

		// If it is not a leaf but within the trie, set isWord to false and
		// delete the stored data
		if (word.equals(firstLetter) && !isLeaf) {
			isWord = false;
			data = null;
		} else {
			// Search for the leaf and collapse afterwards
			if (!isLeaf) {
				Node<T> leaf = getLeaf();
				if (leaf != null && leaf != this) {
					leaf.collapse();
				}
			}
		}
	}

	/**
	 * Helper method to get the leaf Node.
	 * 
	 * @return the leaf Node
	 */
	public Node<T> getLeaf() {
		if (children.size() == 0) {
			return this;
		}

		for (Node<T> child : children) {
			if (child != null && children.size() == 1) {
				return child.getLeaf();
			} else {
				return null;
			}
		}
		return null;
	}

	/**
	 * Helper method to clean this Node when there is 1 child and no Data
	 * exists.
	 */
	public void collapse() {
		if (parent.firstLetter == null || parent.isWord
				|| parent.children.size() != 1) {
			return;
		} else {
			parent.isWord = true;
			parent.isLeaf = true;
			parent.data = data;
			parent.remainder += firstLetter + remainder;
			parent.children.remove(0);

			parent.collapse();
		}
	}

	/**
	 * Generates a String that is compatible with
	 * http://graphviz-dev.appspot.com
	 */
	@Override
	public String toString() {
		String result = "digraph heap {\n";
		return result + toDot();
	}

	/**
	 * Create a string in dot format for this Node
	 * 
	 * @return the String representation for this Node
	 */
	private String toDot() {
		String data = "";
		String color = "";
		if (this.data != null) {
			data = this.data.toString();
			color = "blue";
		} else {
			color = "";
		}
		String res = "n" + id + " [label=\"" + firstLetter + remainder + " "
				+ data + "\",fillcolor=\"" + color + "\",style=\"filled\"]\n";

		for (Node<T> d : children) {
			res += d.toDot();
			res += "n" + id + "-> n" + d.id + ";\n";
		}
		return res;
	}

	/**
	 * Helper method to get all remaining characters
	 * 
	 * @return the remaining characters
	 */
	public String getRemainder() {
		return remainder;
	}

	/**
	 * Helper method to get all children of this Node
	 * 
	 * @return ArrayList of all children
	 */
	public ArrayList<Node<T>> getChildren() {
		return children;
	}

	/**
	 * 
	 * @return the Data Object with type T
	 */
	public T getData() {
		return data;
	}
}
