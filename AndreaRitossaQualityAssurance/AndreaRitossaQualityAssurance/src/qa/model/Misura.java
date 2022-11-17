package qa.model;

public class Misura {

	private String nome;
	private double expected, real;
	
	public Misura(String name, double expected, double real) {
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("nome non valido");	
		this.nome = name;
		this.expected=expected;
		this.real=real;
	}

	public String getName() {
		return nome;
	}

	public double getExpected() {
		return expected;
	}
	
	public double getReal() {
		return real;
	}

	public String toString() {
		return nome + " (atteso: " + expected+ ", reale: " + real + ")";
	}

}
