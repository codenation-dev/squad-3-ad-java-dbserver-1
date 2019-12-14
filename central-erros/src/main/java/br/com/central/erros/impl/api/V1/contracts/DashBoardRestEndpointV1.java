package br.com.central.erros.impl.api.V1.contracts;


import br.com.central.erros.impl.business.dto.LogDTOV1;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DashBoardRestEndpointV1 {

    ResponseEntity<List<LogDTOV1>> buscarLogsPorUsuario(Integer id);


}
