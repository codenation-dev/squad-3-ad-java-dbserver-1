package br.com.central.erros.impl.api.V1;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import br.com.central.erros.impl.api.V1.contracts.UserRestEndpointV1;
import br.com.central.erros.impl.business.dto.EmailDTO;
import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.service.V1.AuthService;
import br.com.central.erros.impl.business.service.V1.UserServiceImplV1;
import br.com.central.erros.impl.business.service.V1.contracts.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/v1/users"})
@Api(value = "Users",  tags = { "Users" })
public class UserRestV1 implements UserRestEndpointV1 {


    private UserServiceImplV1 userServiceV1;

    @Autowired
    public UserRestV1(UserServiceImplV1 userServiceV1) {

        this.userServiceV1 = userServiceV1;
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(path = "/findAll",
            produces = "application/vnd.central.erros.user-v1+json"
    )
    @ApiOperation(value = "Retorna todos usuários cadastrados.", response = UserDTOV1.class)
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
    @ApiOperation(value = "Cadastra um novo usuário ")
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
    @GetMapping(path = "{id}")
    @ApiOperation(value = "Retorna o usuário informado", response = UserDTOV1.class)
    public ResponseEntity<Optional<UserDTOV1>> buscaUsersById(@PathVariable("id") Integer id) {

        ResponseEntity<Optional<UserDTOV1>> response = ResponseEntity.ok(userServiceV1.buscaUsersById(id));
        if (Objects.isNull((response.getBody()))) {
            response = ResponseEntity.noContent().build();
        }

        return response;
    }


    @Override
    public ResponseEntity<Void> atualizaUser(Integer idUser, UserDTOV1 userDTOV1) {
        return null;
    }


    @Override
    public ResponseEntity<Void> editarSenhaUser(String email, String novaSenha) {
        return null;
    }

    @Autowired
    private AuthService service;

    @PostMapping(value = "/recovery")
    @ApiOperation(value = "Recuperar senha.")
    public ResponseEntity<Void> recuperaSenhaUser(@Valid @RequestBody EmailDTO objDto) {
        service.sendNewPassword(objDto.getEmail());
        return ResponseEntity.noContent().build();
    }


}
