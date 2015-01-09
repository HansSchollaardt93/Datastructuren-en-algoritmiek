import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Reduced Trie
 * 
 * @author Hans & Benjamin
 */
public class APL {

	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();

		Trie<Data> trie = new Trie<Data>();

		Scanner scanner = new Scanner(new File("wordlist.txt"));
		int position = 0;
		while (scanner.hasNext()) {
			String word = scanner.next();
			Data data = new Data(++position);
			trie.insert(word, data);
		}

		long time = System.currentTimeMillis() - start;
		System.out.println("ms: " + time);

		scanner.close();

		// System.out.println(trie);

		new FileWriter(new File("output.txt")).write(trie.toString());
	}
}