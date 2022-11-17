package elections.model;

public abstract class AlgoritmoAssegnazioneSbarramento implements AlgoritmoAssegnazione {
	
	double sbarramento;
	
	public double getSbarramento() {
		return sbarramento;
	}

	public AlgoritmoAssegnazioneSbarramento(double sbarramento){
		this.sbarramento=sbarramento;
	}
	
	@Override
	public final RisultatoElezioni calcolaSeggi(Elezioni elezioni) {
		return calcolaSeggiInternal(getVotiFiltratiDaSbarramento(elezioni));
	}
	
	protected abstract RisultatoElezioni calcolaSeggiInternal(Elezioni elezioni);
	
	private Elezioni getVotiFiltratiDaSbarramento(Elezioni elezioni) {
		if(elezioni ==null)
			return elezioni;
		Elezioni votiRidotti = new Elezioni(elezioni.getSeggiDaAssegnare());
		long votiTotali = elezioni.getVotiTotali();
		long sogliaVoti = Math.round(votiTotali * sbarramento);
		for (Partito p : elezioni.getPartiti()) {
			long votiOttenuti = elezioni.getVoti(p);
			long votiConSbarramento = votiOttenuti >= sogliaVoti ? votiOttenuti : 0L;
			votiRidotti.setVoti(p, votiConSbarramento);
		}
		return votiRidotti;
	}
}
