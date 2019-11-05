package br.com.central.erros.impl.api.V1;

import java.util.List;

import br.com.central.erros.impl.business.dto.UserDTOV1;
import org.springframework.http.ResponseEntity;


public interface UserRestEndpointV1 {

    ResponseEntity<List<UserDTOV1>> buscaUsersList();


}
