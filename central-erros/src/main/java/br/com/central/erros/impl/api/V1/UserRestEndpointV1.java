package br.com.central.erros.impl.api.V1;

import java.util.List;
import java.util.Optional;

import br.com.central.erros.impl.business.dto.UserDTOV1;
import org.springframework.http.ResponseEntity;


public interface UserRestEndpointV1 {

    ResponseEntity<List<UserDTOV1>> buscaUsersList();

    ResponseEntity<Optional<UserDTOV1>> buscaUsersById(Long userId);
}
