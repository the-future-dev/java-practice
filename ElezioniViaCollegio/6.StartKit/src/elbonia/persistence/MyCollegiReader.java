package elbonia.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import elbonia.model.Collegio;

public class MyCollegiReader implements CollegiReader {

	@Override
	public List<Collegio> caricaElementi(Reader reader) throws IOException, BadFileFormatException {
		if (reader == null)
			throw new IllegalArgumentException("Nullable Reader");
		BufferedReader br = new BufferedReader(reader);
		List<Collegio> lC = new ArrayList<>();
		
		String line = br.readLine();
		String [] splitted = line.split(";");
		
		while((line = br.readLine())!= null) {
			
		}
		
		return null;
	}

}
