package model;

import java.util.ArrayList;

public class PERTNetwerk extends GenericGraph {
	/**
	 * 
	 * @return all Nodes sorted in the topological order
	 */
	public ArrayList<Node> topologicalSort() {
		ArrayList<Node> orderd = new ArrayList<Node>();
		ArrayList<Node> topological = getFirstNodes(orderd, getNodes());
		// restore inEdges
		for (Node node : getNodes()) {
			for (Edge edge : node.getEdges()) {
				edge.getTo().addIncomingEdge(edge);
			}
		}
		calcEarliestTimes(orderd);
		calcLatestTimes(orderd);
		return topological;
	}

	/**
	 * Calcs all earliest times for each Node in the specified list
	 * 
	 * @param order
	 *            the list of Nodes in topoligal order
	 */
	private void calcEarliestTimes(ArrayList<Node> order) {
		assert order != null : "Node list cannot be null";
		// start at the first node and calc the earliest time
		for (Node node : order) {
			node.calcEarliestTime();
		}
	}

	/**
	 * Calcs all lastest times for each Node in the specified list
	 * 
	 * @param order
	 *            the list of Nodes in topoligal order
	 */
	private void calcLatestTimes(ArrayList<Node> order) {
		assert order != null : "Topological list cannot be null";
		// start at the last node and calc the latest time
		for (int i = order.size() - 1; i >= 0; i--) {
			Node node = order.get(i);
			node.calcLastTime();
		}
	}

	/**
	 * Helper method to get all first Nodes
	 * 
	 * @param orderd
	 *            the list of Nodes in topoligal order
	 * @param nodes
	 * @return ArrayList with all Nodes that dont have incoming edges
	 */
	private ArrayList<Node> getFirstNodes(ArrayList<Node> orderd,
			ArrayList<Node> nodes) {
		assert orderd != null : "Topological list cannot be null";
		assert nodes != null : "Node list cannot be null";

		for (Node node : nodes) {
			ArrayList<Edge> inEdges = node.getIncomingEdges();
			nodes.remove(node);
			// Node doesnt have edges
			if (inEdges.size() == 0) {
				orderd.add(node);
				for (Edge edge : node.getEdges()) {
					edge.getTo().removeInEdge(edge);
				}
			} else {
				// adding Node on the back so it will be visited last
				nodes.add(node);
			}
			break;
		}
		if (nodes.size() == 0) {
			return orderd;
		}
		return getFirstNodes(orderd, nodes);
	}

	/**
	 * A String which can be displayed visually on:
	 * http://graphviz-dev.appspot.com/
	 */
	@Override
	public String toString() {
		String result = "";
		result += "digraph PERT {\n";
		for (Node node : getNodes()) {
			result += node.toDotStringEdges();
		}

		for (Node node : getNodes()) {
			result += node.getName() + " [label=\"" + node + "\"];\n";
		}
		result += "}";
		return result;
	}
}