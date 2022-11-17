package mytv;


import java.io.FileReader;
import java.util.SortedSet;

import mytv.model.*;
import mytv.persistence.*;
import mytv.ui.Controller;
import mytv.ui.MainFrame;
import mytv.ui.MyController;

public class Program
{
	public static void main(String[] args)
	{
		Controller controller = null;

		try {
			ProgrammeReader myreader = new MyProgrammeReader(); 
			SortedSet<Programme> tutti;
			try (FileReader fileReader = new FileReader("Programmes.txt")) {
				tutti = myreader.readAll(fileReader);
			}
			controller = new MyController(tutti);
			
			MainFrame mainFrame = new MainFrame(controller);
			mainFrame.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}