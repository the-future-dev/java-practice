package dentinia.governor.persistence;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Prova {
	public static void main(String[] args) {
		DateTimeFormatter formatterFull = DateTimeFormatter
				.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM)
				.withLocale(Locale.ITALY);
		ZonedDateTime t = ZonedDateTime.now();
		System.out.println(t.format(formatterFull));
	}
}
