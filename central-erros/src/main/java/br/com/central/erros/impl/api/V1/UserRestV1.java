package br.com.central.erros.impl.api.V1;


import br.com.central.erros.impl.api.V1.contracts.UserRestEndpointV1;
import br.com.central.erros.impl.business.dto.EmailDTO;
import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.converter.UserConverter;
import br.com.central.erros.impl.business.service.V1.AuthService;
import br.com.central.erros.impl.business.service.V1.UserServiceImplV1;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Objects;

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
            @ApiImplicitParam(
                    name = "Authorization",
                    required = true,
                    dataType = "string",
                    paramType = "header",
                    value = "Token de autenticação.")})
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
            @ApiImplicitParam(name = "Authorization", required = true,
                    dataType = "string", paramType = "header", value = "Token de autenticação.")
    })
    public ResponseEntity<Void> adicionaUser(@RequestBody UserDTOV1 objDto) {
        objDto = userServiceV1.salvarNovoUSuario(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(objDto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    //@PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userServiceV1.delete(id);
        return ResponseEntity.noContent().build();
    }


    @Override
//    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(path = "/{id}",
            produces = "application/vnd.central.erros.user-v1+json"
    )
    @ApiOperation(value = "Retorna um usuário cadastrado", response = UserDTOV1.class)
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Authorization",
                    required = true,
                    dataType = "string",
                    paramType = "header",
                    value = "Token de autenticação.")})
    public ResponseEntity<UserDTOV1> buscaUser(@Valid @PathVariable Integer id) {
        ResponseEntity<UserDTOV1> response = ResponseEntity.ok(userServiceV1.findById(id));
        if (Objects.isNull(response.getBody())) {
            response = ResponseEntity.noContent().build();
        }
        return response;
    }


    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> atualizaUser(@RequestBody UserDTOV1 userDTOV1, @PathVariable Integer id) {
        //UserV1 obj = userServiceV1.fromDTO(userDTOV1);
        userDTOV1.setId(id);
        userDTOV1 = userServiceV1.update(userDTOV1);
        return ResponseEntity.noContent().build();
    }


    @Override
    public ResponseEntity<Void> editarSenhaUser(String email, String novaSenha) {
        return null;
    }

    @Autowired
    private AuthService service;

    @PostMapping(value = "/recovery")
    @ApiOperation(value = "Recuperar senha.", response = UserDTOV1.class)
    public ResponseEntity<Void> recuperaSenhaUser(@Valid @RequestBody EmailDTO emailDTO) {

        service.sendNewPassword(emailDTO.getEmail());
        return ResponseEntity.noContent().build();
    }


}
