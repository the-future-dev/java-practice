package ubz.model;

import ubz.persistence.DotazioneLoader;

public class CassiereUbz extends Cassiere {
	private Politiche p;
	
	public CassiereUbz(DotazioneLoader loader) {
		super(loader);
		p = loader.getPolitiche();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected int calcolaQuantit�DiQuestoTaglio(Taglio t, int importo){
		Integer minPolicy = p.getQuantit�(t);
		 int quantit�Richiesta = importo/t.getValore();
		 int quantit�Disponibile = super.getDisponibilit�Taglio(t);
		int w = Math.min(quantit�Richiesta, quantit�Disponibile);
		return Math.min(minPolicy, w);
	}


}
