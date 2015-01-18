import java.util.ArrayList;

/**
 * Custom Data class which will be used as 'data' in the Trie.
 * 
 * @author Hans & Benjamin
 * 
 */
public class Data {

	private ArrayList<Integer> positions;

	public Data(int pos) {
		this.positions = new ArrayList<Integer>();
		this.positions.add(pos);
	}

	public ArrayList<Integer> getPosition() {
		return positions;
	}

	public void addPosition(int pos) {
		positions.add(pos);
	}

	@Override
	public String toString() {
		return "positions=" + positions;
	}

}
