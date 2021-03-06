package br.com.contatos.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.contatos.domain.Contato;
import br.com.contatos.service.ContactService;
import br.com.contatos.service.ContatoRepository;
import br.com.contatos.QueryResult;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/contato")
public class ContatoRestController {
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private QueryResult result;
	
	
	@RequestMapping(value="/salvar", method= RequestMethod.POST)
	public  QueryResult salvar(@RequestBody Contato contato){
		
		try {
			contactService.saveContact(contato);
			
			result.setCodigo(1);
			result.setMensagem("Registro inserido com sucesso!");
			
		} catch (Exception e) {
			result.setCodigo(2);
			result.setMensagem("Erro ao salvar o registro ("+e.getMessage()+")");
		}
				
		return result;
	}
	
	@RequestMapping(value="/obter",method = RequestMethod.GET)
	public List<Contato> getTodosContatos(){
		 return contactService.getAllContacts();
	}
	
	@RequestMapping(value="/pesquisar/{nome}",method = RequestMethod.GET)
	public List<Contato> getContatoByNome(@PathVariable(value="nome") String nome){
		 return contactService.findByNomeStartingWith(nome);
	}

}
