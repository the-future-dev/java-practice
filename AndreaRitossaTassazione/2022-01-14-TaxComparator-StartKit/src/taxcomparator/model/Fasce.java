	package taxcomparator.model;

import java.util.List;

public class Fasce {
	private List<Fascia> fasce;
	private double noTaxArea;
	private String name;

	public Fasce(String name, List<Fascia> fasce, double noTaxArea) {
		if (name==null || name.isBlank()) this.name = toFullString(); 
		else this.name=name; 
		if (fasce.isEmpty()) throw new IllegalArgumentException("lista fasce di reddito vuota");
		if (noTaxArea < 0) throw new IllegalArgumentException("no tax area negativa");
		if (!isConsistent(fasce)) throw new IllegalArgumentException("lista fasce di reddito inconsistente");
		this.fasce = fasce;
		this.noTaxArea = noTaxArea;
	}

	private boolean isConsistent(List<Fascia> fasce) {
//		in ordine di reddito crescente (in particolare, la prima fascia inizia dal reddito 0)
//		adiacenti (soglia max di una fascia = soglia min della successiva)
		if (fasce.get(0).getMin() != 0) {
//			System.out.println(fasce.get(0));
			return false;
		}
		for (int i = 1; i< fasce.size(); i++) {
			if (fasce.get(i).getMin() != fasce.get(i-1).getMax()) {
//				System.out.println(i+" : "+fasce.get(i).getMin()+" | "+fasce.get(i-1).getMax());
				return false;
			}
		}
		return true;
	}

	public List<Fascia> getFasce() {
		return fasce;
	}

	public double getNoTaxArea() {
		return noTaxArea;
	}

	@Override
	public String toString() {
		return name;
	}

	public String toFullString() {
		return "Fasce [fasce=" + fasce + ", noTaxArea=" + noTaxArea + "]";
	}
	
}
