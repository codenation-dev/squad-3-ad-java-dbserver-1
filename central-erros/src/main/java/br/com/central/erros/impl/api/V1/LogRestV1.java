package br.com.central.erros.impl.api.V1;


import br.com.central.erros.impl.business.dto.LogDTOV1;

import br.com.central.erros.impl.business.service.V1.LogServiceImplV1;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping({"/v1/log"})
public class LogRestV1 implements LogRestEndpointV1 {

    private LogServiceImplV1 logServiceImplV1;

    @Autowired
    public LogRestV1(LogServiceImplV1 logServiceImplV1) {

        this.logServiceImplV1 = logServiceImplV1;
    }


    @Override
    @GetMapping(path = "{logId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retorna o log informado", response = LogDTOV1.class)
    public Optional<LogDTOV1> buscaLogById(@PathVariable("logId") Long logId) {
        return logServiceImplV1.buscaLogById(logId);
    }
}
