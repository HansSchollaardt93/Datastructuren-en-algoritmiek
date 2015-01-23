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

		// System.out.println(correctNetwerk);
	}

	@Test
	public void testMoreEndNodes() {
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

		Node nogEenNode = new Node("N");

		netwerk.addNode(nogEenNode);

		netwerk.connect(F, nogEenNode, 5);

		netwerk.topologicalSort();

		System.out.println(netwerk);
	}

	@Test
	public void testMoreStartNodes() {
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

		Node nogEenNode = new Node("N");

		netwerk.addNode(nogEenNode);

		netwerk.connect(nogEenNode, G, 5);

		netwerk.topologicalSort();

		// System.out.println(netwerk);
	}

	@Test
	public void testOneLine() {
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

		netwerk.connect(A, B, 2);
		netwerk.connect(B, C, 5);
		netwerk.connect(C, D, 1);
		netwerk.connect(D, E, 3);
		netwerk.connect(E, F, 4);
		netwerk.connect(F, G, 6);

		netwerk.topologicalSort();

		// System.out.println(netwerk);
	}

	@Test(expected = AssertionError.class)
	public void testDoubleNodes() {
		PERTNetwerk netwerk = new PERTNetwerk();
		Node A = new Node("A");
		netwerk.addNode(A);
		netwerk.addNode(A);
		System.out.println(netwerk);
	}

	@Test(expected = AssertionError.class)
	public void testEdgeToSame() {
		PERTNetwerk netwerk = new PERTNetwerk();
		Node A = new Node("A");
		netwerk.addNode(A);
		netwerk.connect(A, A, 5);
		System.out.println(netwerk);
	}

	@Test(expected = AssertionError.class)
	public void testConnectNullNodes() {
		correctNetwerk.connect(null, null, -5);
	}

	@Test(expected = AssertionError.class)
	public void testConnectNodesNegativeWeight() {
		correctNetwerk.connect(new Node("X"), new Node("Y"), -5);
	}

	@Test(expected = AssertionError.class)
	public void testAddNullNodes() {
		correctNetwerk.addNode(null);
	}

	@Test
	public void testTopologicalOrder() {
		assertEquals(correctNetwerk.topologicalSort().toString(),
				"[A (0,0), B (3,4), D (1,3), E (2,4), G (3,3), F (5,5), C (6,6)]");
	}
	// meer begin nodes
}
