package model;


public class Edge {
	private int weight;
	Node from, to;

	public Edge(Node from, Node to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public Node getFrom() {
		return from;
	}

	public Node getTo() {
		return to;
	}

	@Override
	public boolean equals(Object obj) {
		Edge e = (Edge) obj;
		return e.from == from && e.to == to;
	}

	@Override
	public String toString() {
		return "Edge " + from + " -> " + to;
	}

}