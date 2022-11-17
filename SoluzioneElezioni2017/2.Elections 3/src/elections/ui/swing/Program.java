package elections.ui.swing;

import java.io.FileReader;
import java.io.Reader;

import elections.persistence.MySeggiWriter;
import elections.persistence.MyVotiReader;
import elections.persistence.SeggiWriter;
import elections.persistence.VotiReader;
import elections.ui.controller.Controller;
import elections.ui.controller.MyController;

public class Program {
	public static void main(String[] args) {
		Controller controller = null;
		try(Reader reader = new FileReader("RisultatiElezioni.txt")) {
			VotiReader votiReader = new MyVotiReader(reader);
			SeggiWriter seggiWriter = new MySeggiWriter("Report.txt");			
			
			controller = new MyController(votiReader, seggiWriter);

			JMainFrame mainFrame = new JMainFrame(controller);
			mainFrame.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}