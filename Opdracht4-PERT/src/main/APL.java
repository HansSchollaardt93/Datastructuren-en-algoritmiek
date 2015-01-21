package main;

import model.Node;
import model.PERTNetwerk;

public class APL {

	public static void main(String[] args) {
		Node A = new Node("A");
		Node B = new Node("B");
		Node C = new Node("C");
		Node D = new Node("D");
		Node E = new Node("E");
		Node F = new Node("F");
		Node G = new Node("G");

		PERTNetwerk netwerk = new PERTNetwerk(A);

		netwerk.putNode(A);
		netwerk.putNode(B);
		netwerk.putNode(C);
		netwerk.putNode(D);
		netwerk.putNode(E);
		netwerk.putNode(F);
		netwerk.putNode(G);

		A.addEdge(B, 3);
		A.addEdge(G, 1);
		A.addEdge(D, 1);
		B.addEdge(C, 1);
		B.addEdge(F, 1);
		D.addEdge(E, 1);
		G.addEdge(F, 1);
		E.addEdge(F, 1);
		F.addEdge(C, 1);

		// Print graph
		System.out.println(netwerk);
		netwerk.generateTopologicalSort();
		System.out.println(netwerk);
		netwerk.generateTopologicalSort();

	}
}
