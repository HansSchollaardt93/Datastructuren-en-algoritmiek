/**
 * Implementation of a reduced Trie.
 * 
 * @author Hans & Benjamin
 * @param <T>
 *            You can choose your own type here.
 */
public class Trie<T> {

	private Node<T> root; // The root Node of this Trie.

	public Trie() {
		root = new Node<T>();
	}

	/**
	 * Method to insert a String into the Trie.
	 * 
	 * @param word
	 *            the word it self
	 * @param data
	 *            the Data object of the type T
	 */
	public void insert(String word, T data) {
		assert word != null : "Word cannot be null";
		assert !word.equals("") : "Word cannot be empty";
		assert data != null : "Data object cannot be null";

		assert root != null;

		root.insert(word.toLowerCase(), data);

		assert search(word) != null : "Failed to add word: " + word;
	}

	/**
	 * Search in this Trie for a specific String.
	 * 
	 * @param word
	 *            the String to look for
	 * @return the TrieData object if found
	 */
	public T search(String word) {
		assert word != null : "Word cannot be null";
		assert !word.equals("") : "Word cannot be empty";

		Node<T> lastNode = root;

		// Trying to find a node of the last letter of the prefix
		for (int i = 0; i < word.length(); i++) {
			lastNode = lastNode.findNode(word.substring(i, i + 1));

			// If there hasn't been found one, return 'null'
			if (lastNode == null) {
				return null;
			}

			String remaingstr = word.substring(i + 1);
			if (lastNode.getRemainder().equals(remaingstr)) {
				break;
			}
		}
		return lastNode.getData();
	}

	/**
	 * Deletes the Node with the specified String, and restores the Trie
	 * afterwards.
	 * 
	 * @param word
	 *            the String to search for and delete
	 */
	public void delete(String word) {
		assert word != null : "Word cannot be null";
		assert !word.equals("") : "Word cannot be empty";

		Node<T> lastNode = root;

		// Trying to find a node of the last letter of the prefix
		int i = 0;
		for (; i < word.length(); i++) {
			lastNode = lastNode.findNode(word.substring(i, i + 1));

			// If there hasn't been found one, return 'null'
			if (lastNode == null) {
				return;
			}

			String remaingstr = word.substring(i + 1);
			if (lastNode.getRemainder().equals(remaingstr)) {
				break;
			}
		}
		lastNode.delete(word.substring(i));
	}

	/**
	 * Method to get the amount of elements within this Trie
	 * 
	 * @return the size of this Trie
	 */
	public int size() {
		return root.getChildren().size();
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
}
