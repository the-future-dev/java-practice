package flightTracker.ui.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import flightTracker.model.Flight;
import flightTracker.model.FlightPos;
import flightTracker.model.Point;
import flightTracker.persistence.BadFileFormatException;
import flightTracker.persistence.MyFlightReader;

public class MyController extends Controller {

	public MyController(String[] availableFlightFiles) {
		super(availableFlightFiles);
	}

	@Override
	public List<Point> getPoints(Flight flight) {
		List<Point> points = new ArrayList<>();
		for (FlightPos position : flight.getPositions()) {
			Point p = position.getPosition();
			points.add(p);
		}
		return points;
	}

	@Override
	public Flight load(String flightId, Reader reader) throws IOException, BadFileFormatException {
		BufferedReader BR = new BufferedReader(reader);
		MyFlightReader fR = new MyFlightReader();
		return fR.readFlight(flightId, BR);
	}

}
