import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Arrays;

public class APL {

	public static void main(String[] args) throws IOException {
		// executeMeasurement(10, 50, "inputfile.txt");

		// executeMeasurement(5, 100000, "10000000.txt");
		// executeMeasurement(5, 50000, "10000000.txt");
		// executeMeasurement(10, 100000, "inputfile.txt");
		// executeMeasurement(10, 500000, "inputfile.txt");
		// executeMeasurement(10, 25000, "100000.txt");
		// executeMeasurement(10, 2500, "100000.txt");
		// executeMeasurement(10, 2500, "50000.txt");
		executeMeasurement(10, 5000, "50000.txt");
	}

	/**
	 * Method to perform a measurement of the RSHeap.
	 * 
	 * @param times
	 *            the amount of times it has to run.
	 * @param heapsize
	 *            the size of the heap
	 * @param fileName
	 *            the name of the file to use for the measurement
	 * @throws IOException
	 *             thrown if the file does not exist or is not accessible.
	 */
	private static void executeMeasurement(int times, int heapsize,
			String fileName) throws IOException {
		File file = new File(fileName);
		int elements = getAmountOfLinesInFile(file);

		long[] measurements = new long[times];
		int[] runs = new int[times];
		System.out.println("HEAPSIZE: " + heapsize + "\nELEMENTS: " + elements);
		for (int i = 0; i < times; i++) {
			long start = System.currentTimeMillis();
			RSHeap heap = new RSHeap(heapsize, file);
			measurements[i] = (System.currentTimeMillis() - start);
			runs[i] = heap.getRuns();
		}

		System.out.println("Times: " + formatForExcel(measurements));
		System.out.println("Runs: " + formatForExcel(runs));
	}

	private static String formatForExcel(int[] objects) {
		return "\n"
				+ Arrays.toString(objects).replace(", ", "\n").replace("[", "")
						.replace("]", "");
	}

	private static String formatForExcel(long[] objects) {
		return "\n"
				+ Arrays.toString(objects).replace(", ", "\n").replace("[", "")
						.replace("]", "");
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
