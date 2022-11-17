package sudoku.controller;

import java.util.OptionalInt;

import sudoku.model.SudokuBoard;

public class MyController extends AbstractController {

	public MyController(SudokuBoard sud) {
		super(sud);
	}

	@Override
	public String getCellLabel(int row, int col) {
		OptionalInt val = this.sudoku.getCell(row, col);
		return (val.isEmpty())? " " : val.getAsInt()+"";
	}

	@Override
	public boolean setCell(int row, int col, String value) {
		int v;
		try {
			if (value.equals(" ")) {
				return this.sudoku.setCell(row, col, 0);
			} else {
				v = Integer.parseInt(value);
				return this.sudoku.setCell(row, col, v);
			}
		} catch(NumberFormatException e) {
			return false;
		}
	}

}
