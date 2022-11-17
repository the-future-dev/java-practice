package rfd.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DirectRouteFinder extends RouteFinder {

	public DirectRouteFinder(Set<RailwayLine> rwylines) {
		super(rwylines);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Route> getRoutes(String from, String to) {
		List<Route> routes = new ArrayList<>();
		if (from == null || to == null)
			throw new IllegalArgumentException("nullable input");
		Set<RailwayLine> partenzaStazioni = this.getLinesAtStation(from);
		
		for (RailwayLine railway : partenzaStazioni) {
			for(String station : railway.getStations()) {
				if (station.equals(to) && railway.getSegment(from, to).isPresent()) {
					Route r = new Route();
					r.add(railway.getSegment(from, to).get());
					routes.add(r);
				}
			}
		}
		
		return routes;
	}

}
