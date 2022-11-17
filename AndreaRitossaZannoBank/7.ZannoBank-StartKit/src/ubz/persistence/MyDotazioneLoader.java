package ubz.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import ubz.model.Disponibilit�;
import ubz.model.Politiche;

public class MyDotazioneLoader implements DotazioneLoader {
	private Disponibilit� dispcy;
	private Politiche policy;
	
	@Override
	public void load(InputStream r) throws IOException, BadFileFormatException {
		if (r == null)
			throw new IllegalArgumentException("Nullable Input Stream");
		ObjectInputStream readFromMe = new ObjectInputStream(r);
		try {
			dispcy = (Disponibilit�) readFromMe.readObject();
			policy = (Politiche) readFromMe.readObject();
		} catch (ClassNotFoundException | IOException e) {
			throw new BadFileFormatException("not good reading");
		}
		readFromMe.close();
	}

	@Override
	public Disponibilit� getDisponibilit�() {
		// TODO Auto-generated method stub
		return dispcy;
	}

	@Override
	public Politiche getPolitiche() {
		// TODO Auto-generated method stub
		return policy;
	}

}