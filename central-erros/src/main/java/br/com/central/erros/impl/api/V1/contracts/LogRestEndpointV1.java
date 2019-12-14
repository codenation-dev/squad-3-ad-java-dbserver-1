package br.com.central.erros.impl.api.V1.contracts;


import java.util.List;
import java.util.Optional;

import br.com.central.erros.impl.business.dto.LogDTOV1;
import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.entity.enums.Ambiente;
import br.com.central.erros.impl.business.entity.enums.BuscaPor;
import br.com.central.erros.impl.business.entity.enums.OrdenarPor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface LogRestEndpointV1 {

    ResponseEntity<List<LogDTOV1>> buscaLogsListComParametros(Ambiente ambiente,
                                                             Optional<OrdenarPor> ordenarPor,
                                                             Optional<BuscaPor> buscarPor,
                                                             Optional<String> descricaoBusca);

    ResponseEntity<Void> adicionaLog(LogDTOV1 logDTOV1);

    ResponseEntity<Optional<LogDTOV1>> buscaLog(Integer id);




}
