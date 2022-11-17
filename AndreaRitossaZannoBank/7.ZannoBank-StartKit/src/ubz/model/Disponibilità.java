package ubz.model;

import java.util.Map;

public class Disponibilità extends Soldi {

	private static final long serialVersionUID = 1L;

	public Disponibilità(Map<Taglio,Integer> mappaDisponibilità) {
		super(mappaDisponibilità);
	}
	
	public void aggiorna(Taglio t, int value){ 
		super.setQuantità(t, value);
	}


}
