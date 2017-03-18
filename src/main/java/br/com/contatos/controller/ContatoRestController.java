package br.com.contatos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.contatos.Contato;
import br.com.contatos.ContatoRepository;
import br.com.contatos.QueryResult;

@RestController
public class ContatoRestController {
	
	@Autowired
	private ContatoRepository repository;
	
	@Autowired
	private QueryResult result;
	
	
	@RequestMapping(value="contato/salvar", method= RequestMethod.POST)
	public @ResponseBody QueryResult salvar(@RequestBody Contato contato){
		
		try {
		
			repository.save(contato);
			
			result.setCodigo(1);
			result.setMensagem("Registro inserido com sucesso!");
			
		} catch (Exception e) {
			result.setCodigo(2);
			result.setMensagem("Erro ao salvar o registro ("+e.getMessage()+")");
		}
				
		return result;
	}

}
