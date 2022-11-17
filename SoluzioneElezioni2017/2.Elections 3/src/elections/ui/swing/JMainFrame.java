package elections.ui.swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.WindowConstants;

import elections.model.Elezioni;
import elections.ui.controller.Controller;

public class JMainFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private Controller controller;
	private JPanel mainPanel;
	private JElectionPane electionTable;
	private JButton calcola, save;
	private JSlider sbarramento;

	public JMainFrame(Controller controller) {
		this.controller = controller;
		initGUI();
		setSize(400, 280);
	}

	private void initGUI() {

		mainPanel = new JPanel();
		getContentPane().add(mainPanel);

		setTitle("Elezioni di Dentinia");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JLabel label = new JLabel("Elezioni Dentinia - Assegnazione Seggi");
		mainPanel.add(label, BorderLayout.NORTH);

		electionTable = new JElectionPane(controller.ricalcola(0));
		mainPanel.add(electionTable, BorderLayout.CENTER);

		JPanel commandPanel = new JPanel();
		commandPanel.setLayout(new GridLayout(2, 1));
		Box riga1 = new Box(BoxLayout.LINE_AXIS);
		riga1.add(new JLabel("Sbarramento %"));
		sbarramento = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
		sbarramento.setMajorTickSpacing(1);
		sbarramento.setPaintTicks(true);
		sbarramento.setPaintLabels(true);
		riga1.add(sbarramento);
		Box riga2 = new Box(BoxLayout.LINE_AXIS);
		calcola = new JButton("Calcola");
		calcola.addActionListener(this);
		riga2.add(Box.createHorizontalStrut(40));
		riga2.add(calcola);
		riga2.add(Box.createHorizontalStrut(40));
		save = new JButton("Salva");
		save.addActionListener(this);
		riga2.add(save);
		commandPanel.add(riga1);
		commandPanel.add(riga2);
		mainPanel.add(commandPanel, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == save) {
			try {
				controller.salvaSuFile("Proporzionale con sbarramento del " + sbarramento.getValue() + "%");
				save.setEnabled(false);
			} catch (IOException e1) {
				System.err.println(e1);
				return;
			}
		} else if (e.getSource() == calcola) {
			Elezioni elezioni = controller.ricalcola(sbarramento.getValue() / 100.0);
			electionTable.update(elezioni);
			save.setEnabled(true);
		}
	}

}
