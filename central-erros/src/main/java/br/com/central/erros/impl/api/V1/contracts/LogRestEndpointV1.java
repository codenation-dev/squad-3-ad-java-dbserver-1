package br.com.central.erros.impl.api.V1.contracts;


import java.util.List;
import java.util.Optional;

import br.com.central.erros.impl.business.dto.LogDTOV1;
import br.com.central.erros.impl.business.dto.UserDTOV1;
import org.springframework.http.ResponseEntity;

public interface LogRestEndpointV1 {
    ResponseEntity<List<LogDTOV1>> buscaLogsList();
    ResponseEntity<Void> adicionaLog(LogDTOV1 logDTOV1);
    ResponseEntity<LogDTOV1> buscaLog(Integer id);
}
