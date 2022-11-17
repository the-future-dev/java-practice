package pianodistudi.model.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pianodistudi.model.CfuRange;
import pianodistudi.model.Tipologia;

class TipologiaTest {

	@Test
	void testOK() {
		Tipologia a1 = Tipologia.of("A", "Matematica informatica e statistica");
		assertEquals("A", a1.getSigla());
		assertEquals("Matematica informatica e statistica", a1.getSottoAmbito().get());
		assertEquals(Tipologia.A1, a1);
		
		Tipologia a2 = Tipologia.of("A", "Fisica e chimica");
		assertEquals("A", a2.getSigla());
		assertEquals("Fisica e chimica", a2.getSottoAmbito().get());
		assertEquals(Tipologia.A2, a2);
		
		Tipologia b1 = Tipologia.of("B", "Ingegneria elettronica");
		assertEquals("B", b1.getSigla());
		assertEquals("Ingegneria elettronica", b1.getSottoAmbito().get());
		assertEquals(Tipologia.B1, b1);
		
		Tipologia b2 = Tipologia.of("B", "Ingegneria informatica");
		assertEquals("B", b2.getSigla());
		assertEquals("Ingegneria informatica", b2.getSottoAmbito().get());
		assertEquals(Tipologia.B2, b2);
		
		Tipologia b3 = Tipologia.of("B", "Ingegneria delle telecomunicazioni");
		assertEquals("B", b3.getSigla());
		assertEquals("Ingegneria delle telecomunicazioni", b3.getSottoAmbito().get());
		assertEquals(Tipologia.B3, b3);

		Tipologia c = Tipologia.of("C");
		assertEquals("C", c.getSigla());
		assertTrue(c.getSottoAmbito().isEmpty());
		assertEquals(Tipologia.C, c);

		Tipologia d = Tipologia.of("D");
		assertEquals("D", d.getSigla());
		assertTrue(d.getSottoAmbito().isEmpty());
		assertEquals(Tipologia.D, d);
		
		Tipologia e1 = Tipologia.of("E", "prova finale");
		assertEquals("E", e1.getSigla());
		assertEquals("prova finale", e1.getSottoAmbito().get());
		assertEquals(Tipologia.E1, e1);
		
		Tipologia e2 = Tipologia.of("E", "lingue straniere");
		assertEquals("E", e2.getSigla());
		assertEquals("lingue straniere", e2.getSottoAmbito().get());
		assertEquals(Tipologia.E2, e2);

		Tipologia f = Tipologia.of("F");
		assertEquals("F", f.getSigla());
		assertTrue(f.getSottoAmbito().isEmpty());
		assertEquals(Tipologia.F, f);
	}
	
	@Test
	void testKO_NegativeMin() {
		assertThrows(IllegalArgumentException.class, () -> new CfuRange(-1, 6));
	}
	
	@Test
	void testKO_NegativeMax() {
		assertThrows(IllegalArgumentException.class, () -> new CfuRange(1, -6));
	}

	@Test
	void testKO_MaxLessThanMin() {
		assertThrows(IllegalArgumentException.class, () -> new CfuRange(10, 6));
	}

}
