package br.com.contatos.importcontact;
import java.util.List;

import br.com.contatos.domain.Contato;

public interface ContactCreatorListener {
	
	void onComplete(List<Contato> contatos);

}
