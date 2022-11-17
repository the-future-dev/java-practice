package elections.ui.javafx;

import java.io.IOException;

import elections.persistence.BadFileFormatException;
import elections.persistence.SeggiWriter;
import elections.persistence.VotiReader;
import elections.tests.ui.SeggiWriterMock;
import elections.tests.ui.VotiReaderMock;
import elections.ui.controller.Controller;
import elections.ui.controller.MyController;

public class ElectionApplicationMock extends ElectionApplication {

	@Override
	protected Controller createController() {
		VotiReader votiReader = new VotiReaderMock();
		SeggiWriter seggiWriter = new SeggiWriterMock();		
		try {
			return new MyController(votiReader, seggiWriter);
		} catch (IOException | BadFileFormatException e) {
			return null;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
