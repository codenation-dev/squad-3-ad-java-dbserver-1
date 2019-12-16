package br.com.central.erros.impl.business.service.V1.contracts;

import br.com.central.erros.impl.business.dto.LogDTOV1;
import br.com.central.erros.impl.business.entity.enums.Environment;
import br.com.central.erros.impl.business.entity.enums.FindBy;
import br.com.central.erros.impl.business.entity.enums.OrderBy;

import java.util.List;
import java.util.Optional;

public interface LogServiceV1 {

    LogDTOV1 findById(Integer id);
    List<LogDTOV1> findAllByUser(Environment environment, Optional<OrderBy> orderBy, Optional<FindBy> findBy, Optional<String> stringFilter);
    LogDTOV1 save(LogDTOV1 logDTOV1);
    LogDTOV1 update(Integer id, LogDTOV1 logInput);
}
