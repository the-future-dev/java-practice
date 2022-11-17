package elections.ui.controller;

import java.io.IOException;

import elections.model.AlgoritmoAssegnazioneSbarramentoProporzionale;
import elections.model.Elezioni;
import elections.persistence.BadFileFormatException;
import elections.persistence.SeggiWriter;
import elections.persistence.VotiReader;

public class MyController extends Controller {
	
	private SeggiWriter myWriter;
	private Elezioni elezioni;	
	

	public MyController(VotiReader votiReader, SeggiWriter myWriter)
			throws IOException, BadFileFormatException {
		this.myWriter = myWriter;
		elezioni = votiReader.caricaElementi();
		elezioni.setAlgoritmo(new AlgoritmoAssegnazioneSbarramentoProporzionale(0));
	}
	
	@Override
	public Elezioni ricalcola(double sbarramento) {
		elezioni.setAlgoritmo(new AlgoritmoAssegnazioneSbarramentoProporzionale(sbarramento));
		return elezioni;
	}

	@Override
	public void salvaSuFile(String msg) throws IOException {
		if (elezioni == null)
			return;
		
		myWriter.stampaSeggi(elezioni, msg);
	}

	@Override
	public Elezioni getElezioni() {
		return elezioni;
	}

}
