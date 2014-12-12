import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

public class RSHeapTests {

	@Test
	public void testGoodRSHeapRuns() throws IOException {
		RSHeap heap = new RSHeap(10, new File("inputfile.txt"));
		assertEquals(heap.getRuns(), 501);
	}

	@Test
	public void testAllTheSameRSHeap() throws IOException {
		File file = new File("allthesame.txt");
		RSHeap heap = new RSHeap(2, file);
		assertTrue(heap.getRuns() == 1);
		assertEquals("10101010101010101010", fileToString(file));
	}

	@Test
	public void testGoodHeap() throws IOException {
		File file = new File("good.txt");
		RSHeap heap = new RSHeap(3, file);
		assertEquals(2, heap.getRuns());
		assertEquals("456510843", fileToString(file));
	}

	@Test
	public void testWorstCaseScenario() throws IOException {
		File file = new File("worstcase.txt");
		RSHeap heap = new RSHeap(3, file);
		assertEquals(3, heap.getRuns());
		assertEquals("9654328710121110", fileToString(file));
	}

	@Test
	public void testBestCaseScenario() throws IOException {
		File file = new File("bestcase.txt");
		RSHeap heap = new RSHeap(3, file);
		assertEquals(1, heap.getRuns());
		assertEquals("123456789101112", fileToString(file));
	}

	@Test
	public void testNegativeNumbers() throws IOException {
		File file = new File("negative.txt");
		RSHeap heap = new RSHeap(3, file);
		assertEquals(1, heap.getRuns());
		assertEquals("-12-11-10-9-8-7-6-5-4012", fileToString(file));
	}

	@Test
	public void testMoreHeapSizeThanNecessary() throws IOException {
		File file = new File("moreheapsize.txt");
		RSHeap heap = new RSHeap(3, file);
		assertEquals(1, heap.getRuns());
		assertEquals("1", fileToString(file));
	}

	/**
	 * Method to get String contents of a file without any whitespace used for
	 * testing.
	 * 
	 * @param file
	 *            the file to read and covert to String
	 * @return the string of the file without any whitespace
	 * @throws FileNotFoundException
	 *             if the file is not found or unreadable
	 */
	private String fileToString(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		String output = "";
		while (scanner.hasNextLine()) {
			output += scanner.nextLine();
		}
		scanner.close();
		return output;
	}

}