package mytv.ui;

import java.time.LocalDateTime;
import java.util.Date;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import mytv.util.DateConverter;

public class DateTimeSpinner extends JSpinner {

	private static final long serialVersionUID = 1L;

	public DateTimeSpinner() {
		super(new SpinnerDateModel());
		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(this,
				"E dd/MM/yyyy HH:mm");
		setEditor(dateEditor);

	}

	public LocalDateTime getDateTimeValue() {
		return DateConverter.asLocalDateTime((Date) getValue());
	}

}
