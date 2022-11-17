package ubz.persistence;

import java.io.IOException;
import java.io.InputStream;

import ubz.model.Disponibilit�;
import ubz.model.Politiche;

public interface DotazioneLoader {
	public void load(InputStream r) throws IOException, BadFileFormatException;
	public Disponibilit� getDisponibilit�();
	public Politiche getPolitiche();
}
