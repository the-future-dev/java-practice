package tris.model;

public class MyBoard implements Board {

	private char [][] board;
	private static final int DIM = 3;
	
	public MyBoard(char[][] arrayBi) {
		if (arrayBi.length != 3 && arrayBi[0].length != 3) {
			throw new IllegalArgumentException("Not a good array");
		}
		for (int i = 0;i<DIM;i++) {
			for (int j = 0; j<DIM; j++) {
				if (BoardValue.valueOf(""+arrayBi[i][j]) == null) {
					throw new IllegalArgumentException("not a good array");
				}
			}
		}
		board = arrayBi;
	}
	
	public MyBoard(String str) {
		char [] arrayMo = str.toCharArray();
		if (arrayMo.length != 9) {
			throw new IllegalArgumentException("Not a good array");
		}
		board = new char[DIM][DIM];
		for (int i = 0;i<DIM;i++) {
			for (int j = 0; j<DIM; j++) {
				if (BoardValue.valueOf(""+arrayMo[i+j]) == null) {
					throw new IllegalArgumentException("not a good array");
				} else {
					board[i][j] = arrayMo[i+j];					
				}
			}
		}
	}
	
	@Override
	public String getRow(int i) {
		String str = "";
		for (int j = 0; j<DIM; j++)
			str+=board[i][j];
		return str;
	}

	@Override
	public String getColumn(int i) {
		String str = "";
		for (int j = 0; j<DIM; j++)
			str+=board[j][i];
		return str;
	}

	@Override
	public String getDiagonal(int i) {
		if (i == 0) {
			String str = "";
			for (int j = 0; j<DIM; j++) {
				str+=board[j][j];
				System.out.println("["+i+","+j+"] = "+ board[j][j]);
			}
			return str;
		} else {
			String str = "";
			for (int j = DIM-1; j>=0; j--)
				str+=board[j][j];
			return str;
		}
	}

	@Override
	public boolean winning() {
		for (int i= 0; i<DIM; i++) {
			if (winningRow(i)) {
				return true;
			}
			if (winningColumn(i)) {
				return true;
			}
			if (i != 2 && winningDiagonal(i)){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean winningRow(int i) {
		return (getRow(i).equals("XXX") || getRow(i).equals("OOO"));
	}

	@Override
	public boolean winningColumn(int i) {
		return (getColumn(i).equals("XXX") || getColumn(i).equals("OOO"));
	}

	@Override
	public boolean winningDiagonal(int i) {
		return (getDiagonal(i).equals("XXX") || getDiagonal(i).equals("OOO"));
	}

	@Override
	public boolean adjacent(Board that) {
		if (distanceFrom(that) == 1) {
			for(BoardValue bV : BoardValue.values()) {
				int itemsA = 0;
				int itemsB = 0;
				for (int i = 0; i<DIM; i++) {
					for (int j = 0; j<DIM; j++) {
						if (that.getCellChar(i, j) == bV.get().charAt(0)) {
							itemsB++;
						}
						if (this.board[i][j] == bV.get().charAt(0)) {
							itemsA++;
						}
					}
				}
				itemsA = itemsA -itemsB;
				if (!(itemsA == 0 || itemsA == 1 || itemsA == -1)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public int distanceFrom(Board that) {
		int different = 0;
		for (int i =0; i<DIM; i++) {
			for (int j = 0; j<DIM; j++) {
				if (that.getCellChar(i, j) != this.board[i][j]) {
					different++;
				}
			}
		}
		return different;
	}
	
	@Override
	public char getCellChar(int i, int j) {
		return board[i][j];
	}
}
