package model;

import java.util.HashSet;

public class Node {
	public final HashSet<Edge> inEdges, outEdges;
	private String name;
	private int shortestTime, longestTime;
	private boolean isVisited = false;

	public Node(String name) {
		this.name = name;
		this.inEdges = new HashSet<Edge>();
		this.outEdges = new HashSet<Edge>();
		shortestTime = 0;
		longestTime = 0;
	}

	public String getName() {
		return name;
	}

	public void setShortestTime(int shortestTime) {
		this.shortestTime = shortestTime;
	}

	public void setLongestTime(int longestTime) {
		this.longestTime = longestTime;
	}

	public int getLongestTime() {
		return longestTime;
	}

	public int getShortestTime() {
		return shortestTime;
	}

	public Node addEdge(Node toNode, int weight) {
		Edge e = new Edge(this, toNode, longestTime);
		outEdges.add(e);
		toNode.inEdges.add(e);
		return this;
	}

	@Override
	public String toString() {
		return name;
	}

}