package bussy.model;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.SortedSet;
import java.util.TreeSet;

public class MyCercatore implements Cercatore {
//	IllegalArgumentException
	private Map<String, Linea> mappaLinee;
	
	public MyCercatore(Map<String, Linea> mappaLinee) {
		if (mappaLinee == null || mappaLinee.isEmpty()) throw new IllegalArgumentException("Not a good mappa di linee");
		this.mappaLinee = mappaLinee;
	}
	
	@Override
	public SortedSet<Percorso> cercaPercorsi(String fermataDa, String fermataA, OptionalInt durataMax) {
		if (fermataDa == null || fermataA == null) throw new IllegalArgumentException("Not a good fermata");
		
		SortedSet<Percorso> percorsi = new TreeSet<>();
		for (Linea l : mappaLinee.values()) {
			Optional<Percorso> oP = l.getPercorso(fermataDa, fermataA);
			if (oP.isPresent()) {
				Percorso p = oP.get();
				if (durataMax.isEmpty() || durataMax.getAsInt() >= p.getDurata()) {
					percorsi.add(p);
				}
			}
		}
		return percorsi;
	}

	@Override
	public Map<String, Linea> getMappaLinee() {
		return this.mappaLinee;
	}

}
