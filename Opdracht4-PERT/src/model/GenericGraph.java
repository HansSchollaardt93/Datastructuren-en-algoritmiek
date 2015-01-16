package model;

import java.util.HashSet;
import java.util.Set;

public class GenericGraph {
	private Node rootnode;
	private Set<Node> childNodes;

	public GenericGraph(Node node) {
		this.rootnode = node;
		this.childNodes = (new HashSet<>());
	}

	public Set<Node> getChildNodes() {
		return childNodes;
	}

}
