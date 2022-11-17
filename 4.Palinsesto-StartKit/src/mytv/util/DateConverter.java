package mytv.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateConverter {
	public static LocalDate asLocalDate(java.util.Date date) {
		return asLocalDate(date, ZoneId.systemDefault());
	}

	public static LocalDateTime asLocalDateTime(java.util.Date date) {
		return asLocalDateTime(date, ZoneId.systemDefault());
	}

	public static LocalDate asLocalDate(java.util.Date date, ZoneId zone) {
		if (date == null)
			return null;

		return Instant.ofEpochMilli(date.getTime()).atZone(zone).toLocalDate();
	}

	public static LocalDateTime asLocalDateTime(java.util.Date date, ZoneId zone) {
		if (date == null)
			return null;

		return Instant.ofEpochMilli(date.getTime()).atZone(zone).toLocalDateTime();
	}
}
