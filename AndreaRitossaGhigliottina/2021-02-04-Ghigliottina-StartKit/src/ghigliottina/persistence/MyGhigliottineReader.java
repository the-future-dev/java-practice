package ghigliottina.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ghigliottina.model.Esatta;
import ghigliottina.model.Ghigliottina;
import ghigliottina.model.Terna;

public class MyGhigliottineReader implements GhigliottineReader {
	private List<Ghigliottina> ghigliottine;
	
	@Override
	public List<Ghigliottina> getGhigliottine() {
		// TODO Auto-generated method stub
		return this.ghigliottine;
	}

	@Override
	public List<Ghigliottina> readAll(BufferedReader BR) throws IOException, BadFileFormatException {
		if (BR == null)
			throw new IOException("Null Reader");
		List<Ghigliottina> ghiglioList = new ArrayList<>();
		Ghigliottina g;
		while((g = parseOne(BR))!=null) {
			ghiglioList.add(g);
		}
//		System.out.println(ghiglioList.size());
		return ghiglioList;
	}
	
	private Ghigliottina parseOne(BufferedReader BR) throws IOException, BadFileFormatException {
		Ghigliottina g;
		List<Terna> l = new ArrayList<>();
		String line;
		String [] splittedLine = null;
		while((line = BR.readLine())!= null
				&& !(splittedLine = line.split("="))[0].trim().equals("Risposta esatta") ){
//			System.out.println(splittedLine[0]);
			Terna t;
			try {
				String [] firstSecond = splittedLine[0].split("/");
				Esatta e = Esatta.valueOf(splittedLine[1].trim());
				t = new Terna(firstSecond[0].trim(), firstSecond[1].trim(), e);
			}catch( IllegalArgumentException e) {
				throw new BadFileFormatException(e);
			}catch (ArrayIndexOutOfBoundsException e2) {
				throw new BadFileFormatException(e2);
			}
			l.add(t);
		}
		String newLine = BR.readLine();
		if (line != null && (newLine == null|| !newLine.contains("-"))) {
			throw new BadFileFormatException("Not good end line");
		}
		if (line == null) {
			return null;
		} else {
			splittedLine = line.split("=");
			String re = splittedLine[1].trim();
			g = new Ghigliottina(l, re);
			return g;
		}
	}
	
//	public static void main(String[] args) {	
//	}
}
