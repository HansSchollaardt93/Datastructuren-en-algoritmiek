import java.util.ArrayList;

/**
 * Implements a Reduced Trie
 * 
 * @author Benjamin & Hans Schollaardt
 * 
 * @param <T>
 *            the Type of the data to store
 */
public class RedTrie<T> {

	private Node root;

	public RedTrie() {
		root = new Node("", T);
	}

	/**
	 * Method to insert data with a given key ?
	 * 
	 * @param s
	 *            the key of the data?
	 * @param data
	 *            the 'index' of the String s
	 */
	public void insert(String s, T data) {

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
	public T[] search(String s) {
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
	 * Inner-class which will hold the data and the child of a Node of the
	 * RedTrie.
	 * 
	 * @author Benjamin & Hans Schollaardt.
	 * 
	 */
	private class Node {
		private T data;
		private ArrayList<Node> childs;

		public Node(String word, T data) {
			setData(data);
		}

		/**
		 * Method to get the 'identifier' of this Node.
		 * 
		 * @return the 'identifier' of this Node
		 */
		public T getData() {
			return data;
		}

		/**
		 * Method to set the 'identifier' of this Node.
		 */
		public void setData(T data) {
			this.data = data;
		}

		/**
		 * Method to add a Node to this Node.
		 * 
		 * @return the 'next' of the
		 */
		public void addChild(Node child) {
			childs.add(child);
		}

		/**
		 * 
		 * @return whether this Node has a child Node.
		 */
		public boolean hasChild() {
			return childs != null && childs.size() > 0;
		}

		/**
		 * Removes the child Node of this Node by setting it to null.
		 * 
		 * @return whether the child has been removed or not.
		 */
		public boolean removeChild(Node child) {
			return childs.remove(child);
		}

		/**
		 * A Node is a leave when it has not further children.
		 * 
		 * @return whether this Node is a leave or not.
		 */
		public boolean isLeave() {
			return childs == null || childs.size() == 0;
		}

	}

}
