package unident.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import unident.model.AttivitaFormativa;
import unident.model.Ssd;
import unident.model.Tipologia;

public class UniDentPianoDidatticoReader implements PianoDidatticoReader {

	@Override
	public Set<AttivitaFormativa> readAll(Reader rdr) throws IOException {
		if (rdr == null) throw new BadFileFormatException("Not a file");
		BufferedReader BR = new BufferedReader(rdr);
		String line;
		Set<AttivitaFormativa> afSet = new TreeSet<>(new Comparator<AttivitaFormativa>() {
			@Override
			public int compare(AttivitaFormativa o1, AttivitaFormativa o2) {
				return o1.getNome().compareTo(o2.getNome());
			}
		});
		int i = 0;
		while((line = BR.readLine())!= null) {
			if (!line.trim().equals("")) {
				String [] items = line.split("\t{1,}");
				try {
					String code = items[0];
					String nome = items[1];
					try {
						long codeTry = Long.parseLong(items[0]);
						int periodo = Integer.parseInt(items[2]);
					} catch(NumberFormatException e){
						System.out.println("CODE: "+items[0]+" "+items[2]+" not a number");
//						throw new BadFileFormatException(e.getMessage());
					}
					Tipologia type = Tipologia.valueOf(items[3].toUpperCase());
					String settore;
					Ssd ss = Ssd.SENZASETTORE;
					int crediti;
					if (type.equals(Tipologia.A) || type.equals(Tipologia.C) ||type.equals(Tipologia.B)||type.equals(Tipologia.D)) {
						ss = Ssd.of(items[4]);
						crediti = Integer.parseInt(items[5]);
					} else {
						crediti = Integer.parseInt(items[4]);
					}
					if (type == null || ss == null || crediti == -1)
						throw new BadFileFormatException("general");
					afSet.add(new AttivitaFormativa(nome, type, ss, crediti));
					i++;
				} catch(ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
					throw new BadFileFormatException("not good line");
				}
			}
		}
		return afSet;
	}

}
