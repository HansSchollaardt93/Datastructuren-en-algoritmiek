import java.io.File;

public class Randgevallen {
	public static void main(String[] args) {
		int MILLION = 1000000;
		long start, end = 0;
		/**
		 * The default file which contains 1.000.000 elements.
		 */
		File inputFile = new File("inputfile.txt");
		/**
		 * Randgeval 1: aantal elementen (N) < Heapgrootte (H)
		 */
		System.out.println("Randgeval: 1 START!");
		start = System.currentTimeMillis();
		new RSHeap(MILLION * 2, inputFile);
		end = System.currentTimeMillis();
		System.out.println("Randgeval: 1 END! TOOK: " + (end - start) + " MS."
				+ System.lineSeparator());

		/**
		 * Randgeval 2: aantal elementen (N) = Heapgrootte (H)
		 */
		System.out.println("Randgeval: 2 START!");
		start = System.currentTimeMillis();
		new RSHeap(MILLION, inputFile);
		end = System.currentTimeMillis();
		System.out.println("Randgeval: 2 END! TOOK: " + (end - start) + " MS."
				+ System.lineSeparator());

		/**
		 * Randgeval 3: Een enkel element inlezen in de heap
		 */
		System.out.println("Randgeval: 3 START!");
		start = System.currentTimeMillis();
		new RSHeap(10, new File("moreheapsize.txt"));
		end = System.currentTimeMillis();
		System.out.println("Randgeval: 3 END! TOOK: " + (end - start) + " MS."
				+ System.lineSeparator());

		/**
		 * Randgeval 4: Eén element meer inlezen in de heap dan de Heapgrote (H)
		 */
		System.out.println("Randgeval: 4 START!");
		start = System.currentTimeMillis();
		new RSHeap(11, new File("bestcase.txt"));
		end = System.currentTimeMillis();
		System.out.println("Randgeval: 4 END! TOOK: " + (end - start) + " MS."
				+ System.lineSeparator());

		/**
		 * Randgevak 5: Elementen N halve heaplengte meer dan er in één Heap
		 * past (N = 1,5H)
		 */
		System.out.println("Randgeval: 5 START!");
		start = System.currentTimeMillis();
		new RSHeap(15, new File("good.txt"));
		end = System.currentTimeMillis();
		System.out.println("Randgeval: 5 END! TOOK: " + (end - start) + " MS."
				+ System.lineSeparator());

		/**
		 * Randgeval 6: Een groot aantal elementen (N), met een kleine Heapgrote
		 * (H)
		 */
		System.out.println("Randgeval: 6 START!");
		start = System.currentTimeMillis();
		new RSHeap(1, inputFile);
		end = System.currentTimeMillis();
		System.out.println("Randgeval: 6 END! TOOK: " + (end - start) + " MS."
				+ System.lineSeparator());

		/**
		 * Randgeval 7: Een klein aantal elementen (N), met een grote Heapgrote
		 * (H)
		 */
		System.out.println("Randgeval: 7 START!");
		start = System.currentTimeMillis();
		new RSHeap(MILLION, new File("moreheapsize.txt"));
		end = System.currentTimeMillis();
		System.out.println("Randgeval: 7 END! TOOK: " + (end - start) + " MS."
				+ System.lineSeparator());

		/**
		 * Randgeval 8: Een groot aantal elementen (N), met een grote Heapgrote
		 * (H)
		 */
		System.out.println("Randgeval: 8 START!");
		start = System.currentTimeMillis();
		new RSHeap(MILLION * 10, inputFile);
		end = System.currentTimeMillis();
		System.out.println("Randgeval: 8 END! TOOK: " + (end - start) + " MS."
				+ System.lineSeparator());

		/**
		 * Randgeval 9: Een groot aantal elementen (N), met een kleine Heapgrote
		 * (H)
		 */
		System.out.println("Randgeval: 9 START!");
		start = System.currentTimeMillis();
		new RSHeap(1, inputFile);
		end = System.currentTimeMillis();
		System.out.println("Randgeval: 9 END! TOOK: " + (end - start) + " MS."
				+ System.lineSeparator());
	}
}
