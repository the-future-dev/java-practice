package pianodistudi.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.TreeMap;

import pianodistudi.model.AttivitaFormativa;
import pianodistudi.model.Ssd;

public class MyAttivitaFormativeReader implements AttivitaFormativeReader {
//	27991 ANALISI MATEMATICA T-1 MAT/05 9
//	28004 FONDAMENTI DI INFORMATICA T-1 ING-INF/05 12

	@Override
	public Map<String, AttivitaFormativa> recuperaElenco(Reader rdr) throws IOException {
		// TODO Auto-generated method stub
		if (rdr == null)
			throw new BadFileFormatException("nullable reader");
		BufferedReader  br = new BufferedReader(rdr);
		Map<String,AttivitaFormativa> mappReturnable = new TreeMap<>();
		
		String line;
		while((line = br.readLine())!= null) {
			if (!line.trim().equals("")) {
				String[] splittedLine = line.split("\t");
				String[] tokens = new String[4];
				
				int k = 0;
				try {
					for (int i = 0; i<splittedLine.length; i++) {
						if (!splittedLine[i].trim().equals("")) {
							tokens[k] = splittedLine[i].trim();
							k++;	
						}
					}
				} catch(ArrayIndexOutOfBoundsException e) {
					throw new BadFileFormatException("not 4 tokens");
				}
//				for (String t: tokens) {
//					System.out.println(t);
//				}
				String code = tokens[0];
				String nome = tokens[1];
				String sett = tokens[2];
				String cred = tokens[3];
				int cfu = 0;
				try {
					cfu = Integer.parseInt(cred);
				}catch(NumberFormatException e) {
					throw new BadFileFormatException("not a number");
				}
				
				AttivitaFormativa activity = new AttivitaFormativa(code, Ssd.of(sett), cfu);
				
				mappReturnable.put(nome, activity);
			}
		}
		return mappReturnable;
	}

}
