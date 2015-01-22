package model;

import java.util.ArrayList;

public class PERTNetwerk extends GenericGraph {

	public ArrayList<Node> topologicalSort() {
		ArrayList<Node> orderd = new ArrayList<Node>();
		ArrayList<Node> topological = getFirstNodes(orderd, super.getNodes());
		// restore in edges
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

	// alle nodes die geen incomming edges hebben
	// toevoegen aan lijst met georderd lijsten
	private ArrayList<Node> getFirstNodes(ArrayList<Node> orderd,
			ArrayList<Node> nodes) {

		for (Node node : nodes) {
			ArrayList<Edge> inEdges = node.getIncomingEdges();

			nodes.remove(node);
			// Node had geen edges
			if (inEdges.size() == 0) {
				orderd.add(node);
				for (Edge edge : node.getEdges()) {
					edge.getTo().removeInEdge(edge);
				}
			} else {
				// node weer achteraan toevoegen zodat hij als laatste bekeken
				// wordt
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
