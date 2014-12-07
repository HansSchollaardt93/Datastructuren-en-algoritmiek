


public class RSHeap {
private static final int INITIAL_HEAP_SIZE = 5;
private static final int RANDOM_NUMBERS = 20000;
private static final int RANDOMIZE_AROUND = 50000;
private int[] random;

public RSHeap() {
	init();
	//generate random numbers
	
	//feed numbers to IN
//		while(in.hasNext()){
//			
//		}
	
}

private void init() {
	random = new int[RANDOM_NUMBERS];
	for (int i = 0; i < random.length; i++) {
		random[i] = (int) (Math.random() * RANDOMIZE_AROUND);
		System.out.println(random[i]);
	}
	
}

}
