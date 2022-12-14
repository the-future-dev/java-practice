package ubz.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import ubz.persistence.BadFileFormatException;
import ubz.persistence.DotazioneLoader;
import ubz.persistence.MyDotazioneLoader;

public class Disponibilità extends Soldi {

	private static final long serialVersionUID = 1L;

	public Disponibilità(Map<Taglio,Integer> mappaDisponibilità) {
		super(mappaDisponibilità);
	}
	
	public void aggiorna(Taglio t, int value){ 
		super.setQuantità(t, value);
	}
		
	// quick test
	public static void main(String args[]) throws IOException, BadFileFormatException {
		DotazioneLoader loader = new MyDotazioneLoader();
		loader.load(new FileInputStream("DotazioneIniziale.dat"));
		Disponibilità disponbilitàInCassa = loader.getDisponibilità();
		System.out.println(disponbilitàInCassa);
	}

}
