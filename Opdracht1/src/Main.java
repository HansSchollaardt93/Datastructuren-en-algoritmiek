import java.util.Random;

public class Main {
	static long before, after;
	static Random rand = new Random();
	static int[] list;
	static boolean[] usedList;

	public static void main(String[] args) {
		System.out.println("<----     ALGORITME 1     ---->");
		/* ALGORITME 1 */
		System.out.println("Calculation N=5000");
		for (int i = 0; i < 10; i++) {
			executeOpdracht1(5000);
		}
		System.out.println("Calculation N=10000");
		for (int i = 0; i < 10; i++) {
			executeOpdracht1(10000);
		}
		System.out.println("Calculation N=20000");
		for (int i = 0; i < 10; i++) {
			executeOpdracht1(20000);
		}
		System.out.println("Calculation N=50000");
		for (int i = 0; i < 10; i++) {
			executeOpdracht1(50000);
		}
		System.out.println("Calculation N=100000");
		for (int i = 0; i < 10; i++) {
			executeOpdracht1(100000);
		}
		System.out.println("<----     ALGORITME 2     ---->");
		/* ALGORITME 2 */
		System.out.println("Calculation N=100000");
		for (int i = 0; i < 10; i++) {
			executeOpdracht2(100000);
		}
		System.out.println("Calculation N=500000");
		for (int i = 0; i < 10; i++) {
			executeOpdracht2(500000);
		}
		System.out.println("Calculation N=1000000");
		for (int i = 0; i < 10; i++) {
			executeOpdracht2(1000000);
		}
		System.out.println("Calculation N=5000000");
		for (int i = 0; i < 10; i++) {
			executeOpdracht2(5000000);
		}
		System.out.println("Calculation N=10000000");
		for (int i = 0; i < 10; i++) {
			executeOpdracht2(10000000);
		}

		/* ALGORITME 3 */
		System.out.println("<----     ALGORITME 3     ---->");
		System.out.println("Calculation N=5000000");
		for (int i = 0; i < 10; i++) {
			executeOpdracht3(5000000);
		}
		System.out.println("Calculation N=10000000");
		for (int i = 0; i < 10; i++) {
			executeOpdracht3(10000000);
		}
		System.out.println("Calculation N=20000000");
		for (int i = 0; i < 10; i++) {
			executeOpdracht3(20000000);
		}
		System.out.println("Calculation N=40000000");
		for (int i = 0; i < 10; i++) {
			executeOpdracht3(40000000);
		}
		System.out.println("Calculation N=80000000");
		for (int i = 0; i < 10; i++) {
			executeOpdracht3(80000000);
		}

	}

	public static void executeOpdracht1(int elementcount) {
		before = System.currentTimeMillis();
		list = new int[elementcount];

		// Voeg N aantal elementen toe aan de array
		for (int i = 0; i < elementcount; i++) {
			int random;
			do {
				random = getRandInt(elementcount);
				// kijk of element niet al in de array bestaat
			} while (existstInArray(list, random));
			list[i] = random;
			// bestaat al; probeer opnieuw voor deze index
		}

		after = System.currentTimeMillis();

		System.out.println("Total calculationtime: "
				+ calculateTime(before, after));
	}

	public static void executeOpdracht2(int elementcount) {
		before = System.currentTimeMillis();
		list = new int[elementcount];
		usedList = new boolean[elementcount];
		for (int i = 0; i < elementcount; i++) {
			int random;
			do {
				random = getRandInt(elementcount - 1);
			} while (usedList[random] == true);
			usedList[random] = true;
			list[i] = random;
		}

		after = System.currentTimeMillis();

		System.out.println("Total calculationtime: "
				+ calculateTime(before, after));
	}

	public static void executeOpdracht3(int elementcount) {
		before = System.currentTimeMillis();
		int[] list = new int[elementcount];

		for (int i = 1; i < elementcount; i++) {
			list[i] = i;
			int tempPos = new Random().nextInt(i);
			int inhoud = list[tempPos];
			list[tempPos] = i;
			list[i] = inhoud;
		}
		after = System.currentTimeMillis();

		System.out.println("Total calculationtime: "
				+ calculateTime(before, after));
	}

	public static String calculateTime(long before, long after) {
		return after - before + " ms";
	}

	static int getRandInt(int rightbound) {
		int random = rand.nextInt(rightbound + 1);

		return random;
	}

	public static boolean existstInArray(int[] array, int toCheck) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == toCheck) {
				return true;
			}
		}
		return false;
	}
}
