import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TrieTest {
	private Trie<Data> trie;

	@Before
	public void setUp() throws Exception {
		trie = new Trie<Data>();
	}

	@Test
	public void testTrie() {
		assertNotNull(trie);
		assertTrue(trie.size() == 0);
	}

	@Test
	public void testInsertAndSearch() {
		trie.insert("do", new Data(5));
		trie.insert("dorm", new Data(9));
		trie.insert("doll", new Data(13));

		int pos5 = trie.search("do").getPosition().get(0);
		int pos9 = trie.search("dorm").getPosition().get(0);
		int pos13 = trie.search("doll").getPosition().get(0);

		assertEquals(5, pos5);
		assertEquals(9, pos9);
		assertEquals(13, pos13);

		assertFalse(-1 == pos5);
		assertFalse(-1 == pos9);
		assertFalse(-1 == pos13);
	}

	@Test
	public void testDelete() {
		trie.insert("do", new Data(5));
		trie.insert("dorm", new Data(9));
		trie.insert("doll", new Data(13));

		assertNotNull(trie.search("do"));
		trie.delete("do");
		assertNull(trie.search("do"));
	}

	@Test(expected = AssertionError.class)
	public void insertNullOrEmpty() {
		trie.insert(null, new Data(8));
		trie.insert("", new Data(8));
		trie.insert("dorm", null);
	}

	@Test(expected = AssertionError.class)
	public void deleteNullOrEmpty() {
		trie.delete(null);
		trie.delete("");
	}

	@Test(expected = AssertionError.class)
	public void findNullAndEmpty() {
		trie.search(null);
		trie.search("");
	}

}
