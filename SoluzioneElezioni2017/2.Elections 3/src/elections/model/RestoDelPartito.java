package elections.model;

class RestoDelPartito implements Comparable<RestoDelPartito> {
	
	private Partito partito;
	private double resto;	
	
	public RestoDelPartito(Partito partito, double resto) {
		this.partito = partito;
		this.resto = resto;
	}

	public Partito getPartito() {
		return partito;
	}


	public double getResto() {
		return resto;
	}

	@Override
	public int compareTo(RestoDelPartito other) {
		return Double.compare(other.getResto(), this.getResto());
	}

}
