package br.com.central.erros.impl.business.service.V1.contracts;

import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.entity.V1.UserV1;

import java.util.List;

public interface UserServiceV1 {

    List<UserDTOV1> findAll();

    UserDTOV1 findByEmail(String email);

    UserDTOV1 save(UserDTOV1 userDTOV1);

    UserDTOV1 findById(Integer id);

    UserV1 update(UserDTOV1 input, Integer id);

    boolean existsByEmail(String email);

}
