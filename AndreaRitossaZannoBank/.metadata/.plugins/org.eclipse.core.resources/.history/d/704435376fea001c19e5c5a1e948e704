package ubz.ui.swing;

import java.io.FileInputStream;

import ubz.model.CassiereUbz;
import ubz.persistence.DotazioneLoader;
import ubz.persistence.MyDotazioneLoader;
import ubz.ui.controller.Controller;

public class Program {
	
	public static void main(String[] args) {
		Controller controller = null;
		DotazioneLoader loader = new MyDotazioneLoader();
		SwingDialogManager dialogManager = new SwingDialogManager();
		try {
			loader.load(new FileInputStream("DotazioneIniziale.dat"));
			controller = new Controller(new CassiereUbz(loader), dialogManager);
		} catch (Exception e) {
			dialogManager.alert("Errore di I/O", "Errore nel caricamento file binario", e.getMessage());
		}
		JMainFrame mainFrame = new JMainFrame(controller);
		mainFrame.setVisible(true);
	}
	
}