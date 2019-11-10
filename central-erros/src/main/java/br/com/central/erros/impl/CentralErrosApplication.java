package br.com.central.erros.impl;

import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.service.V1.UserServiceV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CentralErrosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CentralErrosApplication.class, args);
	}


	@Autowired
    private UserServiceV1 userServiceV1;

    @Override
    public void run(String... args) throws Exception {


        UserDTOV1 userDTOV1 = new UserDTOV1.Builder().nome("kaio").email("teste").senha("123").token(new Integer(0)).build();
        userServiceV1.salvarNovoUSuario(userDTOV1);


    }
}
