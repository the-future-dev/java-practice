package mytv.ui;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.SortedSet;

import mytv.model.Programme;
import mytv.model.ScheduledProgramme;

public interface Controller {

	boolean isScheduleCreated();

	void createScheduleStartingFrom(LocalDateTime startTime);

	SortedSet<Programme> getAllProgrammes();
	
	List<ScheduledProgramme> getCurrentProgrammeSchedule();
		
	Duration getTotalDuration();

	void addProgramme(Programme p);
	
	/**
	 * removes the specified Programme in the current schedule 
	 */
	ScheduledProgramme removeProgramme(int position);
}
