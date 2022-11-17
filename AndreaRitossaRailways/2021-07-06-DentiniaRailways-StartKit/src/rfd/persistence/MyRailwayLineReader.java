package rfd.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

import rfd.model.MyPointOfInterest;
import rfd.model.PointOfInterest;
import rfd.model.RailwayLine;

public class MyRailwayLineReader implements RailwayLineReader {

	@Override
	public RailwayLine getRailwayLine(Reader rdr) throws IOException {
		if (rdr == null)
			throw new IllegalArgumentException("nullable reader");
		BufferedReader br = new BufferedReader(rdr);
		
		SortedMap<String,PointOfInterest> map = new TreeMap<>();
		SortedSet<String> hubs = new TreeSet<>();
		
		String line;
		while ((line = br.readLine()) != null) {
			StringTokenizer stk = new StringTokenizer(line, "\t");
			
			if (stk.countTokens() != 2) {
				throw new IllegalArgumentException("not 2 elements");
			}
			String prog = stk.nextToken();
			char[] progArray = prog.toCharArray();
			String nome = stk.nextToken(); char[] nomeArray = nome.toCharArray();
			String s = "";
			
			for (char c : progArray) {
				if ( !(c>= '0' && c <= '9') && (c!='+'))
					throw new IllegalArgumentException("not right formato of programmazione");
			}
			
			for (char c: nomeArray) { if (c != '+') s+= c;}
			if (nomeArray[0] <= '9' && nomeArray[0] >= '0')
				throw new IllegalArgumentException("inizia per una cifra");
			
			map.put(s, new MyPointOfInterest(s, prog));
			if (nomeArray[nomeArray.length-1] == '+') {
				hubs.add(s);
			}
		}
		
		return new RailwayLine(map, hubs);
	}

}
