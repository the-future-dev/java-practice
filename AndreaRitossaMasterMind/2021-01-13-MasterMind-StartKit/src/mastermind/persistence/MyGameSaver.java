package mastermind.persistence;

import java.io.PrintWriter;

import mastermind.model.Gioco;

public class MyGameSaver implements GameSaver {
	private PrintWriter pw;
	public MyGameSaver(PrintWriter pw) {
		this.pw = pw;
	}
	
	@Override
	public void save(Gioco gioco) {
		pw.println(gioco);
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		pw.close();
	}

}
