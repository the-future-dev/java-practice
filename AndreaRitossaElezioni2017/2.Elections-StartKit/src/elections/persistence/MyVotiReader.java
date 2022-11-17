package elections.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import elections.model.Elezioni;
import elections.model.Partito;

public class MyVotiReader implements VotiReader {
	
//	SEGGI IN PALIO 600
//	Partito del cioccolato fondente 9.100.000
//	Movimento vaniglia democratica 7.200.000

	private Reader rdr;
	private final NumberFormat fN = NumberFormat.getNumberInstance();

	public MyVotiReader(Reader rdr) {
		if (rdr == null)
			throw new IllegalArgumentException("Nullable input");
		this.rdr = rdr;
	}
	
	@Override
	public Elezioni caricaElementi() throws IOException, BadFileFormatException {
		NumberFormat.getNumberInstance(Locale.ITALY);

		BufferedReader br = new BufferedReader(rdr);
		Elezioni elections = null;
		try {
			String line = br.readLine();
			String [] splitted = line.split("SEGGI IN PALIO ");
			long voti = Long.parseLong(splitted[1]);
			elections = new Elezioni(voti);
			while((line = br.readLine())!= null) {
				splitted = line.split("\\ ");
				for (String s : splitted) {
					System.out.println(s);
				}
				System.out.println(splitted.length);
				String nome = "";
				for (int i = 0; i<splitted.length-1; i++) {
					nome += splitted[i];
				}
				long num = fN.parse(splitted[splitted.length-1]).longValue();
				Partito p = new Partito(nome);
				elections.setVoti(p, num);
			}
		}catch(Error e) {
			throw new BadFileFormatException(e.getLocalizedMessage());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("");
		
		return elections;
	}

}
