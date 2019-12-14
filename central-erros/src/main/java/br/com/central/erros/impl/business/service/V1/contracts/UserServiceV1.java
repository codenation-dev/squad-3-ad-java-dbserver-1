package br.com.central.erros.impl.business.service.V1.contracts;

import br.com.central.erros.impl.business.dto.UserDTOV1;

import java.util.List;
import java.util.Optional;

public interface UserServiceV1 {

    List<UserDTOV1> buscarTodos();

    UserDTOV1 findByEmail(String email);

    UserDTOV1 salvar(UserDTOV1 userDTOV1);

    UserDTOV1 findById(Integer id);

    boolean existeUsuarioComEmail(String email);

}
