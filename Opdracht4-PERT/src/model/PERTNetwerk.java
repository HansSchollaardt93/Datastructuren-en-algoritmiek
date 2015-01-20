package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PERTNetwerk extends GenericGraph {
	private Set<Node> networknodes;
	private ArrayList<Node> topologicalSort;

	public PERTNetwerk(Node node) {
		super(node);
		networknodes = new HashSet<Node>();
		topologicalSort = new ArrayList<>();
	}

	public void vroegsteTijden() {

	}

	public void laatsteTijden() {

	}

	public ArrayList<Node> generateTopologicalSort(ArrayList<Node> visited) {
		assert (networknodes != null) : "There are no nodes to process!";
		assert (topologicalSort != null) : "List cannot be null!";
		assert (topologicalSort.isEmpty()) : "List is not empty!";
		// Iterate through list of nodes

		while (networknodes.size() > 0) {
			Iterator<Node> i = networknodes.iterator();
			while (i.hasNext()) {
				Node n = i.next();
				System.out.println("n: " + n);

				boolean hasedge = false;
				for (Node node : networknodes) {
					System.out.println("node: " + node);
					if (node != n) {
						System.out.println("node!=n");
						System.out.println("hasEdge" + hasEdge(node, n));
						if (hasEdge(node, n)) {
							hasedge = true;
							break;
						}
					}
				}
				if (!hasedge) {
					topologicalSort.add(n);
					i.remove();
				}
			}
		}

		// return the list of nodes
		return visited;
	}

	private boolean hasEdge(Node from, Node to) {
		for (Edge e : to.inEdges) {
			System.out.println("e: " + e);
		}

		return false;
	}

	public void putNode(Node node) {
		assert (node != null);
		assert (!networknodes.contains(node));

		networknodes.add(node);
	}

	@Override
	public String toString() {
		return networknodes.toString();
	}

}
