package ubz.model;

import java.util.Map;

public class Disponibilit� extends Soldi {

	private static final long serialVersionUID = 1L;

	public Disponibilit�(Map<Taglio,Integer> mappaDisponibilit�) {
		super(mappaDisponibilit�);
	}
	
	public void aggiorna(Taglio t, int value){ 
		super.setQuantit�(t, value);
	}


}
