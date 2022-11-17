package qa.ui.javafx;

import java.io.IOException;
import java.io.Reader;

import org.mockito.Mockito;

import qa.persistence.BadFileFormatException;
import qa.persistence.ReaderMock;
import qa.persistence.MisureReader;
import qa.ui.controller.Controller;

public class QualityApplicationMock extends QualityApplication {

	@Override
	protected Controller createController() {
		MisureReader sondaggiReader = new ReaderMock();		
		Reader r = Mockito.mock(Reader.class);
		try {
			return new Controller(sondaggiReader, r, this);
		} catch (IOException | BadFileFormatException e) {
			return null;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
