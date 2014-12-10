import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 * @author Hans Schollaardt
 */
public class RSHeap {
	private static int HEAP_SIZE;
	private static int DEADSPACE_SIZE;
	private static final int RANDOM_NUMBERS = 200;
	private static final int RANDOMIZE_AROUND = 500;
	private int indexRandomArray;
	private int[] random, heap;

	private Scanner in;
	private PrintWriter out;

	public RSHeap(int heapsize) {
		init(heapsize);


		int next;
		// read HEAP_SIZE-elements into heap-array
		
		buildHeap();
		// while array still holds more values, continue
		while (hasNext()) {
			// remove smallest element from heap and write this to OUT
			int smallest = findMin();
			
			next = Integer.parseInt(in.nextLine());
			printToOutput(smallest); 
			
			heap[0] = heap[HEAP_SIZE];
			HEAP_SIZE--;
			perculateDown(0, HEAP_SIZE);
			if (next >= smallest) { // next fits into current run
			// insert next into heap
				HEAP_SIZE++;
				heap[HEAP_SIZE] = next;
				perculateUp(0, HEAP_SIZE);	//re-order the heap
			} else {
				// Heapsize H--, Deadspace++
				perculateDown(0, HEAP_SIZE);
				HEAP_SIZE--;
				DEADSPACE_SIZE++;
				// insert next into deadspace
			}
			if (heap.length == 0){
				buildHeap();
			}

			// write single value out to file
		}
		//write remainder of heap to OUT
		printToOutput(heap);
		buildHeap();
//		write remainder to OUT
		printToOutput(rest);
		in.close();
		out.close();

	}

	private boolean hasNext() {
		return (indexRandomArray >= 0);
	}

	/**
	 * Method to print the remainder of arrays to output file
	 * 
	 * @param array
	 *            array to write to file
	 */
	private void printToOutput(int[] array) {

	}

	/**
	 * Method to print single integer to output file
	 * 
	 * @param smallest
	 */
	private void printToOutput(int smallest) {
		out.println(smallest);
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
		indexRandomArray = random.length - 1;
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
	 * Method to get the smallest value off of the heap
	 * 
	 * @return smallest value in array
	 */
	private int findMin() {
		return heap[0];
	}

/**
 * 
 * @param startIndex
 */
	private void perculateDown(int startIndex, int lastIndex) {
		int left = 0, right = 0, parent = 0;
		parent = heap[startIndex];

		// check if children are smaller then your own value
		if (hasLeftChild(startIndex)) {
			/* PSEUDOCODE
			 * if has right
			 *  compare left and right
			 *  if (left < right) 
			 *  	swap left with parent
			 *  	perculateDown(index left)
			 *  else 
			 *  	swap right with parent
			 *  	perculateDown(index right)
			 * else if left < parent
			 *   swap with parent
			 *   perculateDown(index left)
			 */
			int tempvalue;
			left = getLeftChild(startIndex);
			if(hasRightChild(startIndex)){
				right = getRightChild(startIndex);
				if(left<right){
					//swap left with parent
					tempvalue = left;
					parent = left;
					parent = tempvalue;
					//continue perculate at left childs position
					perculateDown(getLeftChild(startIndex));
					//right is bigger than left
				} else {
					//swap right with parent
					tempvalue = right;
					parent = right;
					parent = tempvalue;
					//continue perculate at right childs position
					perculateDown(getRightChild(startIndex));
				}
			//Only has left child
			} else {
				if(left < parent){
					//swap left with parent
					tempvalue = left;
					parent = left;
					parent = tempvalue;
					//continue perculate at left childs position
					perculateDown(getLeftChild(startIndex));
				}
				//do nothing, numbers are in right order
			}
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
		for (int i = ((heap.length / 2) - 1); i >= 0; i--) {
			perculateDown(i);
		}
	}

	private void deleteMin() {
		// delete index 0
		heap[0] = heap[HEAP_SIZE];
		// perculate down
		perculateDown(0);
	}

	private void insert(int toInsert) {

	}

	private boolean isEmpty() {
		return HEAP_SIZE > 0;
	}

	private int getLeftChild(int index) {
		if (!isLeaf(index)) {
			return ((2 * index));
		}
		return -1;
	}

	private int getRightChild(int index) {
		if (!isLeaf(index)) {
			return ((2 * index) + 1);
		}
		return -1;
	}

	private int getParent(int index) {
		return (index / 2);
	}

	private boolean isLeaf(int index) {
		return ((index >= heap.length / 2) && (index < heap.length));
	}

	private boolean hasLeftChild(int index) {
		return (getLeftChild(index) != -1);
	}

	private boolean hasRightChild(int index) {
		return (getRightChild(index) != -1);
	}
}
