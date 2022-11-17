package elections.tests.ui;

import java.io.IOException;

import elections.model.Partito;
import elections.model.Elezioni;
import elections.persistence.BadFileFormatException;
import elections.persistence.VotiReader;

public class VotiReaderMock implements VotiReader {

	@Override
	public Elezioni caricaElementi() throws IOException, BadFileFormatException {
		Elezioni voti = new Elezioni(100);
		voti.setVoti(new Partito("Zero"), 10);
		voti.setVoti(new Partito("Uno"), 100);
		voti.setVoti(new Partito("Due"), 200);
		voti.setVoti(new Partito("Tre"), 300);
		voti.setVoti(new Partito("Quattro"), 400);
		return voti;
	}

}
