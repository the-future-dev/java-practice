package pianodistudi.model.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pianodistudi.model.Ssd;
import pianodistudi.model.Tipologia;
import pianodistudi.model.AttivitaFormativa;
import pianodistudi.model.Ordinamento;
import pianodistudi.model.PianoDiStudi;
import pianodistudi.model.CfuRange;

class PianoDiStudiTest {

	PianoDiStudi p;
	Ordinamento ord;
	
	@BeforeEach
	void setup() {
		p = new PianoDiStudi();
		p.inserisci( new AttivitaFormativa("Analisi matematica T-1", Ssd.MAT05, 9), Tipologia.A1);
		p.inserisci( new AttivitaFormativa("Analisi matematica T-2", Ssd.MAT05, 6), Tipologia.A1);
		p.inserisci( new AttivitaFormativa("Fondamenti di Informatica T-1", Ssd.INGINF05, 12), Tipologia.A1);
		p.inserisci( new AttivitaFormativa("Fondamenti di Informatica T-2", Ssd.INGINF05, 12), Tipologia.A1);
		p.inserisci( new AttivitaFormativa("Reti logiche T", Ssd.INGINF05, 6), Tipologia.B2);		
		p.inserisci( new AttivitaFormativa("Geometria e algebra T", Ssd.MAT03, 6), Tipologia.A1);
		p.inserisci( new AttivitaFormativa("Lingua inglese B-2", Ssd.QUALSIASI, 6), Tipologia.E2);
		//
		p.inserisci( new AttivitaFormativa("Fisica generale T", Ssd.FIS01, 9), Tipologia.A2);
		p.inserisci( new AttivitaFormativa("Matematica applicata T", Ssd.MAT07, 6), Tipologia.C);
		p.inserisci( new AttivitaFormativa("Calcolatori elettronici T", Ssd.INGINF05, 6), Tipologia.B2);
		p.inserisci( new AttivitaFormativa("Sistemi informativi T", Ssd.INGINF05, 9), Tipologia.B2);
		p.inserisci( new AttivitaFormativa("Sistemi operativi T", Ssd.INGINF05, 9), Tipologia.B2);
		p.inserisci( new AttivitaFormativa("Fondamenti di telecomunicazioni T", Ssd.INGINF03, 9), Tipologia.B3);
		p.inserisci( new AttivitaFormativa("Elettrotecnica T", Ssd.INGIND31, 6), Tipologia.C);
		p.inserisci( new AttivitaFormativa("Economia e organizzazione aziendale T", Ssd.INGIND35, 6), Tipologia.C);
		//
		p.inserisci( new AttivitaFormativa("Elettronica T", Ssd.INGINF01, 6), Tipologia.B1);
		p.inserisci( new AttivitaFormativa("Controlli automatici T", Ssd.INGINF04, 9), Tipologia.B2);
		p.inserisci( new AttivitaFormativa("Reti di calcolatori T", Ssd.INGINF05, 9), Tipologia.B2);
		p.inserisci( new AttivitaFormativa("Tecnologie web T", Ssd.INGINF05, 9), Tipologia.B2);
		p.inserisci( new AttivitaFormativa("Ingegneria del software T", Ssd.INGINF05, 9), Tipologia.B2);
		//
		p.inserisci( new AttivitaFormativa("Tirocinio T", Ssd.QUALSIASI, 6), Tipologia.F);
		p.inserisci( new AttivitaFormativa("Prova finale T", Ssd.QUALSIASI, 3), Tipologia.E1);
		p.inserisci( new AttivitaFormativa("Amministrazione di sistemi T", Ssd.INGINF05, 6), Tipologia.D);
		p.inserisci( new AttivitaFormativa("Diritto dell'inforamtica T", Ssd.IUS14, 6), Tipologia.D);
		//
		ord = new Ordinamento();
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
	}
	
	@Test
	void testOK_1() {
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
		//
		assertTrue(p.verificaOrdinamento(ord));
	}
	
