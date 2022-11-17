package mytv.persistence;

import java.io.IOException;
import java.io.Reader;
import java.util.SortedSet;

import mytv.model.Programme;

public interface ProgrammeReader {
	SortedSet<Programme> readAll(Reader reader) throws IOException, BadFileFormatException;
}
