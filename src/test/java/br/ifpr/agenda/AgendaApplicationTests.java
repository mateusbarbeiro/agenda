package br.ifpr.agenda;

import br.ifpr.agenda.controllers.AutenticacaoController;
import br.ifpr.agenda.dominio.Usuario;
import br.ifpr.agenda.dto.Login;
import br.ifpr.agenda.repositories.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@SpringBootTest
class AgendaApplicationTests {
	@Autowired
	AutenticacaoController controller;

	@Autowired
	UsuarioRepository repository;

	BindingResult bindingResult;

	Model model;

	@Test
	void happyDayCriacaoDeUsuario() {
		MockAgenda mockAgenda = new MockAgenda();
		Usuario user = mockAgenda.cadastroFake("teste", "teste");

		try{
			controller.postUsuario(user, bindingResult, model);
			Usuario createdUser = repository.findByUsername("teste");
			Assertions.assertEquals(user, createdUser);
		} catch (Exception e) {
			System.out.println("Erro:" + e.getMessage());
		}
	}

	@Test
	void tentaCriarUsuarioComErro() {
		MockAgenda mockAgenda = new MockAgenda();
		Usuario user = mockAgenda.cadastroUsuarioComErro("teste");

		try{
			controller.postUsuario(user, bindingResult, model);
			Usuario createdUser = repository.findByUsername("teste");

		} catch (Exception e) {
			System.out.println("Erro:" + e.getMessage());
		}
	}
}
