package br.com.central.erros.impl.business.service.V1.contracts;

import java.util.List;
import java.util.Optional;

import br.com.central.erros.impl.business.dto.LogDTOV1;

public interface LogServiceV1 {
    List<LogDTOV1> buscarPorUsuario(Integer idUsuario);
    LogDTOV1 buscarPorId(Integer id);
    List<LogDTOV1> buscarTodos();
    LogDTOV1 salvar(LogDTOV1 logDTOV1);
}