	@Test
	void testOK_2() {
		/* Scelte di tipo F:
		 * 28072	LABORATORIO DI AMMINISTRAZIONE DI SISTEMI T			senzasettore	6
		 * 28074	TIROCINIO T											senzasettore	6
		 * Scelte di tipo D:
		 * 38378	AFFIDABILITA' E CONTROLLO DELLA QUALITA' T			ING-INF/07		6
		 * 88326	AMM.NE DI SISTEMI E SICUREZZA INFORMATICA T C.I.	ING-INF/05		12
		 * 88324	AMMINISTRAZIONE DI SISTEMI T						ING-INF/05		6
		 * 88325	LABORATORIO DI SICUREZZA INFORMATICA T				ING-INF//05		6
		 * 32099	DIRITTO DELL'INFORMATICA T							IUS/14			6
		 * 94442	PROGETTAZIONE DI APPLICAZIONI WEB T					ING-INF/05		6
		*/
		p.rimuovi(new AttivitaFormativa("Amministrazione di sistemi T", Ssd.INGINF05, 6), Tipologia.D);
		p.inserisci(new AttivitaFormativa("Progettazione di applicazioni web T", Ssd.INGINF05, 6), Tipologia.D);
		//
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
		//
		assertTrue(p.verificaOrdinamento(ord));
	}
	
	@Test
	void testOK_3() {
		/* Scelte di tipo F:
		 * 28072	LABORATORIO DI AMMINISTRAZIONE DI SISTEMI T			senzasettore	6
		 * 28074	TIROCINIO T											senzasettore	6
		 * Scelte di tipo D:
		 * 38378	AFFIDABILITA' E CONTROLLO DELLA QUALITA' T			ING-INF/07		6
		 * 88326	AMM.NE DI SISTEMI E SICUREZZA INFORMATICA T C.I.	ING-INF/05		12
		 * 88324	AMMINISTRAZIONE DI SISTEMI T						ING-INF/05		6
		 * 88325	LABORATORIO DI SICUREZZA INFORMATICA T				ING-INF//05		6
		 * 32099	DIRITTO DELL'INFORMATICA T							IUS/14			6
		 * 94442	PROGETTAZIONE DI APPLICAZIONI WEB T					ING-INF/05		6
		*/
		p.rimuovi(new AttivitaFormativa("Reti logiche T", Ssd.INGINF05, 6), Tipologia.B2);
		p.inserisci(new AttivitaFormativa("Reti logiche T", Ssd.INGINF05, 6), Tipologia.A1);
		//
		assertEquals(180, p.getCfuTot());
		//
		assertEquals(51,p.getCfu(Tipologia.A1)); // +6
		assertEquals( 9,p.getCfu(Tipologia.A2));
		assertEquals( 6,p.getCfu(Tipologia.B1));
		assertEquals(60,p.getCfu(Tipologia.B2)); // -6
		assertEquals( 9,p.getCfu(Tipologia.B3));
		assertEquals(18,p.getCfu(Tipologia.C));
		assertEquals(12,p.getCfu(Tipologia.D));
		assertEquals( 3,p.getCfu(Tipologia.E1));
		assertEquals( 6,p.getCfu(Tipologia.E2));
		assertEquals( 6,p.getCfu(Tipologia.F));
		//
		assertTrue(p.verificaOrdinamento(ord));
	}
	
