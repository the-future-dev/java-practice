package battleship.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import battleship.model.ShipBoard;

public class MyBattleshipReader implements BattleshipReader {
	private ShipBoard playerBoard;
	private ShipBoard solutionBoard;
	
	@Override
	public ShipBoard getSolutionBoard(Reader reader) throws BadFileFormatException, IOException {
		// TODO Auto-generated method stub
		if (solutionBoard == null) {
			solutionBoard = readBoard(reader, "o<>^vxo~ ");
			System.out.println(solutionBoard);
		}
		return this.solutionBoard;
	}

	@Override
	public ShipBoard getPlayerBoard(Reader reader) throws BadFileFormatException, IOException {
		// TODO Auto-generated method stub
		if (playerBoard == null) {
			playerBoard = readBoard(reader, "o<>^vxo# ");
			System.out.println(playerBoard);
		}
		return this.playerBoard;
	}
	
	protected ShipBoard readBoard(Reader reader, String admissibleChars) throws BadFileFormatException, IOException {
		if (reader == null)
			throw new IllegalArgumentException("nullable reader");
		BufferedReader BR = new BufferedReader(reader);
		
		ShipBoard board= new ShipBoard();
		
		String line;
		int nRows = 0;
		while((line = BR.readLine())!= null) {
			char[] arrLine = line.toCharArray();
			for (char c : arrLine)
				if (!admissibleChars.contains(c+""))
					throw new BadFileFormatException("Carattere non ammissibile: "+c);
			
			if((nRows++) > 8)	throw new BadFileFormatException("not 8 rows");
			board.setCellRow(nRows-1, line);
		}
		return board;
	}

}