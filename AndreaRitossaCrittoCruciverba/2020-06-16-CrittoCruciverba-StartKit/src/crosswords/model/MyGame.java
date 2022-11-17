package crosswords.model;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class MyGame extends Scheme implements Game {
	private Map<Integer, Optional<String>> charMap;
	
	public MyGame(int size) {
		super(size);
		charMap = new TreeMap<>();
	}

	@Override
	public void setMapping(int num, String value) {
		Optional<String> STR = Optional.of(value);
		charMap.put(num, STR);
	}

	@Override
	public Optional<String> getMapping(int num) {
		Optional<String> str = charMap.get(num);
		return (str == null || str.isEmpty()) ? Optional.empty() : str;
	}

	@Override
	public boolean isFull() {
		int size = super.getSize();
		for (int i = 0; i<size; i++) {
			for (int j = 0; j<size; j++) {
				int cellval = getCell(i, j);
				if (cellval != 0 && charMap.get(cellval)==null) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void setCellRow(int row, int [] values) {
		super.setCellRow(row, values);
	}
	
	public String toString() {
		int size = super.getSize();
		String ret = "";
		for (int i = 0; i<size; i++) {
			for (int j = 0; j<size; j++) {
				Optional<String> oS = getMapping(getCell(i, j));
				if (oS.isEmpty()) {
					ret+= "X";
				} else {
					ret+= getMapping(getCell(i, j)).get();
				}
				ret+= "\t";
			}
			ret+="\n";
		}
		ret+="\n";
		return ret;
	}
	
}
