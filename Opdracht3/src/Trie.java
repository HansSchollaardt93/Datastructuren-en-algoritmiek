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

	private Node root;

	public Trie() {
		root = new Node("", new Data(0, 0));
	}

	/**
	 * Method to insert data with a given key ?
	 * 
	 * @param s
	 *            the key of the data?
	 * @param data
	 *            the 'index' of the String s
	 */
	public void insert(String s, Data data) {

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
	public Data[] search(String s) {
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
		private ArrayList<Node> childs;
		private ArrayList<Data> datas;

		public Node(String word, Data data) {
			// TODO
		}

		/**
		 * Method to add a child to this Node.
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

	class Data {
		private int start, end;

		public Data(int start, int end) {
			this.start = start;
			this.end = end;
		}

		public int getStart() {
			return start;
		}

		public int getEnd() {
			return end;
		}
	}

}
