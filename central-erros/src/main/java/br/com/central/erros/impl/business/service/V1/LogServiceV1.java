package br.com.central.erros.impl.business.service.V1;

import br.com.central.erros.impl.business.dto.LogDTOV1;
import br.com.central.erros.impl.business.dto.UserDTOV1;

import java.util.List;
import java.util.Optional;

public interface LogServiceV1 {

    Optional<LogDTOV1> buscaLogById(Long logId);
}
