package br.com.central.erros.impl.api.V1;

import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.entity.enums.UserType;
import br.com.central.erros.impl.business.service.V1.UserServiceImplV1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.reactive.server.JsonPathAssertions;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserRestV1Test {

    @InjectMocks
    UserRestV1 userRestV1;

    @Mock
    UserServiceImplV1 userServiceImplV1;


    @Test
    public void testAddUser() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        UserDTOV1 userDTOV1 = new UserDTOV1(1, "Mustafa", "mustava@bol.com.br","82973378044", UserType.PESSOAFISICA, "123" );


        when(userServiceImplV1.save(any(UserDTOV1.class))).thenReturn(userDTOV1);

        ResponseEntity<Void> responseEntity = userRestV1.save(userDTOV1);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");

    }


}
