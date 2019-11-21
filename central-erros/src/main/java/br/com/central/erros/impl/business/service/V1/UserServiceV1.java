package br.com.central.erros.impl.business.service.V1;

import br.com.central.erros.impl.business.dto.UserDTOV1;

import java.util.List;
import java.util.Optional;

public interface UserServiceV1 {

    List<UserDTOV1> buscaUsersList();

    Optional<UserDTOV1> buscaUsersById(Long userId);

}
