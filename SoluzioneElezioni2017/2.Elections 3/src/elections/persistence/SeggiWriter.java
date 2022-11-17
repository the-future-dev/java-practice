package elections.persistence;

import java.io.IOException;

import elections.model.Elezioni;

public interface SeggiWriter {
	void stampaSeggi(Elezioni elezioni, String msg) throws IOException;
}
