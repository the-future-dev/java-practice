package minesweeper.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class MyConfigReader implements ConfigReader {
	private int mines = 0;
	private int size = 0;
	
	public MyConfigReader (Reader reader) throws IOException, BadFileFormatException {
		BufferedReader breader = new BufferedReader(reader);
		String l1 = breader.readLine();
		String l2 = breader.readLine();
		if (breader.readLine() != null || l1 == null || l2 == null) {
			throw new BadFileFormatException("Non di due linee");
		}
		String [] l1Tokens = l1.split(":");
		String [] l2Tokens = l2.split(":");
		
		if (l1Tokens.length != 2 || l2Tokens.length != 2 ) {
			throw new BadFileFormatException("Non due tokens");
		}
		for (int i = 0; i<l1Tokens.length; i++) {
			l1Tokens[i] = l1Tokens[i].trim();
			l2Tokens[i] = l2Tokens[i].trim();
		}
		int number1, number2;
		try {
			number1 = Integer.parseInt(l1Tokens[1]);
			number2 = Integer.parseInt(l2Tokens[1]);	
		} catch (NumberFormatException e) {
			throw new BadFileFormatException("not a number");
		}
		
		int count = 0;
		if (l1Tokens[0].toUpperCase().equals("MINES")) {
			mines = number1;
			count = count+1;
		}
		if (l1Tokens[0].toUpperCase().equals("SIZE")) {
			size = number1;
			count = count+1;
		}
		if (l2Tokens[0].toUpperCase().equals("MINES")) {
			mines = number2;
			count = count+1;
		}
		if (l2Tokens[0].toUpperCase().equals("SIZE")) {
			size = number2;
			count = count+1;
		}
		if (count != 2) {
			throw new BadFileFormatException("Non hai dato bene size e mines");
		}
	}
	
	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public int getMinesNumber() {
		return this.mines;
	}

}
