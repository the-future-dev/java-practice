package ubz.persistence;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import ubz.model.Disponibilit�;
import ubz.model.Politiche;

public class MyDotazioneLoader implements DotazioneLoader {

	private Disponibilit� disponibilit�;
	private Politiche politiche;
	
	public void load(InputStream is) throws IOException, BadFileFormatException {
		if (is == null)
			throw new IllegalArgumentException("inputstream nullo");
		
		try (ObjectInputStream ois = new ObjectInputStream(is)){
						
			Object obj = ois.readObject();	
			if (obj instanceof Disponibilit�) 
				disponibilit� = (Disponibilit�) obj;
			else 
				throw new BadFileFormatException("Disponibilit� non presente nel file binario");
			
			obj = ois.readObject();
			if (obj instanceof Politiche) 
				politiche = (Politiche) obj;
			else 
				throw new BadFileFormatException("Politiche non presenti nel file binario");
			
		} catch (ClassNotFoundException e1) {
			throw new BadFileFormatException("Dati non riconosciuti", e1);
		} catch (EOFException e2) {
			throw new BadFileFormatException("Dati mancanti", e2);
		} catch (IOException e3) {
			throw e3;
		} catch (Exception e4) {
			throw new BadFileFormatException("Errore nella lettura del file", e4);
		}
	}

	@Override
	public Disponibilit� getDisponibilit�() {
		return disponibilit�;
	}

	@Override
	public Politiche getPolitiche() {
		return politiche;
	}


	// quick test
	public static void main(String args[]) throws IOException, BadFileFormatException {
		DotazioneLoader loader = new MyDotazioneLoader();
		loader.load(new FileInputStream("DotazioneIniziale.dat"));
		System.out.println(loader.getDisponibilit�());
		System.out.println(loader.getPolitiche());
	}

}
