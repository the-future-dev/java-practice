package dentinia.governor.model;

import java.util.ArrayList;
import java.util.Collections;

public class LeggeElettoraleDHondt implements LeggeElettorale {

	@Override
	public RisultatoElezioni apply(Elezioni t) {
		RisultatoElezioni re = new RisultatoElezioni(t.getPartiti());
		ArrayList<Quoziente> quozienti = new ArrayList<>();
		
		for (Partito p : t.getPartiti()) {
			for (int i = 1; i< t.getSeggiDaAssegnare(); i++) {
				Quoziente q = new Quoziente(p, (long)Math.floor(t.getVoti(p)/i));
				quozienti.add(q);
			}
		}
		Collections.sort(quozienti);
		
		int seggiAssegnati = 0;
		for (int k = 0; k<quozienti.size() && seggiAssegnati < t.getSeggiDaAssegnare(); k++) {
			Partito p = quozienti.get(k).getPartito();
			re.setSeggi(
					p,
					re.getSeggi(p)+1);
			seggiAssegnati++;
		}
		return re;
	}

}
