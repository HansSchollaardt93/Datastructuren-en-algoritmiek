import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class RSHeapTests {

	@Test
	public void testRSHeap() throws IOException {
		RSHeap heap = new RSHeap(5, new File("inputfile.txt"));
		assertTrue(heap.getHeap().length == 5);
		assertTrue(heap.getRuns() == 50);
	}
}