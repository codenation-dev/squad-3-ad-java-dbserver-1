package br.com.central.erros.impl;

import java.time.LocalDate;
import java.util.Arrays;

import br.com.central.erros.impl.business.entity.V1.LogV1;
import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.enums.Ambiente;
import br.com.central.erros.impl.business.entity.enums.Level;
import br.com.central.erros.impl.business.entity.enums.Perfil;
import br.com.central.erros.impl.business.entity.enums.TipoUser;
import br.com.central.erros.impl.business.repository.V1.LogRepositoryV1;
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
    private LogRepositoryV1 logRepositoryV1;

    @Autowired
    private BCryptPasswordEncoder pe;

    @Override
    public void run(String... args) {
       UserV1 user1 = new UserV1(null, "teste um", "teste1@gmail.com", "36378912377", TipoUser.PESSOAFISICA, pe.encode(
                "123"));
        UserV1 user2 = new UserV1(null, "teste dois", "teste2@gmail.com", "36378912377", TipoUser.PESSOAFISICA,
                pe.encode("123"));
        UserV1 user3 = new UserV1(null, "teste tres", "teste3@gmail.com", "36378912377", TipoUser.PESSOAFISICA, pe.encode(
                "123"));
        UserV1 user4 = new UserV1(null, "Kaio Ferreira ", "grohlbr@gmail.com", "31628382740", TipoUser.PESSOAFISICA,
                pe.encode("123"));
        UserV1 user5 = new UserV1(null, "Samuel Simão", "mucapapipa.br@gmail.com", "31628382740", TipoUser.PESSOAFISICA,
                pe.encode("123"));

        user4.addPerfil(Perfil.ADMIN);
        user5.addPerfil(Perfil.ADMIN);

        userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));

        LogV1 logV1 = new LogV1(null, "12313212",16l, null, "teste", "teste ", Ambiente.PRODUCTION, Level.ERROR, user1);
        LogV1 logV2 = new LogV1(null, "12313212",16l, null, "teste", "teste ", Ambiente.HOMOLOGATION, Level.DEBUG, user2);
        LogV1 logV3 = new LogV1(null, "12313212",16l, null, "teste", "teste ", Ambiente.DEVELOPMENT, Level.WARNING, user3);
        LogV1 logV4 = new LogV1(null, "12313212",16l, LocalDate.now(), "teste", "teste ", Ambiente.PRODUCTION, Level.ERROR,
                user4);
        LogV1 logV5 = new LogV1(null, "12313212",16l, null, "teste", "teste2 ", Ambiente.HOMOLOGATION, Level.ERROR, user1);
        LogV1 logV6 = new LogV1(null, "12313212",16l, null, "teste", "teste2 ", Ambiente.HOMOLOGATION, Level.DEBUG, user2);
        LogV1 logV7 = new LogV1(null, "12313212",16l, null, "teste", "teste2 ", Ambiente.HOMOLOGATION, Level.WARNING, user3);
        LogV1 logV8 = new LogV1(null, "12313212",16l, LocalDate.now(), "teste", "teste2 ", Ambiente.HOMOLOGATION, Level.ERROR,
                user4);
        LogV1 logV9 = new LogV1(null, "12313212",16l, null, "teste", "teste3 ", Ambiente.DEVELOPMENT, Level.ERROR, user1);
        LogV1 logV10 = new LogV1(null, "12313212",16l, null, "teste", "teste3 ", Ambiente.DEVELOPMENT, Level.DEBUG, user2);
        LogV1 logV11 = new LogV1(null, "12313212",16l, null, "teste", "teste3 ", Ambiente.DEVELOPMENT, Level.WARNING, user3);
        LogV1 logV12 = new LogV1(null, "12313212",16l, LocalDate.now(), "teste", "teste3 ", Ambiente.DEVELOPMENT, Level.ERROR,
                user4);


        logRepositoryV1.saveAll(Arrays.asList(logV1, logV2, logV3 ,logV4,logV5 ,logV6,logV7 ,logV8,logV9 ,logV10,logV11 ,logV12 ));



    }
}
