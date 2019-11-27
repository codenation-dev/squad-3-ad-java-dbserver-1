package br.com.central.erros.impl.api.V1;


import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import br.com.central.erros.impl.api.V1.contracts.UserRestEndpointV1;
import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.service.V1.UserServiceImplV1;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/v1/users"})
public class UserRestV1 implements UserRestEndpointV1 {


    private UserServiceImplV1 userServiceV1;

    @Autowired
    public UserRestV1(UserServiceImplV1 userServiceV1) {
        this.userServiceV1 = userServiceV1;
    }

    @Override
//    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(path = "/findAll",
            produces = "application/vnd.central.erros.user-v1+json"
    )
    @ApiOperation(value = "Retorna todos usuários cadastrados", response = UserDTOV1.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header", value = "Token de autenticação.")
    })
    public ResponseEntity<List<UserDTOV1>> buscaUsersList() {

        ResponseEntity<List<UserDTOV1>> response = ResponseEntity.ok(userServiceV1.buscaUsersList());
        if (Objects.isNull(response.getBody())) {
            response = ResponseEntity.noContent().build();
        }
        return response;
    }

    @Override
    @PostMapping("/")
    @ApiOperation(value = "Salva um novo usuário ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header", value = "Token de autenticação.")
    })
    public ResponseEntity<Void> adicionaUser(UserDTOV1 userRequest) {

        userServiceV1.salvarNovoUSuario(userRequest);

        return ResponseEntity.ok().build();
    }


    @Override
//    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(path = "/{id}",
            produces = "application/vnd.central.erros.user-v1+json"
    )
    @ApiOperation(value = "Retorna um usuário cadastrado", response = UserDTOV1.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header", value = "Token de autenticação.")
    })
    public ResponseEntity<UserDTOV1> buscaUser(@Valid @PathVariable Integer id) {

        ResponseEntity<UserDTOV1> response = ResponseEntity.ok(userServiceV1.findById(id));
        if (Objects.isNull(response.getBody())) {
            response = ResponseEntity.noContent().build();
        }
        return response;
    }

    @Override
    @PutMapping("/{codigoUser}")
    @ApiOperation("Atualiza cadastro de um user")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header", value = "Token de autenticação.")
    })
    public ResponseEntity<Void> atualizaUser(@PathVariable Integer codigoUser,
            @Valid @RequestBody UserDTOV1 userDTOV1) {

        userServiceV1.atualizaUser(codigoUser,userDTOV1);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> recuperaSenhaUser(String email) {
        return null;
    }

    @Override
    public ResponseEntity<Void> editarSenhaUser(String email, String novaSenha) {
        return null;
    }

}
