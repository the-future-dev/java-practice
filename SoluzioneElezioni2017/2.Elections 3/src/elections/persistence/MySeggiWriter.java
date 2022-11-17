package elections.persistence;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import elections.model.Elezioni;

public class MySeggiWriter implements SeggiWriter {

	private String outputFileName;

	public MySeggiWriter(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	@Override
	public void stampaSeggi(Elezioni elezioni, String msg) throws IOException {
		PrintWriter pw = new PrintWriter(outputFileName);
		pw.println(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
		pw.println(msg);
		pw.println(elezioni);
		pw.close();
	}

}
