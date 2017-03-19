package br.com.contatos.importcontact;

import java.io.File;

public interface ContactCreator {
	void createContactFromSource(String path,ContactCreatorListener listener);
}
