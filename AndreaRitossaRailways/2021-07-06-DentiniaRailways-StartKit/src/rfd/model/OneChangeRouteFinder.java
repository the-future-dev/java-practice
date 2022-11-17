package rfd.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OneChangeRouteFinder extends RouteFinder {

	public OneChangeRouteFinder(Set<RailwayLine> rwylines) {
		super(rwylines);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Route> getRoutes(String from, String to) {
			List<Route> routes = new ArrayList<>();
			if (from.equals(to))
				return routes;
			if (from == null || to == null)
				throw new IllegalArgumentException("nullable input");
			
			Set<RailwayLine> partenzaStazioni = this.getLinesAtStation(from);
			
			for (RailwayLine railway : partenzaStazioni) {
				for (String point : railway.getTransferPoints()) {
					DirectRouteFinder drf = new DirectRouteFinder(rwylines);
					List<Route> mono = drf.getRoutes(point, to);
					if (mono.size() != 0) {
						Segment firstPath = railway.getSegment(from, point).get();
						Route r = new Route();
						r.add(firstPath);
						r.add(mono.get(0).getRoute());
						routes.add(r);
					}
				}
			}
			
			return routes;
	}

}