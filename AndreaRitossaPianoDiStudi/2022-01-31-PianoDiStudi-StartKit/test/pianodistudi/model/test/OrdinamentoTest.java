package pianodistudi.model.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import pianodistudi.model.Ssd;
import pianodistudi.model.Tipologia;
import pianodistudi.model.Ordinamento;
import pianodistudi.model.Ordinamento.Item;
import pianodistudi.model.CfuRange;

class OrdinamentoTest {

	@Test
	void testOK_ConEmptySector() {
		Ordinamento ord = new Ordinamento();
		ord.addItem(Tipologia.A1, new CfuRange(39, 51), Optional.of(List.of(
				Ssd.INF01, Ssd.INGINF05, Ssd.MAT02, Ssd.MAT03, Ssd.MAT05, Ssd.MAT06, Ssd.MAT08)));
		ord.addItem(Tipologia.A2, new CfuRange(9, 18), Optional.of(List.of(
				Ssd.FIS01, Ssd.FIS03, Ssd.CHIM07)));
		ord.addItem(Tipologia.B1, new CfuRange(6, 15), Optional.of(List.of(
				Ssd.INGINF01, Ssd.INGINF02, Ssd.INGINF07)));
		ord.addItem(Tipologia.B2, new CfuRange(48, 66), Optional.of(List.of(
				Ssd.INGINF05, Ssd.INGINF04)));
		ord.addItem(Tipologia.B3, new CfuRange(9, 15), Optional.of(List.of(
				Ssd.INGINF02, Ssd.INGINF03)));
		ord.addItem(Tipologia.C, new CfuRange(18, 30), Optional.of(List.of(
				Ssd.INGIND31, Ssd.INGIND35, Ssd.IUS14, Ssd.MAT07, Ssd.MAT09)));
		ord.addItem(Tipologia.D, new CfuRange(12, 18), Optional.empty());
		ord.addItem(Tipologia.E1, new CfuRange(3, 9), Optional.of(List.of(
				Ssd.QUALSIASI)));
		ord.addItem(Tipologia.E2, new CfuRange(6, 6), Optional.of(List.of(
				Ssd.QUALSIASI)));
		ord.addItem(Tipologia.F, new CfuRange(6, 6), Optional.empty());
				
		assertEquals(new CfuRange(39,51), ord.getRange(Tipologia.A1));
		assertEquals(new CfuRange( 9,18), ord.getRange(Tipologia.A2));
		assertEquals(new CfuRange( 6,15), ord.getRange(Tipologia.B1));
		assertEquals(new CfuRange(48,66), ord.getRange(Tipologia.B2));
		assertEquals(new CfuRange( 9,15), ord.getRange(Tipologia.B3));
		assertEquals(new CfuRange(18,30), ord.getRange(Tipologia.C));
		assertEquals(new CfuRange(12,18), ord.getRange(Tipologia.D));
		assertEquals(new CfuRange( 3, 9), ord.getRange(Tipologia.E1));
		assertEquals(new CfuRange( 6, 6), ord.getRange(Tipologia.E2));
		assertEquals(new CfuRange( 6, 6), ord.getRange(Tipologia.F));
	}
	
	@Test
	void testOK_WithItems() {
		Item ordItemA1 = new Item(Tipologia.A1, new CfuRange(39, 51), Optional.of(List.of(
				Ssd.INF01, Ssd.INGINF05, Ssd.MAT02, Ssd.MAT03, Ssd.MAT05, Ssd.MAT06, Ssd.MAT08)));
		Item ordItemA2 = new Item(Tipologia.A2, new CfuRange(9, 18), Optional.of(List.of(
				Ssd.FIS01, Ssd.FIS03, Ssd.CHIM07)));
		Item ordItemB1 = new Item(Tipologia.B1, new CfuRange(6, 15), Optional.of(List.of(
				Ssd.INGINF01, Ssd.INGINF02, Ssd.INGINF07)));
		Item ordItemB2 = new Item(Tipologia.B2, new CfuRange(48, 66), Optional.of(List.of(
				Ssd.INGINF05, Ssd.INGINF04)));
		Item ordItemB3 = new Item(Tipologia.B3, new CfuRange(9, 15), Optional.of(List.of(
				Ssd.INGINF02, Ssd.INGINF03)));
		Item ordItemC = new Item(Tipologia.C, new CfuRange(18, 30), Optional.of(List.of(
				Ssd.INGIND31, Ssd.INGIND35, Ssd.IUS14, Ssd.MAT07, Ssd.MAT09)));
		Item ordItemD = new Item(Tipologia.D, new CfuRange(12, 18), Optional.empty());
		Item ordItemE1 = new Item(Tipologia.E1, new CfuRange(3, 9), Optional.of(List.of(
				Ssd.QUALSIASI)));
		Item ordItemE2 = new Item(Tipologia.E2, new CfuRange(6, 6), Optional.of(List.of(
				Ssd.QUALSIASI)));
		Item ordItemF = new Item(Tipologia.F, new CfuRange(6, 6), Optional.empty());
		
		Item[] ordItems = {ordItemA1, ordItemA2, ordItemB1, ordItemB2, ordItemB3, ordItemC, ordItemD, ordItemE1, ordItemE2, ordItemF}; 
		Ordinamento ord = new Ordinamento(ordItems);
		
		assertEquals(ordItemA1, ord.getDettagli(Tipologia.A1));
		assertEquals(ordItemA2, ord.getDettagli(Tipologia.A2));
		assertEquals(ordItemB1, ord.getDettagli(Tipologia.B1));
		assertEquals(ordItemB2, ord.getDettagli(Tipologia.B2));
		assertEquals(ordItemB3, ord.getDettagli(Tipologia.B3));
		assertEquals(ordItemC,  ord.getDettagli(Tipologia.C));
		assertEquals(ordItemD,  ord.getDettagli(Tipologia.D));
		assertEquals(ordItemE1, ord.getDettagli(Tipologia.E1));
		assertEquals(ordItemE2, ord.getDettagli(Tipologia.E2));
		assertEquals(ordItemF,  ord.getDettagli(Tipologia.F));
	}
	
