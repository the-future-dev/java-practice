package ubz.model;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import ubz.persistence.DotazioneLoader;

public class Cassiere {

	private Disponibilit‡ disponibilit‡;

	public Cassiere(DotazioneLoader loader) {
		if (loader == null)
			throw new IllegalArgumentException("loader nullo");
		this.disponibilit‡ = loader.getDisponibilit‡();
	}

	public int getDisponibilit‡Taglio(Taglio t){
		return disponibilit‡.getQuantit‡(t);
	} 
	
	public Prelievo preleva(int importo) throws ImpossibleWithdrawException{
		SortedMap<Taglio, Integer> mappaPrelievo = new TreeMap<>();
		int importoResiduo = importo;
		for(Taglio t: Taglio.values()){
			 int quantit‡TaglioCorrente = calcolaQuantit‡DiQuestoTaglio(t, importoResiduo);
			 importoResiduo -= quantit‡TaglioCorrente * t.getValore();
			 mappaPrelievo.put(t, quantit‡TaglioCorrente);
		}
		if (importoResiduo>0) 
			throw new ImpossibleWithdrawException(importo, importoResiduo);
		else {
			aggiornaDisponibilit‡InCassa(mappaPrelievo);
			return new Prelievo(mappaPrelievo);
		}
	}

	protected void aggiornaDisponibilit‡InCassa(Map<Taglio, Integer> mappaPrelievo) {
		for(Taglio t: Taglio.values()){
			int disponibilit‡Attuale = getDisponibilit‡Taglio(t);
			int banconoteDaPrelevare = mappaPrelievo.get(t);
			disponibilit‡.aggiorna(t, disponibilit‡Attuale-banconoteDaPrelevare);
		}	
	}

	/* versione base SENZA POLITICHE */
	protected int calcolaQuantit‡DiQuestoTaglio(Taglio t, int importo){
		 int quantit‡Richiesta = importo/t.getValore();
		 int quantit‡Disponibile = disponibilit‡.getQuantit‡(t);
		 return Math.min(quantit‡Richiesta, quantit‡Disponibile);
	}
}
