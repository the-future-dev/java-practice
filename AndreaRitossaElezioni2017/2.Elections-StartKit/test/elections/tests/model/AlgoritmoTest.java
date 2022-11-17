package elections.tests.model;


import static org.junit.Assert.*;

import java.util.SortedSet;

import org.junit.Test;

import elections.model.AlgoritmoAssegnazioneSbarramentoProporzionale;
import elections.model.Partito;
import elections.model.RisultatoElezioni;
import elections.model.Elezioni;

public class AlgoritmoTest {	
		
	
	private Elezioni creaElezioni(double sbarramento) {
		Elezioni elezioni = new Elezioni(100,new AlgoritmoAssegnazioneSbarramentoProporzionale(sbarramento));
		elezioni.setVoti(new Partito("Zero"), 10);
		elezioni.setVoti(new Partito("Uno"), 100);
		elezioni.setVoti(new Partito("Due"), 200);
		elezioni.setVoti(new Partito("Tre"), 300);
		elezioni.setVoti(new Partito("Quattro"), 400);
		return elezioni;
	}
	
	@Test
	public void noSbarramento() {
		Elezioni elezioni = creaElezioni(0);
		SortedSet<Partito> partiti = elezioni.getPartiti();
		RisultatoElezioni r = elezioni.getRisultato();
		assertEquals(1, r.getSeggi(findPartito(partiti, "Zero")));
		assertEquals(10, r.getSeggi(findPartito(partiti, "Uno")));
		assertEquals(20, r.getSeggi(findPartito(partiti, "Due")));
		assertEquals(30, r.getSeggi(findPartito(partiti, "Tre")));
		assertEquals(39, r.getSeggi(findPartito(partiti, "Quattro")));
	}
	
	@Test
	public void sbarramento1Percento() {
		Elezioni elezioni = creaElezioni(0.01);
		SortedSet<Partito> partiti = elezioni.getPartiti();
		RisultatoElezioni r = elezioni.getRisultato();
		assertEquals(1, r.getSeggi(findPartito(partiti, "Zero")));
		assertEquals(10, r.getSeggi(findPartito(partiti, "Uno")));
		assertEquals(20, r.getSeggi(findPartito(partiti, "Due")));
		assertEquals(30, r.getSeggi(findPartito(partiti, "Tre")));
		assertEquals(39, r.getSeggi(findPartito(partiti, "Quattro")));
	}
	
	@Test
	public void sbarramento2Percento() {
		Elezioni elezioni = creaElezioni(0.02);
		SortedSet<Partito> partiti = elezioni.getPartiti();
		RisultatoElezioni r = elezioni.getRisultato();
		assertEquals(0, r.getSeggi(findPartito(partiti, "Zero")));
		assertEquals(10, r.getSeggi(findPartito(partiti, "Uno")));
		assertEquals(20, r.getSeggi(findPartito(partiti, "Due")));
		assertEquals(30, r.getSeggi(findPartito(partiti, "Tre")));
		assertEquals(40, r.getSeggi(findPartito(partiti, "Quattro")));
	}
	
	@Test
	public void sbarramento10Percento() {
		Elezioni elezioni = creaElezioni(0.1);
		SortedSet<Partito> partiti = elezioni.getPartiti();
		RisultatoElezioni r = elezioni.getRisultato();
		assertEquals(0, r.getSeggi(findPartito(partiti, "Zero")));
		assertEquals(0, r.getSeggi(findPartito(partiti, "Uno")));
		assertEquals(22, r.getSeggi(findPartito(partiti, "Due")));
		assertEquals(33, r.getSeggi(findPartito(partiti, "Tre")));
		assertEquals(45, r.getSeggi(findPartito(partiti, "Quattro")));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void percentualeMaggiore10() {
		Elezioni elezioni = creaElezioni(0.2);
		elezioni.getRisultato();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void percentualeMinore0() {
		Elezioni elezioni = creaElezioni(-0.01);
		elezioni.getRisultato();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void votiNull() {
		new AlgoritmoAssegnazioneSbarramentoProporzionale(0.01).calcolaSeggi( null);
	}


	private Partito findPartito(SortedSet<Partito> partiti, String nome) {
		for (Partito p : partiti) {
			if (p.getNome().equals(nome))
				return p;
		}
		return null;
	}
}
