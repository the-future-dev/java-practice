package ubz.tests.model;


import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.junit.Test;

import ubz.model.Cassiere;
import ubz.model.CassiereUbz;
import ubz.model.ImpossibleWithdrawException;
import ubz.model.Prelievo;
import ubz.model.Taglio;
import ubz.persistence.LoaderMock;


public class CassiereTest {	
		
	@SuppressWarnings("serial")
	private ArrayList<Prelievo> generaPrelievi() {
		ArrayList<Prelievo> prelievi = new ArrayList<Prelievo>();
		
		Prelievo p307a = new Prelievo( // 0
				new TreeMap<Taglio,Integer>() {{ 
					put(Taglio.CINQUECENTO, 0); put(Taglio.DUECENTO, 1); 	put(Taglio.CENTO,  1); 	put(Taglio.CINQUANTA, 0); 
					put(Taglio.VENTI, 0);		put(Taglio.DIECI, 0);		put(Taglio.CINQUE, 1); 	put(Taglio.DUE, 1); put(Taglio.UNO, 0);
				}} );
		Prelievo p307b = new Prelievo( // 1
				new TreeMap<Taglio,Integer>() {{ 
					put(Taglio.CINQUECENTO, 0); put(Taglio.DUECENTO, 0); 	put(Taglio.CENTO,  3); 	put(Taglio.CINQUANTA, 0); 
					put(Taglio.VENTI, 0);		put(Taglio.DIECI, 0);		put(Taglio.CINQUE, 1); 	put(Taglio.DUE, 1); put(Taglio.UNO, 0);
				}} );
		Prelievo p7000 = new Prelievo( // 2
				new TreeMap<Taglio,Integer>() {{ 
					put(Taglio.CINQUECENTO, 5); put(Taglio.DUECENTO, 22); 	put(Taglio.CENTO,  1); 	put(Taglio.CINQUANTA, 0); 
					put(Taglio.VENTI, 0);		put(Taglio.DIECI, 0);		put(Taglio.CINQUE, 0); 	put(Taglio.DUE, 0); put(Taglio.UNO, 0);
				}} );
		Prelievo p12000 = new Prelievo( // 3
				new TreeMap<Taglio,Integer>() {{ 
					put(Taglio.CINQUECENTO, 0); put(Taglio.DUECENTO, 0); 	put(Taglio.CENTO, 23); 	put(Taglio.CINQUANTA, 194); 
					put(Taglio.VENTI, 0);		put(Taglio.DIECI, 0);		put(Taglio.CINQUE, 0); 	put(Taglio.DUE, 0); put(Taglio.UNO, 0);
				}} );
		Prelievo p526a = new Prelievo( // 4
				new TreeMap<Taglio,Integer>() {{ 
					put(Taglio.CINQUECENTO, 0); put(Taglio.DUECENTO, 0); 	put(Taglio.CENTO,  0); 	put(Taglio.CINQUANTA, 6); 
					put(Taglio.VENTI, 11);		put(Taglio.DIECI, 0);		put(Taglio.CINQUE, 1); 	put(Taglio.DUE, 0); put(Taglio.UNO, 1);
				}} );
		Prelievo p526b = new Prelievo( // 5
				new TreeMap<Taglio,Integer>() {{ 
					put(Taglio.CINQUECENTO, 0); put(Taglio.DUECENTO, 0); 	put(Taglio.CENTO,  0); 	put(Taglio.CINQUANTA, 6); 
					put(Taglio.VENTI, 10);		put(Taglio.DIECI, 2);		put(Taglio.CINQUE, 1); 	put(Taglio.DUE, 0); put(Taglio.UNO, 1);
				}} );
		Prelievo p276 = new Prelievo( // 6
				new TreeMap<Taglio,Integer>() {{ 
					put(Taglio.CINQUECENTO, 0); put(Taglio.DUECENTO, 0); 	put(Taglio.CENTO,  0); 	put(Taglio.CINQUANTA, 0); 
					put(Taglio.VENTI, 13);		put(Taglio.DIECI, 1);		put(Taglio.CINQUE, 1); 	put(Taglio.DUE, 0); put(Taglio.UNO, 1);
				}} );
		Prelievo p277 = new Prelievo( // 7
				new TreeMap<Taglio,Integer>() {{ 
					put(Taglio.CINQUECENTO, 0); put(Taglio.DUECENTO, 0); 	put(Taglio.CENTO,  0); 	put(Taglio.CINQUANTA, 0); 
					put(Taglio.VENTI, 10);		put(Taglio.DIECI, 5);		put(Taglio.CINQUE, 3); 	put(Taglio.DUE, 4); put(Taglio.UNO, 4);
				}} );

		prelievi.add(p307a);
		prelievi.add(p7000);
		prelievi.add(p307a);
		prelievi.add(p307a);
		prelievi.add(p307b);
		prelievi.add(p12000);
		prelievi.add(p526a); // output di Cassiere base
		prelievi.add(p526b); // output di ZannoCassiere
		prelievi.add(p276); // 276 è il caso limite accettato da Ubz
		prelievi.add(p277); // 277 non passa più in Ubz (ma passa nel Cassiere base)
		
		return prelievi;
	}
	
//	@Test
//	public void testCassiereBaseCon276() throws ImpossibleWithdrawException {
//		Cassiere cassiere = new Cassiere(new LoaderMock());
//		List<Prelievo> prelievi = generaPrelievi();
//		assertEquals(prelievi.get(0), cassiere.preleva(307));
//		assertEquals(prelievi.get(1), cassiere.preleva(7000));
//		assertEquals(prelievi.get(2), cassiere.preleva(307));
//		assertEquals(prelievi.get(3), cassiere.preleva(307));
//		assertEquals(prelievi.get(4), cassiere.preleva(307));
//		assertEquals(prelievi.get(5), cassiere.preleva(12000));
//		assertEquals(prelievi.get(6), cassiere.preleva(526));
//		assertEquals(prelievi.get(8), cassiere.preleva(276));
//	}
//	
//	public void testCassiereBaseCon277() throws ImpossibleWithdrawException{
//		Cassiere cassiere = new Cassiere(new LoaderMock());
//		List<Prelievo> prelievi = generaPrelievi();
//		assertEquals(prelievi.get(0), cassiere.preleva(307));
//		assertEquals(prelievi.get(1), cassiere.preleva(7000));
//		assertEquals(prelievi.get(2), cassiere.preleva(307));
//		assertEquals(prelievi.get(3), cassiere.preleva(307));
//		assertEquals(prelievi.get(4), cassiere.preleva(307));
//		assertEquals(prelievi.get(5), cassiere.preleva(12000));
//		assertEquals(prelievi.get(6), cassiere.preleva(526));
//		assertEquals(prelievi.get(9), cassiere.preleva(277)); // !! questo funziona col cassiere base ma non con Ubz
//	}
	
	@Test(expected = ImpossibleWithdrawException.class)
	public void testCassiereUbzCon277() throws ImpossibleWithdrawException{
		Cassiere cassiere = new CassiereUbz(new LoaderMock());
		List<Prelievo> prelievi = generaPrelievi();
		assertEquals(prelievi.get(0), cassiere.preleva(307));
		assertEquals(prelievi.get(1), cassiere.preleva(7000));
		assertEquals(prelievi.get(2), cassiere.preleva(307));
		assertEquals(prelievi.get(3), cassiere.preleva(307));
		assertEquals(prelievi.get(4), cassiere.preleva(307));
		assertEquals(prelievi.get(5), cassiere.preleva(12000));
		assertEquals(prelievi.get(7), cassiere.preleva(526));
		assertEquals(prelievi.get(9), cassiere.preleva(277)); 
	}

}
