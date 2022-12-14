package pianodistudi.model;

public class AttivitaFormativa {
	private String nome;
	private Ssd ssd;
	private int cfu;
	
	public AttivitaFormativa(String nome, Ssd ssd, int cfu) {
		if(nome==null || nome.equals("")) throw new IllegalArgumentException("nome vuoto o nullo in AF:" + nome);
		if(ssd==null) throw new IllegalArgumentException("ssd nullo in AF: " + ssd);
		if(cfu<1) throw new IllegalArgumentException("cfu non valido in AF: "+ cfu);
		this.nome = nome;
		this.ssd = ssd;
		this.cfu = cfu;
	}

	public String getNome() {
		return nome;
	}

	public Ssd getSsd() {
		return ssd;
	}

	public int getCfu() {
		return cfu;
	}

	@Override
	public String toString() {
		return nome + " (ssd: " + ssd + "; cfu: " + cfu + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cfu;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((ssd == null) ? 0 : ssd.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		AttivitaFormativa other = (AttivitaFormativa) obj;
		if (cfu != other.cfu) return false;
		if (nome == null) {
			if (other.nome != null) return false;
		} else if (!nome.equals(other.nome)) return false;
		if (ssd != other.ssd) return false;
		return true;
	}
		
}
