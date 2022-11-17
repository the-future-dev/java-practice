package elections.model;

import java.util.ArrayList;
import java.util.Collections;

public class AlgoritmoAssegnazioneSbarramentoProporzionale extends AlgoritmoAssegnazioneSbarramento {

	public AlgoritmoAssegnazioneSbarramentoProporzionale(double sbarramento) {
		super(sbarramento);
		if (sbarramento < 0 || sbarramento > 0.1)
			throw new IllegalArgumentException("sbarramento non considerabile: "+sbarramento);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected RisultatoElezioni calcolaSeggiInternal(Elezioni elezioni) {
		if(elezioni ==null)
			throw new IllegalArgumentException("Nullable input");
		
		Elezioni votiRidotti = new Elezioni(elezioni.getSeggiDaAssegnare());
		long votiTotali = elezioni.getVotiTotali();
		long sogliaVoti = Math.round(votiTotali * sbarramento);
		for (Partito p : elezioni.getPartiti()) {
			long votiOttenuti = elezioni.getVoti(p);
			long votiConSbarramento = votiOttenuti >= sogliaVoti ? votiOttenuti : 0L;
			votiRidotti.setVoti(p, votiConSbarramento);
		}
		System.out.println("Seggi da Assegnare: "+elezioni.getSeggiDaAssegnare()+" VotiTotali:"+elezioni.getVotiTotali());
		double quozienteElettorale = ((double) elezioni.getVotiTotali()) / (double) elezioni.getSeggiDaAssegnare();
		System.out.println("Double: "+quozienteElettorale);
		RisultatoElezioni result = new RisultatoElezioni(elezioni.getPartiti());
		ArrayList<RestoDelPartito> resti = new ArrayList<>();
		
		int seggiAssegnati = 0;
		for (Partito p : elezioni.getPartiti()) {
			System.out.println(((double) elezioni.getVoti(p)) / ((double) quozienteElettorale));
			int seggi = (int) Math.floor(((double) elezioni.getVoti(p)) / ((double) quozienteElettorale));
			resti.add(new RestoDelPartito(p, elezioni.getVoti(p) % quozienteElettorale));
			seggiAssegnati+= seggi;
			
			result.setSeggi(p, seggi);
			
			
		}
		
		long seggiRimanenti = elezioni.getSeggiDaAssegnare() - seggiAssegnati;
		Collections.sort(resti);
		
		int i = 0;
		while (seggiRimanenti > 0) {
			result.setSeggi(resti.get(i).getPartito(), result.getSeggi(resti.get(i).getPartito())+1);
			seggiRimanenti--;
			i++;
			if (i == resti.size()) i = 0;
		}
		

		
		return result;
	}

}
