package ubz.model;

import ubz.persistence.DotazioneLoader;

public class CassiereUbz extends Cassiere {

	private Politiche politiche;

	public CassiereUbz(DotazioneLoader loader) {
		super(loader);

		this.politiche = loader.getPolitiche();
	}

	@Override
	protected int calcolaQuantit‡DiQuestoTaglio(Taglio t, int importo){
		 int quantit‡DiQuestoTaglio = super.calcolaQuantit‡DiQuestoTaglio(t, importo);
		 int maxErogabileSecondoPolitica = politiche.getQuantit‡(t);
		 quantit‡DiQuestoTaglio = Math.min(maxErogabileSecondoPolitica, quantit‡DiQuestoTaglio);
		 return quantit‡DiQuestoTaglio;
	}

}
