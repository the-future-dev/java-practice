package mytv.ui;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import mytv.model.Programme;
import mytv.model.Schedule;
import mytv.model.ScheduledProgramme;

public class MyController implements Controller {
	private SortedSet allPrograms;
	
	private Schedule s;
	
	public MyController(SortedSet<Programme> allPrograms) {
		allPrograms = new TreeSet<>(allPrograms);
	}
	
	@Override
	public boolean isScheduleCreated() {
		return s != null;
	}

	@Override
	public void createScheduleStartingFrom(LocalDateTime startTime) {
		s = new Schedule(startTime);
	}

	@Override
	public SortedSet<Programme> getAllProgrammes() {
		return this.allPrograms;
	}

	@Override
	public List<ScheduledProgramme> getCurrentProgrammeSchedule() {
		return this.s.getScheduledProgrammes();
	}

	@Override
	public Duration getTotalDuration() {
		return Duration.between(s.getStartTime(), s.getEndTime());
	}

	@Override
	public void addProgramme(Programme p) {
		if (!isScheduleCreated() || p == null) {
			throw new IllegalArgumentException("Absurd");
		}
		s.add(p);
	}

	@Override
	public ScheduledProgramme removeProgramme(int position) {
		if (!isScheduleCreated() || position > s.getScheduledProgrammes().size()) {
			throw new IllegalArgumentException("Absurd");
		}
		return s.remove(position);
	}

}
