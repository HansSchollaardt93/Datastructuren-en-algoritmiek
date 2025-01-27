package model;

import java.util.ArrayList;

public class Node {
	private ArrayList<Edge> edges, inEdges;
	private String name;
	private int earliestTime, lastTime;

	/**
	 * Constructs a new Node with the given name.
	 * 
	 * @param name
	 *            the Name of this Node.
	 */
	public Node(String name) {
		this.name = name;
		// All edges that start from this Node
		edges = new ArrayList<Edge>();
		// Edges that end in this Node
		inEdges = new ArrayList<Edge>();
	}

	/**
	 * 
	 * @param edge
	 *            add an Edge to this Node to connect two Nodes
	 */
	protected void addEdge(Edge edge) {
		assert edge != null : "Edge cannot be null";
		assert !hasEdge(edge) : "Edge already exists";
		edges.add(edge);
		edge.getTo().addIncomingEdge(edge);
	}

	/**
	 * 
	 * @param edge
	 *            the incoming Edge to add
	 */
	protected void addIncomingEdge(Edge edge) {
		assert edge != null : "Edge cannot be null";
		inEdges.add(edge);
	}

	/**
	 * 
	 * @return the name of this Node.
	 */
	protected String getName() {
		return name;
	}

	/**
	 * 
	 * @return a valid dot string for this Node so it can be displayed in
	 *         http://graphviz-dev.appspot.com
	 */
	public String toDotStringEdges() {
		String dot = "";
		for (Edge edge : edges) {
			dot += name + "->" + edge.getTo().name + "[label="
					+ edge.getWeight() + "]\n";
		}
		return dot;
	}

	/**
	 * 
	 * @param edge
	 *            the Edge to look for
	 * @return true or false whether this Node has this Edge
	 */
	private boolean hasEdge(Edge edge) {
		for (Edge theedge : edges) {
			if (theedge.getFrom().equals(edge.getFrom())
					&& theedge.getTo().equals(edge.getTo())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @return all incoming Edges belonging to this Node
	 */
	public ArrayList<Edge> getIncomingEdges() {
		return new ArrayList<Edge>(inEdges);
	}

	/**
	 * 
	 * @return all Edges that belong to this Node
	 */
	public ArrayList<Edge> getEdges() {
		return edges;
	}

	@Override
	public String toString() {
		return name + " (" + earliestTime + "," + lastTime + ")";
	}

	public void removeInEdge(Edge edge) {
		boolean removed = inEdges.remove(edge);
		assert removed : "Edge was not deleted";
	}

	/**
	 * Calculates the earliest time of this Node depending on neighbours
	 */
	public void calcEarliestTime() {
		if (inEdges.isEmpty()) {
			this.earliestTime = 0;
		} else {
			int max = 0;
			for (Edge edge : inEdges) {
				max = Math.max(max,
						(edge.getFrom().earliestTime + edge.getWeight()));
			}
			earliestTime = max;
		}
	}

	/**
	 * Calculates the latest time of this Node depending on neighbours
	 */
	public void calcLastTime() {
		if (edges.isEmpty()) {
			this.lastTime = earliestTime;
		} else {
			int min = Integer.MAX_VALUE;
			for (Edge edge : edges) {
				min = Math.min(min, (edge.getTo().lastTime - edge.getWeight()));
			}
			lastTime = min;
		}
	}

	/**
	 * Overriden so we can check for double Nodes
	 */
	@Override
	public boolean equals(Object object) {
		Node other = (Node) object;
		return other.name.equals(name);
	}

}