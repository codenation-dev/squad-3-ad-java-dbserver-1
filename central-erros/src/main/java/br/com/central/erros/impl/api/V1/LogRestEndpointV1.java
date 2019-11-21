package br.com.central.erros.impl.api.V1;

import br.com.central.erros.impl.business.dto.LogDTOV1;

import java.util.Optional;

public interface LogRestEndpointV1 {

    Optional<LogDTOV1> buscaLogById(Long logId);
}
