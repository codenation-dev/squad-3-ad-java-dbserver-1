package br.com.central.erros.impl.api.V1.contracts;


import br.com.central.erros.impl.business.dto.LogDTOV1;
import br.com.central.erros.impl.business.entity.enums.Environment;
import br.com.central.erros.impl.business.entity.enums.FindBy;
import br.com.central.erros.impl.business.entity.enums.OrderBy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface LogRestEndpointV1 {

    ResponseEntity<Void> save(LogDTOV1 logDTOV1);
    ResponseEntity<LogDTOV1> findById(Integer id);
    ResponseEntity<List<LogDTOV1>> findAllByParams(@RequestParam(required = true, defaultValue = "PRODUCTION") Environment environment,
                                                          @RequestParam(required = false) Optional<OrderBy> orderBy,
                                                          @RequestParam(required = false) Optional<FindBy> findBy,
                                                          @RequestParam(required = false)  Optional<String> stringFilter);
    ResponseEntity<LogDTOV1> updateLogById(@PathVariable("id") Integer id);




    }
