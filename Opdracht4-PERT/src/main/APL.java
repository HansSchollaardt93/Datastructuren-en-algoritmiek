package main;

import model.Edge;
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

		Edge AB = new Edge(A, B, 3);
		Edge AG = new Edge(A, G, 1);
		Edge AD = new Edge(A, D, 1);
		Edge BC = new Edge(B, C, 1);
		Edge BF = new Edge(B, F, 1);
		Edge DE = new Edge(D, E, 1);
		Edge GF = new Edge(G, F, 1);
		Edge EF = new Edge(E, F, 1);
		Edge FC = new Edge(F, C, 1);

		// netwerk.putNode(A);

	}
}
