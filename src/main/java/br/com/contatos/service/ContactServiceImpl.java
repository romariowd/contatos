package br.com.contatos.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.contatos.domain.Contato;
import br.com.contatos.service.ContatoRepository;

@Component("contactService")
@Transactional
public class ContactServiceImpl implements ContactService {

	@Autowired
	ContatoRepository contactRepository;

	@Override
	public void saveContact(Contato contact) {
	  contactRepository.save(contact);
		
	}

	@Override
	public List<Contato> getAllContacts() {
		return contactRepository.findAll();
	}

	@Override
	public void updateContact(Contato contact) {
		saveContact(contact);
		
	}

	@Override
	public void deleteContact(Long id) {
		contactRepository.delete(id);
		
	}

	@Override
	public void importContacts(List<Contato> contacts) {
				for(int i = 0; i < contacts.size();i++){
					Contato contact = contacts.get(i);
					if(contact.getId() == null){
						Contato c = contactRepository.findContactByEmail(contact.getEmail());
						if(c == null){
							List<Contato> ctsAmigos = new ArrayList<>(contact.getContatos());
							contact.getContatos().clear();
							c = contactRepository.save(contact);
							for(int x = 0; x < ctsAmigos.size();x++){
								Contato cAmigo = ctsAmigos.get(x);
								Contato c2 = contactRepository.findContactByEmail(cAmigo.getEmail());
								if(c2 != null){
									c.getContatos().add(c2);
								}
								
							}
						}
							
						
					}else{
					 Contato contatoArmazenado = contactRepository.findOne(contact.getId());
					 List<Contato> friendsAlreadySaved = contatoArmazenado.getContatos();
					 List<Contato> friendsToBeSaved = lookForFriends(contact.getContatos());
					 friendsToBeSaved.removeAll(friendsAlreadySaved);
					 contatoArmazenado.getContatos().addAll(friendsToBeSaved);
					}
				}
		
	}
	
	private List<Contato> lookForFriends(List<Contato> amigos){
		List<Contato> ctsAmigos = new ArrayList<>();
		for(int x = 0; x < amigos.size();x++){
			Contato cAmigo = amigos.get(x);
			Contato c2 = contactRepository.findContactByEmail(cAmigo.getEmail());
			if(c2 != null){
				ctsAmigos.add(c2);
			}
			
		}
		
		return ctsAmigos;
	}
}
