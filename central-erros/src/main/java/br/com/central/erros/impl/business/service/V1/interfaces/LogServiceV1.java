package br.com.central.erros.impl.business.service.V1.interfaces;

import java.util.List;

import br.com.central.erros.impl.business.dto.LogDTOV1;

public interface LogServiceV1 {

    List<LogDTOV1> buscarLogsPorUsuario(Integer idUsuario);

    LogDTOV1 encontrarLogPeloId(Integer id);

    List<LogDTOV1> buscarTodosLogs();
}
