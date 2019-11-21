package br.com.central.erros.impl.business.service.V1;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.central.erros.impl.business.dto.UserDTOV1;
import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.converter.UserConverter;
import br.com.central.erros.impl.business.repository.V1.UserRepositoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplV1 implements UserServiceV1 {


    private UserRepositoryV1 userRepositoryV1;

    @Autowired
    public UserServiceImplV1(UserRepositoryV1 userRepositoryV1) {
        this.userRepositoryV1 = userRepositoryV1;
    }

    @Override
    public List<UserDTOV1> buscaUsersList() {
        List<UserV1> listaFuncionarios = userRepositoryV1.findAll();

        List<UserDTOV1> listFuncionarioDTOV5 =
                listaFuncionarios.stream().map(UserConverter::toDTOV1).collect(Collectors.toList());

        return listFuncionarioDTOV5;
    }

    @Override
    public Optional<UserDTOV1> buscaUsersById(Long userId) {
        Optional<UserV1> optionalUserV1 = userRepositoryV1.findById(userId);

        Optional<UserDTOV1> optionalUserDTOV1 = optionalUserV1.map(UserConverter::toDTOV1);
        return optionalUserDTOV1;
    }


}
