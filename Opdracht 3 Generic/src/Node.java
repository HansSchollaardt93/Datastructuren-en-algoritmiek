import java.util.ArrayList;

/**
 * This TrieNode<T> class keeps track of all his children and other variables
 * such as his characters and if its a complete word
 * 
 * @author Idmon & Emre
 * @param <T>
 *            Data Structure
 * 
 */
public class Node<T> {

	private Node<T> parent; // The Parent node
	private ArrayList<Node<T>> children; // His child nodes
	private boolean isLeaf; // Is it a leaf
	private boolean isWord; // Is it a word
	private String startCharacter; // The starting character
	private String characters; // The remaining characters
	private T data; // Data object to store extra information

	// Keep track of node numbers (For graph visualizing purposes)
	private static int LAST_ID;
	private int id;

	/**
	 * Constructor for the root-node
	 */
	public Node() {
		children = new ArrayList<Node<T>>();
		isLeaf = true;
		isWord = false;
		characters = "";
		id = LAST_ID++;
	}

	/**
	 * Constructor for the child-nodes
	 * 
	 * @param startCharacter
	 *            - The starting character
	 * @param characters
	 *            - The remaining characters
	 * @param data
	 *            - The extra data-object
	 */
	public Node(String startCharacter, String characters, T data) {
		this();
		this.startCharacter = startCharacter;
		this.characters = characters;
		this.data = data;
	}

	/**
	 * Find a node with the same starting character
	 * 
	 * @param c
	 *            - Starting Character you're looking for
	 * @return child - The child with this starting character
	 */
	public Node<T> findNode(String s) {
		for (Node<T> child : children) {
			if (s != null && child.startCharacter != null
					&& s.equals(child.startCharacter)) {
				return child;
			}
		}
		return null;
	}

	/**
	 * Adds a new word to the TrieNode<T>. This method works through recursion
	 * by adding a new child if the character has not been found in its
	 * children. Also if there are still characters remaining, they will be all
	 * stored in the 'characters' variable to reduce the depth of this tree.
	 * 
	 * @param word
	 *            - The word that needs to be added
	 * @param newData
	 *            - The data-object that needs to be stored within the word
	 */
	public void insert(String word, T newData) {
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
			child.data = newData;
			children.add(child);
		} else {

			String childWord = child.startCharacter + child.characters;

			// If the word already exists, edit the stored Data-object and add
			// new position
			if (child.isWord && word.equals(childWord)
					&& newData instanceof Data) {
				Data chData = (Data) child.getData();
				Data nwData = (Data) newData;
				chData.addPosition(nwData.getPosition().get(0));
			} else {

				// Split the characters in seperate nodes
				if (child.characters.length() != 0) {
					child.insert(child.characters, child.data);
					child.isWord = false;
					child.data = null;
					child.characters = "";
				}

				// If it an existing character within the trie
				// Make it a word and store the data
				if (word.length() == 1) {
					child.isWord = true;
					child.data = newData;
				} else {
					child.insert(word.substring(1), newData);
				}
			}
		}
	}

	/**
	 * This method deletes a word from the TrieNode<T> It uses recursion to and
	 * asks from its parent-node to be deleted After deletion, the trie is
	 * automatically gonna re-merge itself to reduce the depth
	 */
	public void delete(String s) {
		System.out.println("Traversing in: " + s);
		// Searching for the to be deleted character
		if (children.size() > 0) {
			for (int i = 0; i < children.size(); i++) {
				Node<T> child = children.get(i);
				if (child != null
						&& s.equals((child.startCharacter + child.characters))) {
					System.out.println("Node '" + s + "' is deleted!");
					child = null;
					children.remove(i);
					isLeaf = children.size() == 0;
					break;
				}
			}
		}

		// If it is a leaf and not root-node, ask parent to delete itself
		if (startCharacter != null && isLeaf) {
			parent.delete(startCharacter + characters);
		}

		// If it is not a leaf but within the trie, set isWord to false and
		// delete the stored data
		if (s.equals(startCharacter) && !isLeaf) {
			System.out
					.println("Character stays in the tree, but word is being deleted");
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
		if (parent.startCharacter == null || parent.isWord
				|| parent.children.size() != 1) {
			return;
		} else {
			parent.isWord = true;
			parent.isLeaf = true;
			parent.data = data;
			parent.characters += startCharacter + characters;
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
		String res = "n" + id + " [label=\"" + startCharacter + characters
				+ " " + data + "\",fillcolor=\"" + color
				+ "\",style=\"filled,rounded\"]\n";

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
	public String getCharacters() {
		return characters;
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
	 * @return the Data Object of type T
	 */
	public T getData() {
		return data;
	}
}