	@Test
	void testOK_conQualsiasi() {
		Ordinamento ord = new Ordinamento();
		ord.addItem(Tipologia.A1, new CfuRange(39, 51), Optional.of(List.of(
				Ssd.INF01, Ssd.INGINF05, Ssd.MAT02, Ssd.MAT03, Ssd.MAT05, Ssd.MAT06, Ssd.MAT08)));
		ord.addItem(Tipologia.A2, new CfuRange(9, 18), Optional.of(List.of(
				Ssd.FIS01, Ssd.FIS03, Ssd.CHIM07)));
		ord.addItem(Tipologia.B1, new CfuRange(6, 15), Optional.of(List.of(
				Ssd.INGINF01, Ssd.INGINF02, Ssd.INGINF07)));
		ord.addItem(Tipologia.B2, new CfuRange(48, 66), Optional.of(List.of(
				Ssd.INGINF05, Ssd.INGINF04)));
		ord.addItem(Tipologia.B3, new CfuRange(9, 15), Optional.of(List.of(
				Ssd.INGINF02, Ssd.INGINF03)));
		ord.addItem(Tipologia.C, new CfuRange(18, 30), Optional.of(List.of(
				Ssd.INGIND31, Ssd.INGIND35, Ssd.IUS14, Ssd.MAT07, Ssd.MAT09)));
		ord.addItem(Tipologia.D, new CfuRange(12, 18), Optional.of(List.of(Ssd.QUALSIASI)));
		ord.addItem(Tipologia.E1, new CfuRange(3, 9), Optional.of(List.of(
				Ssd.QUALSIASI)));
		ord.addItem(Tipologia.E2, new CfuRange(6, 6), Optional.of(List.of(
				Ssd.QUALSIASI)));
		ord.addItem(Tipologia.F, new CfuRange(6, 6),Optional.of(List.of(Ssd.QUALSIASI)));
				
		assertEquals(new CfuRange(39,51), ord.getRange(Tipologia.A1));
		assertEquals(new CfuRange( 9,18), ord.getRange(Tipologia.A2));
		assertEquals(new CfuRange( 6,15), ord.getRange(Tipologia.B1));
		assertEquals(new CfuRange(48,66), ord.getRange(Tipologia.B2));
		assertEquals(new CfuRange( 9,15), ord.getRange(Tipologia.B3));
		assertEquals(new CfuRange(18,30), ord.getRange(Tipologia.C));
		assertEquals(new CfuRange(12,18), ord.getRange(Tipologia.D));
		assertEquals(new CfuRange( 3, 9), ord.getRange(Tipologia.E1));
		assertEquals(new CfuRange( 6, 6), ord.getRange(Tipologia.E2));
		assertEquals(new CfuRange( 6, 6), ord.getRange(Tipologia.F));
	}
	
