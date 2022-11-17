package qa.ui.swing;

import java.io.IOException;
import java.io.Reader;

import org.mockito.Mockito;

import qa.persistence.BadFileFormatException;
import qa.persistence.ReaderMock;
import qa.persistence.MisureReader;
import qa.ui.controller.Controller;

public class GUITest {
	public static void main(String[] args) throws IOException, BadFileFormatException {
		MisureReader mReader = new ReaderMock();	
		Reader r = Mockito.mock(Reader.class);
		Controller controller = new Controller(mReader, r, new SwingDialogManager());

		JMainFrame mainFrame = new JMainFrame(controller);
		mainFrame.setVisible(true);
	}
}
