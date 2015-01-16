package main;

import model.Edge;
import model.PERTNetwerk;
import model.Node;

public class APL {

	public static void main(String[] args) {
		PERTNetwerk netwerk = new PERTNetwerk(new Node());

		Node A = new Node();
		Node B = new Node();
		Node C = new Node();
		Node D = new Node();
		Node E = new Node();
		Node F = new Node();
		Node G = new Node();
		
		Edge AB = new Edge(A,B,3);
		Edge AG = new Edge(A,G,1);
		Edge AD = new Edge(A,D,1);
		Edge BC = new Edge(B,C,1);
		Edge BF = new Edge(B,F,1);
		Edge DE = new Edge(D,E,1);
		Edge GF = new Edge(G,F,1);
		Edge EF = new Edge(E,F,1);
		Edge FC = new Edge(F,C,1);
		
	}
}
