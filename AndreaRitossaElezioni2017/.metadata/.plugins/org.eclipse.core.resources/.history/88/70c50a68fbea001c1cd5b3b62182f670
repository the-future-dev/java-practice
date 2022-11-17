package elections.ui.swing;

import java.io.IOException;

import elections.persistence.BadFileFormatException;
import elections.persistence.SeggiWriter;
import elections.persistence.VotiReader;
import elections.tests.ui.SeggiWriterMock;
import elections.tests.ui.VotiReaderMock;
import elections.ui.controller.Controller;
import elections.ui.controller.MyController;

public class GUITest {
	public static void main(String[] args) throws IOException, BadFileFormatException {
		VotiReader votiReader = new VotiReaderMock();
		SeggiWriter seggiWriter = new SeggiWriterMock();		
		Controller controller = new MyController(votiReader, seggiWriter);

		JMainFrame mainFrame = new JMainFrame(controller);
		mainFrame.setVisible(true);
	}
}
