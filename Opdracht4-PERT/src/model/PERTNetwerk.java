package model;

import java.util.ArrayList;
import java.util.HashSet;
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
		for (Node node : networknodes) {
			// find node with indegree 0
			if (visited.isEmpty()) {
				visited.add(node);
			} else if (visited.contains(node)) {
				return generateTopologicalSort(visited);
			}

			// if more nodes exist, choose one
			// make sure it is not visited already (<-- no cycles allowed!)

			// add it to list
			topologicalSort.add(node);
			// else if none exist you've reached the end?
		}
		// return the list of nodes
		return topologicalSort;
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
