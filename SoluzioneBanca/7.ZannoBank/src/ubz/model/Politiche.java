package ubz.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import ubz.persistence.BadFileFormatException;
import ubz.persistence.LoaderMock;

public class Politiche extends Soldi {

	private static final long serialVersionUID = 1L;

	public Politiche(Map<Taglio,Integer> mappaPolitiche) {
		super(mappaPolitiche);
	}
	
	
	// quick test
	public static void main(String args[]) throws IOException, BadFileFormatException {
		LoaderMock loader = new LoaderMock();
		loader.load(new FileInputStream("DotazioneIniziale.dat"));
		Politiche prelievo = new Politiche(loader.getMappaQuantità());
		System.out.println(prelievo);
	}

}
