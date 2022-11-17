package qa.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

import qa.model.Misura;

public class MyMisureReader implements MisureReader {

	@Override
	public List<Misura> leggiMisure(Reader r) throws IOException, BadFileFormatException {
		if (r == null)
			throw new IllegalArgumentException("nullable input");
		BufferedReader br = new BufferedReader(r);
		
		List<Misura> lm = new LinkedList<>();
		String line;
		String [] splitted;
		while((line = br.readLine())!= null) {
			splitted = line.split(",");
			try {
				String nome = splitted[0].trim();
				double expected = Double.parseDouble(splitted[1].trim());
				double realgott = Double.parseDouble(splitted[2].trim());
				Misura m = new Misura(nome, expected, realgott);
				lm.add(m);
			}catch(Error e) {
				throw new BadFileFormatException("");
			} catch(NumberFormatException e1) {
				throw new BadFileFormatException("Not a number my man");
			} catch(ArrayIndexOutOfBoundsException e2) {
				throw new BadFileFormatException("Not right length of line");
			}
		}
		return lm;
	}

}
