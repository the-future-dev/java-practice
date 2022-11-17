package elbonia.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalcolatoreSeggiMaggioritario implements CalcolatoreSeggi {

	@Override
	public Map<String, Integer> assegnaSeggi(int dimensioneCollegio, List<Collegio> listaCollegi) {
		List<Collegio> raggruppati = Collegio.raggruppaCollegi(listaCollegi, dimensioneCollegio);
		Map<String, Integer> finalVotes = new HashMap<String, Integer>();
		for (Collegio c :raggruppati) {
			for (Partito p : c.getPartiti()) {
				finalVotes.put(p.getNome(), 0);
			}
		}
		
		for (Collegio c : raggruppati) {
			Partito p = c.getVincitore();
			Integer votiPartito = finalVotes.get(p.getNome());
			finalVotes.put(p.getNome(),  dimensioneCollegio+(votiPartito==null ? 0 : votiPartito));
		}
		return finalVotes;
	}

}
