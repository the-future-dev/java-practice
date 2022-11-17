package elections.tests.persistence;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;

import elections.model.Partito;
import elections.model.Elezioni;
import elections.persistence.BadFileFormatException;
import elections.persistence.MyVotiReader;
import elections.persistence.VotiReader;

public class ReaderTest {

	@Before
	public void setUp() throws Exception {

		String s =  "SEGGI IN PALIO 100\nA uno 1.100.000\nB due 2.200.000\n";
		new MyVotiReader(new StringReader(s));
	}

	@Test
	public void testCostruttoreMyVotiReader() throws BadFileFormatException {
		String s =  "SEGGI IN PALIO 100\nA uno 1.100.000\nB due 2.200.000\n";
		new MyVotiReader(new StringReader(s));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCostruttoreMyVotiReaderConReaderNull() throws BadFileFormatException {
		new MyVotiReader(null);
	}


	@Test
	public void testCaricaElementi() throws IOException, BadFileFormatException {
		String s =  "SEGGI IN PALIO 100\nA uno 1.100.000\nB due 2.200.000\n";
		VotiReader reader = new MyVotiReader(new StringReader(s));		
		Elezioni voti = reader.caricaElementi();
		
		assertEquals(100, voti.getSeggiDaAssegnare());
		
		Partito[] partiti = voti.getPartiti().toArray(new Partito[0]);
		assertEquals(2, partiti.length);
		
		assertEquals(1100000, voti.getVoti(partiti[0]));
		assertEquals(2200000, voti.getVoti(partiti[1]));
	}
	

	@Test(expected = BadFileFormatException.class)
	public void mancaNumeroVoti() throws IOException, BadFileFormatException {
		String s =  "SEGGI IN PALIO 100\nA uno XXX\nB due 2.200.000\n";
		VotiReader reader = new MyVotiReader(new StringReader(s));		
		reader.caricaElementi();
	}
	

	@Test(expected = BadFileFormatException.class)
	public void mancaNomePartito() throws IOException, BadFileFormatException {
		String s =  "SEGGI IN PALIO 100\n1.100.000\nB due 2.200.000\n";
		VotiReader reader = new MyVotiReader(new StringReader(s));		
		reader.caricaElementi();
	}

	@Test(expected = BadFileFormatException.class)
	public void mancaNumeroSeggiInPalio() throws IOException, BadFileFormatException {
		String s =  "SEGGI IN PALIO XXX\nA uno 1.100.000\nB due 2.200.000\n";
		VotiReader reader = new MyVotiReader(new StringReader(s));		
		reader.caricaElementi();
	}
	
	@Test(expected = BadFileFormatException.class)
	public void mancaSeggiInPalio() throws IOException, BadFileFormatException {
		String s =  "XXX XXX XXX 100\nA uno 1.100.000\nB due 2.200.000\n";
		VotiReader reader = new MyVotiReader(new StringReader(s));
		reader.caricaElementi();
	}

}
