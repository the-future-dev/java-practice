package bussy.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.TreeMap;

import bussy.model.Fermata;
import bussy.model.Linea;
import bussy.model.LineaCircolare;
import bussy.model.LineaPaP;

public class MyTransportLinesReader implements TransportLinesReader {

	@Override
	public Map<String, Linea> readTransportLines(BufferedReader reader) throws IOException, BadFileFormatException {
		
		Map<String, Linea> ret = new TreeMap<>();
		
		Linea l;
		while ((l = readTransportLine(reader))!= null) {
			ret.put(l.getId(), l);
		}
		return ret;
	}
	
	private Linea readTransportLine(BufferedReader BR) throws IOException, BadFileFormatException {
		String id = BR.readLine();
		if (id == null) return null;
		else {
			String[] div = id.split(" ");
			System.out.println(div[0]+" "+div.length);
			if (!div[0].trim().equals("Linea")) {
				
			}
				
		}
		
		Map<Integer, Fermata> orariPassaggioAlleFermate = new TreeMap<>();
		
		Optional<String> idIni = Optional.empty();
		boolean circolare = false, out = false;
		
		
		String line;
		while((line = BR.readLine())!= null && !out) {
			String[] tokens = line.split(",");
			for (int i = 0; i< tokens.length; i++) {
				tokens[i] = tokens[i].trim();
				if (tokens[i] == "") throw new BadFileFormatException("empty");
			}
			
			if (tokens.length == 1) {
				if (!tokens[0].contains("-")) throw new BadFileFormatException("NOT GOOD");
				out = true;
			} else if (tokens.length != 3) {
				throw new BadFileFormatException("Not right number of tokens");
			}else {
				
				
				try {
					int orario = Integer.parseInt(tokens[0]);
					Fermata f = new Fermata(tokens[1], tokens[2]);
					orariPassaggioAlleFermate.put(orario, f);
				} catch (Error e) {
					throw new BadFileFormatException(e.getMessage());
				}
				if (idIni.isEmpty()) {
					idIni = Optional.of(tokens[2]);
				} else {
					circolare = idIni.get().equals(tokens[2]);
				}
			}
		}
		Linea l;
		if (circolare) {
			l = new LineaCircolare(id, orariPassaggioAlleFermate);
		} else {
			l = new LineaPaP(id, orariPassaggioAlleFermate);
		}
		return l;
	}

}
