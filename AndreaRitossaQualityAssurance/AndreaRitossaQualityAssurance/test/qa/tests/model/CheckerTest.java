package qa.tests.model;


import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

import qa.model.Misura;
import qa.model.WeightChecker;


public class CheckerTest {	
		
	private ArrayList<Misura> generaMisure() {
		ArrayList<Misura> misure = new ArrayList<Misura>();
		misure.add(new Misura("Spicchi di luna", 	250, 252));
		misure.add(new Misura("Penne rigate", 		400, 380));
		misure.add(new Misura("Spicchi di luna", 	250, 230));
		misure.add(new Misura("Penne rigate", 		400, 405));
		misure.add(new Misura("Spicchi di luna", 	250, 200));
		misure.add(new Misura("Patatone one one", 	160, 171));
		misure.add(new Misura("Spicchi di luna", 	250, 238));
		misure.add(new Misura("Penne rigate", 		400, 395));
		misure.add(new Misura("Spicchi di luna", 	250, 229));
		misure.add(new Misura("Patatone one one", 	160, 148));		
		return misure;
	}
	
	@Test
	public void testChecker() {
		WeightChecker checker = new WeightChecker(generaMisure());
		System.out.println(checker.printTabellaPercentuali());
		assertEquals(10, checker.getNumeroMisure());
		assertEquals(5, checker.getMisure("Spicchi di luna").size());
		assertEquals(3, checker.getMisure("Penne rigate").size());
		assertEquals(2, checker.getMisure("Patatone one one").size());
		assertEquals(1, checker.getListaMisureInRange("Spicchi di luna").size());
		assertEquals(2, checker.getListaMisureInRange("Penne rigate").size());
		assertEquals(1, checker.getListaMisureInRange("Patatone one one").size());
		assertEquals(0.2, checker.getPercentualeMisureInRange("Spicchi di luna"), 0.001);
		assertEquals(0.666, checker.getPercentualeMisureInRange("Penne rigate"), 0.001);
		assertEquals(0.5, checker.getPercentualeMisureInRange("Patatone one one"), 0.001);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void sceltaInesistente() {
		WeightChecker sp = new WeightChecker(generaMisure());
		sp.getPercentualeMisureInRange("XXX");
	}
	
}
