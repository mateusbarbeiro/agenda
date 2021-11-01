package br.ifpr.agenda.repositories;

import java.util.List;
import java.util.Optional;

import br.ifpr.agenda.dominio.Contato;

public interface ContatoRepositoryCustom {
	
	Optional<Contato> findCompletoById(Long id);
	List<Contato> findContatoByUsuarioIdNew(Long id);
}
