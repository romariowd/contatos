package br.com.contatos.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.contatos.domain.Contato;

@RepositoryRestResource
public interface ContatoRepository extends JpaRepository<Contato, Long> {

	Contato findContactByEmail(String email);
}
