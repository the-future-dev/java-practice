package mytv.ui;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import mytv.model.ScheduledProgramme;

public class ScheduleList extends JList<ScheduledProgramme> {

	private static final long serialVersionUID = 1L;

	public void load(List<ScheduledProgramme> programmes) {
		DefaultListModel<ScheduledProgramme> model = new DefaultListModel<>();
		for (ScheduledProgramme prog : programmes) {
			model.addElement(prog);
		}
		setModel(model);
	}
}
