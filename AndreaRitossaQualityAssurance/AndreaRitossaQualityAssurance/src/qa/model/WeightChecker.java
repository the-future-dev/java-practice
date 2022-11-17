package qa.model;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WeightChecker extends Checker {
	
	private static NumberFormat fP = NumberFormat.getPercentInstance();
	

	public WeightChecker(List<Misura> elencoMisure) {
		super(elencoMisure);
		fP.setMinimumFractionDigits(0);
		fP.setMaximumFractionDigits(0);
		// TODO Auto-generated constructor stub
	}
	private Tolleranza getTolleranza(double peso) {
		for (Tolleranza t : Tolleranza.values()) {
			if (t.getPeso() > peso) {
				return t;
			}
		}
		return Tolleranza.OLTRE1000;
	}
	
	
	@Override
	public Map<String, Double> getTabellaPercentuali() {
		Map<String, Double> ret = new HashMap<>();
		for (String name : getTabellaMisure().keySet()) {
			List<Misura> lm = getTabellaMisure().get(name);
			int correct = 0;
			int howManyMeasures = lm.size();
			for (Misura m : lm) {
				Tolleranza t = getTolleranza(m.getExpected());
				if (t.getUnit() == 'g') {
					if (verificaScostamentoAssoluto(m.getExpected(), m.getReal(), t.getValue())){
						correct++;
					}
				} else {
					if (verificaScostamentoPercentuale(m.getExpected(), m.getReal(), t.getValue())) {
						correct++;
					}					
				}

			}
			double correctPerc = ((double) correct) / howManyMeasures;
			ret.put(name, correctPerc);
		}
		return ret;
	}

	@Override
	public String printTabellaPercentuali() {
		String str = "TABELLA PERCENTUALI:\n";
		Map<String, Double> map = getTabellaPercentuali();
		for (String name : map.keySet()) {
			Double perc = map.get(name);
			str+= name+" \t\t "+fP.format(perc)+"\n";
		}
		str+= "\n";
		return str;
	}

	private boolean verifyMisura(Misura m){
		Tolleranza t = getTolleranza(m.getExpected());
		if (t.getUnit() == 'g') {
			if (verificaScostamentoAssoluto(m.getExpected(), m.getReal(), t.getValue())){
				return true;
			}
		} else if (t.getUnit() == '%'){
			if (verificaScostamentoPercentuale(m.getExpected(), m.getReal(), t.getValue())) {
				return true;
			}					
		}
		return false;
	}
	
	@Override
	public List<Misura> getListaMisureInRange(String descrizione) {
		List<Misura> lm = new LinkedList<>();
		for (Misura m : getMisure(descrizione)) {
			if (verifyMisura(m)) {
				lm.add(m);
			}
		}
		
		return lm;
	}

	@Override
	public double getPercentualeMisureInRange(String descrizione) {
		try {
			double d = getTabellaPercentuali().get(descrizione);
			return d;
		} catch(NullPointerException e) {
			throw new IllegalArgumentException("Not a misura");
		}
	
	}

}
