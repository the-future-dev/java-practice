package pianodistudi.model;

public class CfuRange {
	private int min, max;
	
	public CfuRange(int min, int max) {
		if (min<0 || max<0) throw new IllegalArgumentException("Argomenti negativi");
		if (min>max) throw new IllegalArgumentException("Min > max");
		this.min=min; this.max=max;
	}
	
	public CfuRange(String range) {
		if (range==null || range.equals("")) throw new IllegalArgumentException("Stringa range mancante");
		String[] items = range.split("-");
		if (items.length!=2) throw new IllegalArgumentException("Stringa range mal formattata");
		int min, max;
		try {
			min = Integer.parseInt(items[0].trim());
			max = Integer.parseInt(items[1].trim());
		}
		catch(NumberFormatException e) {
			throw new IllegalArgumentException("Stringa range mal formattata, valori numerici illegali");
		}
		if (min<0 || max<0) throw new IllegalArgumentException("Argomenti negativi");
		if (min>max) throw new IllegalArgumentException("Min > max");
		this.min=min; this.max=max;
	}

	@Override
	public String toString() {
		return "Range [min=" + min + ", max=" + max + "]";
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}
	
	public boolean contains(int value) {
		return this.getMin()<= value && value <= this.getMax();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + max;
		result = prime * result + min;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		CfuRange other = (CfuRange) obj;
		if (max != other.max) return false;
		if (min != other.min) return false;
		return true;
	}
	
}
