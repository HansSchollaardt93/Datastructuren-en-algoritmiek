import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Reduced Trie
 * 
 * @author Hans & Benjamin
 */
public class APL {

	public static void main(String[] args) throws IOException {
		Trie<Data> trie = new Trie<Data>();
		Scanner scanner = new Scanner(new File("document.txt"));
		int position = 0;
		while (scanner.hasNext()) {
			String word = scanner.next();
			Data data = new Data(++position);
			trie.insert(word, data);
		}
		scanner.close();
		System.out.println(trie);
	}
}