public class APL {

	public static void main(String[] args) {

		executeMeting(10, 5000);
	}

	/**
	 * Method to perform a measurement of the RSHeap.
	 * 
	 * @param count
	 *            the amount of times it has to run.
	 * @param elements
	 *            the size of the amount of elements
	 */
	private static void executeMeting(int times, int elements) {
		long[] measurements = new long[times];

		for (int i = 0; i < times; i++) {
			System.out.println();
			System.out.println("Start measurement: " + i + " with size: "
					+ elements);
			long start = System.currentTimeMillis();
			new RSHeap(elements, elements);
			measurements[i] = (System.currentTimeMillis() - start);
			System.out.println("Measurement took: " + measurements[i] + " ms.");
		}

		System.out
				.println("Average of " + times + " times in MS: "
						+ getAverageTime(measurements) + " and N Elements: "
						+ elements);
	}

	private static long getAverageTime(long[] times) {
		long average = 0;
		for (int i = 0; i < times.length; i++) {
			average += times[i];
		}
		average = (average / times.length);
		return average;
	}
}
