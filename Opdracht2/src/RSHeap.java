import java.util.Scanner;


public class RSHeap {
	private static final int HEAP_SIZE;
	private static final int RANDOM_NUMBERS = 2000;
	private static final int RANDOMIZE_AROUND = 5000;
	private int[] random, heap;
	private boolean done;
	private Scanner in;

	public RSHeap(int heapsize) {
		init(heapsize);
		done = false;
		//while file still holds more values, continue
		while(in.hasNext()){
			buildHeap();
			while();
			
			done = true;
			
			//write out to file
		}
		
	}

	private void init(int heapsize) {
		HEAP_SIZE = heapsize;
		in = new Scanner("inputfile.txt");
		heap = new int[HEAP_SIZE];
		random = new int[RANDOM_NUMBERS];
		// generate random numbers to fill array
		for (int i = 0; i < random.length; i++) {
			random[i] = (int) (Math.random() * RANDOMIZE_AROUND);
			// System.out.println(random[i]);
		}

	}
	
	private void buildHeap(){
		
	}

}
