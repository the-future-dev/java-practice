package flightTracker.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
//import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
//import java.util.Locale;
import java.util.StringTokenizer;

import flightTracker.model.Flight;
import flightTracker.model.FlightPos;

public class MyFlightReader implements FlightReader {

//	UTC;Position;Altitude;Speed;Direction
//	2019-05-10T10:54:39Z;45.661972,8.726303;1975;183;356
//	private DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
//			.withLocale(Locale.ITALY);
	@Override
	public Flight readFlight(String id, BufferedReader reader) throws IOException, BadFileFormatException {
		if (id == null|| id.equals("") || reader == null)
			throw new IllegalArgumentException("nullable input");
		String line = reader.readLine();
		StringTokenizer stk = new StringTokenizer(line,";");
		if (!(stk.nextToken().equals("UTC")
				&& stk.nextToken().equals("Position")
				&& stk.nextToken().equals("Altitude")
				&& stk.nextToken().equals("Speed")
				&& stk.nextToken().equals("Direction")
				)) {
			throw new BadFileFormatException("Not good first line");
		}
		List<FlightPos> tracks = new ArrayList<>();

		while((line = reader.readLine())!= null) {
			stk = new StringTokenizer(line, ";");
			try {
				ZonedDateTime timeStamp = ZonedDateTime.parse(stk.nextToken(), DateTimeFormatter.ISO_DATE_TIME);
				String latitudieELongitudine = stk.nextToken();
				String [] latLong = latitudieELongitudine.split(",");
				if (latLong.length != 2)
					throw new BadFileFormatException("Not good lat long");
			
				double latitudine = Double.parseDouble(latLong[0]);
				double longitudine = Double.parseDouble(latLong[1]);
				double altitudine = Double.parseDouble(stk.nextToken());
				double speed = Double.parseDouble(stk.nextToken());
				if (altitudine != 0 && speed == 0)
					throw new BadFileFormatException("Error in speed logic");
				int direction = Integer.parseInt(stk.nextToken());	
				tracks.add(new FlightPos(timeStamp, latitudine, longitudine, altitudine, speed, direction));
			} catch(NumberFormatException e) {
				throw new BadFileFormatException("Error in parsing number");
			} catch(DateTimeParseException e1) {
				throw new BadFileFormatException("Error in parsing date");
			}

			
//			FlightPos fp = new FlightPos(timeStamp, )
		}
		
		return new Flight(id, tracks);
	}
	
//	private void validate() {
//		
//	}

}
