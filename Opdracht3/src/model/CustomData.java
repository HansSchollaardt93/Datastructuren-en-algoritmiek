package model;

public class CustomData extends TrieData {
	private int customInt;

	public CustomData(String word, int position) {
		super(word, position);
	}

	public int getCustomInt() {
		return customInt;
	}

	public void setCustomInt(int customInt) {
		this.customInt = customInt;
	}

}
