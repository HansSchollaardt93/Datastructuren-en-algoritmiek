package model;

import java.util.ArrayList;

public class TrieData {
	private String word;
	private ArrayList<Integer> positions;

	public TrieData(String word, int position) {
		this.word = word;
		positions = new ArrayList<Integer>();
		positions.add(position);
	}

	public void addPosition(int position) {
		positions.add(position);
	}

	public String getWord() {
		return word;
	}

	@Override
	public String toString() {
		String res = word;
		for (Integer in : positions) {
			res += " " + in;
		}
		return res;
	}

}