package ubz.ui.javafx;

import java.io.InputStream;

import org.mockito.Mockito;

import ubz.model.CassiereUbz;
import ubz.persistence.DotazioneLoader;
import ubz.persistence.LoaderMock;
import ubz.ui.controller.Controller;

public class UBZApplicationMock extends UBZApplication {

	@Override
	protected Controller createController() {
		DotazioneLoader loader = new LoaderMock();		
		try {
			loader.load(Mockito.mock(InputStream.class));
			return new Controller(new CassiereUbz(loader), this);
		} catch (Exception e) {
			alert("Errore di I/O", "Errore nel caricamento file binario", e.getMessage());
			return null;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
