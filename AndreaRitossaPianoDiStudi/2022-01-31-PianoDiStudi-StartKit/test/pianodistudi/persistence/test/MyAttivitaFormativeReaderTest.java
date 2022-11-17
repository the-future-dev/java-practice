package pianodistudi.persistence.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import org.junit.jupiter.api.Test;

import pianodistudi.model.AttivitaFormativa;
import pianodistudi.model.Ssd;
import pianodistudi.persistence.BadFileFormatException;
import pianodistudi.persistence.MyAttivitaFormativeReader;

public class MyAttivitaFormativeReaderTest {

	@Test
	void testOK() throws IOException {
		StringReader fakeReader = new StringReader(
				      "27991	ANALISI MATEMATICA T-1								MAT/05			9\r\n"
				    + "28004	FONDAMENTI DI INFORMATICA T-1						ING-INF/05		12\r\n"
				    + "29228	GEOMETRIA E ALGEBRA T								MAT/03			6\r\n"
				    + "26337	LINGUA INGLESE B-2									lingua straniera	6\r\n"
				    + "27993	ANALISI MATEMATICA T-2								MAT/05			6\r\n"
				    + "28006	FONDAMENTI DI INFORMATICA T-2						ING-INF/05		12\r\n"
				    + "28011	RETI LOGICHE T										ING-INF/05		6\r\n"
				    + "\r\n"
				    + "28012	CALCOLATORI ELETTRONICI T							ING-INF/05		6\r\n"
				    + "30780	FISICA GENERALE T									FIS/01			9\r\n"
				    + "28032	MATEMATICA APPLICATA T								MAT/07			6\r\n"
				    + "28027	SISTEMI INFORMATIVI T								ING-INF/05		9\r\n"
				    + "28030	ECONOMIA E ORGANIZZAZIONE AZIENDALE T				ING-IND/35		6\r\n"
				    + "28029	ELETTROTECNICA T									ING-IND/31		6\r\n"
				    + "28014	FONDAMENTI DI TELECOMUNICAZIONI T					ING-INF/03		9\r\n"
				    + "28020	SISTEMI OPERATIVI T									ING-INF/05		9\r\n"
				    + "\r\n"
				    + "28015	CONTROLLI AUTOMATICI T								ING-INF/04		9\r\n"
				    + "28016	ELETTRONICA T										ING-INF/01		6\r\n"
				    + "28024	RETI DI CALCOLATORI T								ING-INF/05		9\r\n"
				    + "28659	TECNOLOGIE WEB T									ING-INF/05		9\r\n"
				    + "28021	INGEGNERIA DEL SOFTWARE T							ING-INF/05		9\r\n"
				    + "17268	PROVA FINALE										prova finale	3\r\n"
				    + "\r\n"
				    + "28072	LABORATORIO DI AMMINISTRAZIONE DI SISTEMI T			ING-INF/05		6\r\n"
				    + "28074	TIROCINIO T											qualsiasi		6\r\n"
				    + "\r\n"
				    + "38378	AFFIDABILITA' E CONTROLLO DELLA QUALITA' T			ING-INF/07		6\r\n"
				    + "88326	AMM.NE DI SISTEMI E SICUREZZA INFORMATICA T C.I.	ING-INF/05		12\r\n"
				    + "88324	AMMINISTRAZIONE DI SISTEMI T						ING-INF/05		6\r\n"
				    + "88325	LABORATORIO DI SICUREZZA INFORMATICA T				ING-INF//05		6\r\n"
				    + "32099	DIRITTO DELL'INFORMATICA T							IUS/14			6\r\n"
				    + "94442	PROGETTAZIONE DI APPLICAZIONI WEB T					ING-INF/05		6"
				);	
		Map<String,AttivitaFormativa> mappaAF = new MyAttivitaFormativeReader().recuperaElenco(fakeReader);
		
		AttivitaFormativa af = mappaAF.get("FONDAMENTI DI INFORMATICA T-1");

		assertEquals(12, af.getCfu());
		assertEquals(Ssd.INGINF05, af.getSsd());
		//assertEquals(Tipologia.A1, af.getTipologia());
		
		af = mappaAF.get("ANALISI MATEMATICA T-1");
		assertEquals(9, af.getCfu());
		assertEquals(Ssd.MAT05, af.getSsd());
		//assertEquals(Tipologia.A1, af.getTipologia());
		
		af = mappaAF.get("LINGUA INGLESE B-2");
		assertEquals(6, af.getCfu());
		assertEquals(Ssd.LINGUASTRANIERA, af.getSsd());
		//assertEquals(Tipologia.E2, af.getTipologia());
		
		af = mappaAF.get("FISICA GENERALE T");
		assertEquals(9, af.getCfu());
		assertEquals(Ssd.FIS01, af.getSsd());
		//assertEquals(Tipologia.A2, af.getTipologia());
		
		af = mappaAF.get("AFFIDABILITA' E CONTROLLO DELLA QUALITA' T");
		assertEquals(6, af.getCfu());
		assertEquals(Ssd.INGINF07, af.getSsd());
		//assertEquals(Tipologia.D, af.getTipologia());
		
		af = mappaAF.get("PROGETTAZIONE DI APPLICAZIONI WEB T");
		assertEquals(6, af.getCfu());
		assertEquals(Ssd.INGINF05, af.getSsd());
		//assertEquals(Tipologia.D, af.getTipologia());
		
		af = mappaAF.get("LABORATORIO DI AMMINISTRAZIONE DI SISTEMI T");
		assertEquals(6, af.getCfu());
		assertEquals(Ssd.INGINF05, af.getSsd());
		//assertEquals(Tipologia.F, af.getTipologia());
	}
	
