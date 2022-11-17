package qa.ui.swing;

import java.io.FileReader;
import java.io.Reader;

import qa.persistence.MyMisureReader;
import qa.persistence.MisureReader;
import qa.ui.controller.Controller;

public class Program {
	public static void main(String[] args) {
		Controller controller = null;
		try(Reader reader = new FileReader("Misure.txt")) {
			MisureReader mReader = new MyMisureReader();			
			controller = new Controller(mReader, reader, new SwingDialogManager());

			JMainFrame mainFrame = new JMainFrame(controller);
			mainFrame.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}