package br.com.central.erros.impl.api.V1.contracts;


import java.util.List;

import br.com.central.erros.impl.business.dto.LogDTOV1;
import org.springframework.http.ResponseEntity;

public interface DashBoardRestEndpointV1 {

    ResponseEntity<List<LogDTOV1>> buscarLogsPorUsuario(Integer id);


}
