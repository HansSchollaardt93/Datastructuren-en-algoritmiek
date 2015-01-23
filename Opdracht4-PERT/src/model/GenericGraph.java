package model;

import java.util.ArrayList;

public abstract class GenericGraph {

	protected ArrayList<Node> nodes;

	/**
	 * Constructs a default weighted directed Graph
	 */
	public GenericGraph() {
		nodes = new ArrayList<Node>();
	}

	/**
	 * Adds a Node to this Graph
	 * 
	 * @param node
	 *            the Node to add to this Graph
	 */
	public void addNode(Node node) {
		assert nodes.contains(node) : "Node already exists";
		nodes.add(node);
	}

	/**
	 * Connects two Nodes with a given weight
	 */
	public void connect(Node from, Node to, int weight) {
		assert from != null : "Edge from was null";
		assert to != null : "Edge to was null";
		assert isValidNode(from) : "From Node does not exist";
		assert isValidNode(to) : "To Node does not exist";

		Edge edge = new Edge(from, to, weight);
		for (Node node : nodes) {
			if (from == node) {
				node.addEdge(edge);
			}
		}
	}

	/**
	 * 
	 * @param n
	 *            the Node to check
	 * @return true or false whether this is a valid node or not
	 */
	private boolean isValidNode(Node n) {
		return nodes.contains(n);
	}

	/**
	 * @return an immutable copy of the Nodes ArrayList
	 */
	protected ArrayList<Node> getNodes() {
		return new ArrayList<Node>(nodes);
	}

}