	@Test
	void testKO_MissingCode() throws IOException {
		StringReader fakeReader = new StringReader(
				    "ANALISI MATEMATICA T-1								MAT/05			9	A1\r\n"
				  + "28004	FONDAMENTI DI INFORMATICA T-1						ING-INF/05		12	A1\r\n"
				  + "29228	GEOMETRIA E ALGEBRA T								MAT/03			6	A1\r\n"
				  + "26337	LINGUA INGLESE B-2									lingua straniera	6	E2\r\n"
				  + "32099	DIRITTO DELL'INFORMATICA T							IUS/14			6	D\r\n"
				  + "94442	PROGETTAZIONE DI APPLICAZIONI WEB T					ING-INF/05		6	D"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyAttivitaFormativeReader().recuperaElenco(fakeReader));
	}
	
	@Test
	void testKO_SpacesInsteadOfTabs_Between1And2() throws IOException {
		StringReader fakeReader = new StringReader(
				    "27991    ANALISI MATEMATICA T-1								MAT/05			9	A1\r\n"
				  + "28004	FONDAMENTI DI INFORMATICA T-1						ING-INF/05		12	A1\r\n"
				  + "29228	GEOMETRIA E ALGEBRA T								MAT/03			6	A1\r\n"
				  + "26337	LINGUA INGLESE B-2									lingua straniera	6	E2\r\n"
				  + "32099	DIRITTO DELL'INFORMATICA T							IUS/14			6	D\r\n"
				  + "94442	PROGETTAZIONE DI APPLICAZIONI WEB T					ING-INF/05		6	D"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyAttivitaFormativeReader().recuperaElenco(fakeReader));
	}
	
	@Test
	void testKO_SpacesInsteadOfTabs_Between2And3() throws IOException {
		StringReader fakeReader = new StringReader(
				    "27991	ANALISI MATEMATICA T-1  MAT/05			9	A1\r\n"
				  + "28004	FONDAMENTI DI INFORMATICA T-1						ING-INF/05		12	A1\r\n"
				  + "29228	GEOMETRIA E ALGEBRA T								MAT/03			6	A1\r\n"
				  + "26337	LINGUA INGLESE B-2									lingua straniera	6	E2\r\n"
				  + "32099	DIRITTO DELL'INFORMATICA T							IUS/14			6	D\r\n"
				  + "94442	PROGETTAZIONE DI APPLICAZIONI WEB T					ING-INF/05		6	D"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyAttivitaFormativeReader().recuperaElenco(fakeReader));
	}
	
	@Test
	void testKO_MissingSector() throws IOException {
		StringReader fakeReader = new StringReader(
				    "27991	ANALISI MATEMATICA T-1								9	A1\r\n"
				  + "28004	FONDAMENTI DI INFORMATICA T-1						ING-INF/05		12	A1\r\n"
				  + "29228	GEOMETRIA E ALGEBRA T								MAT/03			6	A1\r\n"
				  + "26337	LINGUA INGLESE B-2									lingua straniera	6	E2\r\n"
				  + "32099	DIRITTO DELL'INFORMATICA T							IUS/14			6	D\r\n"
				  + "94442	PROGETTAZIONE DI APPLICAZIONI WEB T					ING-INF/05		6	D"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyAttivitaFormativeReader().recuperaElenco(fakeReader));
	}
	
