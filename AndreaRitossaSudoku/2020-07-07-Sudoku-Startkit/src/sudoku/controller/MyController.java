package sudoku.controller;

import java.util.OptionalInt;

import sudoku.model.SudokuBoard;

public class MyController extends AbstractController {

	public MyController(SudokuBoard sud) {
		super(sud);
	}

	@Override
	public String getCellLabel(int row, int col) {
		OptionalInt w = this.sudoku.getCell(row, col);
		return w.isPresent() ? w.getAsInt()+"" : " ";
	}

	@Override
	public boolean setCell(int row, int col, String value) {
		if (value.equals(" ")) {
			return this.sudoku.setCell(row, col, 0);
		}
		int digit;
		try {
			digit = Integer.parseInt(value);
		} catch (Error e){
			return false;
		}
		return this.sudoku.setCell(row, col, digit);
	}

}