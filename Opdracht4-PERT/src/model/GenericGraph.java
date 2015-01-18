package model;

import java.util.HashSet;
import java.util.Set;

public class GenericGraph {
	private Node rootnode;
	private Set<Node> childNodes;

	public GenericGraph(Node node) {
		this.rootnode = node;
		this.childNodes = new HashSet<Node>();
	}

	public Set<Node> getChildNodes() {
		return childNodes;
	}
	
	public boolean hasPathTo(){
		return false;
	}
	
	
	
	

}
