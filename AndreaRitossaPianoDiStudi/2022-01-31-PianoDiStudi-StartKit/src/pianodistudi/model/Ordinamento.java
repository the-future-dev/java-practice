package pianodistudi.model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Ordinamento {
	
	private Item[] ord;
	
	public Ordinamento(Item[] ord) { // metodo utile a fini di test
		if (ord==null || ord.length!=Tipologia.values().length) throw new IllegalArgumentException("Argomento nullo o di lunghezza errata");
		for (Tipologia t : Tipologia.values()) {
			Item item = ord[t.ordinal()];
			if (item==null) throw new IllegalArgumentException("Argomento nullo per tipologia " + t);
		}
		this.ord=ord;
	}
	
	public Ordinamento() {
		ord = new Item[Tipologia.values().length];
	}
	
	public void addItem(Tipologia tipologia, CfuRange range, Optional<List<Ssd>> settori) {
		if (tipologia==null || range==null || settori==null) throw new IllegalArgumentException("argomenti nulli o negativi");
		Item item = new Item(tipologia, range, settori);
		ord[tipologia.ordinal()] = item;
	}

	public CfuRange getRange(Tipologia t) {
		return ord[t.ordinal()].getRange();
	}

	public Optional<List<Ssd>> getSettori(Tipologia t) {
		return ord[t.ordinal()].getSettori();
	}

	public Item getDettagli(Tipologia t) { // utile a fini di test
		return ord[t.ordinal()];
	}

	@Override
	public String toString() { // utile a fini di test
		return "Ordinamento [ord=" + Arrays.toString(ord) + "]";
	}
	
	/* Per essere intrinsecamente consistente, i range min-max dovrebbero soddisfare i seguenti vincoli:
	 * 1) il massimo di una tipologia + la somma dei minimi delle altre  dev'essere sempre <= CFUMAX (180)
	 * 2) il minimo di una tipologia  + la somma dei massimi delle altre dev'essere sempre >= CFUMAX (180)
	 * Se così non fosse, l'ordinamento consentirebbe piani di studio in cui, rispettivamente: 
	 * 1) sarebbe obbligatorio fare più dei cfu minimi richiesti dalla legge (180)
	 * 2) sarebbe materialmente impossibile raggiungere i cfu minimi richiesti dalla legge (180)
	 */
	@SuppressWarnings("unused")
	public boolean verificaConsistenza() {
		throw new UnsupportedOperationException("Non ancora implementata");
	}
	
	//-----
	
	public static class Item {
		private Tipologia tipologia; 
		private CfuRange range;
		private Optional<List<Ssd>> settori;

		
		public Item(Tipologia tipologia, CfuRange range, Optional<List<Ssd>> settori) {
			if (tipologia==null || range==null || settori==null) throw new IllegalArgumentException("argomenti nulli o negativi");
			this.tipologia = tipologia;
			this.range = range;
			if (settori.isPresent()) this.settori = settori; else this.settori = Optional.of(List.of(Ssd.QUALSIASI));
		}

		public Tipologia getTipologia() {
			return tipologia;
		}

		public int getMin() {
			return range.getMin();
		}

		public int getMax() {
			return range.getMax();
		}
		
		public CfuRange getRange() {
			return range;
		}

		public Optional<List<Ssd>> getSettori() { // metodo utile a fini di test
			return settori;
		}
		
		public boolean contiene(Ssd settore) { // metodo utile a fini di test
			return settori.isPresent() ? settori.get().contains(settore) : false;
		}
		public boolean contiene(int value) { // metodo utile a fini di test
			return this.getMin()<= value && value <= this.getMax();
		}

		@Override
		public String toString() {
			return "OrdinamentoItem [tipologia=" + tipologia + ", range=" + range + ", settori=" + settori + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((range == null) ? 0 : range.hashCode());
			result = prime * result + ((settori == null) ? 0 : settori.hashCode());
			result = prime * result + ((tipologia == null) ? 0 : tipologia.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null) return false;
			if (getClass() != obj.getClass()) return false;
			Item other = (Item) obj;
			if (range == null) {
				if (other.range != null) return false;
			} else if (!range.equals(other.range)) return false;
			if (settori == null) {
				if (other.settori != null) return false;
			} else if (!settori.equals(other.settori)) return false;
			if (tipologia != other.tipologia) return false;
			return true;
		}
		
	}

}
