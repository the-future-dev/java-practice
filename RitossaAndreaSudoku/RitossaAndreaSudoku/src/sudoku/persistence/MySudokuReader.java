package sudoku.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.StringTokenizer;

import sudoku.model.SudokuBoard;

public class MySudokuReader implements SudokuReader {
	private SudokuBoard sudoku;
	
	@Override
	public SudokuBoard getSudoku() {
		return this.sudoku;
	}
	
	public MySudokuReader(Reader rdr) throws IOException, BadFileFormatException {
		if (rdr == null)
			throw new IllegalArgumentException("Nullable");
		BufferedReader br = new BufferedReader(rdr);
		sudoku = parseGrid(br);
	}
	
	private SudokuBoard parseGrid(BufferedReader myBr) throws IOException, BadFileFormatException {
		SudokuBoard insideSudoku = new SudokuBoard();
		int dim = insideSudoku.getSize();
		int rows = 0;
		String line = "";
		while(((line = myBr.readLine())!= null)) {
			if ((rows) >= dim)
				throw new BadFileFormatException("Numero di righe errato: eccesso");
			StringTokenizer tokenized = new StringTokenizer(line);
			if (tokenized.countTokens() != dim)
				throw new BadFileFormatException("Numero di items errato");
			int [] values = new int[dim];
			for (int i = 0; i< dim; i++) {
				String tok = tokenized.nextToken().trim();
				if (tok.length() != 1 || !((tok.charAt(0)>= '1' && tok.charAt(0) <= '9') || tok.charAt(0)== '#') )
					throw new BadFileFormatException("Bad token");
				else if (tok.charAt(0)== '#') {
					values[i] = 0;
				} else
					values[i] = Integer.parseInt(tok);
			}
			insideSudoku.setCellRow(rows, values);
			rows++;
		}
		if (rows < dim)
			throw new BadFileFormatException("Numero di righe errato: difetto");
		return insideSudoku;
	}
}
