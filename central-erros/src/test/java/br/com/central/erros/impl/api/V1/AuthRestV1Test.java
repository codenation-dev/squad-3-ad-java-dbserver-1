package br.com.central.erros.impl.api.V1;

import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.enums.Profile;
import br.com.central.erros.impl.business.entity.enums.UserType;
import br.com.central.erros.impl.business.repository.V1.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthRestV1Test {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository repository;

    @Before
    public void setUpData() {
        UserV1 user1 = new UserV1(1, "teste um", "teste1@gmail.com", "89567601011",
                UserType.PESSOAFISICA, "abobrinha");
        user1.addPerfil(Profile.ADMIN);

        repository.save(user1);
    }

    @Test
    public void sendsVerificationCodeWhenEmailExists() throws Exception {
        final String userEmail = "teste1@gmail.com";
        this.mockMvc.perform(get("/v1/recovery/{email}", userEmail)).
                andExpect(status().isNoContent());
    }

    @Test
    public void returnsNotFoundForInvalidEmail() throws Exception {
        final String userEmail = "teste7@gmail.com";
        this.mockMvc.perform(get("/v1/recovery/{email}", userEmail)).
                andExpect(status().isNotFound());
    }
}