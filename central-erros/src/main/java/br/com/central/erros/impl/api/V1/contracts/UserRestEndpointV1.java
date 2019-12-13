package br.com.central.erros.impl.api.V1.contracts;


import java.util.List;

import br.com.central.erros.impl.business.dto.UserDTOV1;
import org.springframework.http.ResponseEntity;

public interface UserRestEndpointV1 {
    ResponseEntity<List<UserDTOV1>> buscaUsersList();
    ResponseEntity<Void> adicionaUser(UserDTOV1 userDTOV1);
    ResponseEntity<UserDTOV1> buscaUser(Integer id);
    ResponseEntity<UserDTOV1> atualizaUser(UserDTOV1 userDTOV1);
}
