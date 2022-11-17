package ubz.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.util.SortedMap;
import java.util.TreeMap;

import ubz.model.Disponibilità;
import ubz.model.Politiche;
import ubz.model.Taglio;

public class LoaderMock implements DotazioneLoader {
	int[] quantità  = { 5, 25, 30, 200, 200, 100, 100, 50, 50 };
	int[] politiche = { 5, 25, 30, 200,  10,   5,   3,  4,  3 };

	SortedMap<Taglio,Integer> mappaQuantità, mappaPolitiche; 
	
	public LoaderMock(){
		mappaQuantità = new TreeMap<>();
		mappaPolitiche = new TreeMap<>();
		for (Taglio t: Taglio.values()){
			mappaQuantità.put(t, quantità[t.ordinal()]);
			mappaPolitiche.put(t, politiche[t.ordinal()]);
		}
	}
	
	@Override
	public void load(InputStream r) throws IOException, BadFileFormatException {
		if(r==null) System.err.println("inputstream nullo");
	}

	@Override
	public Disponibilità getDisponibilità() {
		return new Disponibilità(mappaQuantità);
	}
	@Override
	public Politiche getPolitiche() {
		return new Politiche(mappaPolitiche);
	}

	// for mock pourposes only
	public SortedMap<Taglio, Integer> getMappaQuantità() {
		return mappaQuantità;
	}

	// for mock pourposes only
	public SortedMap<Taglio, Integer> getMappaPolitiche() {
		return mappaPolitiche;
	}

}
