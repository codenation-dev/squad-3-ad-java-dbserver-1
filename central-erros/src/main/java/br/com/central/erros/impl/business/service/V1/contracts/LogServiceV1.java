package br.com.central.erros.impl.business.service.V1.contracts;

import java.util.List;
import java.util.Optional;

import br.com.central.erros.impl.business.dto.LogDTOV1;
import br.com.central.erros.impl.business.entity.V1.LogV1;
import br.com.central.erros.impl.business.entity.enums.Ambiente;
import br.com.central.erros.impl.business.entity.enums.BuscaPor;

public interface LogServiceV1 {
    List<LogDTOV1> buscarLogsPorUsuario(Integer idUsuario);

    Optional<LogDTOV1> encontrarLogPeloId(Integer id);

    List<LogDTOV1> buscarTodosLogs();


    List<LogDTOV1> buscarTodosLogsDoUsuario(Ambiente ambiente, Optional<String> ordenarPor, String buscarPor, String descricaoBusca);

    LogDTOV1 salvarNovoLog(LogDTOV1 logDTOV1);
}
