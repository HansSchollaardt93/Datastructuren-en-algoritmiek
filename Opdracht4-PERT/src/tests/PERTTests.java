package tests;

import static org.junit.Assert.assertEquals;
import model.Node;
import model.PERTNetwerk;

import org.junit.Before;
import org.junit.Test;

public class PERTTests {
	private PERTNetwerk correctNetwerk;

	@Before
	public void setUp() {
		correctNetwerk = new PERTNetwerk();

		Node A = new Node("A");
		Node B = new Node("B");
		Node C = new Node("C");
		Node D = new Node("D");
		Node E = new Node("E");
		Node F = new Node("F");
		Node G = new Node("G");

		correctNetwerk.addNode(A);
		correctNetwerk.addNode(B);
		correctNetwerk.addNode(C);
		correctNetwerk.addNode(D);
		correctNetwerk.addNode(E);
		correctNetwerk.addNode(F);
		correctNetwerk.addNode(G);

		correctNetwerk.connect(E, F, 1);
		correctNetwerk.connect(A, B, 3);
		correctNetwerk.connect(A, D, 1);
		correctNetwerk.connect(A, G, 3);
		correctNetwerk.connect(G, F, 2);
		correctNetwerk.connect(B, C, 1);
		correctNetwerk.connect(B, F, 1);
		correctNetwerk.connect(D, E, 1);
		correctNetwerk.connect(F, C, 1);
	}

	@Test(expected = AssertionError.class)
	public void testDoubleNodes() {
		PERTNetwerk netwerk = new PERTNetwerk();
		Node A = new Node("A");
		netwerk.addNode(A);
		netwerk.addNode(A);
	}

	@Test(expected = AssertionError.class)
	public void testEdgeToSame() {
		PERTNetwerk netwerk = new PERTNetwerk();
		Node A = new Node("A");
		netwerk.addNode(A);
		netwerk.connect(A, A, 5);
	}

	@Test
	public void testTopologicalOrder() {
		assertEquals(correctNetwerk.topologicalSort().toString(),
				"[A (0,0), B (3,4), D (1,3), E (2,4), G (3,3), F (5,5), C (6,6)]");
	}

}
