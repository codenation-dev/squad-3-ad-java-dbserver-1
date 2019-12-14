package br.com.central.erros.impl.api.V1;


import br.com.central.erros.impl.api.V1.contracts.LogRestEndpointV1;
import br.com.central.erros.impl.business.dto.LogDTOV1;
import br.com.central.erros.impl.business.entity.enums.Environment;
import br.com.central.erros.impl.business.entity.enums.FindBy;
import br.com.central.erros.impl.business.entity.enums.OrderBy;
import br.com.central.erros.impl.business.service.V1.LogServiceImplV1;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping({"/v1/logs"})
@Api(value = "Logs",  tags = { "Logs" })
public class LogRestV1 implements LogRestEndpointV1 {
    private final LogServiceImplV1 logServiceImplV1;

    @Autowired
    public LogRestV1(LogServiceImplV1 logServiceImplV1) {
        this.logServiceImplV1 = logServiceImplV1;
    }

    @GetMapping(path = "/buscaTodos", produces = "application/vnd.central.erros.user-v1+json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header", value = "Token de autenticação.")
    })
    @ApiOperation(value = "Retorna todos os logs com o parâmetros selecionados. ", response = LogDTOV1.class)
    public ResponseEntity<List<LogDTOV1>> findAllByParams(@RequestParam(required = true, defaultValue = "PRODUCTION") Environment environment,
                                                                     @RequestParam(required = false) Optional<OrderBy> orderBy,
                                                                     @RequestParam(required = false) Optional<FindBy> findBy,
                                                                     @RequestParam(required = false)  Optional<String> stringFilter) {

        ResponseEntity<List<LogDTOV1>> logOK = ResponseEntity.ok(logServiceImplV1.findAllByUser(environment, orderBy, findBy, stringFilter));

        if (Objects.isNull(logOK.getBody())) {
            logOK = ResponseEntity.noContent().build();
        }
        return logOK;
    }

    @Override
    @PostMapping("/")
    @ApiOperation(value = "Salva um novo Log ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header", value = "Token de autenticação.")
    })
    public ResponseEntity<Void> save(LogDTOV1 logRequest) {
        logServiceImplV1.save(logRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retorna o log informado", response = LogDTOV1.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header", value = "Token de autenticação.")
    })
    public ResponseEntity<LogDTOV1> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(logServiceImplV1.findById(id));
    }


}
