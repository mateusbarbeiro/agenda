package br.ifpr.agenda;

import br.ifpr.agenda.dominio.Usuario;
import org.junit.jupiter.api.Assertions;

public class MockAgenda {
    public Usuario cadastroFake(String username, String password) {
        Assertions.assertEquals(username, "teste");
        Assertions.assertEquals(password, "teste");

        return new Usuario(username, password);
    }

    public Usuario cadastroUsuarioComErro(String username) {
        Assertions.assertEquals(username, "teste");

        return new Usuario(username);
    }
}
