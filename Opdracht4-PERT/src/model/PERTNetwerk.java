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

	public void generateTopologicalSort() {
		assert (networknodes != null) : "There are no nodes to process!";
		assert (topologicalSort != null) : "List cannot be null!";
		assert (topologicalSort.isEmpty()) : "List is not empty!";

		// resets current topo sort
		topologicalSort.clear();
		// Iterate through list of nodes

		// incoming <- Set of all nodes with no incoming edges
		HashSet<Node> incoming = new HashSet<Node>();
		for (Node n : networknodes) {
			if (n.inEdges.size() == 0) {
				incoming.add(n);
			}
		}

		// while incoming is non-empty do
		while (!incoming.isEmpty()) {
			// remove a node n from incoming
			Node n = incoming.iterator().next();
			incoming.remove(n);

			// insert n into topological sort
			topologicalSort.add(n);

			// for each node m with an edge e from n to m do
			for (Iterator<Edge> it = n.outEdges.iterator(); it.hasNext();) {
				// remove edge e from the graph
				Edge e = it.next();

				Node m = e.to;

				it.remove();// Remove edge from n
				m.inEdges.remove(e);// Remove edge from m
				System.out.println("removing " + e + " from m.inEdges");

				// if m has no other incoming edges then insert m into incoming
				if (m.inEdges.isEmpty()) {
					incoming.add(m);
					System.out.println("adding " + m + " to incoming");
				}
			}
		}
		System.out.println("toposort: " + topologicalSort);
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
