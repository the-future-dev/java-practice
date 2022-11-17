package ubz.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Map;
import java.util.TreeMap;

public class Soldi implements Serializable {

	private static final long serialVersionUID = 1L;

	private double importo;
	private Map<Taglio,Integer> mappa;
	
	public Soldi(Map<Taglio,Integer> mappa) {
		if (mappa == null)
			throw new IllegalArgumentException("mappa non valida");	
		this.mappa = new TreeMap<>(mappa);
		this.importo = calcola();
	}
	
	public int getQuantità(Taglio t){
		return mappa.get(t);
	}
	
	protected Integer setQuantità(Taglio t, int value){
		return mappa.put(t, value);
	}
		
	private double calcola(){
		return mappa.entrySet()
		     .stream()
			 .mapToDouble(entry -> entry.getKey().getValore()*entry.getValue()).sum();
	}

	public double getImporto() {
		return importo;
	}
	
	public String toString() {
		return NumberFormat.getCurrencyInstance().format(importo) + " come " + mappa;
	}

	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof Soldi)) return false;
		return this.mappa.equals(((Soldi)obj).mappa);
	}
	
	@Override
	public int hashCode(){
		return mappa.hashCode();
	}
}
