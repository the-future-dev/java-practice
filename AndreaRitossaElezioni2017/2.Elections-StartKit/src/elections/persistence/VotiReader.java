package elections.persistence;

import java.io.IOException;

import elections.model.Elezioni;

public interface VotiReader {
	public Elezioni caricaElementi() throws IOException, BadFileFormatException;
}
