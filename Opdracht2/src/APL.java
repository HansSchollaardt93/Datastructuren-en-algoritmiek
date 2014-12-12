import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class APL {

	public static void main(String[] args) throws IOException {

		executeMeting(10, 10);
	}

	/**
	 * Method to perform a measurement of the RSHeap.
	 * 
	 * @param times
	 *            the amount of times it has to run.
	 * @param heapsize
	 *            the size of the heap
	 * @throws IOException
	 *             thrown if the file does not exist or is not accessible.
	 */
	private static void executeMeting(int times, int heapsize)
			throws IOException {
		File file = new File("inputfile.txt");
		int elements = getAmountOfLinesInFile(file);

		long[] measurements = new long[times];

		for (int i = 0; i < times; i++) {
			System.out.println();
			System.out.println("Start measurement: " + i + " with size: "
					+ elements);
			long start = System.currentTimeMillis();
			new RSHeap(heapsize, file);
			measurements[i] = (System.currentTimeMillis() - start);
			System.out.println("Measurement took: " + measurements[i] + " ms.");
		}

		System.out
				.println("Average of " + times + " times in MS: "
						+ getAverageTime(measurements) + " and N Elements: "
						+ elements);
	}

	/**
	 * Method to get amount of lines in file.
	 * 
	 * @param inputFile
	 *            the input file which to read the lines from
	 * @return amount of lines of the inputFile
	 * @throws IOException
	 */
	private static int getAmountOfLinesInFile(File inputFile)
			throws IOException {
		LineNumberReader lnr = new LineNumberReader(new FileReader(inputFile));
		lnr.skip(Long.MAX_VALUE);
		lnr.close();
		return lnr.getLineNumber() + 1;
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
