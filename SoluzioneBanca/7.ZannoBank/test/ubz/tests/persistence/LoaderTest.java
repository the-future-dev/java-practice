package ubz.tests.persistence;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.TreeMap;

import org.junit.Test;

import ubz.model.Disponibilit‡;
import ubz.model.Politiche;
import ubz.model.Taglio;
import ubz.persistence.BadFileFormatException;
import ubz.persistence.DotazioneLoader;
import ubz.persistence.MyDotazioneLoader;

public class LoaderTest {

	@Test(expected = IllegalArgumentException.class)
	public void testLeggiRisposteConReaderNull() throws BadFileFormatException, IOException {
		DotazioneLoader loader = new MyDotazioneLoader();
		loader.load(null);
	}
	
	private static Disponibilit‡ generaDisponibilit‡Iniziale(){
		// NB: must be static, because the double brace syntax creates an anonymous inner class, which would hold a ref to the outer context (the text case class)
		// Of course, such Outer.this reference would be null or invalid, unless the outer class is serializable too (which would be very bad anyway)
		// So, making it static solves the issue because there is no outer "this" anymore.
		return new Disponibilit‡( // 0
				new TreeMap<Taglio,Integer>() {
				  private static final long serialVersionUID = 1L;
				  { 
					put(Taglio.CINQUECENTO, 5); put(Taglio.DUECENTO, 25); 	put(Taglio.CENTO, 30); 	put(Taglio.CINQUANTA, 200); 
					put(Taglio.VENTI, 200);		put(Taglio.DIECI, 100);		put(Taglio.CINQUE, 100); 	put(Taglio.DUE, 50); put(Taglio.UNO, 50);
				  }} );
	}

	private static Disponibilit‡ generaPolitiche(){
		// NB: must be static, because the double brace syntax creates an anonymous inner class, which would hold a ref to the outer context (the text case class)
		// Of course, such Outer.this reference would be null or invalid, unless the outer class is serializable too (which would be very bad anyway)
		// So, making it static solves the issue because there is no outer "this" anymore.
		return new Disponibilit‡( // 0
				new TreeMap<Taglio,Integer>() {
				  private static final long serialVersionUID = 1L;
				  { 
					put(Taglio.CINQUECENTO, 5); put(Taglio.DUECENTO, 25); 	put(Taglio.CENTO, 30); 	put(Taglio.CINQUANTA, 200); 
					put(Taglio.VENTI, 10);		put(Taglio.DIECI, 5);		put(Taglio.CINQUE, 3); 	put(Taglio.DUE, 4); put(Taglio.UNO, 3);
				  }} );
	}

	

	@Test
	public void testCaricamentoTabelleOK() throws IOException, BadFileFormatException {
		DotazioneLoader loader = new MyDotazioneLoader();
		loader.load(new FileInputStream("DotazioneIniziale.dat"));
		
		Disponibilit‡ disponibilit‡ = loader.getDisponibilit‡();
		Politiche politiche = loader.getPolitiche();
		assertEquals(disponibilit‡, generaDisponibilit‡Iniziale());
		assertEquals(politiche, generaPolitiche());
	}

	@Test(expected = BadFileFormatException.class)
	public void testCaricamentoTabelleMANCANOpolitiche() throws IOException, BadFileFormatException {
		DotazioneLoader loader = new MyDotazioneLoader();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(bos);
		os.writeObject(LoaderTest.generaDisponibilit‡Iniziale());
		byte[] buf = bos.toByteArray();
		bos.close();
		loader.load(new ByteArrayInputStream(buf));
		Disponibilit‡ disponibilit‡ = loader.getDisponibilit‡();
		assertEquals(disponibilit‡, LoaderTest.generaDisponibilit‡Iniziale());
	}

	@Test(expected = BadFileFormatException.class)
	public void testCaricamentoTabelleMANCANOdisponibilit‡() throws IOException, BadFileFormatException {
		DotazioneLoader loader = new MyDotazioneLoader();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(bos);
		os.writeObject(LoaderTest.generaPolitiche());
		byte[] buf = bos.toByteArray();
		bos.close();
		loader.load(new ByteArrayInputStream(buf));
		Politiche politiche = loader.getPolitiche();
		assertEquals(politiche, LoaderTest.generaPolitiche());
	}

}
