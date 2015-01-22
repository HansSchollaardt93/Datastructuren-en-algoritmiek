package model;

import java.util.ArrayList;

public class PERTNetwerk extends GenericGraph {
	/**
	 * 
	 * @return all Nodes sorted in the topological order
	 */
	public ArrayList<Node> topologicalSort() {
		ArrayList<Node> orderd = new ArrayList<Node>();
		ArrayList<Node> topological = getFirstNodes(orderd, super.getNodes());
		// restore inEdges
		for (Node node : nodes) {
			for (Edge edge : node.getEdges()) {
				edge.getTo().addIncomingEdge(edge);
			}
		}
		getEarliestTimes(orderd);
		getLatestTimes(orderd);
		return topological;
	}

	private void getEarliestTimes(ArrayList<Node> orderd) {
		for (Node node : orderd) {
			node.calcEarliestTime();
		}
	}

	private void getLatestTimes(ArrayList<Node> orderd) {
		for (int i = orderd.size() - 1; i >= 0; i--) {
			Node node = orderd.get(i);
			node.calcLastTime();
		}
	}

	/**
	 * @param orderd
	 * @param nodes
	 * @return ArrayList with all Nodes that dont have incoming edges
	 */
	private ArrayList<Node> getFirstNodes(ArrayList<Node> orderd,
			ArrayList<Node> nodes) {
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
		result += "digraph graaf {\n";
		for (Node node : nodes) {
			result += node.toDotStringEdges();
		}

		for (Node node : nodes) {
			result += node.getName() + " [label=\"" + node.toString()
					+ "\"];\n";
		}
		result += "}";
		return result;
	}
}