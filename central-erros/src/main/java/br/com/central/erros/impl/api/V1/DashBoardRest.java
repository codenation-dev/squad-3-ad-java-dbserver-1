package br.com.central.erros.impl.api.V1;


import java.util.List;
import java.util.Objects;

import br.com.central.erros.impl.api.V1.contracts.DashBoardRestEndpointV1;
import br.com.central.erros.impl.business.dto.LogDTOV1;
import br.com.central.erros.impl.business.service.V1.LogServiceImplV1;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/v1/dashboards"})
@Api(value = "Dashboard",  tags = { "Dashboard" })
public class DashBoardRest implements DashBoardRestEndpointV1 {



    private LogServiceImplV1 logServiceImplV1;

    public DashBoardRest(LogServiceImplV1 logServiceImplV1) {
        this.logServiceImplV1 = logServiceImplV1;
    }


    @GetMapping(path = "/{id}",
            produces = "application/vnd.central.erros.dashboards-v1+json"
    )
    @ApiOperation(value = "Retorna os logs por usuario", response = LogDTOV1.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header", value = "Token de autenticação.")
    })
    public ResponseEntity<List<LogDTOV1>> buscarLogsPorUsuario(@PathVariable("id") Integer id) {


        ResponseEntity<List<LogDTOV1>> response = ResponseEntity.ok(logServiceImplV1.buscarLogsPorUsuario(id));

        if (Objects.isNull(response.getBody())) {
            response = ResponseEntity.noContent().build();
        }
        return response;
    }


}
