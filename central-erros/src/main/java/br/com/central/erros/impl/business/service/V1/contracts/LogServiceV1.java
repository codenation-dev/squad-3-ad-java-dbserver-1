package br.com.central.erros.impl.business.service.V1.contracts;

import br.com.central.erros.impl.business.dto.LogDTOV1;

import java.util.List;

public interface LogServiceV1 {
    List<LogDTOV1> findByUser(Integer userId);
    LogDTOV1 findById(Integer id);
    List<LogDTOV1> findAll();
    LogDTOV1 save(LogDTOV1 logDTOV1);
}
