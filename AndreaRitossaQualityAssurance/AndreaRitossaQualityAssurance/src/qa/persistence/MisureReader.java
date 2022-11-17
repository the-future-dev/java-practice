package qa.persistence;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import qa.model.Misura;

public interface MisureReader {
	public List<Misura> leggiMisure(Reader r) throws IOException, BadFileFormatException;
}
