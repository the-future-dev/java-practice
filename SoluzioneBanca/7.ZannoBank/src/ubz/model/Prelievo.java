package ubz.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import ubz.persistence.BadFileFormatException;
import ubz.persistence.LoaderMock;

public class Prelievo extends Soldi {

	private static final long serialVersionUID = 1L;

	public Prelievo(Map<Taglio,Integer> mappaQuantità) {
		super(mappaQuantità);
	}
		
		// quick test
	public static void main(String args[]) throws IOException, BadFileFormatException {
		LoaderMock loader = new LoaderMock();
		loader.load(new FileInputStream("DotazioneIniziale.dat"));
		Prelievo prelievo = new Prelievo(loader.getMappaQuantità());
		System.out.println(prelievo);
	}

}
