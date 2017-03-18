package br.com.contatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ContatoRepository extends JpaRepository<Contato, Long> {

}
