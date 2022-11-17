package qa.tests.persistence;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.junit.Test;

import qa.model.Misura;
import qa.persistence.BadFileFormatException;
import qa.persistence.MyMisureReader;

public class ReaderTest {

	@Test(expected = IllegalArgumentException.class)
	public void testLeggiRisposteConReaderNull() throws BadFileFormatException, IOException {
		MyMisureReader r = new MyMisureReader();
		r.leggiMisure(null);
	}

	@Test
	public void testLeggiRisposte() throws IOException, BadFileFormatException {
		String s = "Spicchi di luna, 250, 255\n" + "Rigatoncini, 500, 490\n";
		MyMisureReader r = new MyMisureReader();
		List<Misura> misure = r.leggiMisure(new StringReader(s));

		assertEquals(2, misure.size());

		Misura m = misure.get(0);
		assertEquals("Spicchi di luna", m.getName());
		assertEquals(250, m.getExpected(), 0.01);
		assertEquals(255, m.getReal(), 0.01);
				
		m = misure.get(1);
		assertEquals("Rigatoncini", m.getName());
		assertEquals(500, m.getExpected(), 0.01);
		assertEquals(490, m.getReal(), 0.01);
	}

	@Test(expected = BadFileFormatException.class)
	public void etaNonNumerico() throws IOException, BadFileFormatException {
		String s = "Spicchi di luna, XXX, 255\n" + "Rigatoncini, 500, 490\n";
		MyMisureReader r = new MyMisureReader();
		r.leggiMisure(new StringReader(s));
	}

	@Test(expected = BadFileFormatException.class)
	public void mancaNomePartito() throws IOException, BadFileFormatException {
		String s = "250, 255\n" + "Rigatoncini, 500, 490\n";
		MyMisureReader r = new MyMisureReader();
		r.leggiMisure(new StringReader(s));
	}

	@Test(expected = BadFileFormatException.class)
	public void mancaExpected() throws IOException, BadFileFormatException {
		String s = "Spicchi di luna, 255\n" + "Rigatoncini, 500, 490\n";
		MyMisureReader r = new MyMisureReader();
		r.leggiMisure(new StringReader(s));
	}

	@Test(expected = BadFileFormatException.class)
	public void mancanoEntrambi() throws IOException, BadFileFormatException {
		String s = "Spicchi di luna\n" + "Rigatoncini, 500, 490\n";
		MyMisureReader r = new MyMisureReader();
		r.leggiMisure(new StringReader(s));
	}

}
