package ubz.persistence;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.SortedMap;
import java.util.TreeMap;

import ubz.model.Disponibilità;
import ubz.model.Politiche;
import ubz.model.Taglio;

public class BinFileGenerator {

	public static void main(String[] args) throws FileNotFoundException{
		int[] quantità  = { 5, 25, 30, 200, 200, 100, 100, 50, 50 };
		int[] politiche = { 5, 25, 30, 200,  10,   5,   3,  4,  3 };
		
		SortedMap<Taglio,Integer> mappaQuantità = new TreeMap<>();
		SortedMap<Taglio,Integer> mappaPolitiche = new TreeMap<>();
		for (Taglio t: Taglio.values()){
			mappaQuantità.put(t, quantità[t.ordinal()]);
			mappaPolitiche.put(t, politiche[t.ordinal()]);
		}

		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("DotazioneIniziale.dat"))) {
			os.writeObject(new Disponibilità(mappaQuantità));
			os.writeObject(new Politiche(mappaPolitiche));
		}
		catch(IOException e){
			System.out.println(e);
		}
		
		System.out.println("File generato");
		System.out.println(mappaQuantità);
		System.out.println(mappaPolitiche);
	}
}
