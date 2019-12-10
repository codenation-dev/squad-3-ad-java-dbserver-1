package br.com.central.erros.impl.api.V1;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

import br.com.central.erros.impl.api.V1.contracts.LogRestEndpointV1;
import br.com.central.erros.impl.business.dto.LogDTOV1;
import br.com.central.erros.impl.business.entity.enums.Ambiente;
import br.com.central.erros.impl.business.entity.enums.BuscaPor;
import br.com.central.erros.impl.business.service.V1.LogServiceImplV1;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/buscaTodos", produces = "application/vnd.central.erros.user-v1+json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header", value = "Token de autenticação.")
    })
    @ApiOperation(value = "Retorna todos os logs com o parâmetros selecionados. ", response = LogDTOV1.class)
    public ResponseEntity<List<LogDTOV1>> buscaLogsListComParametros(@RequestParam(required = true, defaultValue = "PRODUCTION") Ambiente ambiente,
                                                                     @RequestParam(required = false) Optional<String> ordenarPor,
                                                                     @RequestParam(required = false) BuscaPor buscarPor,
                                                                     @RequestParam(required = false)  String descricaoBusca) {

        ResponseEntity<List<LogDTOV1>> logOK = ResponseEntity.ok(logServiceImplV1.buscarTodosLogsDoUsuario(ambiente, ordenarPor, buscarPor, descricaoBusca));

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
    public ResponseEntity<Void> adicionaLog(LogDTOV1 logRequest) {

        logServiceImplV1.salvarNovoLog(logRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping(path = "{id}")
    @ApiOperation(value = "Retorna o log informado", response = LogDTOV1.class)
    public ResponseEntity<Optional<LogDTOV1>> buscaLog(@PathVariable("id") Integer id) {

        ResponseEntity<Optional<LogDTOV1>> response = ResponseEntity.ok(logServiceImplV1.encontrarLogPeloId(id));
        if (Objects.isNull((response.getBody()))) {
            response = ResponseEntity.noContent().build();
        }

        return response;


    }




}
