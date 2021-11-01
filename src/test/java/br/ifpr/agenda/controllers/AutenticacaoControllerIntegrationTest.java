package br.ifpr.agenda.controllers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.ifpr.agenda.AgendaApplication;
import br.ifpr.agenda.dominio.Usuario;
import br.ifpr.agenda.repositories.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AgendaApplication.class)
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(
  locations = "classpath:application-integrationtest.properties")
public class AutenticacaoControllerIntegrationTest {
//Importante ressaltar que todos os testes aqui são testes de integracao pois as dependencias nao sao mockadas.
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	UsuarioRepository repositorio;
	
	@After
	public void resetDb() {
		//Limpamos as tabelas utilizadas nesta classe de testes apos a execucao de cada 
		//metodo de teste
		repositorio.deleteAll();
	}
	
	@Test
	public void formRegistroUsuario() throws Exception {
		//Teste de que a rota de cadastro esta retornando a view correta
		mockMvc.perform(get("/cadastro-usuario"))
				.andExpect(status().isOk())
				.andExpect(view().name("autenticacao/cadastro"));
	}
	
	@Test
	public void cadastroUsuarioSuccesso() throws Exception {
		//Teste de que a rota de cadastro de usuario esta funcionando
		mockMvc.perform(post("/usuario/salvar")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param("name", "Jose")
				.param("username", "jose")
				.param("password", "password"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/login"));
		
		List<Usuario> usuarios = repositorio.findAll();
		assertThat(usuarios, hasSize(1));
		Assert.assertEquals(usuarios.get(0).getUsername(), "jose");
		Assert.assertEquals(usuarios.get(0).getName(), "Jose");
		//da pra verificar se o usuario tem os mesmos dados (exceto a senha) que cadastramos
	}

	@Test
	public void formLoginUsuario() throws Exception {
		//Teste de que a rota de login esta retornando a view correta
		mockMvc.perform(get("/login"))
				.andExpect(status().isOk())
				.andExpect(view().name("autenticacao/login"));
	}

	@Test
	public void loginUsuarioSuccesso() throws Exception {
		// Cadastra usuario para teste de login
		mockMvc.perform(post("/usuario/salvar")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param("name", "Jose")
				.param("username", "jose")
				.param("password", "password"));

		// Teste de que a rota de login de usuario esta funcionando
		mockMvc.perform(post("/login")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param("username", "jose")
				.param("password", "password"))
				.andExpect(status().is2xxSuccessful());

		List<Usuario> usuarios = repositorio.findAll();
		assertThat(usuarios, hasSize(1));
		//da pra verificar se o usuario tem os mesmos dados (exceto a senha) que cadastramos
	}
}
