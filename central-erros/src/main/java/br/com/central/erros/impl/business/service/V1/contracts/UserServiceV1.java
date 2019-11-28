package br.com.central.erros.impl.business.service.V1.contracts;

import br.com.central.erros.impl.business.dto.UserDTOV1;

import java.util.List;
import java.util.Optional;

public interface UserServiceV1 {

    List<UserDTOV1> buscaUsersList();

    UserDTOV1 salvarNovoUSuario(UserDTOV1 userDTOV1);

    UserDTOV1 findById(Integer id);

    Optional<UserDTOV1> buscaUsersById(Integer userId);



}
