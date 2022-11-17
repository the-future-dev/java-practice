package qa.persistence;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import qa.model.Misura;

public class ReaderMock implements MisureReader {
	private final static int campioni = 31;
	
	public static List<Misura> generaMisureFarlocche() {
		String[] nomiProdotti = {
				"Spicchi di luna",
				"Penne rigate",
				"Patatone one one"
		};
		double[] pesiProdotti = {
				250, 400, 160
		};
		int delta = 30; // -delta, quindi da pesoAtteso-delta a pesoAtteso+delta
		Random deltaGenerator = new Random();
		Random selezionatoreProdotti = new Random();
		List<Misura> list = new ArrayList<>();
		for (int i=0; i<campioni; i++){
			int index = selezionatoreProdotti.nextInt(nomiProdotti.length);
			list.add(new Misura(
					nomiProdotti[index],
					pesiProdotti[index],
					pesiProdotti[index]+deltaGenerator.nextInt(delta)-delta/2));
		}
		return list;
	}
	
	@Override
	public List<Misura> leggiMisure(Reader r) throws IOException, BadFileFormatException {
		return generaMisureFarlocche();
	}

}
