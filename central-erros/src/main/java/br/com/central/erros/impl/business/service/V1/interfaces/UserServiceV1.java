package br.com.central.erros.impl.business.service.V1.interfaces;

import java.util.List;

import br.com.central.erros.impl.business.dto.UserDTOV1;

public interface UserServiceV1 {

    List<UserDTOV1> buscaUsersList();

    UserDTOV1 salvarNovoUSuario(UserDTOV1 userDTOV1);

    UserDTOV1 findById(Integer id);

}
