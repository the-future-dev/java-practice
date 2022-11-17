package dentinia.governor.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.SortedMap;
import java.util.StringTokenizer;

import dentinia.governor.model.Elezioni;
import dentinia.governor.model.Partito;

public class MyVotiReader implements VotiReader {
	private Elezioni elezioni;
	private BufferedReader reader;
	private long seggiDaAssegnare;
	
	
	public MyVotiReader(Reader r) throws IllegalArgumentException, BadFileFormatException, IOException{
		if (r == null) throw new IllegalArgumentException("null reader");
		reader = new BufferedReader(r);

		caricaElementi(elezioni);
	}
	
	private void caricaElementi(Elezioni el) throws IOException, BadFileFormatException{
		String firstLine = reader.readLine();
		String secondLine = reader.readLine();
		if (reader.readLine() != null || firstLine == null || secondLine == null) throw new BadFileFormatException("Not right number of lines");
		
		StringTokenizer a = new StringTokenizer(firstLine);
		if (a.countTokens() != 2 || !a.nextToken().equals("SEGGI")) throw new BadFileFormatException("2");
		
		try{
			seggiDaAssegnare = Long.parseLong(a.nextToken());
			elezioni = new Elezioni(seggiDaAssegnare);
		}catch (NumberFormatException | NullPointerException e ) {
			throw new BadFileFormatException("WTF");
		}
		
		String[] div = secondLine.split(",");
		NumberFormat nf = NumberFormat.getInstance(Locale.ITALY);
		
		for (int j = div.length-1; j>=0; j--) {
			String[] stk = div[j].split(" ");
			if (stk.length < 2) throw new BadFileFormatException("5");
			String name= "";
			for (int i = 0; i< stk.length-1; i++) {
			     name+= stk[i];
			     if (i!= stk.length-1) {
			    	 name+= " ";
			     }
			}
			name = name.trim();
			long num= 0;
			try {
				num = (long) nf.parse(stk[stk.length-1]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				throw new BadFileFormatException("4");
			}
			elezioni.setVoti(new Partito(name), num);
		}
	}
	@Override
	public Elezioni getElezioni() {
		// TODO Auto-generated method stub
		return this.elezioni;
	}
	
	public void main(String[] args) {
		
	}
}
