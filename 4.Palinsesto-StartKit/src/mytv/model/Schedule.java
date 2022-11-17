package mytv.model;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Schedule {
	private LocalDateTime startTime;
	private List<ScheduledProgramme> programmes;
	
	public Schedule(LocalDateTime startTime) throws IllegalArgumentException{
		if (startTime == null) throw new IllegalArgumentException("Il tempo di inizio di una schedule non può essere nullo");
		
		this.startTime = startTime;
		programmes = new LinkedList<>();
	}

//	private void checkStartTime() {
//		LocalDateTime r = getStartTime();
//		for (ScheduledProgramme sp : programmes) {
//			if (sp.getEndTime().isBefore(r)) {
//				r = sp.getEndTime();
//			}
//		}
//		this.startTime = r;
//	}
	
	public void add(Programme p) {
		System.out.println("->");
		ScheduledProgramme w = new ScheduledProgramme(p, getEndTime());
		System.out.println("->"+w);
		programmes.add(w);
	}
	
	public ScheduledProgramme remove(int index) {
		if (index > programmes.size()) {
			System.out.println("NO bro");
		}
		for (int i = index; i < programmes.size()-1; i++) {
			programmes.get(i+1).setStartTime(
				programmes.get(i).getStartTime()
			);
		}
		
		return programmes.remove(index);
	}
	
	public LocalDateTime getEndTime() {
		LocalDateTime r = getStartTime();
		for (ScheduledProgramme sp : programmes) {
			if (sp.getEndTime().isAfter(r)) {
				r = sp.getEndTime();
			}
		}
		return r;
	}

	public LocalDateTime getStartTime() {
		return this.startTime;
	}
	
	public List<ScheduledProgramme> getScheduledProgrammes(){
		return this.programmes;
	}
	
	
}
