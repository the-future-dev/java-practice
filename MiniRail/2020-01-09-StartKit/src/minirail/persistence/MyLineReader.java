package minirail.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import minirail.model.Line;
import minirail.model.Section;

public class MyLineReader implements LineReader {
	private Line line;
	private String lineKeyWord;
	private String lineName;
	private List<Section> sections;
	
	public MyLineReader(Reader reader) throws IOException, BadFileFormatException {
		if (reader == null) throw new IOException("null reader");
		BufferedReader br = new BufferedReader(reader);
		
		String line = br.readLine();
		String[] first = line.split(" ");
		if (!first[0].toLowerCase().equals("line") || first.length != 2) throw new BadFileFormatException("1");
		lineName = first[1];
		
		sections = new ArrayList<>();
		while((line = br.readLine())!= null) {
			StringTokenizer stk = new StringTokenizer(line);
			if (stk.countTokens() != 3 || !stk.nextToken().toLowerCase().equals("section"))
				throw new BadFileFormatException("2");
			String id = stk.nextToken().trim();
			
			double length;
			try{
				length = Double.parseDouble(stk.nextToken().trim());
			} catch(NumberFormatException e ) {
				throw new BadFileFormatException("3");
			}
			sections.add(new Section(id, length));
		}
		
		this.line = new Line(lineName, sections);
	}
	
	@Override
	public Line getLine() {
		return this.line;
	}
}
