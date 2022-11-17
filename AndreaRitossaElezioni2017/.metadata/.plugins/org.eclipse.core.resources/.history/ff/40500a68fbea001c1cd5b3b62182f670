package elections.ui.swing;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import elections.model.Elezioni;
import elections.model.Partito;

public class JElectionPane extends JScrollPane {
	private static final long serialVersionUID = 1L;

	private JTable table;

	public JElectionPane(Elezioni elezioni) {
		Object[] titoliColonne = { "Partito", "Voti", "Seggi" };
		Object[][] rowData = new Object[elezioni.getPartiti().size()][3];
		int i = 0;
		for (Partito p : elezioni.getPartiti()) {
			rowData[i][0] = p.getNome();
			rowData[i][1] = elezioni.getVoti(p);
			rowData[i][2] = elezioni.getRisultato().getSeggi(p);
			i++;
		}
		table = new JTable(rowData, titoliColonne);
		getViewport().removeAll();
		getViewport().add(table, null);
		setPreferredSize(new Dimension(350, 120));
		((JLabel) table.getDefaultRenderer(Object.class)).setHorizontalAlignment(SwingConstants.CENTER);
	}

	public void update(Elezioni elezioni) {		
		int i = 0;
		for (Partito p : elezioni.getPartiti()) {
			table.setValueAt(p.getNome(), i, 0);
			table.setValueAt(elezioni.getVoti(p), i, 1);
			table.setValueAt(elezioni.getRisultato().getSeggi(p), i, 2);
			i++;
		}
	}
}
