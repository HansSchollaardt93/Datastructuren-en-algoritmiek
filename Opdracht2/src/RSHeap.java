import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * 
 * @author Hans Schollaardt
 */
public class RSHeap {
	private int HEAP_SIZE;
	private int DEADSPACE_SIZE;
	private final int RANDOM_NUMBERS;
	private static final int RANDOMIZE_AROUND = 5000;
	private int indexRandomArray = 0, output;
	private int writtenToOutput = 0;
	private int[] random, heap;
	private PrintWriter out;

	public RSHeap(int heapsize, int elements) {
		RANDOM_NUMBERS = elements;
		init();
		DEADSPACE_SIZE = 0;
		heap = new int[heapsize];
		HEAP_SIZE = heapsize - 1;
		fillHeap();
		buildHeap();
		// System.err.println(toDotString());
		startSorting();
	}

	private void startSorting() {
		// while array still holds more values, continue
		while (indexRandomArray < random.length) {
			removeItem();
			insert(random[indexRandomArray]);
			indexRandomArray++;
		}
		// write remainder of heap to OUT
		out.println("<---- REST OF THE HEAP!! --->");
		// System.err.println("<---- REST OF THE HEAP!! --->");
		writeHeap();
		buildHeap();
		out.println("<---- THE REMAINDER!! --->");
		// System.out.println(toDotString());
		// write remainder to OUT
		// System.err.println("<---- THE REMAINDER!! --->");
		writeHeap();
		out.close();
	}

	/**
	 * 
	 * @return
	 */
	private void removeItem() {
		// System.out.println(heap[0] + "  Item number  " + (indexRandomArray) +
		// " HEAPSIZE lenght =" + HEAP_SIZE);
		printToOutput(heap[0] + "  Item number " + (writtenToOutput)
				+ " HEAPSIZE lenght =" + HEAP_SIZE);
		writtenToOutput++;
		output = heap[0];
		heap[0] = heap[HEAP_SIZE];
		HEAP_SIZE--;
		percolateDown(0);
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
				// System.err.println("<---- NEXT RUN!! ---->");
				// HEAPSIZE == 0, dus geen items meer in heap -> maak van
				// deadspace de nieuwe heap; buildHeap
				buildHeap();

				printToOutput("<---- NEXT RUN!! ---->");
			}
		} else {
			HEAP_SIZE++;
			heap[HEAP_SIZE] = toInsert;
			percolateUp(HEAP_SIZE);
		}
		// printCurrentHeap();
	}

	/**
	 * 
	 * @param index
	 */
	private void percolateUp(int index) {
		int parentIndex = (index - 1) / 2;
		assert (parentIndex >= 0);
		if (index > 0) {
			if (heap[parentIndex] > heap[index]) {
				// swap parents value with childs value
				swap(parentIndex, index);
				percolateUp(parentIndex);
			}
		}
	}

	/**
	 * 
	 * @param currentIndex
	 */
	private void percolateDown(int currentIndex) {
		assert (currentIndex >= 0);
		int current = heap[currentIndex];

		int left = currentIndex * 2 + 1;
		int right = currentIndex * 2 + 2;

		/*
		 * check if children are smaller then your own value AND check if child
		 * is located within the heap.
		 */
		// both right and left children exist
		if (right <= HEAP_SIZE) {
			// check if either sides is bigger than yourself; if so, swap with
			// the bigger;
			if (current > heap[left] || current > heap[right]) {
				if (heap[left] < heap[right]) {
					// swap left with parent
					swap(left, currentIndex);
					percolateDown(left);
					// right is bigger than left
				} else {
					// swap right with parent
					swap(right, currentIndex);
					percolateDown(right);
				}
				// check if parent has left child
			}
		} else if (left <= HEAP_SIZE) {
			// only has a left child
			if (current > heap[left]) {
				// swap left with parent
				swap(left, currentIndex);
			}
		}

	}

	private void swap(int current, int toSwapWith) {
		int tempvalue = heap[current];
		heap[current] = heap[toSwapWith];
		heap[toSwapWith] = tempvalue;
	}

	/**
	 * Method to print a line to the output file
	 * 
	 * @param string
	 *            the line to print to file
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
	private void init() {
		random = new int[RANDOM_NUMBERS];
		// generate random numbers to fill array
		for (int i = 0; i < random.length; i++) {
			random[i] = (int) (Math.random() * RANDOMIZE_AROUND);
		}
		// Initialize output
		try {
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
		// reset the heap values
		DEADSPACE_SIZE = 0;
		HEAP_SIZE = heap.length - 1;

		for (int i = ((heap.length - 1) / 2); i >= 0; i--) {
			percolateDown(i);
		}
	}

	private void writeHeap() {
		for (int i = 0; i < HEAP_SIZE; i++) {
			removeItem();
		}
	}

	private void fillHeap() {
		printToOutput("<----   FILLING THINGS UP!   ----->");
		// System.err.println("<----   FILLING THINGS UP!   ----->\n");
		for (int i = 0; i < heap.length; i++) {
			heap[i] = random[i];
			indexRandomArray++;
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

	/**
	 * intended for debugging only!
	 */
	private void printCurrentHeap() {
		int[] tempHeap = new int[HEAP_SIZE];
		for (int i = 0; i < HEAP_SIZE; i++) {
			tempHeap[i] = heap[i];
		}
		int[] tempDS = new int[DEADSPACE_SIZE];
		for (int i = HEAP_SIZE; i < DEADSPACE_SIZE; i++) {
			tempDS[i] = heap[i];
		}
		System.err.println("HEAP: " + Arrays.toString(tempHeap) + "\nDS:"
				+ Arrays.toString(tempDS));
	}
}
