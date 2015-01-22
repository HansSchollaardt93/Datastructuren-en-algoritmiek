package main;

import java.util.ArrayList;

import model.Node;
import model.PERTNetwerk;

public class APL {

	public static void main(String[] args) {
		PERTNetwerk netwerk = new PERTNetwerk();

		Node A = new Node("A");
		Node B = new Node("B");
		Node C = new Node("C");
		Node D = new Node("D");
		Node E = new Node("E");
		Node F = new Node("F");
		Node G = new Node("G");

		netwerk.addNode(A);
		netwerk.addNode(B);
		netwerk.addNode(C);
		netwerk.addNode(D);
		netwerk.addNode(E);
		netwerk.addNode(F);
		netwerk.addNode(G);

		netwerk.connect(E, F, 1);
		netwerk.connect(A, B, 3);
		netwerk.connect(A, D, 1);
		netwerk.connect(A, G, 3);
		netwerk.connect(G, F, 2);
		netwerk.connect(B, C, 1);
		netwerk.connect(B, F, 1);
		netwerk.connect(D, E, 1);
		netwerk.connect(F, C, 1);

		// Print graph
		System.out.println(netwerk.toString());
		ArrayList<Node> orderd = netwerk.topologicalSort();
		ArrayList<Node> order = netwerk.topologicalSort();
		System.out.println(orderd.toString());
		System.out.println(order.toString());

		System.out.println(netwerk);

	}
}
