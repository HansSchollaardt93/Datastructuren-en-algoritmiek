import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 * @author Hans Schollaardt
 */
public class RSHeap {
	private static int HEAP_SIZE;
	private static final int RANDOM_NUMBERS = 2000;
	private static final int RANDOMIZE_AROUND = 5000;
	private int[] random, heap;
	private boolean done;
	private Scanner in;
	private PrintWriter out;

	public RSHeap(int heapsize) {
		init(heapsize);

		int smallest;
		int next;
//		done = false;
		// read HEAP_SIZE-elements into heap-array

		buildHeap(heap);
		// while array still holds more values, continue
		while (in.hasNext()) {
			// remove smallest element from heap and write this to OUT
			smallest = getSmallest();
			writeToOutput(smallest); 
			next = Integer.parseInt(in.nextLine());
			
			
			if (next >= smallest) { // next fits into current run
			// insert next into heap
			} else {
				// Heapsize H--, Deadspace++
				// insert next into heap
			}
			if (heap.length == 0){
				buildheap(dead space);
			}
//			done = true;

			// write out to file
		}
		//write rest of heap to OUT
		printToOutput(heap);
		buildheap(dead space);
//		write rest to OUT
		printToOutput(rest);

	}

	/**
	 * Method to print the remainder of arrays to output file
	 * @param array
	 *            array to write to file
	 */
	private void printToOutput(int[] array) {

	}

	/**
	 * Method to print single integer to output file
	 * @param smallest
	 */
	private void writeToOutput(int smallest) {
		out.println(smallest);
	}

	/**
	 * Initial method to setup constants and initialize values
	 * @param heapsize
	 *            Desired heapsize (length of array)
	 */
	private void init(int heapsize) {
		HEAP_SIZE = heapsize;
		heap = new int[HEAP_SIZE];
		random = new int[RANDOM_NUMBERS];
		// generate random numbers to fill array
		for (int i = 0; i < random.length; i++) {
			random[i] = (int) (Math.random() * RANDOMIZE_AROUND);
			// System.out.println(random[i]);
		}
		// Initialize in- and outputs
		try {
			in = new Scanner("inputfile.txt");
			out = new PrintWriter("outputfile.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to (re-)build the heap-array, according to the specification of
	 * binary heap.
	 * @param array
	 *            Array to rebuild in good heap format
	 */
	private void buildHeap(int[] array) {

	}

	/**
	 * Method to get the smallest value off of the heap
	 * @return smallest value in array
	 */
	private int getSmallest() {
		int smallest = 0;
		for (int i = 0; i < heap.length; i++) {
			if (heap[i] <= smallest) {
				smallest = heap[i];
			}
		}
		return smallest;
	}
}