	@Test
	void testOK_4() {
		/* Scelte di tipo F:
		 * 28072	LABORATORIO DI AMMINISTRAZIONE DI SISTEMI T			senzasettore	6
		 * 28074	TIROCINIO T											senzasettore	6
		 * Scelte di tipo D:
		 * 38378	AFFIDABILITA' E CONTROLLO DELLA QUALITA' T			ING-INF/07		6
		 * 88326	AMM.NE DI SISTEMI E SICUREZZA INFORMATICA T C.I.	ING-INF/05		12
		 * 88324	AMMINISTRAZIONE DI SISTEMI T						ING-INF/05		6
		 * 88325	LABORATORIO DI SICUREZZA INFORMATICA T				ING-INF//05		6
		 * 32099	DIRITTO DELL'INFORMATICA T							IUS/14			6
		 * 94442	PROGETTAZIONE DI APPLICAZIONI WEB T					ING-INF/05		6
		*/
		p.inserisci(new AttivitaFormativa("LABORATORIO DI SICUREZZA INFORMATICA T", Ssd.INGINF05, 6), Tipologia.A1);
		//
		assertEquals(186, p.getCfuTot()); // un esame in più di tipo A1, tot 186 cfu, ma siamo ancora nei limiti
		//
		assertEquals(51,p.getCfu(Tipologia.A1)); // +6
		assertEquals( 9,p.getCfu(Tipologia.A2));
		assertEquals( 6,p.getCfu(Tipologia.B1));
		assertEquals(66,p.getCfu(Tipologia.B2));
		assertEquals( 9,p.getCfu(Tipologia.B3));
		assertEquals(18,p.getCfu(Tipologia.C));
		assertEquals(12,p.getCfu(Tipologia.D));
		assertEquals( 3,p.getCfu(Tipologia.E1));
		assertEquals( 6,p.getCfu(Tipologia.E2));
		assertEquals( 6,p.getCfu(Tipologia.F));
		//
		assertTrue(p.verificaOrdinamento(ord));
	}
	
	@Test
	void testKO_OverMaxInB2() {
		/* Scelte di tipo F:
		 * 28072	LABORATORIO DI AMMINISTRAZIONE DI SISTEMI T			senzasettore	6
		 * 28074	TIROCINIO T											senzasettore	6
		 * Scelte di tipo D:
		 * 38378	AFFIDABILITA' E CONTROLLO DELLA QUALITA' T			ING-INF/07		6
		 * 88326	AMM.NE DI SISTEMI E SICUREZZA INFORMATICA T C.I.	ING-INF/05		12
		 * 88324	AMMINISTRAZIONE DI SISTEMI T						ING-INF/05		6
		 * 88325	LABORATORIO DI SICUREZZA INFORMATICA T				ING-INF//05		6
		 * 32099	DIRITTO DELL'INFORMATICA T							IUS/14			6
		 * 94442	PROGETTAZIONE DI APPLICAZIONI WEB T					ING-INF/05		6
		*/
		p.inserisci(new AttivitaFormativa("LABORATORIO DI SICUREZZA INFORMATICA T", Ssd.INGINF05, 6), Tipologia.B2);
		//
		assertEquals(186, p.getCfuTot()); // un esame in più di tipo A1, tot 186 cfu, ma siamo ancora nei limiti
		//
		assertEquals(45,p.getCfu(Tipologia.A1));
		assertEquals( 9,p.getCfu(Tipologia.A2));
		assertEquals( 6,p.getCfu(Tipologia.B1));
		assertEquals(72,p.getCfu(Tipologia.B2)); // !!
		assertEquals( 9,p.getCfu(Tipologia.B3));
		assertEquals(18,p.getCfu(Tipologia.C));
		assertEquals(12,p.getCfu(Tipologia.D));
		assertEquals( 3,p.getCfu(Tipologia.E1));
		assertEquals( 6,p.getCfu(Tipologia.E2));
		assertEquals( 6,p.getCfu(Tipologia.F));
		//
		assertFalse(p.verificaOrdinamento(ord)); // non rispetta ordinamento perché eccede il max di B2 (66)
	}

