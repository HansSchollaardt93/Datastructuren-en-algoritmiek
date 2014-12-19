/**
 * Implements a Reduced Trie
 * 
 * @author Benjamin & Hans Schollaardt
 * 
 * @param <T>
 *            the Type of the data to store
 */
public class RedTrie<T> {

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
		private Node child;

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
		 * Method to get the 'next' Node.
		 * 
		 * @return the 'next' Node of this Node.
		 */
		public Node getChild() {
			return child;
		}

		/**
		 * Method to set the 'next' Node of this Node.
		 * 
		 * @return the 'next' of the
		 */
		public void setChild(Node child) {
			this.child = child;
		}

		/**
		 * 
		 * @return whether this Node has a child Node.
		 */
		public boolean hasChild() {
			return child != null;
		}

		/**
		 * Removes the child Node of this Node by setting it to null.
		 */
		public void removeChild() {
			this.child = null;
		}

		/**
		 * A Node is a leave when it has not further children.
		 * 
		 * @return whether this Node is a leave or not.
		 */
		public boolean isLeave() {
			return child == null;
		}

	}

}
