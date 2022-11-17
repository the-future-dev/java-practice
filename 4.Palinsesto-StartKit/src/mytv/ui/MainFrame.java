package mytv.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static Dimension labelCommonDimension = new Dimension(160, 20);
	private static EmptyBorder commonPanelBorder = new EmptyBorder(10, 10, 10, 10);
	
	private Controller controller;
	
	private DateTimeSpinner dateTimeSpinner;
	private JButton createButton;
	private SchedulePanel schedulePanel;

	public MainFrame(Controller controller) {
		super("MyTV - Gestione Palinsesto");
		this.controller = controller;
		initGUI();
		// bindData();
	}

	private void initGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 290);

		setLayout(new BorderLayout());

		JPanel pageStartPanel = new JPanel(new BorderLayout(10, 10));
		{
			pageStartPanel.setBorder(commonPanelBorder);
			
			JLabel label1 = new JLabel("Data/Ora inizio programmi:");
			label1.setPreferredSize(labelCommonDimension);
			pageStartPanel.add(label1, BorderLayout.LINE_START);

			dateTimeSpinner = new DateTimeSpinner();
			pageStartPanel.add(dateTimeSpinner, BorderLayout.CENTER);
			
			createButton = new JButton("Crea");
			createButton.addActionListener(this);
			pageStartPanel.add(createButton, BorderLayout.LINE_END);
		}
		add(pageStartPanel, BorderLayout.PAGE_START);
		
		schedulePanel = new SchedulePanel(this.controller);
		schedulePanel.setBorder(commonPanelBorder);
		schedulePanel.setEnabled(false);
		add(schedulePanel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		controller.createScheduleStartingFrom(dateTimeSpinner.getDateTimeValue());
		schedulePanel.setEnabled(true);
		createButton.setEnabled(false);
	}

}
