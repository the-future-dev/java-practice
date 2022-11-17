package elections.tests.ui;

import java.io.IOException;

import elections.model.Elezioni;
import elections.persistence.SeggiWriter;

public class SeggiWriterMock implements SeggiWriter {

	@Override
	public void stampaSeggi(Elezioni risultato, String msg) throws IOException {
		// I'm a lazy mock and I do nothing at all.
    }

}
