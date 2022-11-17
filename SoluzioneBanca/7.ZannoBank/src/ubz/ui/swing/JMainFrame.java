package ubz.ui.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ubz.model.ImpossibleWithdrawException;
import ubz.model.Prelievo;
import ubz.model.Taglio;
import ubz.ui.controller.Controller;

public class JMainFrame extends JFrame
// NB: non Ë presente "implements ActionListener" perchÈ i listener sono realizzati come lambda expression, dunque come ascoltatori esterni
// Si puÚ naturalmente realizzarli in modo classico
{

	private static final long serialVersionUID = 1L;
	
	private Controller controller;
	private JTextField[] labels, labels2, disponibilit‡Fields, prelievoFields;
	private JTextField importoField;
	private JButton conferma;
	private JPanel panel;

	public JMainFrame(Controller controller) {
		this.controller = controller;
		this.setSize(470, 250);
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		labels = new JTextField[Taglio.values().length];
		labels2 = new JTextField[Taglio.values().length];
		disponibilit‡Fields = new JTextField[Taglio.values().length];
		prelievoFields = new JTextField[Taglio.values().length];
		importoField = new JTextField(10);
		importoField.setHorizontalAlignment(SwingConstants.CENTER);
		importoField.setFont(new Font(importoField.getFont().getFontName(),Font.BOLD,16));
		conferma = new JButton("Preleva");
		
		// -- predisposizione textfields e labels
		for (Taglio t : Taglio.values()){
			labels[t.ordinal()] = new JTextField("Ä " + t.getValore(),6);
			labels[t.ordinal()].setHorizontalAlignment(SwingConstants.CENTER);
			labels[t.ordinal()].setEditable(false);
			disponibilit‡Fields[t.ordinal()] = new JTextField(6);
			disponibilit‡Fields[t.ordinal()].setHorizontalAlignment(SwingConstants.CENTER);
			disponibilit‡Fields[t.ordinal()].setEditable(false);
			//
			labels2[t.ordinal()] = new JTextField("Ä " + t.getValore(), 6);
			labels2[t.ordinal()].setHorizontalAlignment(SwingConstants.CENTER);
			labels2[t.ordinal()].setEditable(false);
			prelievoFields[t.ordinal()] = new JTextField(6); 
			prelievoFields[t.ordinal()].setHorizontalAlignment(SwingConstants.CENTER);
			prelievoFields[t.ordinal()].setEditable(false);
		}
		Box cassaPane = Box.createVerticalBox();
		JPanel labelsPane = new JPanel(); labelsPane.setLayout(new GridLayout()); 
		JPanel dispoPane = new JPanel();  dispoPane.setLayout(new GridLayout()); 
		addAll(labelsPane, labels);
		addAll(dispoPane, disponibilit‡Fields);
		cassaPane.add(new JLabel("Tagli banconote e monete"));
		cassaPane.add(labelsPane);
		cassaPane.add(new JLabel("Quantit‡ disponibili in cassa"));
		cassaPane.add(dispoPane);
		panel.add(cassaPane, BorderLayout.NORTH);	
		aggiornaDisponibilit‡();
		
		Box importoPane = Box.createHorizontalBox();
		importoPane.setAlignmentX(CENTER_ALIGNMENT);
		importoPane.add(new JLabel("Importo da prelevare"));
		importoPane.add(importoField);
		importoPane.add(conferma);
		panel.add(importoPane, BorderLayout.CENTER);
		
		Box prelievoPane = Box.createVerticalBox();
		JPanel labelsPane2 = new JPanel(); labelsPane2.setLayout(new GridLayout());
		JPanel prelPane = new JPanel(); prelPane.setLayout(new GridLayout());
		
		addAll(labelsPane2,labels2);
		addAll(prelPane,prelievoFields);
		prelievoPane.add(new JLabel("Tagli banconote e monete"));
		prelievoPane.add(labelsPane2);
		prelievoPane.add(new JLabel("Prelievo"));
		prelievoPane.add(prelPane);
		panel.add(prelievoPane, BorderLayout.SOUTH);		
		
		conferma.addActionListener(event -> {
				try {
					Prelievo p = controller.preleva( Integer.parseInt(importoField.getText()) );
					aggiornaPrelievo(p);
				}
				catch(ImpossibleWithdrawException e){
					controller.alert("Errore", "Prelievo non possibile", e.getMessage());
				}
				aggiornaDisponibilit‡();
			});
		this.getContentPane().add(panel);
	}
	
	private void addAll(JPanel panel, Container[] components){
		for(Container c: components) panel.add(c); 
	}
	

	private void aggiornaPrelievo(Prelievo prelievo) {
		for (Taglio t : Taglio.values()){
			prelievoFields[t.ordinal()].setText(""+prelievo.getQuantit‡(t));
		}
	}
	
	private void aggiornaDisponibilit‡(){
		for (Taglio t : Taglio.values()){
			disponibilit‡Fields[t.ordinal()].setText(""+controller.getDisponibilit‡Attuale(t));
		}
	}
}