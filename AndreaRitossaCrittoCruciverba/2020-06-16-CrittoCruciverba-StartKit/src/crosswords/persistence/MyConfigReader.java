package crosswords.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.StringTokenizer;

import crosswords.model.Scheme;

public class MyConfigReader implements ConfigReader {
	private Scheme board;
	private int size;
	
	public MyConfigReader(Reader reader) throws IOException, BadFileFormatException {
		if (reader == null) throw new IOException("File not found");
		BufferedReader br = new BufferedReader(reader);
		
		String firstLine = br.readLine();
		
		if (firstLine == null) throw new BadFileFormatException("Not first Line");
		
		size = parseFirstLine(firstLine);
		board = parseOtherLines(br);
		br.close();
	}
	
	private int parseFirstLine(String line) throws BadFileFormatException {
		StringTokenizer stk = new StringTokenizer(line, " x");
		String s;
		if (stk.countTokens() != 3) throw new BadFileFormatException("Has "+stk.countTokens()+" tokens");
		if (!(s = stk.nextToken().trim()).equals("DIM"))
			throw new BadFileFormatException(s+" not DIM");
		
		int a, b;
		try {
			a = Integer.parseInt(stk.nextToken().trim());
			b = Integer.parseInt(stk.nextToken().trim());
		}catch(NumberFormatException e) {
			throw new BadFileFormatException("number");
		}
		
		if (a != b)
			throw new BadFileFormatException(a+" != "+b);
		
		return a;
	}
	
	private Scheme parseOtherLines(BufferedReader br) throws IOException, BadFileFormatException {
		int riga = 0;
		Scheme scheme = new Scheme(this.size);
		
		String line;
		while((line = br.readLine())!= null && riga < this.size) {
			String [] elements = line.split(" ");
			int [] numValues = new int[this.size];
			int colonna = 0;
			for (colonna = 0; colonna < elements.length && colonna <this.size; colonna++) {
				if(elements[colonna].equals("#")) {
					numValues[colonna] = 0;
				} else {
					try {
						System.out.print(elements[colonna]+"\t");
						numValues[colonna] = Integer.parseInt(elements[colonna]);
					} catch(NumberFormatException e) {
						throw new BadFileFormatException("Not a number");
					}
				}
			}
			
			if (colonna!= this.size)
				throw new BadFileFormatException("Not right size");
//			System.out.println("\n"+numValues.length+"\n");
			scheme.setCellRow(riga, numValues);
			riga++;
		}
		return scheme;
	}
	
	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public Scheme getScheme() {
		return this.board;
	}

}
