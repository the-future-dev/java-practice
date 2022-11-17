package taxcomparator.model;

import java.text.DecimalFormat;

public class MyCalcolatoreImposta implements CalcolatoreImposta {
	private Fasce fasce;
	private DecimalFormat currencyFormatter = new DecimalFormat("¤ #,##0.##");
	
	private StringBuilder logger;
	
	
	private int determinaIndiceFasciaMax(double massimo) {
//		Collections.sort(fasce.getFasce());
		for (int i = 0; i<fasce.getFasce().size(); i++) {
			Fascia f = fasce.getFasce().get(i);
			if (f.getMax() > massimo) {
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public double calcolaImposta(double reddito, Fasce fasce) {
		this.fasce = fasce;
		this.logger = new StringBuilder("");
//		Imponibile lordo = € 44.000, no-tax area = € 8.174, imponibile netto = € 35.826
		double iCorrente = reddito - fasce.getNoTaxArea();
		logger.append("Imponibile lordo = "+reddito+", no-tax area= "+fasce.getNoTaxArea()+", imponibile netto ="+iCorrente+"\n");
		
		double totImposta = 0;
		for (int i = determinaIndiceFasciaMax(iCorrente); i>=0; i--) {
			Fascia f = fasce.getFasce().get(i);
			logger.append(f.toString()+"\n");
			double rangeDiImposta = iCorrente-f.getMin();
			double imposta = rangeDiImposta * f.getAliquota();
			logger.append(
					"Imponibile corrente = "+currencyFormatter.format(iCorrente)
				+	", imposta = "+currencyFormatter.format(imposta)
				+	", imposta = "+currencyFormatter.format(iCorrente-rangeDiImposta)+"\n");
			totImposta += imposta;
			iCorrente = iCorrente-rangeDiImposta;
			
		}
		return totImposta;
	}
/*
 * System.out.println(f.toString());
			double range = f.getMax()-f.getMin();
			double imponibileSingolo = (reddito > f.getMax()) ? range : (reddito - f.getMin());
			double imposta;
			imponibileNettoRestante -= imponibileSingolo;
			imposta = imponibileSingolo * f.getAliquota();
			if (i == 0) {
				System.out.println("INSIDE");
				imposta -= fasce.getNoTaxArea() * f.getAliquota();
			}
			
			totImposta += imposta;
//			logger.append("Imponibile corrente= "+ currencyFormatter.format(imponibile)+", imposta = "+currencyFormatter.format(imposta)+", imponibile restante = "+currencyFormatter.format(reddito - imposta));
			System.out.println("Imponibile corrente= "+ currencyFormatter.format(imponibileSingolo+imponibileNettoRestante)+", imposta = "+currencyFormatter.format(imposta)+", imponibile restante = "+currencyFormatter.format(imponibileNettoRestante));
*/
	
	
	@Override
	public String getLog() {
		if (logger.isEmpty())
			throw new IllegalArgumentException("nullable logger");
		return logger.toString();
	}

}
