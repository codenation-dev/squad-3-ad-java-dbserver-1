package br.com.central.erros.impl.api.V1.contracts;


import br.com.central.erros.impl.business.dto.UserDTOV1;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserRestEndpointV1 {
    ResponseEntity<List<UserDTOV1>> findAll();
    ResponseEntity<Void> save(UserDTOV1 userDTOV1);
    ResponseEntity<UserDTOV1> findById(Integer id);
    ResponseEntity<UserDTOV1> update(Optional<String> newName, Optional<String> newEmail, Integer id);
}
