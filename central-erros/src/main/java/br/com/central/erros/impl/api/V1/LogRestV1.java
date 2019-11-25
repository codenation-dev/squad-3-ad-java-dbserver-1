package br.com.central.erros.impl.api.V1;


import java.util.List;
import java.util.Objects;

import br.com.central.erros.impl.api.V1.contracts.LogRestEndpointV1;
import br.com.central.erros.impl.business.dto.LogDTOV1;
import br.com.central.erros.impl.business.service.V1.LogServiceImplV1;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/v1/logs"})
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

        ResponseEntity<List<LogDTOV1>> logOK = ResponseEntity.ok(logServiceImplV1.buscarTodosLogs());

        if (Objects.isNull(logOK.getBody())) {
            logOK = ResponseEntity.noContent().build();
        }
        return logOK;
    }

    @Override
    public ResponseEntity<Void> adicionaLog(LogDTOV1 logDTOV1) {
        return null;
    }

    @Override
    public ResponseEntity<LogDTOV1> buscaLog(Integer id) {
        return null;
    }



}
