package br.com.central.erros.impl.api.V1;


import java.util.List;

import br.com.central.erros.impl.api.V1.contracts.LogRestEndpointV1;
import br.com.central.erros.impl.business.dto.LogDTOV1;
import br.com.central.erros.impl.business.service.V1.LogServiceImplV1;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/v1/logs"})
@Api(value = "Logs",  tags = { "Logs" })
public class LogRestV1 implements LogRestEndpointV1 {
    private LogServiceImplV1 logServiceImplV1;

    @Autowired
    public LogRestV1(LogServiceImplV1 logServiceImplV1) {
        this.logServiceImplV1 = logServiceImplV1;
    }

    @GetMapping(path = "/findAll", produces = "application/vnd.central.erros.user-v1+json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header", value = "Token de autenticação.")
    })
    @ApiOperation(value = "Retorna todos os logs. ", response = LogDTOV1.class)
    public ResponseEntity<List<LogDTOV1>> buscaLogsList() {
        return ResponseEntity.ok(logServiceImplV1.buscarTodos());
    }

    @Override
    @PostMapping("/")
    @ApiOperation(value = "Salva um novo Log ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header", value = "Token de autenticação.")
    })
    public ResponseEntity<Void> adicionaLog(LogDTOV1 logRequest) {
        logServiceImplV1.salvar(logRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retorna o log informado", response = LogDTOV1.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header", value = "Token de autenticação.")
    })
    public ResponseEntity<LogDTOV1> buscaLog(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(logServiceImplV1.buscarPorId(id));
    }

    @GetMapping(path = "/{id}", params = {"userId"})
    @ApiOperation(value = "Retorna logs por usuario", response = LogDTOV1.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header", value = "Token de autenticação.")
    })
    public ResponseEntity<List<LogDTOV1>> buscarPorUsuario(final @RequestParam(name="userId") Integer id) {
        return ResponseEntity.ok(logServiceImplV1.buscarPorUsuario(id));
    }
}
