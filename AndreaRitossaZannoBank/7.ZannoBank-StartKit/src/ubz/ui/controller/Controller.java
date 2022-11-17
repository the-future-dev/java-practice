package ubz.ui.controller;

import java.io.IOException;

import ubz.model.Cassiere;
import ubz.model.ImpossibleWithdrawException;
import ubz.model.Prelievo;
import ubz.model.Taglio;
import ubz.persistence.BadFileFormatException;

public class Controller {
	private Cassiere cassiere;
	private DialogManager dialogManager;

	public Controller(Cassiere cassiere, DialogManager dialogManager) throws IOException, BadFileFormatException {
		this.cassiere = cassiere;
		this.dialogManager = dialogManager;
	}
	
	public void alert(String title, String header, String content) {
		dialogManager.alert(title, header, content);
	}

	public int getDisponibilit‡Attuale(Taglio t) {
		return cassiere.getDisponibilit‡Taglio(t);
	}

	public Prelievo preleva(int importo) throws ImpossibleWithdrawException {
		return cassiere.preleva(importo);
	}

}
