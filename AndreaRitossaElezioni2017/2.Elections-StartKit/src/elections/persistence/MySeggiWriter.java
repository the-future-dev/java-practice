package elections.persistence;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import elections.model.Elezioni;

public class MySeggiWriter implements SeggiWriter {

	private DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
	private String path;

	public MySeggiWriter(String path) throws IOException {
		this.path = path;
	}
	
/* */
	public String getPath() {
		return this.path;
	}
	
	@Override
	public void stampaSeggi(Elezioni risultato, String msg) throws IOException {
		PrintWriter f = new PrintWriter(new FileWriter(path));	
		LocalDateTime ora = LocalDateTime.now();
		f.println(formatter.format(ora)+"\n");
		f.println(msg);
		f.println(risultato);
		f.close();
	}
}
