package br.com.central.erros.impl.api.V1;


import java.util.List;
import java.util.Objects;

import br.com.central.erros.impl.business.dto.LogDTOV1;
import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.service.V1.LogServiceImplV1;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/v1/logs"})
public class LogRestV1 {


    private LogServiceImplV1 logServiceImplV1;

    @Autowired
    public LogRestV1(LogServiceImplV1 logServiceImplV1) {
        this.logServiceImplV1 = logServiceImplV1;
    }

    @GetMapping(path = "/findAll",
//            headers = "Accept=application/empresa.funcionarios-v5+json",
            produces = "application/vnd.central.erros.user-v1+json"
    )
    @ApiOperation(value = "Retorna todos usuários cadastrados", response = UserDTOV1.class)
    public ResponseEntity<List<LogDTOV1>> buscaUsersList() {


        ResponseEntity<List<LogDTOV1>> logOK = ResponseEntity.ok(logServiceImplV1.buscarTodosLogs());

        if (Objects.isNull(logOK.getBody())) {
            logOK = ResponseEntity.noContent().build();
        }
        return logOK;
    }


//    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(path = "/{id}",
//            headers = "Accept=application/empresa.funcionarios-v5+json",
            produces = "application/vnd.central.erros.user-v1+json"
    )
    @ApiOperation(value = "Retorna um usuário cadastrado", response = UserDTOV1.class)
    public ResponseEntity<List<LogDTOV1>> buscarLogsPorUsuario(@PathVariable Integer id) {


        ResponseEntity<List<LogDTOV1>> response = ResponseEntity.ok(logServiceImplV1.buscarLogsPorUsuario(id));

        if (Objects.isNull(response.getBody())) {
            response = ResponseEntity.noContent().build();
        }
        return response;
    }


}
