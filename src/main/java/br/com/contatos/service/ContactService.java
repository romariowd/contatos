package br.com.contatos.service;

import java.util.List;

import br.com.contatos.domain.Contato;

public interface ContactService {
	void saveContact(Contato contact);
	List<Contato> getAllContacts();
	void updateContact(Contato contact);
	void deleteContact(Long id);
	
	void importContacts(List<Contato> contacts);

}
