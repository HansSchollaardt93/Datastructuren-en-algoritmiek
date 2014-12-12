import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class RSHeapTests {

	@Test
	public void testRSHeap() {
		RSHeap heap = new RSHeap(5, 50);
		assertTrue(heap.getHeap().length == 5);
		System.out.println(Arrays.toString(heap.getRandom()));
		assertTrue(heap.getRandom().length == 50);
		System.out.println(heap.getWrittenToOutput());
		assertTrue(heap.getWrittenToOutput() == 50);
	}
}