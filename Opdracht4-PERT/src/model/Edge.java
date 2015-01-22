package model;

public class Edge {
	// connections where this Edge belongs to
	private Node from, to;
	private int weight;

	/**
	 * Construct a node between to edges
	 * 
	 * @param from
	 *            origin node
	 * @param to
	 *            the target node
	 * @param weight
	 *            weight of the edge
	 */
	protected Edge(Node from, Node to, int weight) {
		assert from != null : "From was null";
		assert to != null : "To was null";
		assert weight >= 0 : "Node weight was to low";

		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	public Node getFrom() {
		return from;
	}

	public Node getTo() {
		return to;
	}

	public int getWeight() {
		return weight;
	}
}