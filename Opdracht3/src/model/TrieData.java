package model;

import java.util.ArrayList;

public class TrieData {
	private String word;
	private ArrayList<Integer> positions;

	public TrieData(String word) {
		this.word = word;
		positions = new ArrayList<Integer>();
	}

	public TrieData(String word, int position) {
		this(word);
		positions.add(position);
	}

	public void removePosition() {

	}

	public void addPosition(int position) {
		positions.add(position);
	}

	public String getWord() {
		return word;
	}

	public ArrayList<Integer> getPositionList() {
		return new ArrayList<Integer>(positions);
	}

	public void setWord(String word) {
		this.word = word;
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
