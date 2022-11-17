package minirail.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import minirail.model.Gauge;
import minirail.model.Train;

public class MyConfigReader implements ConfigReader {
	private Gauge gauge;
	private String stringGauge;
	private List<Train> trains;
	
	public MyConfigReader(Reader reader) throws IOException, BadFileFormatException {
		if (reader == null ) throw new IOException("not a good file");
		BufferedReader br = new BufferedReader(reader);
		
		String scala = br.readLine();
		if (scala == null || scala.isEmpty()) throw new BadFileFormatException("NOT a good first line my man");
		String[] firstDiv = scala.split(" ");
		if (firstDiv.length != 2) throw new BadFileFormatException("Line not w 2 words");
		if ( !firstDiv[1].toLowerCase().trim().equals("gauge") ) throw new BadFileFormatException(""+firstDiv[1].toLowerCase()+"|");
		try {
			gauge = Gauge.valueOf(firstDiv[0]);
		} catch (IllegalArgumentException e) {
			throw new BadFileFormatException("");
		}
		if (gauge == null) throw new BadFileFormatException("Not a good gauge");
		
		trains = new ArrayList<>();
		String line; 
		while((line = br.readLine()) != null) {
//			IC583, 65 cm, 160 km/h
//			R2961, 68 cm, 140 km/h
			String[] divTrain = line.split(",");
			if (divTrain == null || divTrain.length != 3) throw new BadFileFormatException("Not 3 tokens on train reading");
			String name = divTrain[0];
			
			StringTokenizer len = new StringTokenizer(divTrain[1]);
			if (len.countTokens() != 2) throw new BadFileFormatException("Error on length reading: 1");
			
			double length;
			try{
				length = Double.parseDouble(len.nextToken().trim());
			} catch(NumberFormatException e ) {
				throw new BadFileFormatException("Error on length: 2");
			}
			String lengthUnit = len.nextToken().trim();
			if (!(lengthUnit.equals("cm") || lengthUnit.equals("in") )) {
				throw new BadFileFormatException("Error on length: 3");
			}
			
			StringTokenizer vel = new StringTokenizer(divTrain[2]);
			if (vel.countTokens() != 2) throw new BadFileFormatException("Error on speed reading: 1|"+vel.countTokens());
			
			double speed;
			try{
				speed = Double.parseDouble(vel.nextToken().trim());
			} catch(NumberFormatException e ) {
				throw new BadFileFormatException("Error on speed: 2");
			}
			String speedUnit = vel.nextToken().trim();
			if (!(speedUnit.equals("km/h")|| speedUnit.equals("mph") )) {
				throw new BadFileFormatException("Error on speed: 3");
			}
			
			trains.add(new Train(name, length, speed));
		}
	}
	
	@Override
	public List<Train> getTrains() {
		// TODO Auto-generated method stub
		return this.trains;
	}

	@Override
	public Gauge getGauge() {
		// TODO Auto-generated method stub
		return this.gauge;
	}

}
