package pianodistudi.persistence;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;

import pianodistudi.model.AttivitaFormativa;

public interface AttivitaFormativeReader {
	public Map<String, AttivitaFormativa> recuperaElenco(Reader rdr) throws IOException;
}