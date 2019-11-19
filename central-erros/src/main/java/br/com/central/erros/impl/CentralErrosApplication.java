package br.com.central.erros.impl;

import java.util.Arrays;

import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.enums.Perfil;
import br.com.central.erros.impl.business.entity.enums.TipoUser;
import br.com.central.erros.impl.business.repository.V1.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CentralErrosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CentralErrosApplication.class, args);
	}


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder pe;


    @Override
    public void run(String... args) throws Exception {


        UserV1 user1 = new UserV1(null, "teste um", "teste1@gmail.com", "36378912377", TipoUser.PESSOAFISICA, pe.encode(
                "123"));
        UserV1 user2 = new UserV1(null, "teste dois", "teste2@gmail.com", "36378912377", TipoUser.PESSOAFISICA,
                pe.encode("123"));
        UserV1 user3 = new UserV1(null, "teste tres", "teste3@gmail.com", "36378912377", TipoUser.PESSOAFISICA, pe.encode(
                "123"));

        UserV1 user4 = new UserV1(null, "Kaio Ferreira ", "teste4@gmail.com", "31628382740", TipoUser.PESSOAFISICA,
                pe.encode("123"));

        user4.addPerfil(Perfil.ADMIN);

        userRepository.saveAll(Arrays.asList(user1, user2, user3, user4));



    }
}
