package pianodistudi.model.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pianodistudi.model.Piano;
import pianodistudi.model.Tipologia;


class PianoTest {

	@Test
	void testOK() {
		Piano p = Piano.getPianoStandard();
		
		assertEquals(180, p.getCfuTot());
		//
		assertEquals(45,p.getCfu(Tipologia.A1));
		assertEquals( 9,p.getCfu(Tipologia.A2));
		assertEquals( 6,p.getCfu(Tipologia.B1));
		assertEquals(66,p.getCfu(Tipologia.B2));
		assertEquals( 9,p.getCfu(Tipologia.B3));
		assertEquals(18,p.getCfu(Tipologia.C));
		assertEquals(12,p.getCfu(Tipologia.D));
		assertEquals( 3,p.getCfu(Tipologia.E1));
		assertEquals( 6,p.getCfu(Tipologia.E2));
		assertEquals( 6,p.getCfu(Tipologia.F));
	}}
