package br.com.central.erros.impl;

import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.enums.Perfil;
import br.com.central.erros.impl.business.repository.V5.UserRepositoryV1;
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
    private UserRepositoryV1 userRepositoryV1;

	@Autowired
    private UserServiceV1 userServiceV1;

    @Override
    public void run(String... args) throws Exception {


        //criando perfil admin

        UserV1 perfilAdmin = new UserV1(null, "admin", "admin@admin.com", "3333", null);
        perfilAdmin.addPerfil(Perfil.ADMIN);

        userRepositoryV1.save(perfilAdmin);


        //Perfil cliente
        UserV1 perfilCliente = new UserV1(null, "cliente", "cliente@admin.com", "1233", null);
        userRepositoryV1.save(perfilCliente);



//        UserDTOV1 userDTOV1 = new UserDTOV1.Builder().nome("kaio").email("teste").senha("123").token(new Integer(0)).build();
//        userServiceV1.salvarNovoUSuario(userDTOV1);


    }
}
