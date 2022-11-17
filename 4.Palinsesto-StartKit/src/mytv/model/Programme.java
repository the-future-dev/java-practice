package mytv.model;

import java.time.Duration;

public class Programme implements Comparable<Programme> {
	private String title;
	private Duration duration;

	public Programme(String title, Duration duration) {
		this.title = title;
		this.duration = duration;
	}

	public String getTitle() {
		return title;
	}

	public Duration getDuration() {
		return duration;
	}
	
	public String toString(){
		return this.getTitle() + " (" + duration.toMinutes() + " min)";
	}

	@Override
	public int compareTo(Programme that) {
		return this.getTitle().compareTo(that.getTitle());
	}
}
