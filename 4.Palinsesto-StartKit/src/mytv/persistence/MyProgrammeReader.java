package mytv.persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.Duration;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

import mytv.model.Programme;

public class MyProgrammeReader implements ProgrammeReader {
	
	public MyProgrammeReader() {
		
	}
	
	@Override
	public SortedSet<Programme> readAll(Reader reader) throws IOException, BadFileFormatException {
		if (reader == null) throw new IOException("Null Reader");
		
		BufferedReader f = new BufferedReader(reader);
		SortedSet<Programme> programmes = new TreeSet<Programme>();
		
		Programme p = null;
		while ((p = readLine(f))!= null) {
			programmes.add(p);
		}
		return programmes;
	}
	
	private Programme readLine(BufferedReader BR) throws IOException, BadFileFormatException{
		String line = BR.readLine();
		if (line == null) return null;
		
		String[] parti = line.split("\t");
		String[] dur = parti[parti.length-1].split(":");
		if (dur.length != 2) throw new BadFileFormatException("");
		Duration d = Duration.ofHours(Integer.parseInt(dur[0])).plusMinutes(Integer.parseInt(dur[1]));
		Programme p = new Programme(parti[0], d);
		return p;
	}
	
	
}
