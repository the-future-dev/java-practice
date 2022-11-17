package mytv.util;

import java.time.Duration;

public abstract class DurationHelpers {

	public static String formatDuration(Duration d) {
		long hours = d.toHours();
		long minutes = d.toMinutes() - hours * 60;
		String result = hours + ":" + (minutes < 10 ? "0" : "") + minutes;
		return result;
	}
	
}
