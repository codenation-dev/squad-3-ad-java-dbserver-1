package br.com.central.erros.impl.api.V1;


import java.util.List;
import java.util.Objects;

import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.service.V1.UserServiceV1;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/v1/users"})
public class UserRestV1 implements UserRestEndpointV1 {


    private UserServiceV1 userServiceV1;

    @Autowired
    public UserRestV1(UserServiceV1 userServiceV1) {
        this.userServiceV1 = userServiceV1;
    }


    @Override
    @GetMapping(path = "/findAll",
//            headers = "Accept=application/empresa.funcionarios-v5+json",
            produces = "application/vnd.central.erros.user-v1+json"
    )
    @ApiOperation(value = "Retorna todos usuários cadastrados", response = UserDTOV1.class)
    public ResponseEntity<List<UserDTOV1>> buscaUsersList() {

        ResponseEntity<List<UserDTOV1>> response = ResponseEntity.ok(userServiceV1.buscaUsersList());
        if (Objects.isNull(response.getBody())) {
            response = ResponseEntity.noContent().build();
        }
        return response;
    }



}
