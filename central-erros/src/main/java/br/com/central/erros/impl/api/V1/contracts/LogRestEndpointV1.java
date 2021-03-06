package br.com.central.erros.impl.api.V1.contracts;


import br.com.central.erros.impl.business.dto.LogDTOV1;
import br.com.central.erros.impl.business.entity.enums.Environment;
import br.com.central.erros.impl.business.entity.enums.FindBy;
import br.com.central.erros.impl.business.entity.enums.OrderBy;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface LogRestEndpointV1 {

    ResponseEntity<Void> save(LogDTOV1 logDTOV1);
    ResponseEntity<LogDTOV1> findById(Integer id);
    ResponseEntity<List<LogDTOV1>> findAll(Integer userId,
                                           Environment environment,
                                           Optional<OrderBy> orderBy,
                                           Optional<FindBy> findBy,
                                           Optional<String> stringFilter);
    ResponseEntity<LogDTOV1> archiveLogById(Integer id);

    }
