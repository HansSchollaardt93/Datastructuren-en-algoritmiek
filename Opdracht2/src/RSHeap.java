import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * 
 * @author Hans Schollaardt
 */
public class RSHeap {
	private static int HEAP_SIZE;
	private static final int RANDOM_NUMBERS = 137;
	private static final int RANDOMIZE_AROUND = 5000;
	private int indexRandomArray, output;
	private int[] random, heap;
	private PrintWriter out;
	private int DEADSPACE_SIZE;

	public RSHeap(int heapsize) {
		init(heapsize);
		// read HEAP_SIZE-elements into heap-array
		fillHeap();
		// Build the heap for the first time
		buildHeap();
		// while array still holds more values, continue
		while (indexRandomArray < random.length) {

			removeItem();
			insert(random[indexRandomArray]);
			indexRandomArray++;
		}
//		// write remainder of heap to OUT
//		System.err.println("<---- REST OF THE HEAP!! --->");
//		printToOutput(0, HEAP_SIZE);
//		buildHeap();
//		// write remainder to OUT
//		System.err.println("<---- THE REMAINDER!! --->");
//		printToOutput(0, HEAP_SIZE);
out.close();
		System.out.println(toDotString());
	}

	/**
	 * 
	 * @return
	 */
	private int removeItem() {
		System.out.println(heap[0] + "  Item number  "
				+ (indexRandomArray+1));
		printToOutput(heap[0] + "\t  Item number  "
				+ (indexRandomArray+1));
		output = heap[0];
		heap[0] = heap[HEAP_SIZE];
		HEAP_SIZE--;
		perculateDown(0);
		return output;
	}

	/**
	 * 
	 * @param toInsert
	 */
	private void insert(int toInsert) {
		if (toInsert < output) {
			heap[HEAP_SIZE + 1] = toInsert;
			DEADSPACE_SIZE++;
			if (heap.length - DEADSPACE_SIZE == 0) {
				System.err.println("<---- NEXT RUN!! ---->");
				out.println("<---- NEXT RUN!! ---->");
				buildHeap();
			}
		} else {
			HEAP_SIZE++;
			heap[HEAP_SIZE] = toInsert;
			perculateUp(HEAP_SIZE);
		}
	}

	/**
	 * 
	 * @param index
	 */
	private void perculateUp(int index) {
		int parentIndex = (index - 1) / 2;
		if (index >= 0) {
			if (heap[parentIndex] > heap[index]) {
				// swap parents value with childs value
				swap(parentIndex, index);
				perculateUp(parentIndex);
			}
		}
	}

	/**
	 * 
	 * @param currentIndex
	 */
	private void perculateDown(int currentIndex) {
		int current = heap[currentIndex];
		
		int left = (currentIndex * 2) + 1;
		int right = (currentIndex * 2) + 2;

		/*
		 * check if children are smaller then your own value AND check if child
		 * is located within the heap.
		 */
		if (right <= HEAP_SIZE) {
			// both right and left children exist
			if (current > heap[left] || current > heap[right]) {
				if (heap[left] < heap[right]) {
					// swap left with parent
					swap(left, currentIndex);
					perculateDown(left);
					// right is bigger than left
				} else {
					// swap right with parent
					swap(right, currentIndex);
					perculateDown(right);
				}
			//only has a left child
			} else {
				if (heap[left] < current) {
					// swap left with parent
					swap(left, currentIndex);
				}
			}
		}
	}

	private void swap(int current, int toSwapWith) {
		int temp = heap[current];
		heap[current] = heap[toSwapWith];
		heap[toSwapWith] = temp;
	}

	/**
	 * Method to print the remainder of arrays to output file
	 * 
	 * @param array
	 *            array to write to file
	 */
	private void printToOutput(int startindex, int endindex) {
		for (int i = startindex; i < endindex; i++) {
			out.println(heap[i]);
		}
	}

	/**
	 * Method to print single integer to output file
	 * 
	 * @param smallest
	 */
	private void printToOutput(String string) {
		out.println(string);
	}

	/**
	 * Initial method to setup constants and initialize values
	 * 
	 * @param heapsize
	 *            Desired heapsize (length of array)
	 */
	private void init(int heapsize) {
		HEAP_SIZE = heapsize;
		DEADSPACE_SIZE = 0;
		heap = new int[HEAP_SIZE];
		random = new int[RANDOM_NUMBERS];
		// generate random numbers to fill array
		for (int i = 0; i < random.length; i++) {
			random[i] = (int) (Math.random() * RANDOMIZE_AROUND);
			// System.out.println(random[i]);
		}
		// Initialize in- and outputs
		try {
			// in = new Scanner("inputfile.txt");
			out = new PrintWriter("outputfile.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to (re-)build the heap-array, according to the specification of
	 * binary heap.
	 * 
	 * @param array
	 *            Array to rebuild in good heap format
	 */
	private void buildHeap() {
		// perculate down
		DEADSPACE_SIZE = 0;
		HEAP_SIZE = heap.length - 1;
		for (int i = ((heap.length - 1) / 2); i >= 0; i--) {
			perculateDown(i);
		}
	}

	private void fillHeap() {
		System.err.println("<----   FILLING THINGS UP!   ----->\n");
		for (int i = 0; i < heap.length; i++) {
			System.out.println("Filling index " + i);
			heap[i] = random[i];
		}
	}

	/**
	 * Created a DotString of the heap
	 * 
	 * @return The DotString of the heap
	 */
	private String toDotString() {
		String res = "digraph heap {\n";
		for (int i = 0; i < heap.length; i++) {
			res += "n" + i + "[label=\"" + heap[i] + "\"]\n";
		}
		for (int i = 0; i < heap.length / 2; i++) {
			res += "n" + i + "-> n" + (i * 2 + 1) + ";\n";
			res += "n" + i + "-> n" + (i * 2 + 2) + ";\n";
		}
		res += "}";
		return res;

	}
}