	@Test
	void testKO_null_ViaItem() {
		Item ordItemA1 = new Item(Tipologia.A1, new CfuRange(39, 51), Optional.of(List.of(
				Ssd.INF01, Ssd.INGINF05, Ssd.MAT02, Ssd.MAT03, Ssd.MAT05, Ssd.MAT06, Ssd.MAT08)));
		Item ordItemA2 = new Item(Tipologia.A2, new CfuRange(9, 18), Optional.of(List.of(
				Ssd.FIS01, Ssd.FIS03, Ssd.CHIM07)));
		Item ordItemB1 = new Item(Tipologia.B1, new CfuRange(6, 15), Optional.of(List.of(
				Ssd.INGINF01, Ssd.INGINF02, Ssd.INGINF07)));
		Item ordItemB2 = new Item(Tipologia.B2, new CfuRange(48, 66), Optional.of(List.of(
				Ssd.INGINF05, Ssd.INGINF04)));
		Item ordItemB3 = new Item(Tipologia.B3, new CfuRange(9, 15), Optional.of(List.of(
				Ssd.INGINF02, Ssd.INGINF03)));
		Item ordItemC = new Item(Tipologia.C, new CfuRange(18, 30), Optional.of(List.of(
				Ssd.INGIND31, Ssd.INGIND35, Ssd.IUS14, Ssd.MAT07, Ssd.MAT09)));
		Item ordItemD = new Item(Tipologia.D, new CfuRange(12, 18), Optional.empty());
		Item ordItemE1 = new Item(Tipologia.E1, new CfuRange(3, 9), Optional.of(List.of(
				Ssd.QUALSIASI)));
		Item ordItemE2 = new Item(Tipologia.E2, new CfuRange(6, 6), Optional.of(List.of(
				Ssd.QUALSIASI)));
		Item ordItemF = new Item(Tipologia.F, new CfuRange(6, 6), Optional.empty());
		
		assertThrows(IllegalArgumentException.class, () -> new Ordinamento(new Item[]{null, ordItemA2, ordItemB1, ordItemB2, ordItemB3, ordItemC, ordItemD, ordItemE1, ordItemE2, ordItemF}));
		assertThrows(IllegalArgumentException.class, () -> new Ordinamento(new Item[]{ordItemA1, null, ordItemB1, ordItemB2, ordItemB3, ordItemC, ordItemD, ordItemE1, ordItemE2, ordItemF}));
		assertThrows(IllegalArgumentException.class, () -> new Ordinamento(new Item[]{ordItemA1, ordItemA2, null, ordItemB2, ordItemB3, ordItemC, ordItemD, ordItemE1, ordItemE2, ordItemF}));
		assertThrows(IllegalArgumentException.class, () -> new Ordinamento(new Item[]{ordItemA1, ordItemA2, ordItemB1, null, ordItemB3, ordItemC, ordItemD, ordItemE1, ordItemE2, ordItemF}));
		assertThrows(IllegalArgumentException.class, () -> new Ordinamento(new Item[]{ordItemA1, ordItemA2, ordItemB1, ordItemB2, null, ordItemC, ordItemD, ordItemE1, ordItemE2, ordItemF}));
		assertThrows(IllegalArgumentException.class, () -> new Ordinamento(new Item[]{ordItemA1, ordItemA2, ordItemB1, ordItemB2, ordItemB3, null, ordItemD, ordItemE1, ordItemE2, ordItemF}));
		assertThrows(IllegalArgumentException.class, () -> new Ordinamento(new Item[]{ordItemA1, ordItemA2, ordItemB1, ordItemB2, ordItemB3, ordItemC, null, ordItemE1, ordItemE2, ordItemF}));
		assertThrows(IllegalArgumentException.class, () -> new Ordinamento(new Item[]{ordItemA1, ordItemA2, ordItemB1, ordItemB2, ordItemB3, ordItemC, ordItemD, null, ordItemE2, ordItemF}));
		assertThrows(IllegalArgumentException.class, () -> new Ordinamento(new Item[]{ordItemA1, ordItemA2, ordItemB1, ordItemB2, ordItemB3, ordItemC, ordItemD, ordItemE1, null, ordItemF}));
		assertThrows(IllegalArgumentException.class, () -> new Ordinamento(new Item[]{ordItemA1, ordItemA2, ordItemB1, ordItemB2, ordItemB3, ordItemC, ordItemD, ordItemE1, ordItemE2, null}));
	}
	
	@Test
	void testKO_null_ViaAddItems() {
		Ordinamento ord = new Ordinamento();
		
		assertThrows(IllegalArgumentException.class, () -> ord.addItem(null, new CfuRange(39, 51), Optional.of(List.of(
				Ssd.INF01, Ssd.INGINF05, Ssd.MAT02, Ssd.MAT03, Ssd.MAT05, Ssd.MAT06, Ssd.MAT08))));
		
		assertThrows(IllegalArgumentException.class, () -> ord.addItem(Tipologia.A1, null, Optional.of(List.of(
				Ssd.INF01, Ssd.INGINF05, Ssd.MAT02, Ssd.MAT03, Ssd.MAT05, Ssd.MAT06, Ssd.MAT08))));
		assertThrows(IllegalArgumentException.class, () -> ord.addItem(Tipologia.A1, new CfuRange(39, 51), null));
		assertThrows(IllegalArgumentException.class, () -> ord.addItem(Tipologia.A1, new CfuRange(52, 51), Optional.of(List.of(
				Ssd.INF01, Ssd.INGINF05, Ssd.MAT02, Ssd.MAT03, Ssd.MAT05, Ssd.MAT06, Ssd.MAT08))));
		assertThrows(IllegalArgumentException.class, () -> ord.addItem(Tipologia.A1, new CfuRange(-1, 5), Optional.of(List.of(
				Ssd.INF01, Ssd.INGINF05, Ssd.MAT02, Ssd.MAT03, Ssd.MAT05, Ssd.MAT06, Ssd.MAT08))));
		assertThrows(IllegalArgumentException.class, () -> ord.addItem(Tipologia.A1, new CfuRange(9, -5), Optional.of(List.of(
				Ssd.INF01, Ssd.INGINF05, Ssd.MAT02, Ssd.MAT03, Ssd.MAT05, Ssd.MAT06, Ssd.MAT08))));
	}
	

}
