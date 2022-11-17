package mytv.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import mytv.util.DurationHelpers;

public class ScheduledProgramme {
	private final static DateTimeFormatter formatter = DateTimeFormatter
			.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT)
			.withLocale(Locale.ITALY);
	
	private Programme programme;
	private LocalDateTime startTime;

	public ScheduledProgramme(Programme programme, LocalDateTime startTime) {
		if (programme == null)
			throw new IllegalArgumentException("programme");
		if (startTime == null)
			throw new IllegalArgumentException("startTime");
		
		this.programme = programme;
		this.startTime = startTime;
	}
	
	public Programme getProgramme() {
		return programme;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}
	
	public void setStartTime(LocalDateTime value) {
		if (startTime == null)
			throw new IllegalArgumentException("startTime");
		
		this.startTime = value;
	}
	
	public LocalDateTime getEndTime() {
		return getStartTime().plus(getProgramme().getDuration());
	}
	
	@Override
	public String toString() {
		return getProgramme().getTitle() + " - " + formatter.format(getStartTime()) 
				+ " (" + DurationHelpers.formatDuration(getProgramme().getDuration()) + ")";
	}
}
