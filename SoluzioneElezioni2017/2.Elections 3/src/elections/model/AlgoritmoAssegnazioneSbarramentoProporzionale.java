package elections.model;

import java.util.ArrayList;
import java.util.Collections;

public class AlgoritmoAssegnazioneSbarramentoProporzionale extends AlgoritmoAssegnazioneSbarramento {

	
	public AlgoritmoAssegnazioneSbarramentoProporzionale(double sbarramento)
	{		
		super(sbarramento);
	}
	
	@Override
	protected RisultatoElezioni calcolaSeggiInternal(Elezioni elezioni) {
		if (sbarramento < 0.0 || sbarramento > 0.1)
			throw new IllegalArgumentException("sbarramento");
		if (elezioni == null)
			throw new IllegalArgumentException("elezioni");

		long seggiDaAssegnare = elezioni.getSeggiDaAssegnare();
		long votiTotaliInQuoziente = elezioni.getVotiTotali();
		double quozienteElettorale = (double) votiTotaliInQuoziente / seggiDaAssegnare;
		int seggiAssegnati = 0;

		RisultatoElezioni risultato = new RisultatoElezioni(elezioni.getPartiti());
		ArrayList<RestoDelPartito> partitiConPi�AltiResti = new ArrayList<>();
		for (Partito p : elezioni.getPartiti()) {
			long votiPartito = elezioni.getVoti(p);
			long seggiPartito = (long) Math.floor(votiPartito / quozienteElettorale);
			risultato.setSeggi(p, seggiPartito);
			seggiAssegnati += seggiPartito;
			if (votiPartito > 0) {
				double resto = elezioni.getVoti(p) % quozienteElettorale;
				partitiConPi�AltiResti.add(new RestoDelPartito(p, resto));
			}
		}
		Collections.sort(partitiConPi�AltiResti);

		for (int i = 0; i < partitiConPi�AltiResti.size() && seggiAssegnati < seggiDaAssegnare; i++) {
			RestoDelPartito resto = partitiConPi�AltiResti.get(i);
			Partito p = resto.getPartito();
			long seggiAttuali = risultato.getSeggi(p);
			risultato.setSeggi(p, seggiAttuali + 1);
			seggiAssegnati++;
		}

		return risultato;
	}

	
	
}