	@Test
	void testKO_WrongSector() throws IOException {
		StringReader fakeReader = new StringReader(
				    "27991	ANALISI MATEMATICA T-1								MT/05			9	A1\r\n"
				  + "28004	FONDAMENTI DI INFORMATICA T-1						ING-INF/05		12	A1\r\n"
				  + "29228	GEOMETRIA E ALGEBRA T								MAT/03			6	A1\r\n"
				  + "26337	LINGUA INGLESE B-2									lingua straniera	6	E2\r\n"
				  + "32099	DIRITTO DELL'INFORMATICA T							IUS/14			6	D\r\n"
				  + "94442	PROGETTAZIONE DI APPLICAZIONI WEB T					ING-INF/05		6	D"
				);
		assertThrows(BadFileFormatException.class, () -> new MyAttivitaFormativeReader().recuperaElenco(fakeReader));
	}
	
	@Test
	void testKO_MissingCfu() throws IOException {
		StringReader fakeReader = new StringReader(
				    "27991	ANALISI MATEMATICA T-1								MAT/05			A1\r\n"
				  + "28004	FONDAMENTI DI INFORMATICA T-1						ING-INF/05		12	A1\r\n"
				  + "29228	GEOMETRIA E ALGEBRA T								MAT/03			6	A1\r\n"
				  + "26337	LINGUA INGLESE B-2									lingua straniera	6	E2\r\n"
				  + "32099	DIRITTO DELL'INFORMATICA T							IUS/14			6	D\r\n"
				  + "94442	PROGETTAZIONE DI APPLICAZIONI WEB T					ING-INF/05		6	D"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyAttivitaFormativeReader().recuperaElenco(fakeReader));
	}
	
	@Test
	void testKO_WrongCfu() throws IOException {
		StringReader fakeReader = new StringReader(
				    "27991	ANALISI MATEMATICA T-1								MAT/05			p	A1\r\n"
				  + "28004	FONDAMENTI DI INFORMATICA T-1						ING-INF/05		12	A1\r\n"
				  + "29228	GEOMETRIA E ALGEBRA T								MAT/03			6	A1\r\n"
				  + "26337	LINGUA INGLESE B-2									lingua straniera	6	E2\r\n"
				  + "32099	DIRITTO DELL'INFORMATICA T							IUS/14			6	D\r\n"
				  + "94442	PROGETTAZIONE DI APPLICAZIONI WEB T					ING-INF/05		6	D"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyAttivitaFormativeReader().recuperaElenco(fakeReader));
	}
	
	@Test
	void testKO_MissingTipologia() throws IOException {
		StringReader fakeReader = new StringReader(
				    "27991	ANALISI MATEMATICA T-1								MAT/05			9\r\n"
				  + "28004	FONDAMENTI DI INFORMATICA T-1						ING-INF/05		12	A1\r\n"
				  + "29228	GEOMETRIA E ALGEBRA T								MAT/03			6	A1\r\n"
				  + "26337	LINGUA INGLESE B-2									lingua straniera	6	E2\r\n"
				  + "32099	DIRITTO DELL'INFORMATICA T							IUS/14			6	D\r\n"
				  + "94442	PROGETTAZIONE DI APPLICAZIONI WEB T					ING-INF/05		6	D"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyAttivitaFormativeReader().recuperaElenco(fakeReader));
	}
	
	@Test
	void testKO_WrongTipologia() throws IOException {
		StringReader fakeReader = new StringReader(
				    "27991	ANALISI MATEMATICA T-1								MAT/05			9	Q3\r\n"
				  + "28004	FONDAMENTI DI INFORMATICA T-1						ING-INF/05		12	A1\r\n"
				  + "29228	GEOMETRIA E ALGEBRA T								MAT/03			6	A1\r\n"
				  + "26337	LINGUA INGLESE B-2									lingua straniera	6	E2\r\n"
				  + "32099	DIRITTO DELL'INFORMATICA T							IUS/14			6	D\r\n"
				  + "94442	PROGETTAZIONE DI APPLICAZIONI WEB T					ING-INF/05		6	D"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyAttivitaFormativeReader().recuperaElenco(fakeReader));
	}
	
	@Test
	void testKO_MissingLinguaStraniera() throws IOException {
		StringReader fakeReader = new StringReader(
				    "27991	ANALISI MATEMATICA T-1								MAT/05			9	A1\r\n"
				  + "28004	FONDAMENTI DI INFORMATICA T-1						ING-INF/05		12	A1\r\n"
				  + "29228	GEOMETRIA E ALGEBRA T								MAT/03			6	A1\r\n"
				  + "26337	LINGUA INGLESE B-2													6	E2\r\n"
				  + "32099	DIRITTO DELL'INFORMATICA T							IUS/14			6	D\r\n"
				  + "94442	PROGETTAZIONE DI APPLICAZIONI WEB T					ING-INF/05		6	D"
				);	
		assertThrows(BadFileFormatException.class, () -> new MyAttivitaFormativeReader().recuperaElenco(fakeReader));
	}
}
