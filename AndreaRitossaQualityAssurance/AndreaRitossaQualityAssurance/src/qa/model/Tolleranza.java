package qa.model;

public enum Tolleranza {
	FINOA50(  50, 9.0, '%'), FINOA100(100, 4.5, 'g'), 
	FINOA200(200, 4.5, '%'), FINOA300(300, 9.0, 'g'), 
	FINOA500(500, 3.0, '%'), FINOA1000(1000, 15, 'g'), 
	OLTRE1000(1000, 1.5, '%');
	private Tolleranza(double peso, double value, char unit){
		this.peso=peso; this.value=value; this.unit=unit;
	}
	private double peso, value;
	private char unit;
	public double getPeso() { return peso; }
	public char getUnit() { return unit; }
	public double getValue() { return value; }
	
//	public static int verifica(double pesoAtteso, double pesoReale){
//		// ritorna -1 se tutto ok, 0 se borderline, +1 se critica
//		// per "critica" si intende fuori range per difetto, ossia prodotto mancante (truffa)
//		// al contrario, prodotto in eccesso è sempre ok
//		for(Tolleranza t: Tolleranza.values())
//			if (t.getPeso()>pesoAtteso){
//				System.out.println("checking " + pesoAtteso + " vs. " + pesoReale + ", range is " + t);
//				System.out.println("g: " + (pesoReale-pesoAtteso) + ", " +  t.getValue());
//				switch (t.getUnit()) {
//					case '%': return Double.compare(pesoAtteso-pesoReale,  t.getValue()*pesoAtteso/100); // implicit break
//					case 'g': return Double.compare(pesoAtteso-pesoReale,  t.getValue()); // implicit break
//					default: throw new IllegalArgumentException("unexpected unit type");
//				}
//			}
//		return Double.compare(pesoReale-pesoAtteso,  OLTRE1000.getValue()*pesoAtteso/100);
//	}
	
//	public static void main(String[] rgs){
//		System.out.println(verifica(250,252));
//		System.out.println(verifica(250,241));
//		System.out.println(verifica(250,239));
//		System.out.println(verifica(250,237));
//		System.out.println(verifica(250,244));
//		System.out.println(verifica(250,248));
//		System.out.println(verifica(250,260));
//		System.out.println(verifica(250,247));
//		System.out.println(verifica(250,245));
//		System.out.println(verifica(250,255));
//
//		System.out.println(verifica(500,508));
//		System.out.println(verifica(500,490));
//		System.out.println(verifica(500,483));
//		System.out.println(verifica(500,488));
//		System.out.println(verifica(500,492));
//		System.out.println(verifica(500,504));
//		System.out.println(verifica(500,512));
//		System.out.println(verifica(500,507));
//		System.out.println(verifica(500,501));
//		System.out.println(verifica(500,486));
//		System.out.println(verifica(500,478));
//
//		System.out.println(verifica(180,172));
//		System.out.println(verifica(180,170));
//		System.out.println(verifica(180,183));
//		System.out.println(verifica(180,186));
//		System.out.println(verifica(180,181));
//		System.out.println(verifica(180,176));
//		System.out.println(verifica(180,173));
//		System.out.println(verifica(180,171));
//	}
}
