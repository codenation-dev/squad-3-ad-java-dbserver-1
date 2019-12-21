package br.com.central.erros.impl.api.V1;


import br.com.central.erros.impl.api.V1.contracts.UserRestEndpointV1;
import br.com.central.erros.impl.business.dto.UserDTOV1;
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
import java.util.Optional;

@RestController
@RequestMapping({"/v1/users"})
@Api(value = "Users",  tags = { "Users" })
public class UserRestV1 implements UserRestEndpointV1 {


    private final UserServiceImplV1 userServiceV1;

    @Autowired
    public UserRestV1(UserServiceImplV1 userServiceV1) {

        this.userServiceV1 = userServiceV1;
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(path = "/findAll",
            produces = "application/vnd.central.erros.user-v1+json"
    )
    @ApiOperation(value = "Retorna todos usuários cadastrados", response = UserDTOV1.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header", value = "Token de autenticação.")
    })
    public ResponseEntity<List<UserDTOV1>> findAll() {
        ResponseEntity<List<UserDTOV1>> response = ResponseEntity.ok(userServiceV1.findAll());
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

    public ResponseEntity<Void> save(@RequestBody @Valid UserDTOV1 objDto) {
        objDto = userServiceV1.save(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(objDto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @Override
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
                    value = "Token de autenticação.")
    })
    public ResponseEntity<UserDTOV1> findById(@Valid @PathVariable Integer id) {
        return ResponseEntity.ok(userServiceV1.findById(id));
    }

    @PatchMapping(path = "/{id}")
    @ApiOperation(value = "Edita um usuário", response = UserDTOV1.class)
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Authorization",
                    required = true,
                    dataType = "string",
                    paramType = "header",
                    value = "Token de autenticação.")
    })
    public ResponseEntity<UserDTOV1> update(@RequestParam(required = false) Optional<String> newName,
                                            @RequestParam(required = false) Optional<String> newEmail,
                                            @PathVariable Integer id) {
        UserDTOV1 old = userServiceV1.findById(id);
        newName.ifPresent(old::setName);
        newEmail.ifPresent(old::setEmail);
        userServiceV1.update(old, id);
        return ResponseEntity.noContent().build();
    }
}
