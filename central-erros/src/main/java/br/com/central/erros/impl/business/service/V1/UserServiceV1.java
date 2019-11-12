package br.com.central.erros.impl.business.service.V1;

import java.util.List;

import br.com.central.erros.impl.business.dto.UserDTOV1;

public interface UserServiceV1 {

    List<UserDTOV1> buscaUsersList();

}
