package br.com.central.erros.impl.api.V1.contracts;


import br.com.central.erros.impl.business.dto.LogDTOV1;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LogRestEndpointV1 {
    ResponseEntity<List<LogDTOV1>> findAll();
    ResponseEntity<Void> save(LogDTOV1 logDTOV1);
    ResponseEntity<LogDTOV1> findById(Integer id);
    ResponseEntity<List<LogDTOV1>> findByUser(Integer userId);
}
