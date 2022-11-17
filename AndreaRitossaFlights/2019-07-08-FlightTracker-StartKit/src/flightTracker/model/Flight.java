package flightTracker.model;

import java.time.Duration;
import java.util.List;

public class Flight {
	private String id;
	private List<FlightPos> tracks;
	
	public Flight(String id, List<FlightPos> ts) {
		if (id == null || ts == null ||id.equals("") || ts.size() == 0)
			throw new IllegalArgumentException("nullable input");
		this.id = id;
		this.tracks = ts;
	}
	
	public Duration getDuration() {
		FlightPos ini = tracks.get(0);
		FlightPos fin = tracks.get(tracks.size()-1);
		
		for (FlightPos f : tracks) {
			if (f.getTimestamp().compareTo(ini.getTimestamp()) < 0) {
				ini = f;
			}
			if (f.getTimestamp().compareTo(fin.getTimestamp()) > 0) {
				fin = f;
			}
		}
		
		return Duration.between(ini.getTimestamp(), fin.getTimestamp());
	}
	
	

	public String getId() {
		return id;
	}

	public List<FlightPos> getPositions() {
		return tracks;
	}
	
}
