package pianodistudi.model.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pianodistudi.model.CfuRange;

class RangeTest {

	@Test
	void testOK_1() {
		CfuRange range = new CfuRange(39, 51);
		assertEquals(39, range.getMin());
		assertEquals(51, range.getMax());
		assertTrue(range.contains(40));
		assertTrue(range.contains(39));
		assertFalse(range.contains(38));
		assertTrue(range.contains(51));
		assertFalse(range.contains(52));
	}
	
	@Test
	void testOK_2() {
		CfuRange range = new CfuRange(0, 9);
		assertEquals(0, range.getMin());
		assertEquals(9, range.getMax());
		assertTrue(range.contains(4));
		assertTrue(range.contains(9));
		assertFalse(range.contains(10));
	}
	
	@Test
	void testOK_3() {
		CfuRange range = new CfuRange(9, 9);
		assertEquals(9, range.getMin());
		assertEquals(9, range.getMax());
		assertTrue(range.contains(9));
		assertFalse(range.contains(10));
		assertFalse(range.contains(8));
	}
	
	@Test
	void testEquals() {
		CfuRange range1 = new CfuRange(9, 9);
		CfuRange range2 = new CfuRange(9, 9);
		assertEquals(range1, range2);
		assertEquals(range2, range1);
		assertEquals(range1, range1);
	}
	
	@Test
	void testNotEquals() {
		CfuRange range1 = new CfuRange(6, 9);
		CfuRange range2 = new CfuRange(9, 9);
		CfuRange range3 = new CfuRange(6, 6);
		assertNotEquals(range1, range2);
		assertNotEquals(range1, range3);
		assertNotEquals(range2, range3);
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

	@Test
	void testOK_StringCtor() {
		CfuRange range = new CfuRange("39-51");
		assertEquals(39, range.getMin());
		assertEquals(51, range.getMax());
		assertTrue(range.contains(40));
		assertTrue(range.contains(39));
		assertFalse(range.contains(38));
		assertTrue(range.contains(51));
		assertFalse(range.contains(52));
	}

	@Test
	void testKO_StringWithoutDash() {
		assertThrows(IllegalArgumentException.class, () -> new CfuRange("1025"));
	}

	@Test
	void testKO_StringEmpty() {
		assertThrows(IllegalArgumentException.class, () -> new CfuRange(""));
		assertThrows(IllegalArgumentException.class, () -> new CfuRange(null));
	}

	@Test
	void testKO_StringWithoutFirstNumber() {
		assertThrows(IllegalArgumentException.class, () -> new CfuRange("aa-25"));
	}

	@Test
	void testKO_StringWithoutSecondNumber() {
		assertThrows(IllegalArgumentException.class, () -> new CfuRange("12-a5"));
	}

	@Test
	void testKO_StringWithoutBothNumbers() {
		assertThrows(IllegalArgumentException.class, () -> new CfuRange("b-a"));
	}

	@Test
	void testKO_StringDashesOnly() {
		assertThrows(IllegalArgumentException.class, () -> new CfuRange("--"));
	}

}
