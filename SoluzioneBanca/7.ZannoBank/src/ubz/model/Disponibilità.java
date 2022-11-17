package ubz.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import ubz.persistence.BadFileFormatException;
import ubz.persistence.DotazioneLoader;
import ubz.persistence.MyDotazioneLoader;

public class Disponibilit‡ extends Soldi {

	private static final long serialVersionUID = 1L;

	public Disponibilit‡(Map<Taglio,Integer> mappaDisponibilit‡) {
		super(mappaDisponibilit‡);
	}
	
	public void aggiorna(Taglio t, int value){ 
		super.setQuantit‡(t, value);
	}
		
	// quick test
	public static void main(String args[]) throws IOException, BadFileFormatException {
		DotazioneLoader loader = new MyDotazioneLoader();
		loader.load(new FileInputStream("DotazioneIniziale.dat"));
		Disponibilit‡ disponbilit‡InCassa = loader.getDisponibilit‡();
		System.out.println(disponbilit‡InCassa);
	}

}
