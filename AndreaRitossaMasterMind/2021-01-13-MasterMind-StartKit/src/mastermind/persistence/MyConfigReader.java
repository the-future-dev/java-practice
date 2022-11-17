package mastermind.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class MyConfigReader implements ConfigReader {
	private int dim = 0;
	private int maxTentativi = 0;
	
	public MyConfigReader(Reader reader) throws BadFileFormatException {
		if (reader == null)
			throw new BadFileFormatException("null reader");
		BufferedReader br = new BufferedReader(reader);
		String line1, line2;
		try {
			line1 = br.readLine();
			line2 = br.readLine();
		} catch(IOException e) {
			throw new BadFileFormatException(e.getMessage());
		}
		if (line1== null || line2 == null)
			throw new BadFileFormatException("null line");
		String [] s_l1 = line1.split("=");
		String [] s_l2 = line2.split("=");
		if (s_l1.length != 2 || s_l2.length != 2)
			throw new BadFileFormatException("not one =");
		
		try {
			if (s_l1[0].trim().toLowerCase().equals("tentativi") && s_l2[0].trim().toLowerCase().equals("lunghezza combinazione")) {
				maxTentativi = Integer.parseInt(s_l1[1].trim());
				dim = Integer.parseInt(s_l2[1].trim());
			}else if (s_l2[0].toLowerCase().trim().equals("tentativi") && s_l1[0].trim().toLowerCase().equals("lunghezza combinazione")) {
				dim = Integer.parseInt(s_l1[1].trim());
				maxTentativi = Integer.parseInt(s_l2[1].trim());
			} else {
				throw new BadFileFormatException("no one gives tentativi...");
			}
		} catch (NumberFormatException e) {
			throw new BadFileFormatException(e.getMessage());
		}
		
	}
	@Override
	public int dimensioneCodice() {
		return this.dim;
	}

	@Override
	public int numeroTentativi() {
		return this.maxTentativi;
	}

}
