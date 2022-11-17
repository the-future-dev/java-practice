package ghigliottina.model;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Ghigliottina implements Iterator<Terna>{

	private Iterator<Terna> iterator;
	private String rispostaEsatta;
	private List<Terna> terne;
	
	public Ghigliottina(List<Terna> terne, String rE) {
		if (terne == null
				|| rE == null
				|| rE.trim().equals(""))
			throw new IllegalArgumentException("Nullable parameter to Ghigliottina constructor");
		this.rispostaEsatta = rE;
		this.terne = terne;
		iterator = terne.iterator();
	}

	public String getRispostaEsatta() {
		return rispostaEsatta;
	}

	public List<Terna> getTerne() {
		return terne;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return iterator.hasNext();
	}

	@Override
	public Terna next() {
		// TODO Auto-generated method stub
		return iterator.next();
	}

	@Override
	public String toString() {
		return "Ghigliottina [iterator=" + iterator + ", rispostaEsatta=" + rispostaEsatta + ", terne=" + terne + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(iterator, rispostaEsatta, terne);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Ghigliottina))
			return false;
		Ghigliottina other = (Ghigliottina) obj;
		return Objects.equals(iterator, other.iterator) && Objects.equals(rispostaEsatta, other.rispostaEsatta)
				&& Objects.equals(terne, other.terne);
	}
	
	
}
