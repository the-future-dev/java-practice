package ghigliottina.ui;

import java.util.List;
import java.util.Random;

import ghigliottina.model.Ghigliottina;

public class MyController implements Controller {
	List<Ghigliottina> list;
	
	public MyController(List<Ghigliottina> list) {
		this.list = list;
	}
	
	@Override
	public Ghigliottina sorteggiaGhigliottina() {
		// TODO Auto-generated method stub
		Random r = new Random();
		return list.get(r.nextInt(list.size()));
	}

	@Override
	public List<Ghigliottina> listaGhigliottine() {
		// TODO Auto-generated method stub
		return this.list;
	}

}
