package br.ifpr.agenda.seguranca;

import javax.transaction.Transactional;

import br.ifpr.agenda.dominio.Role;
import br.ifpr.agenda.dominio.Usuario;
import br.ifpr.agenda.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
    @Transactional
    public class PopulacaoInicialBanco implements CommandLineRunner {

        @Autowired
        private UsuarioRepository usuarioRepository;

        @Autowired
        private PasswordEncoder encoder;

        @Override
        public void run(String... args) throws Exception {


            Usuario usuarioAdm = new Usuario();
            usuarioAdm.setUsername("admin");
            usuarioAdm.setName("admin");
            usuarioAdm.setPassword(encoder.encode("admin"));
            usuarioAdm.setRole(Role.ADMIN.getNome());
            usuarioRepository.save(usuarioAdm);

//            File jsonFile = ResourceUtils.getFile("classpath:municipios.json");
//            ObjectMapper cidadeMapper = new ObjectMapper();
//            JsonNode dataNode = cidadeMapper.readTree(jsonFile).get("data");
//
//            dataNode.forEach((cidadeNode) -> {
//                Cidade cidade = new Cidade();
//                cidade.setCodigo(cidadeNode.get("Codigo").asText());
//                cidade.setNome(cidadeNode.get("Nome").asText());
//                cidade.setUf(cidadeNode.get("Uf").asText());
//                cidadeRepo.save(cidade);
//            });
//
//            cidadeRepo.flush();
//            Departamento departamento1 = new Departamento("Tecnologia da Informação", "TI");
//            Departamento departamento2 = new Departamento("Recursos Humanos", "RH");
//            Departamento departamento3 = new Departamento("Produção", "PROD");
//            departamentoRepo.save(departamento1);
//            departamentoRepo.save(departamento2);
//            departamentoRepo.save(departamento3);
//
//            departamentoRepo.flush();
//
//            Cidade cidade1 = cidadeRepo.findById(1L).get();
//
//            Pessoa p1 = new Pessoa("Joao");
//            p1.setDataNascimento(LocalDate.of(1990, 4, 1));
//            p1.setEmail("joao@gmail.com");
//            p1.setCpf("10518516962");
//            p1.setCidade(cidade1);
//            p1.setDepartamento(departamento1);
//
//            Pessoa p2 = new Pessoa("Maria");
//            p2.setDataNascimento(LocalDate.of(1900, 1, 1));
//            p2.setEmail("maria@gmail.com");
//            p2.setCpf("10518516962");
//            p2.setCidade(cidade1);
//            p2.setDepartamento(departamento2);
//            Pessoa p3 = new Pessoa("Willian");
//            p3.setDataNascimento(LocalDate.of(1990, 2, 1));
//            p3.setEmail("willian@gmail.com");
//            p3.setCpf("10518516962");
//            p3.setCidade(cidade1);
//            p3.setDepartamento(departamento3);
//
//            pessoaRepo.save(p1);
//            pessoaRepo.save(p2);
//            pessoaRepo.save(p3);

    }
}
