package model;

import java.util.HashSet;

public class Node {
	public final HashSet<Edge> inEdges, outEdges;
	private String name;

	public Node(String name) {
		this.name = name;
		this.inEdges = new HashSet<Edge>();
		this.outEdges = new HashSet<Edge>();
	}

	public String getName() {
		return name;
	}

}