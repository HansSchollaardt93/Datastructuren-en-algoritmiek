import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author Hans Schollaardt
 */
public class RSHeap {
	private int heapSize, deadSpaceSize, writtenToOutput, output, runs;
	private int[] heap;
	private PrintWriter out;
	private Scanner scanner;
	private File inputFile;

	/**
	 * Constructor for a RSHeap with on the go generated numbers
	 * 
	 * @param heapsize
	 *            the size of the heap
	 * @param inputFile
	 *            the input file to read
	 * @throws IOException
	 *             thrown when file is not found or unreadable.
	 */
	public RSHeap(int heapsize, File inputFile) {
		this.inputFile = inputFile;
		init();
		deadSpaceSize = 0;
		heap = new int[heapsize];
		heapSize = heapsize - 1;

		// 1e heapSize (N) inlezen
		for (int i = 0; i < heapsize; i++) {
			if (scanner.hasNextInt()) {
				int next = scanner.nextInt();
				heap[i] = next;
			}
		}
		runs = 1;
		buildHeap();
		startSorting();
	}

	/**
	 * Initial method to setup constants and initialize values
	 */
	private void init() {
		try {
			scanner = new Scanner(inputFile);
			out = new PrintWriter("outputfile.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Helper methods required for testing
	 */

	/**
	 * 
	 * @return the heap int array
	 */
	public int[] getHeap() {
		return heap;
	}

	/**
	 * End of helper methods for testing
	 */

	private void startSorting() {
		// while array still holds more values, continue
		while (scanner.hasNextInt()) {
			removeItem();
			insert(scanner.nextInt());
		}
		// write remainder of heap to OUT
		out.println("<---- REST OF THE HEAP!! --->");
		writeHeap();
		buildHeap();
		out.println("<---- THE REMAINDER!! --->");
		// write remainder to OUT
		writeHeap();
		out.close();
	}

	/**
	 * Writes the next int to the file and shifts the deadspace and heapsize.
	 */
	private void removeItem() {
		printToOutput(heap[0] + "  Item number " + (writtenToOutput)
				+ " HEAPSIZE lenght =" + heapSize);
		output = heap[0];
		heap[0] = heap[heapSize];
		assert (heap[0] >= output) : "Heap[0] smaller than last value!";
		heapSize--;
		percolateDown(0);
		writtenToOutput++;
	}

	/**
	 * 
	 */
	private void insert(int toInsert) {
		if (toInsert < output) {
			heap[heapSize + 1] = toInsert;
			deadSpaceSize++;
			if (heap.length - deadSpaceSize == 0) {
				buildHeap();
				printToOutput("<---- NEXT RUN!! ---->");
				runs++;
			}
		} else {
			heapSize++;
			heap[heapSize] = toInsert;
			percolateUp(heapSize);
		}
	}

	/**
	 * Method to get the amount of runs.
	 * 
	 * @return the amount of runs that was needed to complete the algorithm.
	 */
	public int getRuns() {
		return runs;
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
		if (right <= heapSize) {
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
		} else if (left <= heapSize) {
			// only has a left child
			if (current > heap[left]) {
				// swap left with parent
				swap(left, currentIndex);
			}
		}

	}

	/**
	 * Swaps two elements in the heap
	 * 
	 * @param current
	 * @param toSwapWith
	 */
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
	 * Method to (re-)build the heap-array, according to the specification of
	 * binary heap.
	 * 
	 * @param array
	 *            Array to rebuild in good heap format
	 */
	private void buildHeap() {
		// perculate down
		// reset the heap values
		deadSpaceSize = 0;
		heapSize = heap.length - 1;

		for (int i = ((heap.length - 1) / 2); i >= 0; i--) {
			percolateDown(i);
		}
	}

	private void writeHeap() {
		for (int i = 0; i < heapSize; i++) {
			removeItem();
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
		int[] tempHeap = new int[heapSize];
		for (int i = 0; i < heapSize; i++) {
			tempHeap[i] = heap[i];
		}
		int[] tempDS = new int[deadSpaceSize];
		for (int i = heapSize; i < deadSpaceSize; i++) {
			tempDS[i] = heap[i];
		}
		System.err.println("HEAP: " + Arrays.toString(tempHeap) + "\nDS:"
				+ Arrays.toString(tempDS));
	}

}