	@Test
	void testKO_UnderMinInA2() {
		/* Scelte di tipo F:
		 * 28072	LABORATORIO DI AMMINISTRAZIONE DI SISTEMI T			senzasettore	6
		 * 28074	TIROCINIO T											senzasettore	6
		 * Scelte di tipo D:
		 * 38378	AFFIDABILITA' E CONTROLLO DELLA QUALITA' T			ING-INF/07		6
		 * 88326	AMM.NE DI SISTEMI E SICUREZZA INFORMATICA T C.I.	ING-INF/05		12
		 * 88324	AMMINISTRAZIONE DI SISTEMI T						ING-INF/05		6
		 * 88325	LABORATORIO DI SICUREZZA INFORMATICA T				ING-INF//05		6
		 * 32099	DIRITTO DELL'INFORMATICA T							IUS/14			6
		 * 94442	PROGETTAZIONE DI APPLICAZIONI WEB T					ING-INF/05		6
		*/
		p.rimuovi(new AttivitaFormativa("Fisica generale T", Ssd.FIS01, 9), Tipologia.A2);
		//
		assertEquals(171, p.getCfuTot()); // un esame in più di tipo A1, tot 186 cfu, ma siamo ancora nei limiti
		//
		assertEquals(45,p.getCfu(Tipologia.A1));
		assertEquals( 0,p.getCfu(Tipologia.A2)); // !!
		assertEquals( 6,p.getCfu(Tipologia.B1));
		assertEquals(66,p.getCfu(Tipologia.B2));
		assertEquals( 9,p.getCfu(Tipologia.B3));
		assertEquals(18,p.getCfu(Tipologia.C));
		assertEquals(12,p.getCfu(Tipologia.D));
		assertEquals( 3,p.getCfu(Tipologia.E1));
		assertEquals( 6,p.getCfu(Tipologia.E2));
		assertEquals( 6,p.getCfu(Tipologia.F));
		//
		assertFalse(p.verificaOrdinamento(ord)); // non rispetta ordinamento perché eccede il max di B2 (66)
	}
	
	@Test
	void testKO_AFgiàPresenteNellaStessaTipologia() {
		/* Scelte di tipo F:
		 * 28072	LABORATORIO DI AMMINISTRAZIONE DI SISTEMI T			senzasettore	6
		 * 28074	TIROCINIO T											senzasettore	6
		 * Scelte di tipo D:
		 * 38378	AFFIDABILITA' E CONTROLLO DELLA QUALITA' T			ING-INF/07		6
		 * 88326	AMM.NE DI SISTEMI E SICUREZZA INFORMATICA T C.I.	ING-INF/05		12
		 * 88324	AMMINISTRAZIONE DI SISTEMI T						ING-INF/05		6
		 * 88325	LABORATORIO DI SICUREZZA INFORMATICA T				ING-INF//05		6
		 * 32099	DIRITTO DELL'INFORMATICA T							IUS/14			6
		 * 94442	PROGETTAZIONE DI APPLICAZIONI WEB T					ING-INF/05		6
		*/
		assertThrows(IllegalArgumentException.class, () -> p.inserisci(new AttivitaFormativa("Fisica generale T", Ssd.FIS01, 9), Tipologia.A2));
	}
	
	@Test
	void testKO_AFgiàPresenteInAltraTipologia() {
		/* Scelte di tipo F:
		 * 28072	LABORATORIO DI AMMINISTRAZIONE DI SISTEMI T			senzasettore	6
		 * 28074	TIROCINIO T											senzasettore	6
		 * Scelte di tipo D:
		 * 38378	AFFIDABILITA' E CONTROLLO DELLA QUALITA' T			ING-INF/07		6
		 * 88326	AMM.NE DI SISTEMI E SICUREZZA INFORMATICA T C.I.	ING-INF/05		12
		 * 88324	AMMINISTRAZIONE DI SISTEMI T						ING-INF/05		6
		 * 88325	LABORATORIO DI SICUREZZA INFORMATICA T				ING-INF//05		6
		 * 32099	DIRITTO DELL'INFORMATICA T							IUS/14			6
		 * 94442	PROGETTAZIONE DI APPLICAZIONI WEB T					ING-INF/05		6
		*/
		assertThrows(IllegalArgumentException.class, () -> p.inserisci(new AttivitaFormativa("Fisica generale T", Ssd.FIS01, 9), Tipologia.D));
	}
	
	@Test
	void testKO_RimozioneAfNonPresente() {
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
		//
		assertTrue(p.verificaOrdinamento(ord));
		//
		assertThrows(IllegalArgumentException.class, () -> p.rimuovi(new AttivitaFormativa("AFFIDABILITA' E CONTROLLO DELLA QUALITA' T", Ssd.INGINF07, 6), Tipologia.D));
	}
	
}
