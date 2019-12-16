package br.com.central.erros.impl;

import br.com.central.erros.impl.business.entity.V1.LogV1;
import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.enums.Environment;
import br.com.central.erros.impl.business.entity.enums.Level;
import br.com.central.erros.impl.business.entity.enums.Profile;
import br.com.central.erros.impl.business.entity.enums.UserType;
import br.com.central.erros.impl.business.repository.V1.LogRepositoryV1;
import br.com.central.erros.impl.business.repository.V1.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;

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
       UserV1 user1 = new UserV1(null, "teste um", "teste1@gmail.com", "36378912377", UserType.PESSOAFISICA, pe.encode(
                "123"));
        UserV1 user2 = new UserV1(null, "teste dois", "teste2@gmail.com", "36378912377", UserType.PESSOAFISICA,
                pe.encode("123"));
        UserV1 user3 = new UserV1(null, "teste tres", "teste3@gmail.com", "36378912377", UserType.PESSOAFISICA, pe.encode(
                "123"));
        UserV1 user4 = new UserV1(null, "Kaio Ferreira ", "grohlbr@gmail.com", "31628382740", UserType.PESSOAFISICA,
                pe.encode("123"));
        UserV1 user5 = new UserV1(null, "Samuel Simão", "mucapapipa.br@gmail.com", "31628382740", UserType.PESSOAFISICA,
                pe.encode("123"));

        user4.addPerfil(Profile.ADMIN);
        user5.addPerfil(Profile.ADMIN);

        userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));

        LogV1 logV1 = new LogV1(null, "12313212",16l, null, "teste", "teste ", Environment.PRODUCTION, Level.ERROR, user1, true);
        LogV1 logV2 = new LogV1(null, "12313212",16l, null, "teste", "teste ", Environment.HOMOLOGATION, Level.DEBUG, user1,true);
        LogV1 logV3 = new LogV1(null, "12313212",16l, null, "teste", "teste ", Environment.DEVELOPMENT, Level.WARNING, user3, false);
        LogV1 logV4 = new LogV1(null, "12313212",20l, LocalDate.now(), "teste", "teste ", Environment.PRODUCTION, Level.ERROR,
                user4, false);
        LogV1 logV5 = new LogV1(null, "11111111",16l, null, "teste", "teste ", Environment.PRODUCTION, Level.ERROR, user1, true);
        LogV1 logV6 = new LogV1(null, "22222222",16l, null, "teste", "teste ", Environment.HOMOLOGATION, Level.DEBUG, user1,true);
        LogV1 logV7 = new LogV1(null, "12312122",16l, null, "teste", "teste ", Environment.DEVELOPMENT, Level.WARNING, user3, false);
        LogV1 logV8 = new LogV1(null, "33333333",20l, LocalDate.now(), "teste", "teste ", Environment.PRODUCTION, Level.ERROR,
                user4, false);
        LogV1 logV9 = new LogV1(null, "44444444",26l, null, "teste", "teste ", Environment.PRODUCTION, Level.ERROR, user1, true);
        LogV1 logV10 = new LogV1(null, "55555555",30l, null, "teste", "teste ", Environment.PRODUCTION, Level.DEBUG, user1,true);
        LogV1 logV11 = new LogV1(null, "66666666",45l, null, "teste", "teste ", Environment.PRODUCTION, Level.WARNING, user3, false);
        LogV1 logV12 = new LogV1(null, "777777777",1000l, LocalDate.now(), "teste", "teste ", Environment.PRODUCTION, Level.ERROR,
                user4, false);

        logRepositoryV1.saveAll(Arrays.asList(logV1, logV2, logV3 ,logV4, logV5, logV6, logV7, logV8, logV9, logV10, logV11, logV12 ));
    }
}
