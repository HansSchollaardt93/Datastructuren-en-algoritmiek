package model;

public class Edge {
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
		assert from != null : "From cannot be null";
		assert to != null : "To cannot be null";
		assert weight >= 0 : "The weight has to be >=0";

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

	@Override
	public String toString() {
		return from + " " + to + " (" + weight + ")";
	}

}