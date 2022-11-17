package ubz.ui.swing;

import java.io.InputStream;

import org.mockito.Mockito;

import ubz.model.CassiereUbz;
import ubz.persistence.DotazioneLoader;
import ubz.persistence.LoaderMock;
import ubz.ui.controller.Controller;

public class GUITest {
	public static void main(String[] args) {
		Controller controller = null;
		DotazioneLoader loader = new LoaderMock();
		SwingDialogManager dialogManager = new SwingDialogManager();
		try {
			loader.load(Mockito.mock(InputStream.class));
			controller = new Controller(new CassiereUbz(loader), dialogManager);
		} catch (Exception e) {
			dialogManager.alert("Errore di I/O", "Errore nel caricamento file binario", e.getMessage());
		}
		JMainFrame mainFrame = new JMainFrame(controller);
		mainFrame.setVisible(true);
	}
}
